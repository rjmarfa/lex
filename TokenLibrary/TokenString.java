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
public class TokenString extends Token{

    public String literal = "";

    public TokenString(String name){
        super(TokenTypes.STRINGLITERAL);
        literal = name;
    }

}
