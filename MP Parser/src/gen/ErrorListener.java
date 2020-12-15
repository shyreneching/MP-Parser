package gen;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ErrorListener extends BaseErrorListener {
    public static ErrorListener INSTANCE = new ErrorListener();
    private static final boolean REPORT_SYNTAX_ERRORS = true;

    private String errorMsg = "";

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException re) {

        if (!REPORT_SYNTAX_ERRORS) {
            return;
        }

        String sourceName = recognizer.getInputStream().getSourceName();
        if (!sourceName.isEmpty()) {
            sourceName = String.format("%s; ", sourceName);
        }

        String[] temp;
        String[] unnanType = {"boolean;", "byte;", "short;", "int;", "long;", "char;", "float;", "double;", "String;", "Object;"};
        if (msg.contains("extraneous")){
            temp = msg.split("'");
            System.out.println(temp[1]);
            if (msg.contains("'++' expecting ';'")){
                msg = "redundant '+' in arithmetic expression";
            } else if (msg.contains("input ';' expecting")){
                msg = "lacking argument before ';'";
            } else if (temp[1].equals(")")){
                msg = "uneven parentheses, missing one '('";
            } else if(temp[1].equals("(")){
                msg = "uneven parentheses, missing one ')'";
            } else {
                msg = "irrelevant character '"+ temp[1] +"' found";
            }
        }
        else if (msg.contains("missing")){
            temp = msg.split("'");
//            System.out.println(temp[1]);
            if (msg.contains("++")){
                msg = "multiple '+' in arithmetic expression";
            } else if (msg.contains("--")){
                msg = "multiple '-' in arithmetic expression";
//            }
//            else if (msg.contains("missing ';'")){
//                msg = "Excess '"+ temp[3] + "'";
            } else{
                msg = "looking for '" + temp[1] +"' but found " + temp[3];
            }
        }
        else if(msg.contains("mismatched input")) {
            temp = msg.split("'");
            if (Arrays.stream(unnanType).anyMatch(msg::contains)){
                temp = msg.split("'");
                msg = "no identifier found for '" + temp[1].replace(";", "") + "' data type";

            } else {
                msg = "found '"+ temp[1] + "' looking for " + msg.split("expecting ")[1]+"";
            }
        }
        else if(msg.contains("no viable alternative")){
            temp = msg.split("'");
            msg = "mo solution for : '" + temp[1];
        } else if(msg.contains("cannot find symbol")) {
            msg = "No symbol '" + msg.split("'")[1] + "' found";

        } else {
            msg = "";
        }


//        underlineError(recognizer, offendingSymbol, line, charPositionInLine);
        //System.err.println(msg+ ", line "+line+":"+charPositionInLine+" in file: "+ sourceName);
        errorMsg = errorMsg + "\n" + "Syntax Error: " + msg+ ", line "+line+":"+charPositionInLine+" in file: "+ sourceName;
    }

//    protected void underlineError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine) {
//
//        CommonTokenStream tokens = (CommonTokenStream)recognizer.getInputStream();
//        String input = tokens.getTokenSource().getInputStream().toString();
//        String[] lines = input.split("\n"); String errorLine = lines[line - 1];
//        System.err.println(errorLine);
//        for (int i=0; i<charPositionInLine; i++) System.err.print(" ");
//        int start = offendingSymbol.getNumberOfOnChannelTokens();
//        int stop = charPositionInLine + 6;
//        if ( start>=0 && stop>=0 ) {
//            for (int i=start; i<=stop; i++) System.err.print("^");
//
//        }
//        System.err.println();
//    }

    @Override
    public String toString() {
        return errorMsg;
    }
//    @Override public void reportAmbiguity(org.antlr.v4.runtime.Parser recognizer, org.antlr.v4.runtime.dfa.DFA dfa, int startIndex, int stopIndex, boolean exact, java.util.BitSet ambigAlts, org.antlr.v4.runtime.atn.ATNConfigSet configs) {
//        System.out.println("Hello ambiguity");
//    }
//
//    @Override public void reportAttemptingFullContext(org.antlr.v4.runtime.Parser recognizer, org.antlr.v4.runtime.dfa.DFA dfa, int startIndex, int stopIndex, java.util.BitSet conflictingAlts, org.antlr.v4.runtime.atn.ATNConfigSet configs) { /* compiled code */ }
//
//    @Override public void reportContextSensitivity(org.antlr.v4.runtime.Parser recognizer, org.antlr.v4.runtime.dfa.DFA dfa, int startIndex, int stopIndex, int prediction, org.antlr.v4.runtime.atn.ATNConfigSet configs) { System.out.println("Hello reportContextSensitivity"); }
}

