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
    private String pseudo1;
    private String pseudo2;
    private String pseudo3;
    private String pseudo4;
    private String pseudo5;
    private String pseudo6;
    private String pseudo7;
    private String pseudo8;
    private Color couleur;
    
    public MessageInfosJoueurs(Actions action, String pseudo1, String pseudo2, String pseudo3, String pseudo4, String pseudo5, String pseudo6, String pseudo7, String pseudo8, Color couleur){
        this.action = action;
        this.pseudo1 = pseudo1;
        this.pseudo2 = pseudo2;
        this.pseudo3 = pseudo3;
        this.pseudo4 = pseudo4;
        this.pseudo5 = pseudo5;
        this.pseudo6 = pseudo6;
        this.pseudo7 = pseudo7;
        this.pseudo8 = pseudo8;
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
        return getPseudo1();
    }

    /**
     * @return the couleur
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * @return the pseudo1
     */
    public String getPseudo1() {
        return pseudo1;
    }

    /**
     * @return the pseudo2
     */
    public String getPseudo2() {
        return pseudo2;
    }

    /**
     * @return the pseudo3
     */
    public String getPseudo3() {
        return pseudo3;
    }

    /**
     * @return the pseudo4
     */
    public String getPseudo4() {
        return pseudo4;
    }

    /**
     * @return the pseudo5
     */
    public String getPseudo5() {
        return pseudo5;
    }

    /**
     * @return the pseudo6
     */
    public String getPseudo6() {
        return pseudo6;
    }

    /**
     * @return the pseudo7
     */
    public String getPseudo7() {
        return pseudo7;
    }

    /**
     * @return the pseudo8
     */
    public String getPseudo8() {
        return pseudo8;
    }
}
