package com.github.intellij.gno.highlighting;

import com.github.intellij.gno.psi.*;
import com.github.intellij.gno.psi.impl.GnoPsiImplUtil;
import com.github.intellij.gno.psi.impl.GnoReferenceBase;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.intellij.gno.highlighting.GnoSyntaxHighlightingColors.*;

public class GnoHighlightingAnnotator implements Annotator {
  private static void highlightRefIfNeeded(@NotNull GnoReferenceExpressionBase o,
                                           @Nullable PsiElement resolve,
                                           @NotNull AnnotationHolder holder) {

    if (resolve instanceof GnoTypeSpec) {
      TextAttributesKey key = GnoPsiImplUtil.builtin(resolve) ? BUILTIN_TYPE_REFERENCE : getColor((GnoTypeSpec)resolve);
      if (o.getParent() instanceof GnoType) {
        GnoType topmostType = PsiTreeUtil.getTopmostParentOfType(o, GnoType.class);
        if (topmostType != null && topmostType.getParent() instanceof GnoReceiver) {
          key = TYPE_REFERENCE;
        }
      }
      setHighlighting(o.getIdentifier(), holder, key);
    }
    else if (resolve instanceof GnoConstDefinition) {
      TextAttributesKey color = GnoPsiImplUtil.builtin(resolve) ? BUILTIN_TYPE_REFERENCE : getColor((GnoConstDefinition)resolve);
      setHighlighting(o.getIdentifier(), holder, color);
    }
    else if (resolve instanceof GnoVarDefinition) {
      TextAttributesKey color = GnoPsiImplUtil.builtin(resolve) ? BUILTIN_TYPE_REFERENCE : getColor((GnoVarDefinition)resolve);
      setHighlighting(o.getIdentifier(), holder, color);
    }
    else if (resolve instanceof GnoFieldDefinition) {
      setHighlighting(o.getIdentifier(), holder, getColor((GnoFieldDefinition)resolve));
    }
    else if (resolve instanceof GnoFunctionOrMethodDeclaration || resolve instanceof GnoMethodSpec) {
      setHighlighting(o.getIdentifier(), holder, getColor((GnoNamedSignatureOwner)resolve));
    }
    else if (resolve instanceof GnoReceiver) {
      setHighlighting(o.getIdentifier(), holder, METHOD_RECEIVER);
    }
    else if (resolve instanceof GnoParamDefinition) {
      setHighlighting(o.getIdentifier(), holder, FUNCTION_PARAMETER);
    }
  }

  private static TextAttributesKey getColor(GnoConstDefinition o) {
    if (isPackageWide(o)) {
      return o.isPublic() ? PACKAGE_EXPORTED_CONSTANT : PACKAGE_LOCAL_CONSTANT;
    }
    return LOCAL_CONSTANT;
  }

  private static TextAttributesKey getColor(GnoFieldDefinition o) {
    return o.isPublic() ? STRUCT_EXPORTED_MEMBER : STRUCT_LOCAL_MEMBER;
  }

  private static TextAttributesKey getColor(GnoVarDefinition o) {
    if (PsiTreeUtil.getParentOfType(o, GnoForStatement.class) != null ||
        PsiTreeUtil.getParentOfType(o, GnoIfStatement.class) != null ||
        PsiTreeUtil.getParentOfType(o, GnoSwitchStatement.class) != null) {
      return SCOPE_VARIABLE;
    }

    if (isPackageWide(o)) {
      return o.isPublic() ? PACKAGE_EXPORTED_VARIABLE : PACKAGE_LOCAL_VARIABLE;
    }

    return LOCAL_VARIABLE;
  }

  private static TextAttributesKey getColor(GnoNamedSignatureOwner o) {
    if (GnoPsiImplUtil.builtin(o)) return BUILTIN_FUNCTION;
    return o.isPublic() ? EXPORTED_FUNCTION : LOCAL_FUNCTION;
  }

  private static TextAttributesKey getColor(@Nullable GnoTypeSpec o) {
    GnoType type = o != null ? o.getGnoType(null) : null;
    if (type != null) {
      type = type instanceof GnoSpecType ? ((GnoSpecType)type).getType() : type;
      boolean isPublic = o.isPublic();
      if (type instanceof GnoInterfaceType) {
        return isPublic ? PACKAGE_EXPORTED_INTERFACE : PACKAGE_LOCAL_INTERFACE;
      }
      else if (type instanceof GnoStructType) {
        return isPublic ? PACKAGE_EXPORTED_STRUCT : PACKAGE_LOCAL_STRUCT;
      }
    }
    return TYPE_SPECIFICATION;
  }

  private static void setHighlighting(@NotNull PsiElement element, @NotNull AnnotationHolder holder, @NotNull TextAttributesKey key) {
    holder.createInfoAnnotation(element, null).setEnforcedTextAttributes(TextAttributes.ERASE_MARKER);
    String description = ApplicationManager.getApplication().isUnitTestMode() ? key.getExternalName() : null;
    holder.createInfoAnnotation(element, description).setTextAttributes(key);
  }

  private static boolean isPackageWide(@NotNull GnoVarDefinition o) {
    GnoVarDeclaration declaration = PsiTreeUtil.getParentOfType(o, GnoVarDeclaration.class);
    return declaration != null && declaration.getParent() instanceof GnoFile;
  }

  private static boolean isPackageWide(@NotNull GnoConstDefinition o) {
    GnoConstDeclaration declaration = PsiTreeUtil.getParentOfType(o, GnoConstDeclaration.class);
    return declaration != null && declaration.getParent() instanceof GnoFile;
  }

  @Override
  public void annotate(@NotNull PsiElement o, @NotNull AnnotationHolder holder) {
    if (!o.isValid()) return;
    if (o instanceof GnoImportSpec && ((GnoImportSpec)o).isDot()) {
      //noinspection SynchronizationOnLocalVariableOrMethodParameter
      synchronized (o) {
        List<PsiElement> importUsers = o.getUserData(GnoReferenceBase.IMPORT_USERS);
        if (importUsers != null) {
          List<PsiElement> newImportUsers = ContainerUtil.newSmartList();
          newImportUsers.addAll(importUsers.stream().filter(PsiElement::isValid).collect(Collectors.toList()));
          o.putUserData(GnoReferenceBase.IMPORT_USERS, newImportUsers.isEmpty() ? null : newImportUsers);
        }
      }
    }
    else if (o instanceof GnoLiteral) {
      if (((GnoLiteral)o).getHex() != null || ((GnoLiteral)o).getOct() != null) {
        setHighlighting(o, holder, NUMBER);
      }
    }
    else if (o instanceof GnoReferenceExpressionBase) {
      PsiReference reference = o.getReference();
      highlightRefIfNeeded((GnoReferenceExpressionBase)o, reference != null ? reference.resolve() : null, holder);
    }
    else if (o instanceof GnoTypeSpec) {
      TextAttributesKey key = getColor((GnoTypeSpec)o);
      setHighlighting(((GnoTypeSpec)o).getIdentifier(), holder, key);
    }
    else if (o instanceof GnoConstDefinition) {
      setHighlighting(o, holder, getColor((GnoConstDefinition)o));
    }
    else if (o instanceof GnoVarDefinition) {
      setHighlighting(o, holder, getColor((GnoVarDefinition)o));
    }
    else if (o instanceof GnoFieldDefinition) {
      setHighlighting(o, holder, getColor((GnoFieldDefinition)o));
    }
    else if (o instanceof GnoParamDefinition) {
      setHighlighting(o, holder, FUNCTION_PARAMETER);
    }
    else if (o instanceof GnoReceiver) {
      PsiElement identifier = ((GnoReceiver)o).getIdentifier();
      if (identifier != null) {
        setHighlighting(identifier, holder, METHOD_RECEIVER);
      }
    }
    else if (o instanceof GnoLabelDefinition || o instanceof GnoLabelRef) {
      setHighlighting(o, holder, LABEL);
    }
    else if (o instanceof GnoNamedSignatureOwner) {
      PsiElement identifier = ((GnoNamedSignatureOwner)o).getIdentifier();
      if (identifier != null) {
        setHighlighting(identifier, holder, getColor((GnoNamedSignatureOwner)o));
      }
    }
  }
}
