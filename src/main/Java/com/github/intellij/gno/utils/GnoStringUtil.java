package com.github.intellij.gno.utils;

import java.util.Locale;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class GnoStringUtil {
    private static final int[] EMPTY_INT_ARRAY = new int[0];

    public GnoStringUtil() {
    }

    @Contract(
            pure = true
    )
    public static @NotNull String repeat(@NotNull String s, int count) {

        if (count == 0) {
            return "";
        } else {
            assert count >= 0 : count;

            return s.repeat(count);
        }
    }

    public static @Nullable String unescapeStringLiteralText(@Nullable String stringLiteralText) {
        GnoDecodedString decodedString = decodeStringLiteralExpression(stringLiteralText);
        return decodedString.correct ? decodedString.unescapedString : null;
    }

    public static GnoDecodedString decodeStringLiteralExpression(@Nullable String stringLiteralText) {
        String unquoted = unquoteStringLiteralExpression(stringLiteralText);
        if (unquoted != null && !unquoted.isEmpty()) {
            boolean raw = stringLiteralText.charAt(0) == '`';
            return raw ? new GnoDecodedString(true, EMPTY_INT_ARRAY, unquoted) : decode(unquoted);
        } else {
            return new GnoDecodedString(unquoted != null, EMPTY_INT_ARRAY, "");
        }
    }

    public static @NotNull String createSingleLineString(@NotNull String stringContent) {

        StringBuilder result = new StringBuilder();
        result.append("\"");
        escapeString(stringContent, result);
        result.append("\"");

        return result.toString();
    }

    public static @NotNull String escapeString(@NotNull String string) {

        StringBuilder escapedString = new StringBuilder();
        escapeString(string, escapedString);

        return escapedString.toString();
    }

    public static void escapeString(@NotNull String chars, @NotNull StringBuilder outChars) {

        int c;
        for(int index = 0; index < chars.length(); index += Character.charCount(c)) {
            c = chars.codePointAt(index);
            switch (c) {
                case 7:
                    outChars.append("\\a");
                    break;
                case 8:
                    outChars.append("\\b");
                    break;
                case 9:
                    outChars.append("\\t");
                    break;
                case 10:
                    outChars.append("\\n");
                    break;
                case 11:
                    outChars.append("\\v");
                    break;
                case 12:
                    outChars.append("\\f");
                    break;
                case 13:
                    outChars.append("\\r");
                    break;
                case 34:
                    outChars.append("\\\"");
                    break;
                case 92:
                    outChars.append("\\\\");
                    break;
                default:
                    switch (Character.getType(c)) {
                        case 0:
                        case 15:
                        case 18:
                            if (c <= 65535) {
                                outChars.append("\\u").append(String.format(Locale.US, "%04X", c));
                            } else {
                                outChars.append("\\U").append(String.format(Locale.US, "%08X", c));
                            }
                            break;
                        default:
                            outChars.appendCodePoint(c);
                    }
            }
        }

    }

    public static @NotNull GnoDecodedString decode(@NotNull String chars) {

        int[] offsets = new int[chars.length() + 1];
        offsets[chars.length()] = -1;
        StringBuilder outChars = new StringBuilder();
        if (chars.indexOf(92) < 0) {
            outChars.append(chars);

            for(int i = 0; i < offsets.length; offsets[i] = i++) {
            }

        } else {
            int index = 0;

            while(index < chars.length()) {
                char c = chars.charAt(index++);
                offsets[outChars.length()] = index - 1;
                offsets[outChars.length() + 1] = index;
                if (c == '\\') {
                    if (index == chars.length()) {
                        return new GnoDecodedString(false, offsets, outChars.toString());
                    }

                    c = chars.charAt(index++);
                    switch (c) {
                        case '\n':
                        case 'n':
                            outChars.append('\n');
                            break;
                        case '"':
                            outChars.append('"');
                            break;
                        case '\'':
                            outChars.append('\'');
                            break;
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                            int v = c - 48;
                            if (index < chars.length()) {
                                c = chars.charAt(index++);
                                if ('0' <= c && c <= '7') {
                                    v <<= 3;
                                    v += c - 48;
                                    if (c <= '3' && index < chars.length()) {
                                        c = chars.charAt(index++);
                                        if ('0' <= c && c <= '7') {
                                            v <<= 3;
                                            v += c - 48;
                                        } else {
                                            --index;
                                        }
                                    }
                                } else {
                                    --index;
                                }
                            }

                            outChars.append((char)v);
                            break;
                        case 'U':
                            if (index + 8 > chars.length()) {
                                return new GnoDecodedString(false, offsets, outChars.toString());
                            }

                            try {
                                int w = Integer.parseInt(chars.substring(index, index + 8), 16);
                                c = chars.charAt(index);
                                if (c != '+' && c != '-') {
                                    outChars.append((char)w);
                                    index += 8;
                                    break;
                                }

                                return new GnoDecodedString(false, offsets, outChars.toString());
                            } catch (Exception var9) {
                                return new GnoDecodedString(false, offsets, outChars.toString());
                            }
                        case '\\':
                            outChars.append('\\');
                            break;
                        case 'a':
                            outChars.append('\u0007');
                            break;
                        case 'b':
                            outChars.append('\b');
                            break;
                        case 'f':
                            outChars.append('\f');
                            break;
                        case 'r':
                            outChars.append('\r');
                            break;
                        case 't':
                            outChars.append('\t');
                            break;
                        case 'u':
                            if (index + 4 > chars.length()) {
                                return new GnoDecodedString(false, offsets, outChars.toString());
                            }

                            try {
                                int z = Integer.parseInt(chars.substring(index, index + 4), 16);
                                c = chars.charAt(index);
                                if (c != '+' && c != '-') {
                                    outChars.append((char)z);
                                    index += 4;
                                    break;
                                }

                                return new GnoDecodedString(false, offsets, outChars.toString());
                            } catch (Exception var8) {
                                return new GnoDecodedString(false, offsets, outChars.toString());
                            }
                        case 'v':
                            outChars.append('\u000b');
                            break;
                        case 'x':
                            if (index + 2 > chars.length()) {
                                return new GnoDecodedString(false, offsets, outChars.toString());
                            }

                            try {
                                int y = Integer.parseInt(chars.substring(index, index + 2), 16);
                                outChars.append((char)y);
                                index += 2;
                                break;
                            } catch (Exception var7) {
                                return new GnoDecodedString(false, offsets, outChars.toString());
                            }
                        default:
                            return new GnoDecodedString(false, offsets, outChars.toString());
                    }

                    offsets[outChars.length()] = index;
                } else {
                    outChars.append(c);
                }
            }

        }
        return new GnoDecodedString(true, offsets, outChars.toString());
    }

    @Contract(
            pure = true
    )
    public static @NotNull String notNullize(@Nullable String s, @NotNull String defaultValue) {

        String var10000 = s != null ? s : defaultValue;

        return var10000;
    }

    @Contract(
            value = "null -> false",
            pure = true
    )
    public static boolean isNotEmpty(@Nullable String s) {
        return s != null && !s.isEmpty();
    }

    @Contract(
            pure = true
    )
    public static @NotNull String trimStart(@NotNull String s, @NotNull String prefix) {

        if (s.startsWith(prefix)) {
            String var10000 = s.substring(prefix.length());

            return var10000;
        } else {

            return s;
        }
    }

    @Contract(
            pure = true
    )
    public static @NotNull String trimEnd(@NotNull String s, @NotNull String suffix) {

        if (s.endsWith(suffix)) {

            return s.substring(0, s.length() - suffix.length());
        } else {

            return s;
        }
    }

    @Contract(
            pure = true
    )
    public static @NotNull String trimLeading(@NotNull String string) {

        int index;
        for(index = 0; index < string.length() && Character.isWhitespace(string.charAt(index)); ++index) {
        }

        return string.substring(index);
    }

    @Contract(
            value = "null -> null",
            pure = true
    )
    private static @Nullable String unquoteStringLiteralExpression(@Nullable String elementText) {
        return isStringLiteralExpression(elementText) ? elementText.substring(1, elementText.length() - 1) : null;
    }

    @Contract(
            value = "null -> false",
            pure = true
    )
    private static boolean isStringLiteralExpression(@Nullable String elementText) {
        if (elementText != null && elementText.length() > 1) {
            char firstChar = elementText.charAt(0);
            return (firstChar == '`' || firstChar == '"') && elementText.charAt(elementText.length() - 1) == firstChar;
        } else {
            return false;
        }
    }

    public record GnoDecodedString(boolean correct, int @NotNull [] offsets, @NotNull String unescapedString) {
        public GnoDecodedString {

        }
        }
}
