/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion;


import jeudumorpion.modele.Case;
import jeudumorpion.modele.Joueur;
import jeudumorpion.Vues.VueGrille;
import java.util.Observable;
import java.util.Observer;
import jeudumorpion.Vues.VueSelection;
import jeudumorpion.Vues.VueSpecification;
import jeudumorpion.modele.Grille;
import jeudumorpion.utilitaires.Message;
import jeudumorpion.utilitaires.Actions;

/**
 *
 * @author chapellr
 */
public class Controleur implements Observer{
   //private Grille grille = new Grille();
   private Joueur joueurs;
   private VueGrille vueGrille;
   private VueSelection vueSelection;
   private VueSpecification vueSpe;
   
   public Controleur(){
       vueGrille=new VueGrille(); //A enlever et mettre dans le update
       vueGrille.afficher();
       vueSelection=new VueSelection();
       vueSelection.afficher();
       //vueSpe = new VueSpecification();
       //vueSpe.afficher();
   }
    @Override
    public void update(Observable arg0, Object arg) {
        if (arg instanceof Message){
            Message message = (Message) arg;
            if (((Message) arg).getAction()== Actions.NEWPARTIE){
                vueSpe = new VueSpecification();
                vueSpe.afficher();
                //vueSpe.addObserver(this);
            }
        }
        
    }
   
    
    
}
