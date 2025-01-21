// Generated by JFlex 1.9.1 http://jflex.de/  (tweaked for IntelliJ platform)
// source: _GnoLexer.flex

package fleet.com.github.intellij.gno.lexer;

import fleet.com.intellij.lexer.FlexLexer;
import fleet.com.intellij.psi.tree.IElementType;

import static fleet.com.intellij.psi.TokenType.BAD_CHARACTER;
import static fleet.com.intellij.psi.TokenType.WHITE_SPACE;
import static fleet.com.github.intellij.gno.psi.GnoTypes.*;


public class _GnoLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0, 0
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\37\u0100\1\u0200\267\u0100\10\u0300\u1020\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\2\3\1\4\22\0\1\1\1\5"+
    "\1\6\2\0\1\7\1\10\1\0\1\11\1\12\1\13"+
    "\1\14\1\15\1\16\1\17\1\20\12\21\1\22\1\23"+
    "\1\24\1\25\1\26\2\0\32\27\1\30\1\31\1\32"+
    "\1\33\1\27\1\0\1\34\1\27\1\35\1\36\1\37"+
    "\1\40\1\41\1\42\1\43\1\27\1\44\1\45\1\46"+
    "\1\47\1\50\1\51\1\27\1\52\1\53\1\54\1\55"+
    "\1\56\1\57\1\27\1\60\1\27\1\61\1\62\1\63"+
    "\7\0\1\3\u01a2\0\2\3\326\0\u0100\3";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[1024];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\1\1\4\1\1\1\5"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\26\1\27\1\30\15\25\1\31\1\32\1\33\1\34"+
    "\1\0\1\35\1\0\1\36\1\37\1\40\1\41\1\42"+
    "\1\43\1\44\1\45\1\46\2\0\1\47\1\50\1\51"+
    "\1\52\1\53\1\54\1\55\1\56\1\57\1\60\7\25"+
    "\1\61\1\62\13\25\1\63\1\64\1\65\1\66\1\47"+
    "\1\67\1\70\5\25\1\71\3\25\1\72\1\73\6\25"+
    "\1\74\1\75\1\76\3\25\1\77\1\100\7\25\1\101"+
    "\1\102\1\25\1\103\10\25\1\104\2\25\1\105\1\106"+
    "\1\107\1\110\1\111\1\25\1\112\1\25\1\113";

  private static int [] zzUnpackAction() {
    int [] result = new int[151];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\64\0\150\0\234\0\234\0\320\0\u0104\0\u0138"+
    "\0\u016c\0\64\0\64\0\u01a0\0\u01d4\0\64\0\u0208\0\u023c"+
    "\0\u0270\0\u02a4\0\u02d8\0\64\0\u030c\0\u0340\0\u0374\0\u03a8"+
    "\0\64\0\64\0\u03dc\0\u0410\0\u0444\0\u0478\0\u04ac\0\u04e0"+
    "\0\u0514\0\u0548\0\u057c\0\u05b0\0\u05e4\0\u0618\0\u064c\0\u0680"+
    "\0\64\0\u06b4\0\64\0\64\0\u0104\0\64\0\u06e8\0\64"+
    "\0\64\0\64\0\u071c\0\64\0\64\0\64\0\64\0\64"+
    "\0\u0750\0\u0784\0\u07b8\0\u0784\0\64\0\64\0\u07ec\0\64"+
    "\0\64\0\64\0\u0820\0\64\0\u0854\0\u0888\0\u08bc\0\u08f0"+
    "\0\u0924\0\u0958\0\u098c\0\u03a8\0\u03a8\0\u09c0\0\u09f4\0\u0a28"+
    "\0\u0a5c\0\u0a90\0\u0ac4\0\u0af8\0\u0b2c\0\u0b60\0\u0b94\0\u0bc8"+
    "\0\64\0\64\0\64\0\64\0\u0784\0\64\0\64\0\u0bfc"+
    "\0\u0c30\0\u0c64\0\u0c98\0\u0ccc\0\u03a8\0\u0d00\0\u0d34\0\u0d68"+
    "\0\u03a8\0\u03a8\0\u0d9c\0\u0dd0\0\u0e04\0\u0e38\0\u0e6c\0\u0ea0"+
    "\0\u03a8\0\u03a8\0\u03a8\0\u0ed4\0\u0f08\0\u0f3c\0\u03a8\0\u03a8"+
    "\0\u0f70\0\u0fa4\0\u0fd8\0\u100c\0\u1040\0\u1074\0\u10a8\0\u03a8"+
    "\0\u03a8\0\u10dc\0\u03a8\0\u1110\0\u1144\0\u1178\0\u11ac\0\u11e0"+
    "\0\u1214\0\u1248\0\u127c\0\u03a8\0\u12b0\0\u12e4\0\u03a8\0\u03a8"+
    "\0\u03a8\0\u03a8\0\u03a8\0\u1318\0\u03a8\0\u134c\0\u03a8";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[151];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length() - 1;
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpacktrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\2\1\5\1\6\1\7\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30"+
    "\1\31\1\2\1\32\1\33\1\30\1\34\1\35\1\36"+
    "\1\37\1\40\1\30\1\41\2\30\1\42\1\43\1\30"+
    "\1\44\1\45\1\46\1\47\1\30\1\50\2\30\1\51"+
    "\1\52\1\53\65\0\1\3\64\0\1\4\106\0\1\54"+
    "\36\0\2\55\1\0\1\55\1\0\1\55\1\56\22\55"+
    "\1\57\32\55\25\0\1\60\46\0\1\61\14\0\1\62"+
    "\5\0\1\63\55\0\1\64\52\0\1\65\10\0\1\66"+
    "\54\0\1\67\6\0\1\70\55\0\1\71\44\0\2\72"+
    "\3\0\13\72\1\73\4\72\1\74\36\72\21\0\1\22"+
    "\67\0\1\75\54\0\1\76\5\0\1\77\1\100\63\0"+
    "\1\101\63\0\1\102\1\103\56\0\1\30\5\0\1\30"+
    "\4\0\25\30\30\0\1\104\57\0\1\30\5\0\1\30"+
    "\4\0\1\105\5\30\1\106\5\30\1\107\10\30\24\0"+
    "\1\30\5\0\1\30\4\0\3\30\1\110\21\30\24\0"+
    "\1\30\5\0\1\30\4\0\11\30\1\111\13\30\24\0"+
    "\1\30\5\0\1\30\4\0\14\30\1\112\4\30\1\113"+
    "\3\30\24\0\1\30\5\0\1\30\4\0\14\30\1\114"+
    "\10\30\24\0\1\30\5\0\1\30\4\0\4\30\1\115"+
    "\5\30\1\116\1\117\11\30\24\0\1\30\5\0\1\30"+
    "\4\0\1\120\24\30\24\0\1\30\5\0\1\30\4\0"+
    "\7\30\1\121\15\30\24\0\1\30\5\0\1\30\4\0"+
    "\1\122\24\30\24\0\1\30\5\0\1\30\4\0\3\30"+
    "\1\123\21\30\24\0\1\30\5\0\1\30\4\0\3\30"+
    "\1\124\14\30\1\125\2\30\1\126\1\30\24\0\1\30"+
    "\5\0\1\30\4\0\24\30\1\127\24\0\1\30\5\0"+
    "\1\30\4\0\1\130\24\30\30\0\1\131\34\0\1\132"+
    "\1\0\2\55\3\0\57\55\25\0\1\133\55\0\1\134"+
    "\44\0\2\72\3\0\13\72\1\135\43\72\2\73\1\0"+
    "\61\73\25\0\1\136\63\0\1\137\57\0\1\30\5\0"+
    "\1\30\4\0\17\30\1\140\5\30\24\0\1\30\5\0"+
    "\1\30\4\0\1\141\24\30\24\0\1\30\5\0\1\30"+
    "\4\0\13\30\1\142\11\30\24\0\1\30\5\0\1\30"+
    "\4\0\4\30\1\143\20\30\24\0\1\30\5\0\1\30"+
    "\4\0\17\30\1\144\5\30\24\0\1\30\5\0\1\30"+
    "\4\0\16\30\1\145\6\30\24\0\1\30\5\0\1\30"+
    "\4\0\13\30\1\146\11\30\24\0\1\30\5\0\1\30"+
    "\4\0\15\30\1\147\7\30\24\0\1\30\5\0\1\30"+
    "\4\0\20\30\1\150\4\30\24\0\1\30\5\0\1\30"+
    "\4\0\15\30\1\151\7\30\24\0\1\30\5\0\1\30"+
    "\4\0\11\30\1\152\13\30\24\0\1\30\5\0\1\30"+
    "\4\0\1\30\1\153\23\30\24\0\1\30\5\0\1\30"+
    "\4\0\20\30\1\154\4\30\24\0\1\30\5\0\1\30"+
    "\4\0\11\30\1\155\13\30\24\0\1\30\5\0\1\30"+
    "\4\0\16\30\1\156\6\30\24\0\1\30\5\0\1\30"+
    "\4\0\7\30\1\157\15\30\24\0\1\30\5\0\1\30"+
    "\4\0\15\30\1\160\7\30\24\0\1\30\5\0\1\30"+
    "\4\0\16\30\1\161\6\30\24\0\1\30\5\0\1\30"+
    "\4\0\3\30\1\162\21\30\24\0\1\30\5\0\1\30"+
    "\4\0\13\30\1\163\11\30\24\0\1\30\5\0\1\30"+
    "\4\0\17\30\1\164\5\30\24\0\1\30\5\0\1\30"+
    "\4\0\1\165\2\30\1\166\21\30\24\0\1\30\5\0"+
    "\1\30\4\0\3\30\1\167\21\30\24\0\1\30\5\0"+
    "\1\30\4\0\1\30\1\170\23\30\24\0\1\30\5\0"+
    "\1\30\4\0\14\30\1\171\10\30\24\0\1\30\5\0"+
    "\1\30\4\0\3\30\1\172\21\30\24\0\1\30\5\0"+
    "\1\30\4\0\10\30\1\173\14\30\24\0\1\30\5\0"+
    "\1\30\4\0\21\30\1\174\3\30\24\0\1\30\5\0"+
    "\1\30\4\0\3\30\1\175\21\30\24\0\1\30\5\0"+
    "\1\30\4\0\21\30\1\176\3\30\24\0\1\30\5\0"+
    "\1\30\4\0\20\30\1\177\4\30\24\0\1\30\5\0"+
    "\1\30\4\0\3\30\1\200\21\30\24\0\1\30\5\0"+
    "\1\30\4\0\20\30\1\201\4\30\24\0\1\30\5\0"+
    "\1\30\4\0\21\30\1\202\3\30\24\0\1\30\5\0"+
    "\1\30\4\0\16\30\1\203\6\30\24\0\1\30\5\0"+
    "\1\30\4\0\16\30\1\204\6\30\24\0\1\30\5\0"+
    "\1\30\4\0\16\30\1\205\6\30\24\0\1\30\5\0"+
    "\1\30\4\0\1\206\24\30\24\0\1\30\5\0\1\30"+
    "\4\0\16\30\1\207\6\30\24\0\1\30\5\0\1\30"+
    "\4\0\1\30\1\210\23\30\24\0\1\30\5\0\1\30"+
    "\4\0\1\30\1\211\23\30\24\0\1\30\5\0\1\30"+
    "\4\0\1\30\1\212\23\30\24\0\1\30\5\0\1\30"+
    "\4\0\11\30\1\213\13\30\24\0\1\30\5\0\1\30"+
    "\4\0\20\30\1\214\4\30\24\0\1\30\5\0\1\30"+
    "\4\0\4\30\1\215\20\30\24\0\1\30\5\0\1\30"+
    "\4\0\5\30\1\216\17\30\24\0\1\30\5\0\1\30"+
    "\4\0\13\30\1\217\11\30\24\0\1\30\5\0\1\30"+
    "\4\0\20\30\1\220\4\30\24\0\1\30\5\0\1\30"+
    "\4\0\20\30\1\221\4\30\24\0\1\30\5\0\1\30"+
    "\4\0\6\30\1\222\16\30\24\0\1\30\5\0\1\30"+
    "\4\0\20\30\1\223\4\30\24\0\1\30\5\0\1\30"+
    "\4\0\1\224\24\30\24\0\1\30\5\0\1\30\4\0"+
    "\3\30\1\225\21\30\24\0\1\30\5\0\1\30\4\0"+
    "\1\30\1\226\23\30\24\0\1\30\5\0\1\30\4\0"+
    "\3\30\1\227\21\30\3\0";

  private static int [] zzUnpacktrans() {
    int [] result = new int[4992];
    int offset = 0;
    offset = zzUnpacktrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpacktrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\7\1\2\11\2\1\1\11\5\1\1\11"+
    "\4\1\2\11\16\1\1\11\1\1\2\11\1\0\1\11"+
    "\1\0\3\11\1\1\5\11\2\0\2\1\2\11\1\1"+
    "\3\11\1\1\1\11\24\1\4\11\1\1\2\11\70\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[151];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** Number of newlines encountered up to the start of the matched text. */
  @SuppressWarnings("unused")
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  @SuppressWarnings("unused")
  protected int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  @SuppressWarnings("unused")
  private boolean zzEOFDone;

  /* user code: */
  public _GnoLexer() {
    this((java.io.Reader)null);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public _GnoLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** Returns the maximum size of the scanner buffer, which limits the size of tokens. */
  private int zzMaxBufferLen() {
    return Integer.MAX_VALUE;
  }

  /**  Whether the scanner buffer can grow to accommodate a larger token. */
  private boolean zzCanGrow() {
    return true;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      {@code false}, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position {@code pos} from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException
  {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { return BAD_CHARACTER;
            }
          // fall through
          case 76: break;
          case 2:
            { return WHITE_SPACE;
            }
          // fall through
          case 77: break;
          case 3:
            { return EOL;
            }
          // fall through
          case 78: break;
          case 4:
            { return NOT;
            }
          // fall through
          case 79: break;
          case 5:
            { return REMAINDER;
            }
          // fall through
          case 80: break;
          case 6:
            { return BIT_AND;
            }
          // fall through
          case 81: break;
          case 7:
            { return LPAREN;
            }
          // fall through
          case 82: break;
          case 8:
            { return RPAREN;
            }
          // fall through
          case 83: break;
          case 9:
            { return MUL;
            }
          // fall through
          case 84: break;
          case 10:
            { return PLUS;
            }
          // fall through
          case 85: break;
          case 11:
            { return COMMA;
            }
          // fall through
          case 86: break;
          case 12:
            { return MINUS;
            }
          // fall through
          case 87: break;
          case 13:
            { return DOT;
            }
          // fall through
          case 88: break;
          case 14:
            { return QUOTIENT;
            }
          // fall through
          case 89: break;
          case 15:
            { return INT;
            }
          // fall through
          case 90: break;
          case 16:
            { return COLON;
            }
          // fall through
          case 91: break;
          case 17:
            { return SEMICOLON;
            }
          // fall through
          case 92: break;
          case 18:
            { return LESS;
            }
          // fall through
          case 93: break;
          case 19:
            { return ASSIGN;
            }
          // fall through
          case 94: break;
          case 20:
            { return GREATER;
            }
          // fall through
          case 95: break;
          case 21:
            { return IDENTIFIER;
            }
          // fall through
          case 96: break;
          case 22:
            { return LBRACK;
            }
          // fall through
          case 97: break;
          case 23:
            { return RBRACK;
            }
          // fall through
          case 98: break;
          case 24:
            { return BIT_XOR;
            }
          // fall through
          case 99: break;
          case 25:
            { return LBRACE;
            }
          // fall through
          case 100: break;
          case 26:
            { return BIT_OR;
            }
          // fall through
          case 101: break;
          case 27:
            { return RBRACE;
            }
          // fall through
          case 102: break;
          case 28:
            { return NOT_EQ;
            }
          // fall through
          case 103: break;
          case 29:
            { return STRING;
            }
          // fall through
          case 104: break;
          case 30:
            { return REMAINDER_ASSIGN;
            }
          // fall through
          case 105: break;
          case 31:
            { return COND_AND;
            }
          // fall through
          case 106: break;
          case 32:
            { return BIT_AND_ASSIGN;
            }
          // fall through
          case 107: break;
          case 33:
            { return BIT_CLEAR;
            }
          // fall through
          case 108: break;
          case 34:
            { return MUL_ASSIGN;
            }
          // fall through
          case 109: break;
          case 35:
            { return PLUS_PLUS;
            }
          // fall through
          case 110: break;
          case 36:
            { return PLUS_ASSIGN;
            }
          // fall through
          case 111: break;
          case 37:
            { return MINUS_MINUS;
            }
          // fall through
          case 112: break;
          case 38:
            { return MINUS_ASSIGN;
            }
          // fall through
          case 113: break;
          case 39:
            { return COMMENT;
            }
          // fall through
          case 114: break;
          case 40:
            { return QUOTIENT_ASSIGN;
            }
          // fall through
          case 115: break;
          case 41:
            { return VAR_ASSIGN;
            }
          // fall through
          case 116: break;
          case 42:
            { return SEND_CHANNEL;
            }
          // fall through
          case 117: break;
          case 43:
            { return SHIFT_LEFT;
            }
          // fall through
          case 118: break;
          case 44:
            { return LESS_OR_EQUAL;
            }
          // fall through
          case 119: break;
          case 45:
            { return EQ;
            }
          // fall through
          case 120: break;
          case 46:
            { return GREATER_OR_EQUAL;
            }
          // fall through
          case 121: break;
          case 47:
            { return SHIFT_RIGHT;
            }
          // fall through
          case 122: break;
          case 48:
            { return BIT_XOR_ASSIGN;
            }
          // fall through
          case 123: break;
          case 49:
            { return GO;
            }
          // fall through
          case 124: break;
          case 50:
            { return IF;
            }
          // fall through
          case 125: break;
          case 51:
            { return BIT_OR_ASSIGN;
            }
          // fall through
          case 126: break;
          case 52:
            { return COND_OR;
            }
          // fall through
          case 127: break;
          case 53:
            { return BIT_CLEAR_ASSIGN;
            }
          // fall through
          case 128: break;
          case 54:
            { return TRIPLE_DOT;
            }
          // fall through
          case 129: break;
          case 55:
            { return SHIFT_LEFT_ASSIGN;
            }
          // fall through
          case 130: break;
          case 56:
            { return SHIFT_RIGHT_ASSIGN;
            }
          // fall through
          case 131: break;
          case 57:
            { return FOR;
            }
          // fall through
          case 132: break;
          case 58:
            { return MAP;
            }
          // fall through
          case 133: break;
          case 59:
            { return NIL;
            }
          // fall through
          case 134: break;
          case 60:
            { return VAR;
            }
          // fall through
          case 135: break;
          case 61:
            { return CASE;
            }
          // fall through
          case 136: break;
          case 62:
            { return CHAN;
            }
          // fall through
          case 137: break;
          case 63:
            { return ELSE;
            }
          // fall through
          case 138: break;
          case 64:
            { return FUNC;
            }
          // fall through
          case 139: break;
          case 65:
            { return TYPE;
            }
          // fall through
          case 140: break;
          case 66:
            { return CONST;
            }
          // fall through
          case 141: break;
          case 67:
            { return DEFER;
            }
          // fall through
          case 142: break;
          case 68:
            { return IMPORT;
            }
          // fall through
          case 143: break;
          case 69:
            { return RETURN;
            }
          // fall through
          case 144: break;
          case 70:
            { return SELECT;
            }
          // fall through
          case 145: break;
          case 71:
            { return STRUCT;
            }
          // fall through
          case 146: break;
          case 72:
            { return SWITCH;
            }
          // fall through
          case 147: break;
          case 73:
            { return DEFAULT;
            }
          // fall through
          case 148: break;
          case 74:
            { return PACKAGE;
            }
          // fall through
          case 149: break;
          case 75:
            { return INTERFACE;
            }
          // fall through
          case 150: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
