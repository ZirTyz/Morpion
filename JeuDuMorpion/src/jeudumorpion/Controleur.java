/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion;


import java.awt.Color;
import java.awt.color.ColorSpace;
import jeudumorpion.modele.Case;
import jeudumorpion.modele.Joueur;
import jeudumorpion.Vues.VueGrille;
import java.util.Observable;
import java.util.Observer;
import jeudumorpion.Vues.VueAccueil;
import jeudumorpion.Vues.VueSpecificationNbJoueurs;
import jeudumorpion.modele.Grille;
import jeudumorpion.modele.Signe;
import jeudumorpion.utilitaires.Message;
import jeudumorpion.utilitaires.Actions;
import jeudumorpion.utilitaires.MessageCase;
import jeudumorpion.utilitaires.MessageCreation;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.text.StyledEditorKit;
import jeudumorpion.Vues.RegleDuJeu;
import jeudumorpion.Vues.VueAide;
import jeudumorpion.Vues.VueInformationsJoueurs;
import jeudumorpion.Vues.VueTableau;
import jeudumorpion.Vues.popUpDuel;
import jeudumorpion.Vues.popUpPartie;
import jeudumorpion.Vues.popUpTournois;
import jeudumorpion.utilitaires.MessageInfosJoueurs;
/**
 *
 * @author chapellr
 */
public class Controleur implements Observer{

    private ArrayList<Joueur> duel = new ArrayList<>();

   private ArrayList<Joueur> joueurs = new ArrayList<>();
   private ArrayList<Joueur> joueursScore = new ArrayList<>();
   private VueGrille vueGrille;
   private VueAccueil vueAccueil;
   private VueSpecificationNbJoueurs vueSpe;
   private VueTableau tableau;
   private Grille grille = new Grille();
   private int nbCaseCoche=0;
   private Joueur joueurCourant; // joueur qui joue
   private Joueur joueurAttaquant; //joueur qui commence
   private popUpPartie victoire;
   private popUpDuel victoireDuel;
   private popUpTournois victoireTourn;
   private VueInformationsJoueurs vueInfo;
   private int nbj;
   private int tousJoueurs =0;
   private int jChoisie =0;
   private int joueursPatieCourante =0;
   private boolean revanche = false;
   private Color fond;
   private int nbMatch=0;
   private RegleDuJeu regle;
   private VueAide aide;
   public Controleur(){
       //fond = new Color(179, 204, 255);
       
       vueAccueil=new VueAccueil();
       setFond(new Color(77, 149, 145));
       vueAccueil.setBackgroundColorAcceuil(fond);
       vueAccueil.afficher();
       vueAccueil.addObserver(this);
       aide = new VueAide();
       aide.afficher();
       aide.addObserver(this);
   }
    @Override
    public void update(Observable arg0, Object arg) {
        if (arg instanceof Message){
            Message message = (Message) arg;
            
            
            /****************   Procédure de création de partie   ****************/
            if (((Message) arg).getAction()== Actions.NEWPARTIE){
                vueSpe = new VueSpecificationNbJoueurs(fond);
                vueSpe.afficher();
                vueSpe.addObserver(this);
                vueAccueil.fermer();
            }
            
            
            /****************   Procédure de création de tableau Score   ****************/
            if(((Message) arg).getAction()== Actions.TABLEAU){
                tableau = new VueTableau(joueurs, fond);
                tableau.afficher();

            }
            
            
            /****************   Retour à l'acceuil   ****************/
            if(((Message) arg).getAction()== Actions.ACCUEIL){
               vueAccueil.afficher();
               vueAccueil.addObserver(this);
               vueGrille.fermer();
            }
            
            
            /****************   ???   ****************/
            if(((Message) arg).getAction()== Actions.TOUR_SUIVANT){
                
            }
            
            
            /****************   Procédure pour recommencer un DUEL   ****************/
            if(((Message) arg).getAction()== Actions.REMATCH){
                
                
                duel.set(0, duel.get(1));
                duel.set(1, joueurCourant);
                joueurAttaquant = duel.get(0);
                resetGrilles();

                this.victoireDuel.fermer();

                
            }
            
            
            /****************   Changement de match dans un CHAMPIONNAT   ****************/
            if(((Message) arg).getAction()== Actions.MATCHSUIVANT){
                if(revanche){     
                    revanche =false;
                    this.tournois(joueurs);
                    resetGrilles();
                    vueGrille.setAffrontement(duel.get(0), duel.get(1));
                    victoireTourn.fermer();
               }
                else {
                    revanche= true;
                    duel.set(0, duel.get(1));
                    duel.set(1, joueurCourant);
                    joueurAttaquant = duel.get(0);
                    resetGrilles();
                    victoireTourn.fermer();
                
                    
                }
            }
            
            
            /****************   Changement du type de joueur ciblé   ****************/
            if(message.getAction()== Actions.COLOR_COLOREE){
                //fond = new Color(179, 204, 255);
                setFond(new Color(239, 125, 49));
                vueAccueil.setBackgroundColorAcceuil(fond);
                vueAccueil.getSombre().setEnabled(true);
                vueAccueil.getColore().setEnabled(false);
                
            }
            
            if(message.getAction()== Actions.COLOR_SOMBRE){
                
                setFond(new Color(77, 149, 145));
                vueAccueil.setBackgroundColorAcceuil(fond);
                vueAccueil.getSombre().setEnabled(false);
                vueAccueil.getColore().setEnabled(true);
                
            }
            /************************************************************************/

            if(message.getAction()== Actions.REGLE){
                regle = new RegleDuJeu();
                regle.afficher();
                vueAccueil.fermer();
            }

            
            

            
        }
        if (arg instanceof MessageCreation){
            
            
            /****************   Validation du nombre de joueur d'un tournois   ****************/
            if(((MessageCreation) arg).getAction()== Actions.VALIDER_NBJOUEUR){ 

                
                nbj = ((MessageCreation) arg).getNbJoueur();
                vueInfo = new VueInformationsJoueurs(((MessageCreation) arg).getNbJoueur(), fond);
                vueInfo.afficher();
                vueInfo.addObserver(this);
                vueSpe.fermer();
                
            }
        }
        
        
        if (arg instanceof MessageInfosJoueurs){
            
            /***************************************************************************************/
            /****************   Procédure d'inscription des joueurs à un tournois   ****************/
            /***************************************************************************************/
            // + création du premier match
            /***************************************************************************************/
            if(((MessageInfosJoueurs) arg).getAction() == Actions.INSCRIPTION_JOUEUR){
            for (String nomJ : ((MessageInfosJoueurs) arg).getPseudoJoueur().keySet()){
                Joueur j = new Joueur(nomJ);
                joueurs.add(j);
                joueursScore.add(j);
            }
            Collections.shuffle(joueurs);
            this.initTournois(joueurs);
            this.tournois(joueurs);
            
            vueGrille = new VueGrille(duel.get(0), duel.get(1), fond);
            vueGrille.tableauVictoire(joueursScore, nbj);
            
            this.vueGrille.joueurActif(joueurCourant);
            vueGrille.afficher();
            vueGrille.addObserver(this);
            vueInfo.fermer();
            
            
            }
        }
        
        
        if (arg instanceof MessageCase){
            
            
            /****************   Procédure de Cocher une Case   ****************/
            if(((MessageCase) arg).getAction()==Actions.COCHER_CASE){
               
                int x =((MessageCase) arg).getX();
                int y =((MessageCase) arg).getY();
                /*Joueur courant que le morpion dois connaitre pour changer l'état case avec le signe*/
//                this.grille.getCase(x, y).setEtat_case();

                        
                    System.out.println("La case est cochée");
                    this.grille.getCase(x, y).setJoueurAyantCoché(joueurCourant);
                    this.grille.getCase(x, y).setEtat_case(joueurCourant.getSigne());

                

                if(joueurCourant.getSigne().equals(Signe.X)){
                    this.vueGrille.getBoutonsCase().get(((MessageCase) arg).getNumBtn()).setIcon(new ImageIcon( System.getProperty("user.dir")+"/src/jeudumorpion/Vues/imagesJoueurs/delete-462216_960_720_1.png"));
                }
                else if (joueurCourant.getSigne().equals(Signe.O)) {;
                    this.vueGrille.getBoutonsCase().get(((MessageCase) arg).getNumBtn()).setIcon(new ImageIcon( System.getProperty("user.dir")+"/src/jeudumorpion/Vues/imagesJoueurs/1024px-Cercle_noir_100%.svg_1.png"));;
                }


                    setNbCaseCoche(getNbCaseCoche()+1);
                
                if (getNbCaseCoche()==9){
                    vueGrille.deleteObserver(this);
                    if(joueurs.size()==2){
                        
                        // VICTOIRE //
                        if(grille.Gagnant(joueurCourant.getSigne())){
                            // Ajout des points
                            if (joueurAttaquant==joueurCourant){
                                joueurCourant.addPoint(2);
                            }
                            else joueurCourant.addPoint(3);
                            victoireDuel = new popUpDuel(joueurCourant,true);
                            victoireDuel.afficher();
                            victoireDuel.addObserver(this);
                            
                            
                            
                            

    
                        } else { // EGALITE DUEL // 
                            duel.get(0).addPoint(1);
                            duel.get(1).addPoint(1);
                            vueGrille.tableauVictoire(joueursScore, nbj);
                            victoireDuel = new popUpDuel(joueurCourant,false);
                            victoireDuel.afficher();
                            victoireDuel.addObserver(this);
                        }
                    } else { // EGALITE TOURNOIS // 
                        duel.get(0).addPoint(1);
                        duel.get(1).addPoint(1);
                        vueGrille.tableauVictoire(joueursScore, nbj);
                        victoireTourn = new popUpTournois(joueurCourant,false);
                        victoireTourn.afficher();
                        victoireTourn.addObserver(this);
                        
                    }
                }
                else if(getNbCaseCoche()>=5 && getNbCaseCoche()<9){
                    if(grille.Gagnant(joueurCourant.getSigne())){
                       // joueurs.replace(joueurCourant, joueurs.get(joueurCourant)+3);
//                       vueGrille.fermer();
                        vueGrille.deleteObserver(this);
                        if(!revanche){
                            if(nbj == 2){
                                // Ajout des points
                                if (joueurAttaquant==joueurCourant){
                                joueurCourant.addPoint(2);
                                }
                                else joueurCourant.addPoint(3);
                                victoireDuel = new popUpDuel(joueurCourant,true);
                                victoireDuel.afficher();
                                victoireDuel.addObserver(this);
                                vueGrille.tableauVictoire(joueursScore, nbj);
                                
                                
                                
                            
                            }
                            else{
                                if (joueurAttaquant==joueurCourant){
                                joueurCourant.addPoint(2);
                                }
                                else joueurCourant.addPoint(3);
                                System.out.println(joueurCourant.getPoints());
                                for (int i = 0;i<joueurs.size();i++)
                                    System.out.println(joueurs.get(i).getPoints());
                                victoireTourn = new popUpTournois(joueurCourant,true);
                                victoireTourn.afficher();
                                victoireTourn.addObserver(this);
                                vueGrille.tableauVictoire(joueursScore, nbj);
                            }
                        }
                        else{
                            // Ajout des points
                            if (joueurAttaquant==joueurCourant){
                                joueurCourant.addPoint(2);
                            }
                            else joueurCourant.addPoint(3);
                        victoire = new popUpPartie(joueurCourant,true);
                        victoire.afficher();
                        victoire.addObserver(this);
                        vueGrille.tableauVictoire(joueursScore, nbj);


                        
                        }    

                    }else{
                        duel.set(0, duel.get(1));
                        duel.set(1, joueurCourant);
                        setJoueurCourant(duel.get(0));
                        this.vueGrille.joueurActif(joueurCourant);

                    }
                        
                     
                }
               else{

                    duel.set(0, duel.get(1));
                    duel.set(1, joueurCourant);
                    setJoueurCourant(duel.get(0));
                    this.vueGrille.joueurActif(joueurCourant);
                    vueGrille.tableauVictoire(joueursScore, nbj);
                }
            

            }
            
        }
        

        
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
   
    public void initTournois(ArrayList<Joueur> joueurs){
//  
        for(int y=0;y<joueurs.size()-1;y++){
            for(int x = 1;y+x<joueurs.size();x++){
                joueurs.get(y).ajouterJ(joueurs.get(y+x));
                System.out.println("jeudumorpion.Controleur.initTournois()");
            }
        }
    }
    
    public void melangeTournois(ArrayList<Joueur> joueurs){
        for(int x = 0;x<joueurs.size()-1;x++){
            joueurs.get(x).Shuffle();
        }
       
    }
    public void tournois(ArrayList<Joueur> j){ // problème !!!
        System.out.println(j.get(0).getJoueurA().size());
        if (j.get(0).getJoueurA().size()!=0){
            
            if (jChoisie!=nbj){
                if(!(j.get(jChoisie).getJoueurA().isEmpty())){
                    duel.clear();
                    duel.add(j.get(jChoisie));
                    duel.add(j.get(jChoisie).getJoueurA().get(0));
                    duel.get(0).setSigne(Signe.X);
                    duel.get(1).setSigne(Signe.O);
                    joueurCourant =duel.get(0);
                    joueurAttaquant =duel.get(0);
                    j.get(jChoisie).getJoueurA().remove(0);
                    System.out.println(duel.get(0).getPseudo());
                    jChoisie=jChoisie+1;
                    
                } else {
                    jChoisie=jChoisie+1;
                    this.tournois(j);

                }
            }else {
                jChoisie=0;
                this.tournois(j);

            }
        }
        else {
            System.out.println("tout les matchs sont fait");
            tableau = new VueTableau(j, fond);
            tableau.afficher();
            
        }
        
    }
    
    public void resetGrilles(){
                setJoueurCourant(duel.get(0));
                this.grille.reset();
                this.vueGrille.reset();
                this.vueGrille.joueurActif(joueurCourant);
                this.vueGrille.addObserver(this);
                nbCaseCoche=0;
    }
//        ajouter les points et enfin travailler les vues...

    /**
     * @param fond the fond to set
     */
    public void setFond(Color fond) {
        this.fond = fond;
    }
        
    
    
}
