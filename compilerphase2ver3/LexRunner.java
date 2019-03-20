package compilerphase2ver3;

import TokenLibrary.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

public class LexRunner {

    public static void main(String[] args) {


        Hashtable<String, TokenTypes> reservedWords = new Hashtable<String, TokenTypes>();
        Hashtable<String, TokenIdentifier> identifiers = new Hashtable<String, TokenIdentifier>();

        reservedWords = InhaleText.ExhaleReserves();
        Scanner scan = new Scanner(reservedWords, identifiers, "C:\\Users\\RJ\\Desktop\\Program.txt");

        while (Scanner.IsInputEnd()) {
            Token currentToken = scan.ConsumeNextToken();
            if (currentToken.getTokenType() == TokenTypes.ERROR) {
                System.out.println("SYNTAX ERROR LINE: " + Scanner.line);
                break;
            } else {
                switch (currentToken.getTokenType()) {
                    case WHITESPACE:
                    case SINGLECOMMENT:
                    case MULTICOMMENT:
                        break;
                    case NEWLINE:
                        System.out.println("\n");
                        break;
                    case STRINGLITERAL:
                        TokenString strT = (TokenString) currentToken;
                        System.out.print("[" + currentToken.getTokenType() + " = " + strT.literal + "] " );
                        break;
                    case LETTERLITERAL:
                        TokenChar lit = (TokenChar) currentToken;
                        System.out.print("[" + currentToken.getTokenType() + " = " + lit.literal + "] " );
                        break;
                    case ID:
                        TokenIdentifier tid = (TokenIdentifier) currentToken;
                        System.out.print("[" + currentToken.getTokenType() + " = " + tid.idName + "] " );
                        break;
                    default:
                        System.out.print("[" + currentToken.getTokenType() + "] ");
                }
            }
        }
    }
}