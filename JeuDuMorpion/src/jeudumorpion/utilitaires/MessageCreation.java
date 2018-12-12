/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion.utilitaires;

/**
 *
 * @author mirasl
 */
public class MessageCreation extends Message{
    private final Integer nbJoueur;
    
    
    public MessageCreation(Actions action, int nbJoueur){
        super(action);
        this.nbJoueur =nbJoueur;
    }

    /**
     * @return the nbJoueur
     */
    public Integer getNbJoueur() {
        return nbJoueur;
    }

}
