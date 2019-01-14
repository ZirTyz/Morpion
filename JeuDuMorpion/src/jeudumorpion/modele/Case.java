/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion.modele;

import jeudumorpion.modele.Joueur;

/**
 *
 * @author chapellr
 */
public class Case implements Comparable<Case>{

    private int numCase;
    private Joueur joueurAyantCoché;
    private Signe etat_case = Signe.NULL;
    
    public Case(int c){
        this.numCase= c;
        this.joueurAyantCoché = null;
    }
    
        public Case(int c, Joueur j){
        this.numCase= c;
        this.joueurAyantCoché = j;
    }

    /**
     * @return the numCase
     */
    public int getNumCase() {
        return numCase;
    }

    /**
     * @return the joueurAyantCoché
     */
    public Joueur getJoueurAyantCoché() {
        return joueurAyantCoché;
    }

    /**
     * @param joueurAyantCoché the joueurAyantCoché to set
     */
    public void setJoueurAyantCoché(Joueur joueurAyantCoché) {
        this.joueurAyantCoché = joueurAyantCoché;
    }
    /**
     * @param numCase the numCase to set
     */
    public void setNumCase(int numCase) {
        this.numCase = numCase;
    }

    /**
     * @return the etat_case
     */
    public Signe getEtat_case() {
        return etat_case;
    }

    /**
     * @param etat_case the etat_case to set
     */
    public void setEtat_case(Signe etat_case) {
        this.etat_case = etat_case;
    }

    @Override
    public int compareTo(Case arg0) {
        if (this.numCase<arg0.numCase){
            return -1;
        }
        else if (this.numCase==arg0.numCase){
            return 0;
        }
        else return 1;
    }
}
