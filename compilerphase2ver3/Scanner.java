/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerphase2ver3;

/**
 *
 * @author RJ
 */
import TokenLibrary.*;

import java.io.PushbackInputStream;
import java.util.Hashtable;

/*import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.List;*/

public class Scanner {

    public static PushbackInputStream bitSyntax;
    Hashtable<String, TokenTypes> reservedWords;
    Hashtable<String, TokenIdentifier> identifiers;
    int currBit = 0;
    static int line = 1;

    public Scanner(Hashtable<String, TokenTypes> reservedWords, Hashtable<String, TokenIdentifier> identifiers, String fileNameUrl) {
        this.reservedWords = reservedWords;
        this.identifiers = identifiers;

        try {
            bitSyntax = InhaleText.ExhaleText(fileNameUrl);
        } catch (Exception e) {
            System.out.println("Error in the source file");
        }

    }

    static boolean IsInputEnd() {
        try {
            int i = bitSyntax.read();

            if (i != -1) {
                bitSyntax.unread(i);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    @SuppressWarnings("UnusedAssignment")
    public Token ConsumeNextToken() {
        Token requestToken;

        int CURRENT_STATE = 0;
        boolean returnString = false;
        String tokenS = "";

        while (!returnString) {
            char c = Consume();
            tokenS += c;

            switch (CURRENT_STATE) {
                case 0:

                    
                    //End of the line
                    if (c == ';') {
                        //tokenS = Remove(tokenS);
                        CURRENT_STATE=51;
                    }
                    //START & MONEY
                    else if (c == 'M') {
                        CURRENT_STATE = 1;
                    }
                    
                    //INTEGER & INPUT & IF
                     else if (c=='I') {
                        CURRENT_STATE = 5;
                    }
                    
                    //STRING & SELECT
                     else if (c == 'S') {
                        CURRENT_STATE = 8;
                    }
                    
                     //OUTPUT & OR
                     else if (c== 'O'){
                     
                         CURRENT_STATE = 11;
                         
                     }
                  
                   
                   
                    
                    //CHANGE
                    else if (c== 'C'){
                     
                         CURRENT_STATE = 16;
                         
                         
                     }
                  

                   //ELIF & ELSE & END
                    else if (c== 'E'){
                     
                         CURRENT_STATE = 19;
                         
                     }
                    
                     //LPAR
                    else if (c== '('){
                     
                         CURRENT_STATE = 22;
                         
                     }
                    
                    //RPAR
                    else if (c== ')'){
                     
                         CURRENT_STATE = 23;
                         
                     }
                    
                    //LCUR
                    else if (c== '{'){
                     
                         CURRENT_STATE = 24;
                        
                         
                     }
                    
                    
                     
                    
                    //RCUR
                    else if (c=='}'){
                        CURRENT_STATE = 25;
                        
                    }
                 
                    
                    //MORETHAN & EQ
                    else if (c=='>'){
                        CURRENT_STATE = 26;
                    }
                    
                    //LESSTHAN & EQ
                    else if (c=='<'){
                        CURRENT_STATE = 26;
                    }
                    
                    //NOTEQ
                    else if (c== '!'){
                        CURRENT_STATE = 26;
                    }
                    
                    //EQ and ASSIGN
                    else if (c== '='){
                        CURRENT_STATE = 27;
                    }
                    
                    //MULTIPLICATION
                    else if (c== '*'){
                        CURRENT_STATE = 28;
                    }
                    // DIVIDE&COMMENT
                    else if (c== '/'){
                        CURRENT_STATE = 29;
                    }
                    
                    // ADD
                    else if (c=='+'){
                        CURRENT_STATE = 30;
                        
                    }
                    
                    //SUBTRACT
                    else if (c=='-'){
                        CURRENT_STATE = 30;
                    }
                    
                    //ID
                     else if (IsLC(c)){
                        CURRENT_STATE = 31;
                    }
                     
                     //DIGIT
                    else if (IsDigit(c)){
                        CURRENT_STATE = 32;
                    }
                    //LOOP
                    else if (c=='L'){
                        CURRENT_STATE = 33;
                    }
                    
                    //AND
                    
                    else if (c=='A'){
                        CURRENT_STATE = 37;
                    }
                    //NAND
                    else if (c=='N'){
                        CURRENT_STATE = 0;
                    }
                    
                    //XOR
                  
                    else if (c=='X'){
                        CURRENT_STATE = 0;
                    }
                   
                    //BOOL
                    else if (c=='B'){
                        CURRENT_STATE =41 ;
                    }
                    
                    //WHITESPACE
                    else if (c==' '){
                        CURRENT_STATE = 47;
                    }
                    //String Literal
                     else if (c=='$'){
                        CURRENT_STATE = 52;
                        //System.out.println(c);
                    }
                   
                    
                    
                    
                    
                   break;
                   
                    
                    
                    
                    
                    //START
                case 1:
                    if (c == 'A') {
                        CURRENT_STATE = 2;
                    } 
                    else if (c=='O'){
                        CURRENT_STATE = 14;
                    }
                                  
                    else {
                        return new Token(TokenTypes.ERROR);
                    }
                    break;
                case 2:
                    if (c == 'I') {
                        CURRENT_STATE = 3;
                    } else {
                        return new Token(TokenTypes.ERROR);
                    }

                    break;
                    
                    
                    //continue
                    //continue
                    // Hastable<String, TokenType> reserved = new Hastable<String, TokenType>();
                    // reserved.put("continue", TOKENTYPE.CONTNIUE)
                    
                    //currLetter =  _
                    //fullInput = continue
                    /*
                    Method Token ConsumeToken()
                    CURRENTSTATE = 0;
                    
                    while(true){
                    char curentletter = GetLetterFromFile()
                    String fullInput += c;
                        switch(CURRENTSTATE)
                        {
                             case 0:
                                if currentletter is alphabet
                                    go to case 1 
                             break;
                            case 1
                                 if currentLetter is alphabet
                                case = 1 
                    else if current c is WhiteSpaceOrSymbol
                    case = 2
                        if(fullinut)
                    
                    
                                    
                    
                        }
                    
                    }
                    */
                    
                               
                case 3:
                    if ( c == 'N') { // Line enders //If its alphabets, bad, if not, good
                        //Goback();
                        //tokenS = Remove(tokenS);
                        CURRENT_STATE = 4;
                    } else {
                        return new Token(TokenTypes.ERROR);
                    }
                    break;
                    
                case 4:
                    if ( c== '{') {
                        Goback();
                        return new Token(TokenTypes.START);
                    }
                    else if (c == ' '){
                        CURRENT_STATE = 4;
                    }
                    else {
                        return new Token(TokenTypes.ERROR);
                    }
                    
                    break;
                //INT & INPUT
                case 5:
                    if (c == 'N') { // Line enders //If its alphabets, bad, if not, good
                        //Goback();
                        //tokenS = Remove(tokenS);
                        CURRENT_STATE=6;
                    
                    } 
                    else if(c== 'F'){
                    CURRENT_STATE=18;
                    }
                    else {
                        return new Token(TokenTypes.ERROR);
                    }
                    
                   break;
                    
                    
             
                case 6:
                    if (c == 'T') {
                        CURRENT_STATE = 7;
                    }
                    else if (c==' '){
                    Goback();
                    return new Token(TokenTypes.INPUT);
                    }
                   
                    else {
                        return new Token(TokenTypes.ERROR);
                    }
                    break;
                    
                case 7:
                    if (c == ' ') {
                        Goback();
                        return new Token(TokenTypes.INTEGER);
                    } 
                                      
                    else {
                        return new Token(TokenTypes.ERROR);
                    }
                    //break;
                //STRING
                case 8:
                    if (c == 'T') {
                        CURRENT_STATE=9;
                    }
                    else if (c=='E'){
                        CURRENT_STATE=49;
                    }
                    else {
                        return new Token(TokenTypes.ERROR);
                    }
                    
                   break;
                case 9:
                    if (c == 'R') {
                        CURRENT_STATE = 10;
                    } else {
        
                        return new Token(TokenTypes.ERROR);
                        
                    }
                    break;
                    
                   
                case 10:
                    
                    if (c == ' '){
                    Goback();
                    return new Token(TokenTypes.STRING);
                    }
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
               // break;
                    
                //OUPUT       
                case 11:

                    if (c == 'U'){
                    CURRENT_STATE = 12;
                    }     
                    else if (c=='R'){
                    CURRENT_STATE = 40;
                    }
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                break;

                case 12:

                    if (c == 'T'){
                    CURRENT_STATE = 13;
                    }                 
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                break;

                case 13:

                    if (c == ' ' || c=='('){
                    Goback();
                    return new Token(TokenTypes.OUTPUT);
                    }                 
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                //break;

                //MONEY
                case 14:

                    if (c == 'N'){
                    CURRENT_STATE = 15;
                    } 
                   
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                break;

                case 15:

                    if (c == ' ' || c==';'){
                        Goback();
                    return new Token(TokenTypes.MONEY);
                    }                 
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                //break;
                    
                //CHANGE
                case 16:

                    if (c == 'H'){
                   
                    CURRENT_STATE=17;
                   
                    }                 
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                break;
                
                case 17:

                    if (c ==' '|| c==';'){
                        Goback();
                    return new Token(TokenTypes.CHANGE);
                    }                 
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                //break;
                    
                //IFMAIN
                case 18:

                    if (c == ' '){
                        Goback();
                    return new Token(TokenTypes.IFMAIN);
                    }                 
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
               // break;
                //ELIF & ELSE
                case 19:

                    if (c == 'L'){
                        
                    CURRENT_STATE=20;
                    }   
                    else if (c=='N'){
                    CURRENT_STATE= 45;
                    }
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                    break;
                case 20:

                    if (c == 'I'){
                    CURRENT_STATE = 21;
                    }
                    else if (c== 'S'){
                    CURRENT_STATE = 21;
                    }
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                break;

                case 21:

                    if (c == 'F'){
                        CURRENT_STATE = 18;
                        }  
                    else if (c=='E'){
                    CURRENT_STATE = 18;
                    }
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                 //LPAR
                 case 22:

                    if (AlphaOrDigit(c)||c==' '){
                        Goback();
                        return new Token(TokenTypes.LPAR);
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    //break;
                //RPAR
                case 23:

                    if (c==' ' || c== '{' || c==';'){
                        Goback();
                        return new Token(TokenTypes.RPAR);
                        } 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                   // break;
                //LCUR
                case 24:

                    if (c==' '){
                      
                        Goback();
                        return new Token(TokenTypes.LCUR);
                        
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                //RCUR
                case 25:

                      if (c==' '){
                        Goback();
                        return new Token(TokenTypes.RCUR);
                        }
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                //RELOP
                case 26:

                    if (c==' '){
                        Goback();
                        return new Token(TokenTypes.RELOP);
                        } 
                   
                    else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                   // break;
                //ASSIGN
                case 27:

                    if (c == ' '){
                        Goback();
                       return new Token(TokenTypes.ASSIGN);
                        } 
                    else if (c == '='){
                    CURRENT_STATE = 26;
                    }
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                //MULDIV
                case 28:

                    if (IsDigit(c)||c==' '){
                        Goback();
                        return new Token(TokenTypes.MULDIV);
                        }  
                   
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    //break;
                
                    
                    //COMMENT with MULDIV
                case 29:

                    if (c == '/'){
                        Goback();
                        return new Token(TokenTypes.SCOMMENT);
                        }  
                    else if (c=='*'){
                        Goback();
                        return new Token(TokenTypes.MCOMMENT);
                    }
                    else if (IsDigit(c)||c==' '){
                        Goback();
                        return new Token(TokenTypes.MULDIV);
                    }
                    
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    //break;
                //ADDSUB
                 case 30:

                    if (c == ' ' || AlphaOrDigit(c)){
                        Goback();
                        return new Token(TokenTypes.ADDSUB);
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    //break;
                    
                //ID
                 case 31:

                    if (AlphaOrDigit(c)){
                        CURRENT_STATE = 31;
                        }  
                    else if (c==';' || c==' ' || c==')'){
                        Goback();
                        return new Token(TokenTypes.ID);
                    }
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                    
                  //DIGIT  
                 case 32:

                    if (IsDigit(c)){
                       
                        CURRENT_STATE= 48;
                        }  
                    else if (c == ' '|| c== ';'){
                        Goback();
                        return new Token(TokenTypes.DIGIT);
                        
                    }
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    //break;
                    
                    //LOOP
                 case 33:

                    if (c == 'O'){
                        CURRENT_STATE = 34;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                case 34:

                    if (c == 'O'){
                        CURRENT_STATE = 35;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                case 35:

                    if (c == 'P'){
                        CURRENT_STATE = 36;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                case 36:

                    if (c == '('){
                        Goback();
                        return new Token(TokenTypes.LOOP);
                        }   
                    else if (c==' '){
                        CURRENT_STATE=36;
                    }
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                    
                    
                case 37:

                    if (c == 'N'){
                        CURRENT_STATE = 38;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                case 38:

                    if (c == 'D'){
                        CURRENT_STATE = 39;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                   
                 case 39:

                    if (c == ' ' || AlphaOrDigit(c) ){
                        Goback();
                        return new Token(TokenTypes.G_LOG);
                        }  
                    else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    //break;
                   
                    //L_LOG
                   case 40:

                    if (c == ' ' || AlphaOrDigit(c)){
                        Goback();
                        return new Token(TokenTypes.G_LOG);
                        } 
                   
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                   // break;
                    
                    //BOOL
                    case 41:

                    if (c == 'O'){
                        CURRENT_STATE = 42;
                        } 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                    
                    case 42:

                    if (c == 'O'){
                        CURRENT_STATE = 43;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                    
                    case 43:

                    if (c == 'L'){
                        CURRENT_STATE=44;
                        } 
                   
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                    
                    
                    case 44:

                    if (c == ' '){
                        Goback();
                        return new Token(TokenTypes.BOOLEAN);
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                   // break;
                    //END
                    case 45:

                    if (c == 'D'){
                        CURRENT_STATE = 46;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                    
                    case 46:

                    if (c == ' '){
                        Goback();
                        return new Token(TokenTypes.END);
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    //break;
                    
                    //WHTESPACE
                    case 47:

                    if (c == ' ') {
                        CURRENT_STATE = 47;
                    } else {
                        Goback();
                        return new Token(TokenTypes.WHITESPACE);
                        
                    }
                    break;
                    
                    case 48:

                    if (IsDigit(c)) {
                        CURRENT_STATE = 32;
                    } else {
                      
                        return new Token(TokenTypes.ERROR);
                        
                    }
                    break;
                    //SELECT
                    case 49:

                    if (c=='L') {
                        CURRENT_STATE = 50;
                    } else {
                      
                        return new Token(TokenTypes.ERROR);
                        
                    }
                    break;
                    
                    case 50:

                    if (c==' ') {
                        Goback();
                        return new Token(TokenTypes.SELECT);
                    } else {
                      
                        return new Token(TokenTypes.ERROR);
                        
                    }
                    //break;
                    //ENDLINE
                    case 51:

                    if (c==' ') {
                        Goback();
                        return new Token(TokenTypes.ENDLINE);
                        
                    } else {
                      
                        return new Token(TokenTypes.ERROR);
                        
                    }
                    //break;
                    //STRING Literal
                    case 52:

                    if (AlphaOrDigit(c) || c==' ') {
                        CURRENT_STATE=52;
                        //System.out.print(c);
                    } 
                    else if (c=='$'){
                    //Goback();
                    return new Token(TokenTypes.STRLit);
                    }
                    else {
                      
                        return new Token(TokenTypes.ERROR);
                        
                    }
                    break;






            }
        }

        return new Token(TokenTypes.ERROR);

    }

    char Consume() {
        try {
            currBit = bitSyntax.read();
            if (currBit == -1) {
                return '$';
            }

            return (char) currBit;

        } catch (Exception e) {
            System.out.println("Consume Error!");
        }
        return '0';
    }

    void Goback() {
        try {
            bitSyntax.unread(currBit);
        } catch (Exception e) {
            System.out.println("Pushback Error!");
        }
    }

    String Remove(String tokenString) {
        return tokenString.substring(0, tokenString.length() - 1);
    }

    boolean IsAlpha(char c) {
        return Character.isLetter(c);
    }

    boolean IsDigit(char c) {
        return Character.isDigit(c);
    }

    boolean AlphaOrDigit(char c) {
        return (Character.isDigit(c) || Character.isLetter(c));
    }

    boolean AlphaAndDigit(char c) {
        return (Character.isDigit(c) && Character.isLetter(c));
    }
    
     boolean IsLC(char c) {
        return Character.isLowerCase(c);
    }

    private boolean parseInt(char c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
