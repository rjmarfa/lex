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

                    if (currBit == 10) {
                        return new Token(TokenTypes.NEWLINE);
                    }
                    //End of the line
                    if (c == ';') {
                        tokenS = Remove(tokenS);
                        return new Token(TokenTypes.WHITESPACE);
                    }
                    //NEW_LINE & TAB & division op
                    else if (c == '/') {
                        CURRENT_STATE = 1;
                    }
                    
                    //FRAC_LIT & STAT_LIT
                     else if (IsDigit(c)) {
                        CURRENT_STATE = 6;
                    }
                    
                    //WHITESPACE
                     else if (c == ' ') {
                        CURRENT_STATE = 9;
                    }
                    
                     //HOLE & HEAL
                     else if (c== 'H'){
                     
                         CURRENT_STATE = 10;
                         
                     }
                  
                   
                   //FOLLOW & DESC_LITf & FRAC
                   else if (c== 'F'){
                     
                         CURRENT_STATE = 14;
                         
                     }
                  
                    
                    //LENGTH
                    else if (c== 'l'){
                     
                         CURRENT_STATE = 20;
                         
                     }
                  

                   //SUBS
                    else if (c== 's'){
                     
                         CURRENT_STATE = 24;
                         
                     }
                    
                     //DESC_DATA & DESC & DHEAL & DDAM & DMG & DG
                    else if (c== 'D'){
                     
                         CURRENT_STATE = 29;
                         
                     }
                    
                    //INIT_DATA & _VAR
                    else if (c== 'I'){
                     
                         CURRENT_STATE = 33;
                         
                     }
                    
                    //STAT & NAME_LIT & SCALE & SHRINK & S_ARRAY
                    else if (c== 'S'){
                     
                         CURRENT_STATE = 40;
                         
                     }
                    
                    
                     
                    
                    //NAME & NOT & NAND & NOR
                    else if (c=='N'){
                        CURRENT_STATE = 48;
                    }
                 
                    
                    //id
                    else if (c=='i'){
                        CURRENT_STATE = 53;
                    }
                    
                    //STAT_LIT
                    else if (IsDigit(c)){
                        CURRENT_STATE = 52;
                    }
                    
                    //DESC_LITt
                    else if (c== 'T'){
                        CURRENT_STATE = 59;
                    }
                    
                    //LOCAL
                    else if (c== 'L'){
                        CURRENT_STATE = 67;
                    }
                    
                    //PATH & PLAYER
                    else if (c== 'P'){
                        CURRENT_STATE = 73;
                    }
                    // ALT & ALTPATH & AND
                    else if (c== 'A'){
                        CURRENT_STATE = 77;
                    }
                    
                    // COMBOS & Chain
                    else if (c=='C'){
                        CURRENT_STATE = 79;
                    }
                    
                    //CONDITION & RELOPS
                    else if (c=='<'){
                        CURRENT_STATE = 88;
                    }
                     else if (c=='>'){
                        CURRENT_STATE = 88;
                    }
                    else if (c=='!'){
                        CURRENT_STATE = 89;
                    }
                    else if (c=='='){
                        CURRENT_STATE = 89;
                    }
                    
                    //GAMESTART
                    
                    else if (c=='#'){
                        CURRENT_STATE = 96;
                    }
                    //GAME
                    else if (c=='G'){
                        CURRENT_STATE = 97;
                    }
                    
                    //L_PAR & R_PAR
                  
                    else if (c=='('){
                        CURRENT_STATE = 101;
                    }
                    else if (c==')'){
                        CURRENT_STATE = 102;
                    }
                    
                    //G_EFF
                    else if (c=='R'){
                        CURRENT_STATE = 111;
                    }
                    else if (c=='E'){
                        CURRENT_STATE = 116;
                    }
                    //GRADE 
                    else if (c=='U'){
                        CURRENT_STATE = 29;
                    }
                    //ASSIGN
                    else if (c==':'){
                        CURRENT_STATE = 126;
                    }
                    //L_LOG
                    
                    else if (c=='O'){
                        CURRENT_STATE = 128;
                    }
                    else if (c=='X'){
                        CURRENT_STATE = 0;
                    }
                    //BRACES
                     else if (c=='{'){
                        CURRENT_STATE = 134;
                    }
                    else if (c=='}'){
                        CURRENT_STATE = 135;
                    }
                    
                    //Ops
                    else if (c=='+'){
                        CURRENT_STATE = 136;
                    }
                     else if (c=='-'){
                        CURRENT_STATE = 136;
                    }
                     else if (c=='*'){
                        CURRENT_STATE = 136;
                    }
                    
                    
                    
                    
                   break;
                   
                    
                    
                    
                    
                    //New Line & tab & div
                case 1:
                    if (c == 'n') {
                        CURRENT_STATE = 2;
                    } 
                    //Tab
                    else if (c == 't'){
                        CURRENT_STATE = 4;
                    }

                    else if (c == ' '){
                        CURRENT_STATE = 9;
                    }
                    else if (c == '/'){
                        CURRENT_STATE = 139;
                    }
                    
                    else if (!IsAlpha(c)){
                        return new Token(TokenTypes.MULDIVOP);
                    }
                    
                    else {
                        return new Token(TokenTypes.ERROR);
                    }
                    break;
                case 2:
                    if (c == 'l') {
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
                    if (  /*!IsAlpha(c)*/c == ' ' || c == ';') { // Line enders //If its alphabets, bad, if not, good
                        //Goback();
                        //tokenS = Remove(tokenS);
                        return new Token(TokenTypes.NEW_LINE);
                    } else {
                        return new Token(TokenTypes.ERROR);
                    }
                    
                case 4:
                    if (c == 'b') {
                        CURRENT_STATE = 5;
                    } else {
                        return new Token(TokenTypes.ERROR);
                    }
                    
                    break;
                    
                case 5:
                    if (/*!IsAlpha(c)*/c == ' ' || c == ';') { // Line enders //If its alphabets, bad, if not, good
                        //Goback();
                        //tokenS = Remove(tokenS);
                        return new Token(TokenTypes.TAB);
                    } else {
                        return new Token(TokenTypes.ERROR);
                    }
                    
                   
                    
                    
                    //FRAC_LIT
                case 6:
                    if (c == '.') {
                        CURRENT_STATE = 7;
                    } 
                    else if (IsDigit(c)){
                        CURRENT_STATE = 52;
                    }
                    else if (c==' '|| c== ';'){
                        return new Token(TokenTypes.STAT_LIT);
                    }
                    
                    else {
                        return new Token(TokenTypes.ERROR);
                    }
                    break;
                    
                case 7:
                    if (IsDigit(c)) {
                        CURRENT_STATE = 7;
                    } 
                    
                    else if (c == ' ' || c == ';'){
                        Goback();
                        CURRENT_STATE = 9;
                        return new Token(TokenTypes.FRAC_LIT);
                    }
                    
                    else {
                        return new Token(TokenTypes.ERROR);
                    }
                    break;
                case 8:
                    if (c == 'E') {
                        return new Token(TokenTypes.HOLE);
                    } else {
                        return new Token(TokenTypes.ERROR);
                    }
                    
                    //WHITESPACE
                case 9:
                    if (c == ' ') {
                        CURRENT_STATE = 9;
                    } else {
                        Goback();
                        return new Token(TokenTypes.WHITESPACE);
                        
                    }
                    break;
                    
                    //ID & Reserved word
                case 10:
                    
                    if (c == 'O'){
                    CURRENT_STATE = 11;
                    }
                    else if (c== 'E'){
                        CURRENT_STATE = 123;
                    }
                    else if (c == 'G'){
                        return new Token (TokenTypes.GRADE);
                    }
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                break;

                case 11:

                    if (c == 'L'){
                    CURRENT_STATE = 12;
                    }                 
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                break;

                case 12:

                    if (c == 'E'){
                    CURRENT_STATE = 13;
                    }                 
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                break;

                case 13:

                    if (c == ' ' || c == ';'){
                    return new Token(TokenTypes.HOLE);
                    }                 
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                //break;

                //FOLLOW & DESC_LITf
                case 14:

                    if (c == 'O'){
                    CURRENT_STATE = 15;
                    } 
                    else if (c=='A'){
                        CURRENT_STATE = 63;
                    }
                    else if (c== 'R'){
                        CURRENT_STATE = 44;
                    }
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                break;

                case 15:

                    if (c == 'L'){
                    CURRENT_STATE = 16;
                    }                 
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                break;

                case 16:

                    if (c == 'L'){
                    CURRENT_STATE = 17;
                    }                 
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                break;

                case 17:

                    if (c == 'O'){
                    CURRENT_STATE = 18;
                    }                 
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                break;

                case 18:

                    if (c == 'W'){
                    CURRENT_STATE = 19;
                    }                 
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                break;

                case 19:

                    if (c == ' ' || c == ';'){
                        Goback();
                    return new Token(TokenTypes.FOLL);
                    }                 
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }

                case 20:

                    if (c == 'e'){
                    CURRENT_STATE = 21;
                    }                 
                    else{ 
                        return new Token(TokenTypes.ERROR);
                    }
                break;

                case 21:

                    if (c == 'n'){
                        CURRENT_STATE = 22;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;

                 case 22:

                    if (c == '('){
                        CURRENT_STATE = 23;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;

                case 23:

                    if (IsAlpha(c)){
                        CURRENT_STATE = 23;
                        }  

                    else if (c == ')'){
                        return new Token(TokenTypes.LENGTH);

                    }               
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;

                case 24:

                    if (c == 'b'){
                        CURRENT_STATE = 25;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;

                case 25:

                    if (c == '('){
                        CURRENT_STATE = 26;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;

                case 26:

                    if (IsDigit(c)){
                        CURRENT_STATE = 26;
                        }  
                    else if (c == ','){
                        CURRENT_STATE = 27;

                    }else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                case 27:

                    if (c == ' '){
                        CURRENT_STATE = 28;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                case 28:

                    if (IsDigit(c)){
                        CURRENT_STATE = 28;
                        }  
                    else if (c==')'){
                        return new Token(TokenTypes.SUBS);

                    }               
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                
                    
                    //DESC_DATA & DHEAL & DDAM
                case 29:

                    if (c == 'E'){
                        CURRENT_STATE = 30;
                        }  
                    else if (c=='H'){
                        CURRENT_STATE = 103;
                    }
                    else if (c=='D'){
                        CURRENT_STATE = 103;
                    }
                    else if (c== 'M'){
                        CURRENT_STATE = 124;
                    }
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                
                 case 30:

                    if (c == 'S'){
                        CURRENT_STATE = 31;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                
                 case 31:

                    if (c == 'C'){
                        CURRENT_STATE = 32;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                 case 32:

                    if (c == ' ' || c== ';'){
                        
                        Goback();
                        return new Token(TokenTypes.DESC_DATA);
                        }  
                    else if (c == 'v'){
                        return new Token(TokenTypes.DESC_VAR);
                        
                    }
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    //break;
                    
                    //INITIAL & INITIALv
                 case 33:

                    if (c == 'N'){
                        CURRENT_STATE = 34;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                case 34:

                    if (c == 'I'){
                        CURRENT_STATE = 35;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                case 35:

                    if (c == 'T'){
                        CURRENT_STATE = 36;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                case 36:

                    if (c == 'I'){
                        CURRENT_STATE = 37;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                case 37:

                    if (c == 'A'){
                        CURRENT_STATE = 38;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                case 38:

                    if (c == 'L'){
                        CURRENT_STATE = 39;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                   
                 case 39:

                    if (c == ' ' || c == ';' ){
                        Goback();
                        return new Token(TokenTypes.INIT_DATA);
                        }  
                    else if (c == 'v'){
                        return new Token(TokenTypes.INIT_VAR);
                    }
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    //break;
                   
                    //STAT & NAME_LIT & SCALE
                   case 40:

                    if (c == 'T'){
                        CURRENT_STATE = 41;
                        } 
                    else if (c=='t'){
                        CURRENT_STATE = 54;
                    }
                    else if (c=='C'){
                        CURRENT_STATE = 107;
                        }
                    else if (c=='H'){
                        CURRENT_STATE = 111;
                        }
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                    
                    case 41:

                    if (c == 'A'){
                        CURRENT_STATE = 42;
                        } 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                    
                    case 42:

                    if (c == 'T'){
                        CURRENT_STATE = 43;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                    
                    case 43:

                    if (c == ' ' || c== ';'){
                        Goback();
                        return new Token(TokenTypes.STAT_DATA);
                        } 
                    else if (c== 'v'){
                        return new Token(TokenTypes.STAT_VAR);
                        
                    }
                    else if (c=='['){
                        CURRENT_STATE = 130;
                    }
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    //break;
                    
                    //FRAC_DATA
                    case 44:

                    if (c == 'A'){
                        CURRENT_STATE = 45;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                    
                    case 45:

                    if (c == 'C'){
                        CURRENT_STATE = 47;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                    
                    case 46:

                    if (c == 'C'){
                        CURRENT_STATE = 47;
                        }                 
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    break;
                    
                    case 47:

                    if (c == ' '|| c==';'){
                        Goback();
                        return new Token(TokenTypes.FRAC_DATA);
                       
                        }
                    else if (c=='v'){
                        return new Token(TokenTypes.FRAC_VAR);
                    }
                        else{ 
                            return new Token(TokenTypes.ERROR);
                        }
                    //break;
                    
                    //NAME DATA
                    case 48:

                        if (c == 'A'){
                            CURRENT_STATE = 49;
                            } 
                        else if (c=='O'){
                            CURRENT_STATE = 127;
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                     case 49:

                        if (c == 'M'){
                            CURRENT_STATE = 50;
                            }  
                        else if (c=='N'){
                               CURRENT_STATE = 128;
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                     case 50:

                        if (c == 'E'){
                            CURRENT_STATE = 51;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                     case 51:

                        if (c == ' '|| c==';'){
                            Goback();
                            return new Token(TokenTypes.NAME_DATA);
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                   // break;
                        
                        //STAT_LIT & FRAC_LIT
                     case 52:

                        if (c==' '|| c==';'){
                            Goback();
                            return new Token(TokenTypes.STAT_LIT);
                            }
                        else if (IsDigit(c)){
                            CURRENT_STATE = 52;
                        }
                        else if (c== '.'){
                            CURRENT_STATE = 7;
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                     //id
                     case 53:

                        if (c=='d'){
                            CURRENT_STATE = 138;
                            }   
                        else if (c == ' '||c==';' || c== '<'|| c=='{'|| c== '('){
                            Goback();
                            return new Token(TokenTypes.ID);
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        
                        //NAME_LIT
                     case 54:

                        if (c == 'r'){
                            CURRENT_STATE = 55;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                    case 55:

                        if (c == 'i'){
                            CURRENT_STATE = 56;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                    case 56:

                        if (c == 'n'){
                            CURRENT_STATE = 57;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                    case 57:

                        if (c == 'g'){
                            CURRENT_STATE = 58;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                    case 58:

                        if (c == ' '|| c== ';'){
                            Goback();
                            return new Token(TokenTypes.NAME_LIT);
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                   // break;
                    
                    //DESC_LITt
                    case 59:

                        if (c == 'R'){
                            CURRENT_STATE = 60;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                    case 60:

                        if (c == 'U'){
                            CURRENT_STATE = 61;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                    case 61:

                        if (c == 'E'){
                            CURRENT_STATE = 62;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                    case 62:

                        if (c == ' ' || c==';'){
                            Goback();
                            return new Token(TokenTypes.DESC_LITt);
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                   // break;
                    
                    //DESC_LITf
                    case 63:

                        if (c == 'L'){
                            CURRENT_STATE = 64;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                    case 64:

                        if (c == 'S'){
                            CURRENT_STATE = 65;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                    case 65:

                        if (c == 'E'){
                            CURRENT_STATE = 66;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                    case 66:

                        if (c == ' '|| c==';'){
                            Goback();
                            return new Token(TokenTypes.DESC_LITf);
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    //break;
                    
                    //LOCAL
                    case 67:

                        if (c == 'O'){
                            CURRENT_STATE = 68;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                    case 68:

                        if (c == 'C'){
                            CURRENT_STATE = 69;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                    case 69:

                        if (c == 'A'){
                            CURRENT_STATE = 70;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                    case 70:

                        if (c == 'L'){
                            CURRENT_STATE = 71;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    
                    case 71:

                        if (c == ' '|| c==';'){
                            Goback();
                            return new Token(TokenTypes.LOC);
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                   // break;
                        
                    //DESC
                    case 72:

                        if (c == ' '|| c==';'){
                            Goback();
                            return new Token(TokenTypes.DESC);
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        
                    //PATH
                    case 73:

                        if (c == 'A'){
                            CURRENT_STATE = 74;
                            } 
                        else if (c=='L'){
                            CURRENT_STATE = 90;
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    case 74:

                        if (c == 'T'){
                            CURRENT_STATE = 75;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    case 75:

                        if (c == 'H'){
                            CURRENT_STATE = 76;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    break;
                    case 76:

                        if (c == ' '|| c==';'){
                            Goback();
                            return new Token(TokenTypes.PATHMAIN);
                            }
                        else if (c=='P'){
                            CURRENT_STATE = 0;
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                    //ALT & ALTPATH & AND
                    case 77:

                        if (c == 'L'){
                            CURRENT_STATE = 78;
                            }
                        else if (c== 'N'){
                            CURRENT_STATE = 128;
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 78:

                        if (c == 'T'){
                            CURRENT_STATE = 76;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        //break;
                        
                        //COMBO_F & Chain
                    case 79:

                        if (c == 'O' || c=='o'){
                            CURRENT_STATE = 80;
                            } 
                        
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                     case 80:

                        if (c == 'M' || c=='m'){
                            CURRENT_STATE = 81;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 81:

                        if (c == 'B' || c== 'b'){
                            CURRENT_STATE = 82;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 82:

                        if (c == 'O'){
                            CURRENT_STATE = 83;
                            }  
                        else if (c== 'o'){
                            CURRENT_STATE = 87;
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 83:

                        if (c == '<'){
                            CURRENT_STATE = 84;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 85:

                        if (IsDigit(c)){
                            CURRENT_STATE = 85;
                            } 
                        else if (c==','){
                            CURRENT_STATE = 85;
                        }
                        else if (c=='>'){
                            CURRENT_STATE = 86;
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 86:

                        if (c == ' '){
                            return new Token (TokenTypes.COMBO_F);
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        //break;
                        
                    //CHAIN extension
                        case 87:

                        if (IsAlpha(c)){
                            CURRENT_STATE = 87;
                            }
                        else if (c=='<'){
                            Goback();
                            return new Token (TokenTypes.CHAIN);
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                        
                        
                        //CONDITION & Relops
                        case 88:

                        if (IsDigit(c)){
                            CURRENT_STATE = 88;
                            } 
                        else if (c == ' '){
                            Goback();
                            return new Token(TokenTypes.CONDITION);
                        }
                        else if (c == '<'){
                            CURRENT_STATE= 89;
                        }
                        else if (c == '>'){
                            CURRENT_STATE= 89;
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                        
                        //
                        case 89:

                        if (c== ' '){
                            return new Token(TokenTypes.RELOP);
                            } 
                        else if (c == '='){
                            return new Token(TokenTypes.RELOP);
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                     //   break;
                            
                          
                        case 90:

                        if (c == 'A'){
                            CURRENT_STATE = 91;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                        case 91:

                        if (c == 'Y'){
                            CURRENT_STATE = 92;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                        case 92:

                        if (c == 'E'){
                            CURRENT_STATE = 93;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;

                        case 93:

                        if (c == 'R'){
                            CURRENT_STATE = 94;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                        case 94:

                        if (c == ' '){
                            Goback();
                            //CURRENT_STATE = 95;
                            return new Token(TokenTypes.PLAYER_O);
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        //break;
                        case 95:

                        if (IsAlpha(c)){
                            CURRENT_STATE = 95;
                            } 
                        else if (c=='{'){
                            Goback();
                            return new Token(TokenTypes.PLAYER_O);
                        }
                        else if (c=='<'){
                            Goback();
                            CURRENT_STATE = 88;
                           
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                      
                        //GAME_START
                     case 96:

                        if (IsAlpha(c)){
                            CURRENT_STATE = 96;
                            } 
                        else if (c=='{'){
                            Goback();
                            return new Token(TokenTypes.GAME_START);
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                        
                    //GAME
                     case 97:
                      if (c == 'A'){
                            CURRENT_STATE = 98;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                     case 98:
                      if (c == 'M'){
                            CURRENT_STATE = 99;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 99:
                      if (c == 'E'){
                            CURRENT_STATE = 100;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 100:
                      if (c == ' '){
                            CURRENT_STATE = 100 ;
                            } 
                      else if (c == '#'){
                          Goback();
                          return new Token(TokenTypes.GAME);
                      }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                       
                      //L_PAR & R_PAR
                        
                    case 101:
                        return new Token(TokenTypes.L_PAR);
                    case 102:
                        return new Token(TokenTypes.R_PAR);
                        
                     // DHEAL & DDAM
                    case 103:
                        if (c == 'E'){
                            CURRENT_STATE = 104;
                            }
                        else if (c== 'A'){
                            CURRENT_STATE= 104;
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 104:
                        if (c == 'A'){
                            CURRENT_STATE = 105;
                            } 
                        else if (c=='M'){
                            CURRENT_STATE = 106;
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 105:
                        if (c == 'L'){
                            CURRENT_STATE = 106;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 106:
                        if (c == ' '){
                            Goback();
                            return new Token(TokenTypes.DOTS);
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        //break;
                        
                    // SCALE Extenson
                    case 107:
                        if (c == 'A'){
                            CURRENT_STATE = 108;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 108:
                        if (c == 'L'){
                            CURRENT_STATE = 109;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 109:
                        if (c == 'E'){
                            CURRENT_STATE = 110;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 110:
                        if (c == ' '){
                            Goback();
                            return new Token(TokenTypes.SCL);
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        //break;
                    
                        // SHRINK
                    case 111:
                        if (c == 'R'){
                            CURRENT_STATE = 112;
                            }  
                        else if (c== 'E'){
                            CURRENT_STATE =112;
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 112:
                        if (c == 'I'){
                            CURRENT_STATE = 113;
                            }     
                        else if (c == 'M'){
                            CURRENT_STATE = 113;
                            }        
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 113:
                        if (c == 'N'){
                            CURRENT_STATE = 114;
                            } 
                        else if (c == 'A'){
                            CURRENT_STATE = 113;
                            }   
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 114:
                        if (c == 'K'){
                            CURRENT_STATE = 115;
                            }  
                        if (c == 'I'){
                            CURRENT_STATE = 114;
                            }  
                        if (c == 'N'){
                            CURRENT_STATE = 115;
                            }        
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 115:
                        if (c == ' '){
                            Goback();
                            return new Token(TokenTypes.G_EFF);
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                       // break;
                       //ENLARGE 
                    case 116:
                        if (c == 'N'){
                            CURRENT_STATE = 117;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 117:
                        if (c == 'L'){
                            CURRENT_STATE = 118;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 119:
                        if (c == 'A'){
                            CURRENT_STATE = 120;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;

                    case 120:
                        if (c == 'R'){
                            CURRENT_STATE = 121;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 121:
                        if (c == 'G'){
                            CURRENT_STATE = 122;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 122:
                        if (c == 'E'){
                            CURRENT_STATE = 115;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    
                        
                    //L_EFF
                    case 123:
                        if (c == 'A'){
                            CURRENT_STATE = 124;
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 124:
                        if (c == 'L'){
                            CURRENT_STATE = 125;
                            } 
                        else if (c== 'G'){
                            CURRENT_STATE = 125;
                        }
                        
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    case 125:
                        if (c == ' '){
                            Goback();
                            return new Token(TokenTypes.L_EFF);
                            }                 
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        //break;
                        //ASSIGN
                    case 126:
                        if (c == ':'){
                            CURRENT_STATE = 126;
                            } 
                        else if (c== ' '){
                            Goback();
                            return new Token(TokenTypes.ASSIGN);
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                        
                    //NOT
                     case 127:
                        if (c == 'T'){
                            CURRENT_STATE = 127;
                            
                        }   
                        else if (c=='R'){
                            CURRENT_STATE = 129;
                        }
                        else if (c==' '){
                            Goback();
                            return new Token(TokenTypes.NOT);
                           
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                    //G_LOG 
                        
                      case 128:
                        if (c == 'D'){
                            CURRENT_STATE = 128;
                            
                        }    
                        else if (c==' '){
                            Goback();
                            return new Token(TokenTypes.G_LOG);
                           
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                        
                        //L_LOG
                        case 129:
                        if (c == 'R'){
                            CURRENT_STATE = 129;
                            
                        }    
                        else if (c==' '|| c==';'){
                            Goback();
                            return new Token(TokenTypes.L_LOG);
                           
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                        
                        //S_ARRAY EXTENSION
                        
                        case 130:
                        if (IsDigit(c)){
                            CURRENT_STATE = 130;
                            
                        }    
                        else if (c==']'){
                            CURRENT_STATE = 131;
                        
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                        
                        case 131:
                        if (c == '['){
                            CURRENT_STATE = 132;
                            
                        }    
                        else if (c==' '|| c==';'){
                            Goback();
                            return new Token(TokenTypes.S_ARRAY);
                           
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                        
                        case 132:
                        if (IsDigit(c)){
                            CURRENT_STATE = 132;
                            
                        }    
                        else if (c==']'){
                           CURRENT_STATE = 133;
                           
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        break;
                        case 133:
                        if (c == ' ' || c == ';'){
                            Goback();
                            return new Token(TokenTypes.M_ARRAY);
                            
                        }    
                       
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        //break;
                        
                        case 134:
                        if (c==' '){
                            Goback();
                            return new Token(TokenTypes.LEFTBRACE);
                            
                        }
                        
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        case 135:
                        if (IsDigit(c) || IsAlpha(c)){
                            Goback();
                            return new Token(TokenTypes.RIGHTBRACE);
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        //ops
                        
                         case 136:
                        if (c== ' '){
                            Goback();
                            return new Token(TokenTypes.ADDSUBOP);
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        case 137:
                        if (c== ' '){
                            Goback();
                            return new Token(TokenTypes.MULDIVOP);
                        }
                            else{ 
                                return new Token(TokenTypes.ERROR);
                            }
                        //id ext
                         case 138:
                        if (AlphaOrDigit(c)){
                            CURRENT_STATE = 138;
                            //return new Token(TokenTypes.MULDIVOP);
                        }
                       
                            else{ 
                                return new Token(TokenTypes.ID);
                            }
                        //comment
                        case 139:
                        if (AlphaOrDigit(c)){
                            CURRENT_STATE = 139;
                        }   
                        
                        else if (c==' '){
                            CURRENT_STATE = 140;
                        }
                            else{ 
                                return new Token(TokenTypes.ID);
                            }
                       
                        
                        case 140:
                        if (AlphaOrDigit(c)){
                            return new Token(TokenTypes.COMMENT);
                        }
                       
                            else{ 
                                return new Token(TokenTypes.ID);
                            }















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

    private boolean parseInt(char c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
