/*
Vide jusqu'à la première rencontre à modifier
 */
package jeudumorpion.Vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import jeudumorpion.modele.Joueur;

/**
 *
 * @author Lucas
 */
public class VueTableau {
    private JFrame window;
    private JPanel panelTableau;
    private JPanel tableauDroite;
    private JPanel tableauGauche;
    private JLabel nomJoueur;    
    private JLabel pointJoueur;
    private int nbJoueurs;
    private JButton chooseColor = new JButton();
    private JButton valider;
    private HashMap<JTextField, Color> nomsJoueurs = new HashMap();
    private Color fond;
    
    public VueTableau(ArrayList<Joueur> joueurs, Color fond){
        this.fond = fond;
        this.nbJoueurs = nbJoueurs;
        //Création de la fenêtre + séparation en différent layout
        window = new JFrame("Informations");
        window.setSize(800, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(fond);
        
    
    
        JLabel tab = new JLabel("Tableau des victoires", JLabel.CENTER);
        panelTableau = new JPanel(new BorderLayout());
        panelTableau.setBackground(fond);
        panelTableau.add(tab, BorderLayout.NORTH);
        
       
        tableauGauche = new JPanel(new GridLayout(joueurs.size(), 1));
        panelTableau.add(tableauGauche, BorderLayout.CENTER);
        tableauGauche.setOpaque(false);
        tableauDroite = new JPanel(new GridLayout(joueurs.size(), 1));
        panelTableau.add(tableauDroite, BorderLayout.EAST);
        tableauDroite.setOpaque(false);
        Collections.sort(joueurs);
        for(int i =0; i < joueurs.size(); i++){

            if (i==0){
                tableauGauche = new JPanel(new GridLayout(joueurs.size(), 1));}
                nomJoueur = new JLabel(i+1+" - " + joueurs.get(i).getPseudo());
                tableauGauche.add(nomJoueur);
                tableauGauche.setOpaque(false);

        }
        panelTableau.add(tableauGauche,BorderLayout.CENTER);
        for (int i=0;i<joueurs.size();i++){

            if (i==0){
            tableauDroite = new JPanel(new GridLayout(joueurs.size(), 1));}
            String point = String.valueOf(joueurs.get(i).getPoints());
            pointJoueur = new JLabel(point);
            tableauDroite.add(pointJoueur);
            tableauDroite.setOpaque(false);
        }
        panelTableau.add(tableauDroite, BorderLayout.EAST);
        window.add(panelTableau) ;
        }
    public void afficher() {
        this.window.setVisible(true);
    }
    public void fermer(){
        this.window.setVisible(false);
    }
    }   

