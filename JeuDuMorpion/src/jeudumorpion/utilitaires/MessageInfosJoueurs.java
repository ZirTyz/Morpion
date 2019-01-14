/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion.utilitaires;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author rose
 */
public class MessageInfosJoueurs extends Message{
    private Color couleur;
    private HashMap<String, Color> pseudoJoueur;
    
    public MessageInfosJoueurs(Actions action, HashMap<String, Color> pseudoJoueur){
        super(action);
        this.couleur = couleur;
        this.pseudoJoueur = pseudoJoueur;
    }

    /**
     * @return the pseudoJoueur
     */
    public HashMap<String, Color> getPseudoJoueur() {
        return pseudoJoueur;
    }


}
