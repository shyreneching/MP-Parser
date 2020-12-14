import gen.*;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        try {
            CharStream input = CharStreams.fromFileName("input/test2.java");
//                    (CharStream) new ANTLRFileStream("input/test3.java");
            JavaLexer lexer = new JavaLexer(input);
            JavaParser parser = new JavaParser(new CommonTokenStream(lexer));
            lexer.removeErrorListeners();
            parser.removeErrorListeners();
            lexer.addErrorListener(ErrorListener.INSTANCE);
            parser.addErrorListener(ErrorListener.INSTANCE);

//            parser.addErrorListener(new ErrorListener());
            parser.addParseListener(new JavaCustomListener());
            parser.compilationUnit();
            System.out.println(ErrorListener.INSTANCE.toString());
        } catch (IOException ex) {
            //System.out.println(ex);
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
