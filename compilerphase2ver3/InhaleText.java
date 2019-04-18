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
import TokenLibrary.TokenTypes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

public class InhaleText{

    public static Hashtable<String, TokenTypes> ExhaleReserves(){
        Hashtable<String, TokenTypes> reservedWords = new  Hashtable<String, TokenTypes>();
                reservedWords.put("+", TokenTypes.ADDSUB);
		reservedWords.put("-", TokenTypes.ADDSUB);
		reservedWords.put("/", TokenTypes.MULDIV);
                reservedWords.put("//", TokenTypes.SCOMMENT);
		reservedWords.put("*", TokenTypes.MULDIV);
                
                reservedWords.put(">", TokenTypes.RELOP);
                reservedWords.put("<", TokenTypes.RELOP);
                reservedWords.put(">=", TokenTypes.RELOP);
                reservedWords.put("<=", TokenTypes.RELOP);
                reservedWords.put("==", TokenTypes.RELOP);
                reservedWords.put("!=", TokenTypes.RELOP);
                reservedWords.put("=", TokenTypes.ASSIGN);
                
                reservedWords.put("SEL", TokenTypes.SELECT);
                reservedWords.put("MON", TokenTypes.MONEY);
                reservedWords.put("CH", TokenTypes.CHANGE);
                
                reservedWords.put("STRING", TokenTypes.STRING);
                reservedWords.put("INT", TokenTypes.INTEGER);
                reservedWords.put("TRUE", TokenTypes.BOOLVALUE);
                reservedWords.put("FALSE", TokenTypes.BOOLVALUE);
                
                reservedWords.put("IF", TokenTypes.IFMAIN);
                reservedWords.put("ELIF", TokenTypes.IFMAIN);
                reservedWords.put("ELSE", TokenTypes.IFMAIN);
                reservedWords.put("LOOP", TokenTypes.LOOP);
                                
                reservedWords.put(";", TokenTypes.ENDLINE);
        
                reservedWords.put("MAIN", TokenTypes.START);
                reservedWords.put("END", TokenTypes.END);
               //reservedWords.put("$", TokenTypes.ID);
                
                
                reservedWords.put("(", TokenTypes.LPAR);
                reservedWords.put(")", TokenTypes.RPAR);
                reservedWords.put("{", TokenTypes.LCUR);
                reservedWords.put("}", TokenTypes.RCUR);
                
                //reservedWords.put("NOT", TokenTypes.NOT);
                
                reservedWords.put("AND", TokenTypes.G_LOG);
                reservedWords.put("NAND", TokenTypes.G_LOG);
                reservedWords.put("OR", TokenTypes.L_LOG);
                reservedWords.put("NOR", TokenTypes.L_LOG);
                reservedWords.put("XOR", TokenTypes.L_LOG);
                                
             

        return reservedWords;
    }

    public static PushbackInputStream ExhaleText(String pathDirectory){
        FileReader inputStream = null;
        FileWriter outputStream = null;

        PushbackInputStream fr = null;
        byte[] syntax;

        Path path = Paths.get(pathDirectory);
        try {
            syntax = Files.readAllBytes(path);
            ByteArrayInputStream array = new ByteArrayInputStream(syntax);
            PushbackInputStream push = new PushbackInputStream(array);

            return push;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}