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
public class TokenIdentifier extends Token{
    public String idName = "";

    public TokenIdentifier(String name){
        super(TokenTypes.ID);
        idName = name;
    }

}
