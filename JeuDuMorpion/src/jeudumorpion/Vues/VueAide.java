/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion.Vues;

import javax.swing.JFrame;
import jeudumorpion.Controleur;

/**
 *
 * @author Lucas
 */
public class VueAide {
    private JFrame frame;
    
    public VueAide(){
        frame = new JFrame("AIDE pour jouer au Morpion");
        frame.setSize(400, 800);  
    }

    public void afficher() {
        this.frame.setVisible(true);
    }

    public void addObserver(Controleur aThis) {
        this.addObserver(aThis);
    }
    
}
