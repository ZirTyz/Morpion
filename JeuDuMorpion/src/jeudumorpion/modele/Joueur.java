/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion.modele;
import java.awt.Color;
import java.util.ArrayList;
/**
 *
 * @author chapellr
 */
public class Joueur {
    private ArrayList<Case> casesCochée = new ArrayList<>();
    private Signe signe;
    private String pseudo;
    private int points;

    
    public Joueur(String pseudo){
        this.casesCochée = null;
        this.signe = null;
        this.pseudo = pseudo;
        this.points = 0;
    }

    
    public void addCase(Case c){
        if (this.casesCochée.size() < 6) {
           this.casesCochée.add(c); 
        }
        else {
            System.out.println("Vous ne pouvez pas cocher une case de plus.");
        }
        
    }
    
    
    /**
     * @return the signe
     */
    public Signe getSigne() {
        return signe;
    }

    /**
     * @param signe the signe to set
     */
    public void setSigne(Signe signe) {

        this.signe = signe;
    }

    public String signeString(Signe s) {
        if (s == s.O){
            return "O";
        }
        else{
            return "X";
        }
    }

    
    /**
     * @return the pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }
    
    
}
