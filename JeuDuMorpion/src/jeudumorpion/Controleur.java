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
import jeudumorpion.modele.Signe;
import jeudumorpion.utilitaires.Message;
import jeudumorpion.utilitaires.Actions;
import jeudumorpion.utilitaires.MessageCase;
import jeudumorpion.utilitaires.MessageCreation;
import java.util.ArrayList;
/**
 *
 * @author chapellr
 */
public class Controleur implements Observer{
   //private Grille grille = new Grille();
   private ArrayList<Joueur> joueurs = new ArrayList<>();
   private VueGrille vueGrille;
   private VueSelection vueSelection;
   private VueSpecification vueSpe;
   private Grille grille;
   
   public Controleur(){
       
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
            if(((Message) arg).getAction()== Actions.TABLEAU){
//                vueTab = new VueTableau();
            }
            
        }
        if (arg instanceof MessageCreation){
            if(((MessageCreation) arg).getAction()== Actions.VALIDER_NBJOUEUR){ // creer une grille de la bonne taille vu le nombre de joueur
                //vueCreationJoueur = new vueCrationJoueur(arg.getNbJoeur);
//                Ajoute tout les joueurs dans l'arraylist
                
//                puis après création de la grille avec tout de bon

//          if(((MessageCreation) arg.getAction()==Actions.VALIDER_CRÉATION_PARTIE){

//                vueCration.afficher
//                vueGrille=new VueGrille(((MessageCreation) arg).getNbJoueur()); 
//                vueGrille.afficher();
//                vueGrille.addObserver(this);
//            }
                
            }
        }
        if (arg instanceof MessageCase){
            if(((MessageCase) arg).getAction()==Actions.COCHER_CASE){
                int x =((MessageCase) arg).getX();
                int y =((MessageCase) arg).getY();
                /*Joueur courant que le morpion dois connaitre pour changer l'état case avec le signe*/
//                this.grille.getCase(x, y).setEtat_case();
                this.grille.addCaseCoché(this.grille.getCase(x, y));
            }
        }
        /*
        faire le close pour tout fermer
        faire les different Action pour tout les radio bouton des choix du nombre de perso
        
        Faire un quitter
        Et regarder tout les boutons à faire.
        */
        
    }
   
    
    
}
