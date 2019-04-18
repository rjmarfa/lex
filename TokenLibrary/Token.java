/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TokenLibrary;

/**
 *
 * @author RJ
 */
public class Token {
    public TokenTypes tokenType;

    public Token(TokenTypes tokenType){
        this.tokenType = tokenType;
    }

    public TokenTypes getTokenType(){
        return tokenType;
    }

}