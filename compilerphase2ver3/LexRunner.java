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
                System.out.println("SYNTAX ERROR");
                break;
            } else {
                switch (currentToken.getTokenType()) {
                    case WHITESPACE:
                   // case SCOMMENT:
                    //case MCOMMENT:
                        break;
                 
                    default:
                        System.out.println("[" + currentToken.getTokenType() + "] ");
                }
            }
        }
    }
}