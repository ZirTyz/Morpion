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
import jeudumorpion.Vues.VueAcceuil;
import jeudumorpion.Vues.VueSpecificationNbJoueurs;
import jeudumorpion.modele.Grille;
import jeudumorpion.modele.Signe;
import jeudumorpion.utilitaires.Message;
import jeudumorpion.utilitaires.Actions;
import jeudumorpion.utilitaires.MessageCase;
import jeudumorpion.utilitaires.MessageCreation;
import java.util.ArrayList;
import jeudumorpion.Vues.VueInformationsJoueurs;
import jeudumorpion.Vues.popUpPartie;
import jeudumorpion.utilitaires.MessageInfosJoueurs;
/**
 *
 * @author chapellr
 */
public class Controleur implements Observer{
   //private Grille grille = new Grille();
    private ArrayList<Joueur> duel = new ArrayList<>();
   //private HashMap<Joueur,Integer> joueurs = new HashMap<>();
   private ArrayList<Joueur> joueurs = new ArrayList<>();
   private VueGrille vueGrille;
   private VueAcceuil vueSelection;
   private VueSpecificationNbJoueurs vueSpe;
   private Grille grille = new Grille();
   private int nbCaseCoche=0;
   private Joueur joueurCourant;
   private popUpPartie victoire;
   private VueInformationsJoueurs vueInfo;
   private int nbj;
   
   public Controleur(){
       
       vueSelection=new VueAcceuil();
       vueSelection.afficher();
       vueSelection.addObserver(this);
//       Joueur a = new Joueur("Jacques");
//       Joueur b = new Joueur("Frnaklin");
//       vueGrille = new VueGrille(a, b);
//       vueGrille.afficher();
//       vueSpe = new VueSpecification();
//       vueSpe.afficher();
                //vueInfo = new VueInformations(nbj);
                //vueInfo.afficher();
   }
    @Override
    public void update(Observable arg0, Object arg) {
        if (arg instanceof Message){
            Message message = (Message) arg;
            if (((Message) arg).getAction()== Actions.NEWPARTIE){
                vueSelection.fermer();
                vueSpe = new VueSpecificationNbJoueurs();
                vueSpe.afficher();
                vueSpe.addObserver(this);
            }
            if(((Message) arg).getAction()== Actions.TABLEAU){
//                vueTab = new VueTableau();
            }
            
        }
        if (arg instanceof MessageCreation){
            if(((MessageCreation) arg).getAction()== Actions.VALIDER_NBJOUEUR){ 
                //vueCreationJoueur = new vueCrationJoueur(arg.getNbJoeur);
//                    for (int x=0; x<((MessageCreation) arg).getNbJoueur();x++){
//                        joueurs.add(new Joueur("J"+(x+1)));
//                    }
//                    duel.add(joueurs.get(0));
//                    duel.get(0).setSigne(Signe.X);
//                    duel.add(joueurs.get(1));
//                    duel.get(1).setSigne(Signe.O);
//
//                    setJoueurCourant(duel.get(0));
//                    vueGrille = new VueGrille(duel.get(0), duel.get(1));
//                    vueGrille.afficher();
//                    vueGrille.addObserver(this);
                vueSpe.fermer();
                nbj = ((MessageCreation) arg).getNbJoueur();
                vueInfo = new VueInformationsJoueurs(((MessageCreation) arg).getNbJoueur());
                vueInfo.afficher();
                vueInfo.addObserver(this);

                
            }
        }
        if (arg instanceof MessageInfosJoueurs){
            if(((MessageInfosJoueurs) arg).getAction() == Actions.INSCRIPTION_JOUEUR){
                for (int x = 1; x <= nbj; x++){
                Joueur j = new Joueur(((MessageInfosJoueurs) arg).getPseudo());
                duel.add(j);
            }
            vueGrille = new VueGrille(duel.get(0), duel.get(1));
            vueGrille.afficher();
            vueGrille.addObserver(this);
            vueInfo.fermer();
            }
        }
        if (arg instanceof MessageCase){
            if(((MessageCase) arg).getAction()==Actions.COCHER_CASE){
                int x =((MessageCase) arg).getX();
                int y =((MessageCase) arg).getY();
                /*Joueur courant que le morpion dois connaitre pour changer l'état case avec le signe*/
//                this.grille.getCase(x, y).setEtat_case();
                this.grille.getCase(x, y).setJoueurAyantCoché(joueurCourant);
                this.grille.getCase(x, y).setEtat_case(joueurCourant.getSigne());
                this.grille.addCaseCoché(this.grille.getCase(x, y));                
                    setNbCaseCoche(getNbCaseCoche()+1);
                if (getNbCaseCoche()==9){
                    if(grille.Gagnant(joueurCourant.getSigne())){
                       // joueurs.replace(joueurCourant, joueurs.get(joueurCourant)+3); Augmentation des points.
//                       vueGrille.fermer();
                    } else {for (int i=0;i<duel.size();i++){
                       // joueurs.replace(duel.get(i), joueurs.get(duel.get(i))+1);
//                       vueGrille.fermer();
                    }}
                }
                else if(getNbCaseCoche()>=5 && getNbCaseCoche()<9){
                    if(grille.Gagnant(joueurCourant.getSigne())){
                       // joueurs.replace(joueurCourant, joueurs.get(joueurCourant)+3);
//                       vueGrille.fermer();
                        victoire = new popUpPartie(joueurCourant);
                        victoire.afficher();
                    }else{
                    duel.set(0, duel.get(1));
                    duel.set(1, joueurCourant);
                    setJoueurCourant(duel.get(0));
                    }
                        
                     
                } else{
                    duel.set(0, duel.get(1));
                    duel.set(1, joueurCourant);
                    setJoueurCourant(duel.get(0));
                }
            }
        }
        /*
        faire le close pour tout fermer
        faire les different Action pour tout les radio bouton des choix du nombre de perso
        
        Faire un quitter
        Et regarder tout les boutons à faire.
        */
        
    }

    /**
     * @return the nbCaseCoche
     */
    public int getNbCaseCoche() {
        return nbCaseCoche;
    }

    /**
     * @param nbCaseCoche the nbCaseCoche to set
     */
    public void setNbCaseCoche(int nbCaseCoche) {
        this.nbCaseCoche = nbCaseCoche;
    }

    /**
     * @return the joueurCourant
     */
    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    /**
     * @param joueurCourant the joueurCourant to set
     */
    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }
   
    
    
}
