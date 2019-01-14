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
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import jeudumorpion.Vues.RegleDuJeu;
import jeudumorpion.Vues.VueInformationsJoueurs;
import jeudumorpion.Vues.popUpDuel;
import jeudumorpion.Vues.popUpPartie;
import jeudumorpion.Vues.popUpTournois;
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
   private VueAccueil vueSelection;
   private VueSpecificationNbJoueurs vueSpe;
   private Grille grille = new Grille();
   private int nbCaseCoche=0;
   private Joueur joueurCourant;
   private popUpPartie victoire;
   private popUpDuel victoire2;
   private popUpTournois victoireTourn;
   private VueInformationsJoueurs vueInfo;
   private int nbj;
   private int tousJoueurs =0;
   private int jChoisie =0;
   private int joueursPatieCourante =0;
   private int nbTours = 0;
   private Color fond;
   private int nbMatch=0;
   private RegleDuJeu regle;
   
   public Controleur(){
       //fond = new Color(179, 204, 255);
       
       vueSelection=new VueAccueil();
       setFond(new Color(77, 149, 145));
        vueSelection.setBackgroundColorAcceuil(fond);
       vueSelection.afficher();
       vueSelection.addObserver(this);
//       Joueur a = new Joueur("Jacques");
//       Joueur b = new Joueur("Frnaklin");
//       vueGrille = new VueGrille(a, b);
//       vueGrille.afficher();
//       vueSpe = new VueSpecification();
//       vueSpe.afficher();
                //vueInfo = new VueInformations(nbj);TABLEAU
                //vueInfo.afficher();
   }
    @Override
    public void update(Observable arg0, Object arg) {
        if (arg instanceof Message){
            Message message = (Message) arg;
            if (((Message) arg).getAction()== Actions.NEWPARTIE){
                
                vueSpe = new VueSpecificationNbJoueurs(fond);
                vueSpe.afficher();
                vueSpe.addObserver(this);
                vueSelection.fermer();
            }
            if(((Message) arg).getAction()== Actions.TABLEAU){
//                vueTab = new VueTableau();
            }
            if(((Message) arg).getAction()== Actions.ACCUEIL){
               vueSelection.afficher();
               vueSelection.addObserver(this);
            }
            
            if(((Message) arg).getAction()== Actions.TOUR_SUIVANT){
                
            }
            
            if(((Message) arg).getAction()== Actions.REMATCH){
                
                duel.set(0, duel.get(1));
                duel.set(1, joueurCourant);
                
                resetGrilles();

                this.victoire2.fermer();

                
            }
            
            if(((Message) arg).getAction()== Actions.MATCHSUIVANT){
//                if revanche non faite
                this.tournois(joueurs);
                resetGrilles();
            }
            if(message.getAction()== Actions.COLOR_COLOREE){
                //fond = new Color(179, 204, 255);
                setFond(new Color(239, 125, 49));
                vueSelection.setBackgroundColorAcceuil(fond);
                vueSelection.getSombre().setEnabled(true);
                vueSelection.getColore().setEnabled(false);
                
            }
            
            if(message.getAction()== Actions.COLOR_SOMBRE){
                
                setFond(new Color(77, 149, 145));
                vueSelection.setBackgroundColorAcceuil(fond);
                vueSelection.getSombre().setEnabled(false);
                vueSelection.getColore().setEnabled(true);
                
            }
            if(message.getAction()== Actions.REGLE){
                regle = new RegleDuJeu();
                regle.afficher();
                vueSelection.fermer();
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
                
                nbj = ((MessageCreation) arg).getNbJoueur();
                vueInfo = new VueInformationsJoueurs(((MessageCreation) arg).getNbJoueur(), fond);
                vueInfo.afficher();
                vueInfo.addObserver(this);
                vueSpe.fermer();
                
            }
        }
        if (arg instanceof MessageInfosJoueurs){
            if(((MessageInfosJoueurs) arg).getAction() == Actions.INSCRIPTION_JOUEUR){
            for (String nomJ : ((MessageInfosJoueurs) arg).getPseudoJoueur().keySet()){
                Joueur j = new Joueur(nomJ);
                joueurs.add(j);
            }
            this.initTournois(joueurs);
            this.tournois(joueurs);
//            duel.add(joueurs.get(tousJoueurs));
//            
//            //duel.get(joueursPatieCourante).setSigne(Signe.X);
//            duel.get(0).setSigne(Signe.X);
//            
//            duel.add(joueurs.get(tousJoueurs+1));
//            
////            duel.get(joueursPatieCourante+1).setSigne(Signe.O);
//            duel.get(1).setSigne(Signe.O);
//            
////            joueurCourant = duel.get(joueursPatieCourante);
//            joueurCourant =duel.get(0);
            
//            vueGrille = new VueGrille(duel.get(joueursPatieCourante), duel.get(joueursPatieCourante+1));
            vueGrille = new VueGrille(duel.get(0), duel.get(1), fond);
            
            this.vueGrille.joueurActif(joueurCourant);
            vueGrille.afficher();
            vueGrille.addObserver(this);
            vueInfo.fermer();
            nbTours = nbTours+1;
            
            }
        }
        
        if (arg instanceof MessageCase){
            if(((MessageCase) arg).getAction()==Actions.COCHER_CASE){
               
                int x =((MessageCase) arg).getX();
                int y =((MessageCase) arg).getY();
                /*Joueur courant que le morpion dois connaitre pour changer l'état case avec le signe*/
//                this.grille.getCase(x, y).setEtat_case();

                        
                    System.out.println("La case est cochée");
                    this.grille.getCase(x, y).setJoueurAyantCoché(joueurCourant);
                    this.grille.getCase(x, y).setEtat_case(joueurCourant.getSigne());
//                    this.grille.addCaseCoché(this.grille.getCase(x, y));

                
//                if(joueurCourant.getPseudo().equals(duel.get(joueursPatieCourante).getPseudo())){
                if(joueurCourant.getSigne().equals(Signe.X)){
                    this.vueGrille.getBoutonsCase().get(((MessageCase) arg).getNumBtn()).setIcon(new ImageIcon("/users/info/etu-s2/mirasl/Morpion/Morpion/JeuDuMorpion/src/jeudumorpion/Vues/imagesJoueurs/delete-462216_960_720_1.png"));
                }
//                else if (joueurCourant.getPseudo().equals(duel.get(joueursPatieCourante+1).getPseudo())) {;
                else if (joueurCourant.getSigne().equals(Signe.O)) {;
                    this.vueGrille.getBoutonsCase().get(((MessageCase) arg).getNumBtn()).setIcon(new ImageIcon("/users/info/etu-s2/mirasl/Morpion/Morpion/JeuDuMorpion/src/jeudumorpion/Vues/imagesJoueurs/1024px-Cercle_noir_100%.svg_1.png"));;
                }
//                if(joueurCourant.getPseudo().equals(duel.get(0).getPseudo())){
//                    this.vueGrille.getBoutonsCase().get(((MessageCase) arg).getNumBtn()).setIcon(new ImageIcon("/home/rose/NetBeansProjects/Morp/morpi/Morpion/JeuDuMorpion/src/jeudumorpion/Vues/imagesJoueurs/delete-462216_960_720_1.png"));
//                }
////                else if (joueurCourant.getPseudo().equals(duel.get(joueursPatieCourante+1).getPseudo())) {;
//                else if (joueurCourant.getPseudo().equals(duel.get(1).getPseudo())) {;
//                    this.vueGrille.getBoutonsCase().get(((MessageCase) arg).getNumBtn()).setIcon(new ImageIcon("/home/rose/NetBeansProjects/Morp/morpi/Morpion/JeuDuMorpion/src/jeudumorpion/Vues/imagesJoueurs/1024px-Cercle_noir_100%.svg_1.png"));;
//                }
                
//                if(joueurCourant.getSigne().equals(Signe.X)){
//                    this.vueGrille.getBoutonsCase().get(((MessageCase) arg).getNumBtn()).setIcon(new ImageIcon("/home/miras/NetBeansProjects/Morpion/Morpion/Morpion/JeuDuMorpion/src/jeudumorpion/Vues/imagesJoueurs/delete-462216_960_720_1.png"));
//                }
//                else if (joueurCourant.getSigne().equals(Signe.O)) {;
//                    this.vueGrille.getBoutonsCase().get(((MessageCase) arg).getNumBtn()).setIcon(new ImageIcon("/home/miras/NetBeansProjects/Morpion/Morpion/Morpion/JeuDuMorpion/src/jeudumorpion/Vues/imagesJoueurs/1024px-Cercle_noir_100%.svg_1.png"));;
//                }

                    setNbCaseCoche(getNbCaseCoche()+1);
                
                if (getNbCaseCoche()==9){
                    
                    if(joueurs.size()==2){
                        
                        // VICTOIRE //
                        if(grille.Gagnant(joueurCourant.getSigne())){
                            victoire2 = new popUpDuel(joueurCourant);
                            victoire2.afficher();
                            victoire2.addObserver(this);
                            
                            // Ajout des points
                            if (duel.get(1)==joueurCourant){
                                joueurCourant.addPoint(3);
                            }
                            else joueurCourant.addPoint(2);
                            

    
                        } else { // EGALITE //
                            duel.get(0).addPoint(1);
                            duel.get(1).addPoint(1);
                        }
                    } else {
                        
                    }
                }
                else if(getNbCaseCoche()>=5 && getNbCaseCoche()<9){
                    if(grille.Gagnant(joueurCourant.getSigne())){
                       // joueurs.replace(joueurCourant, joueurs.get(joueurCourant)+3);
//                       vueGrille.fermer();
                        
                        if(nbTours == (nbj - 1)){
                            if(nbj == 2){
                                victoire2 = new popUpDuel(joueurCourant);
                                victoire2.afficher();
                                victoire2.addObserver(this);
                                vueGrille.tableauVictoire(duel, nbj);
                                
                                // Ajout des points
                                if (duel.get(1)==joueurCourant){
                                    joueurCourant.addPoint(3);
                                }
                                else joueurCourant.addPoint(2);
                            
                            }
                            else{
                                victoireTourn = new popUpTournois(joueurCourant);
                                victoireTourn.afficher();
                                victoireTourn.addObserver(this);
                            }
                        }
                        else{
                        victoire = new popUpPartie(joueurCourant);
                        victoire.afficher();
                        victoire.addObserver(this);
//                        y = y+2;

                        // Ajout des points
                            if (duel.get(1)==joueurCourant){
                                joueurCourant.addPoint(3);
                            }
                            else joueurCourant.addPoint(2);
                        }

                    }else{
                    duel.set(0, duel.get(1));
                    duel.set(1, joueurCourant);
                    setJoueurCourant(duel.get(0));
                    this.vueGrille.joueurActif(joueurCourant);
                    }
                        
                     
                }
               else{
                    /* inutile puisque déjà fais en dessous... */
//                    if(joueurCourant.getPseudo().equals(duel.get(joueursPatieCourante).getPseudo())){
//                        joueurCourant = duel.get(joueursPatieCourante+1);
//                        this.vueGrille.joueurActif(joueurCourant);
//                }
//                else if (joueurCourant.getPseudo().equals(duel.get(joueursPatieCourante+1).getPseudo())) {
//                    joueurCourant = duel.get(joueursPatieCourante);
//                    this.vueGrille.joueurActif(joueurCourant);
//                }
                    duel.set(0, duel.get(1));
                    duel.set(1, joueurCourant);
                    setJoueurCourant(duel.get(0));
                    this.vueGrille.joueurActif(joueurCourant);
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
   
    public void initTournois(ArrayList<Joueur> joueurs){
//        if(nbMatch<joueurs.size()/2){
//            duel.clear();
//            duel.add(joueurs.get(0));
//            duel.add(joueurs.get(1));
//            duel.get(0).setSigne(Signe.X);
//            duel.get(1).setSigne(Signe.O);
//            joueurs.set(joueurs.size()-1, duel.get(0));
//            joueurs.set(joueurs.size()-1, duel.get(1));
//            this.setJoueurCourant(duel.get(0));
//            nbMatch=nbMatch+1;
//              
//        } else if (nbMatch%joueurs.size()-1!=0){
//                duel.clear();
//                duel.add(joueurs.get(0));
//                duel.add(joueurs.get(2));
//                duel.get(0).setSigne(Signe.X);
//                duel.get(1).setSigne(Signe.O);
//                joueurs.set(joueurs.size()-1, duel.get(0));
//                joueurs.set(joueurs.size()-1, duel.get(1));
//                this.setJoueurCourant(duel.get(0));
//                nbMatch=nbMatch+1;
//        } else if (nbMatch%joueurs.size()-1==0){
//                duel.clear();
//                duel.add(joueurs.get(0));
//                duel.add(joueurs.get(1));
//                duel.get(0).setSigne(Signe.X);
//                duel.get(1).setSigne(Signe.O);
//                joueurs.set(joueurs.size()-1, duel.get(0));
//                joueurs.set(joueurs.size()-1, duel.get(1));
//                this.setJoueurCourant(duel.get(0));
//                nbMatch=nbMatch+1;                
//        }   
        for(int x = 0;x<joueurs.size()-1;x++){
            joueurs.get(x).ajouterJ(joueurs.get(x+1));
        }

    }
    
    public void melangeTournois(ArrayList<Joueur> joueurs){
        for(int x = 0;x<joueurs.size()-1;x++){
            joueurs.get(x).Shuffle();
        }
       
    }
    public void tournois(ArrayList<Joueur> j){
        if (j.get(0).getJoueurA()!=null){
            if (jChoisie!=nbj){
                if(j.get(jChoisie).getJoueurA() !=null){
                    duel.clear();
                    duel.add(j.get(jChoisie));
                    duel.add(j.get(jChoisie).getJoueurA().get(0));
                    duel.get(0).setSigne(Signe.X);
                    duel.get(1).setSigne(Signe.O);
                    joueurCourant =duel.get(0);
                    j.get(jChoisie).getJoueurA().remove(0);
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
//            vueTableau tableau = new vueTableau(j);
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
