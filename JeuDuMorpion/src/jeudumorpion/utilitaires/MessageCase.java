/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion.utilitaires;

import jeudumorpion.modele.Joueur;

/**
 *
 * @author mirasl
 */
public class MessageCase extends Message{
    private final Integer y;
    private final Integer x;
//    private final Joueur joueurCourant;
    public MessageCase(Actions action, int y,int x/*,Joueur joueur*/){
        super(action);
        this.y=y;
        this.x=x;
//        this.joueurCourant=joueur;
    }


    /**
     * @return the y
     */
    public Integer getY() {
        return y;
    }

    /**
     * @return the x
     */
    public Integer getX() {
        return x;
    }

}
