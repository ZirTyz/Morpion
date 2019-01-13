/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion.Vues;

import java.util.Comparator;
import jeudumorpion.modele.Joueur;

/**
 *
 * @author rose
 */
public class ComparateurPoint implements Comparator<Joueur>{

    @Override
    public int compare(Joueur t, Joueur t1) {
        if(t.getPoints() > t1.getPoints()){
            return 1;
        }
        else if (t.getPoints() < t1.getPoints()){
            return -1;
        }
        else{
            return 0;
        }
    }
    
}
