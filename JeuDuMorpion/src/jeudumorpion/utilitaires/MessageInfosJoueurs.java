/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion.utilitaires;

import java.awt.Color;

/**
 *
 * @author rose
 */
public class MessageInfosJoueurs {
    private Actions action;
    private String pseudo;
    private Color couleur;
    
    public MessageInfosJoueurs(Actions action, String pseudo, Color couleur){
        this.action = action;
        this.pseudo = pseudo;
        this.couleur = couleur;
    }

    /**
     * @return the action
     */
    public Actions getAction() {
        return action;
    }

    /**
     * @return the pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * @return the couleur
     */
    public Color getCouleur() {
        return couleur;
    }
}
