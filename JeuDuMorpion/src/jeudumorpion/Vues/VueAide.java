/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion.Vues;

import java.util.Observable;
import javax.swing.JFrame;
import jeudumorpion.Controleur;

/**
 *
 * @author Lucas
 */
public class VueAide extends Observable{
    private JFrame frame;
    
    public VueAide(){
        frame = new JFrame("AIDE pour jouer au Morpion");
        frame.setSize(400, 800);  
    }

    public void afficher() {
        this.frame.setVisible(true);
    }

    
    
}
