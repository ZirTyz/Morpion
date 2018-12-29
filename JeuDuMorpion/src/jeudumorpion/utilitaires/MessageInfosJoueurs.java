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
public class MessageInfosJoueurs {
    private Actions action;
    private Color couleur;
    private HashMap<String, Color> pseudoJoueur;
    
    public MessageInfosJoueurs(Actions action, HashMap<String, Color> pseudoJoueur){
        this.action = action;
        this.couleur = couleur;
        this.pseudoJoueur = pseudoJoueur;
    }

    /**
     * @return the action
     */
    public Actions getAction() {
        return action;
    }

    /**
     * @return the couleur
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * @return the pseudoJoueur
     */
    public HashMap<String, Color> getPseudoJoueur() {
        return pseudoJoueur;
    }


}
