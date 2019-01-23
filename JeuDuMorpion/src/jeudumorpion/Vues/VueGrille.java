/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion.Vues;


import java.awt.*;
import java.util.Observable;
import java.util.HashMap;
import javax.swing.*;
import java.awt.color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import jeudumorpion.modele.Case;
import jeudumorpion.modele.Grille;
import jeudumorpion.modele.Joueur;
import jeudumorpion.modele.Signe;
import static jeudumorpion.modele.Signe.NULL;
import jeudumorpion.utilitaires.*;
/**
 *
 * @author chapellr
 */
public class VueGrille extends Observable{
    private HashMap<Integer,ICase> iCases = new HashMap();
    private JFrame window;
    private final int borderWidth = 4;
    private final int rows = 3;
    private final int cols = 3;
    private JPanel indicationJoueur;
    private ArrayList<JButton> boutonsCase= new ArrayList();
    private JLabel jouer ;
    private int tour = 0;
    private int tour2 = 0;
    private Color fond;
    private JPanel panelTableau;
    private JPanel panelDroite;
    private JPanel marges = new JPanel(new BorderLayout());
    private JPanel panelMorp;
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel contientJouer;
    private JLabel nomJoueur;
    private JLabel pointJoueur;
    private JLabel nbptsJoueur;
    private JPanel tableauGauche;
    private JPanel tableauDroite;
    private JLabel affrontement; 
    
    public VueGrille(Joueur a, Joueur b, Color fond){
        //Création de la fenêtre + séparation en différent layout
        this.fond = fond;
        window = new JFrame("Morpion");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1400, 800);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
 
        mainPanel.setBackground(fond);
        window.add(mainPanel) ;
        JPanel panelHaut = new JPanel(new BorderLayout()) ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        
        //Marge autour du titre + titre
        panelHaut.add(new JLabel("            "), BorderLayout.SOUTH );
        panelHaut.add(new JLabel("            "), BorderLayout.NORTH);
        panelHaut.setOpaque(false);
        JLabel labelTitre = new JLabel("Jeu du morpion - Tournoi", JLabel.CENTER) ;

        labelTitre.setFont(new Font("Princetown LET", Font.PLAIN, (int) (labelTitre.getFont().getSize() * 2.5)));
        panelHaut.add(labelTitre, BorderLayout.CENTER);
        
        JButton retour = new JButton("Retour");
        panelHaut.add(retour, BorderLayout.WEST);
//        retour.setContentAreaFilled(false);
        retour.setBorderPainted(false);
        retour.setFocusPainted(false);
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setChanged();
                notifyObservers(new Message(Actions.ACCUEIL));
            }
        });
        //Marge autour des cases du morpion

        
        mainPanel.add(marges, BorderLayout.CENTER);
        panelMorp = new JPanel( new GridLayout(rows,cols));
        marges.add(new JLabel("        "), BorderLayout.NORTH);
        marges.add(new JLabel("        "), BorderLayout.WEST );
        marges.add(new JLabel("        "), BorderLayout.SOUTH );
        marges.add(new JLabel("        "), BorderLayout.EAST );
        marges.setOpaque(false);
        marges.add(panelMorp, BorderLayout.CENTER);
        
        
        
        //Cases du morpion
        this.initGrille();

        // Faire l'est de la vue: Joueur a contre joueur b
        JPanel margesPanDroite = new JPanel(new BorderLayout());
        margesPanDroite.setOpaque(false);
        mainPanel.add(margesPanDroite, BorderLayout.EAST);
        margesPanDroite.add(new JLabel(" "), BorderLayout.NORTH);
        margesPanDroite.add(new JLabel("                "), BorderLayout.EAST);
        margesPanDroite.add(new JLabel("        "), BorderLayout.WEST);
        margesPanDroite.add(new JLabel(" "), BorderLayout.SOUTH );
        panelDroite = new JPanel(new BorderLayout());
        margesPanDroite.add(panelDroite, BorderLayout.CENTER);
        indicationJoueur = new JPanel(new BorderLayout());
        panelDroite.add(indicationJoueur, BorderLayout.NORTH);
        
        affrontement = new JLabel(a.getPseudo() + " contre " + b.getPseudo(), JLabel.CENTER);
        affrontement.setFont(new Font(affrontement.getName(), Font.PLAIN, affrontement.getFont().getSize()*2));
        indicationJoueur.add(affrontement, BorderLayout.NORTH);
        indicationJoueur.setOpaque(false);
        
       
        panelDroite.setOpaque(false);

        


    }

    public void afficher() {
        this.window.setVisible(true);
    }
    public void fermer(){
        this.window.setVisible(false);
    }
    
    public void setAffrontement(Joueur a, Joueur b){
        indicationJoueur.remove(affrontement);
        affrontement = new JLabel(a.getPseudo() + " contre " + b.getPseudo(), JLabel.CENTER);
        affrontement.setFont(new Font(affrontement.getName(), Font.PLAIN, affrontement.getFont().getSize()*2));
        indicationJoueur.add(affrontement, BorderLayout.NORTH);
        indicationJoueur.setOpaque(false);
    
    }
    public void joueurActif(Joueur j){
        //À toi de jouer !
        if (tour!=0){
            indicationJoueur.remove(contientJouer);
        
        } 
        jouer = new JLabel("À toi de jouer " + j.getPseudo(), JLabel.CENTER);
        jouer.setFont(new Font("Princetown LET", Font.PLAIN, (int) (jouer.getFont().getSize() * 1.5)));
        contientJouer = new JPanel(new GridLayout(3,1));
        contientJouer.add(new JLabel(""), BorderLayout.NORTH);
        contientJouer.add(jouer, BorderLayout.CENTER);
        contientJouer.add(new JLabel(""), BorderLayout.SOUTH);
        contientJouer.setOpaque(false);
        indicationJoueur.add(contientJouer, BorderLayout.CENTER);
        tour = tour+1;

    }
    

    /**
     * @return the boutonsCase
     */
    public ArrayList<JButton> getBoutonsCase() {
        return boutonsCase;
    }
    
    public void tableauVictoire(ArrayList<Joueur> j, int nombrejoueurs){
        if (tour2==0){
        JLabel tab = new JLabel("Tableau des victoires", JLabel.CENTER);
        panelTableau = new JPanel(new BorderLayout());
        panelTableau.add(tab, BorderLayout.NORTH);
        panelTableau.setOpaque(false);
        panelDroite.add(panelTableau, BorderLayout.CENTER);
        tableauGauche = new JPanel(new GridLayout(nombrejoueurs, 1));
        panelTableau.add(tableauGauche, BorderLayout.CENTER);
        tableauGauche.setOpaque(false);
        tableauDroite = new JPanel(new GridLayout(nombrejoueurs, 1));
        panelTableau.add(tableauDroite, BorderLayout.EAST);
        tableauDroite.setOpaque(false);
        
        }
        Collections.sort(j);
        for(int i =0; i < j.size(); i++){
            if (tour2!=0){
                panelTableau.remove(tableauGauche);
            }
            if (i==0){
                tableauGauche = new JPanel(new GridLayout(nombrejoueurs, 1));}
                nomJoueur = new JLabel(i+1+" - " + j.get(i).getPseudo());
                tableauGauche.add(nomJoueur);
                tableauGauche.setOpaque(false);

        }
        panelTableau.add(tableauGauche,BorderLayout.CENTER);
        for (int i=0;i<j.size();i++){
            if(tour2!=0){
                panelTableau.remove(tableauDroite);
            }
            if (i==0){
            tableauDroite = new JPanel(new GridLayout(nombrejoueurs, 1));}
            String point = String.valueOf(j.get(i).getPoints());
            pointJoueur = new JLabel(point);
            tableauDroite.add(pointJoueur);
            tableauDroite.setOpaque(false);
        }
        panelTableau.add(tableauDroite, BorderLayout.EAST);
        tour2 = tour2+1;
    }
    
    public void reset(){
        marges.remove(panelMorp);
        panelMorp = new JPanel( new GridLayout(rows,cols));
        marges.add(panelMorp, BorderLayout.CENTER);
        this.initGrille();
    }
    
    public void initGrille(){
        Dimension d = new Dimension(mainPanel.getWidth()/2, mainPanel.getHeight());
        
        
        marges.setPreferredSize(d);
        
        panelMorp.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                JButton btnCase = new JButton();
                btnCase.setContentAreaFilled(false);
                btnCase.setFocusPainted(false);
                boutonsCase.add(btnCase);
                        int x = row;
                        int y = col;
                        btnCase.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent arg0) {
                                setChanged();
                                notifyObservers(new MessageCase(Actions.COCHER_CASE, y, x, getBoutonsCase().indexOf(btnCase)));
                                clearChanged();
                                btnCase.removeActionListener(this);
                            }
                        });
                          panelMorp.add(btnCase);  

                if (row == 0) {
                    if (col == 0) {
                        // Top left corner, draw all sides
                        btnCase.setBorder(BorderFactory.createMatteBorder(0, 0, borderWidth, borderWidth, Color.BLACK));
                        
                    }
                    else if (col == cols-1){
                        btnCase.setBorder(BorderFactory.createMatteBorder(0, 0, borderWidth, 0, Color.BLACK));
                        
                    }
                    else {
                        // Top edge, draw all sides except left edge
                        btnCase.setBorder(BorderFactory.createMatteBorder(0, 0, borderWidth, borderWidth, Color.BLACK));
                    }
                }
                else if (row == rows -1){
                    if (col == 0){
                        btnCase.setBorder(BorderFactory.createMatteBorder(0,0, 0,borderWidth,Color.BLACK));
                    }
                    else if (col == cols -1){
                        btnCase.setBorder(BorderFactory.createMatteBorder(0,0, 0,0,Color.BLACK));
                    }
                    else {
                        btnCase.setBorder(BorderFactory.createMatteBorder(0,0, 0,borderWidth,Color.BLACK));
                    }
                        
                    }
                else {
                    if (col == 0) {
                        // Left-hand edge, draw all sides except top
                        btnCase.setBorder(BorderFactory.createMatteBorder(0,0, borderWidth, borderWidth,Color.BLACK));
                    }
                    else if (col == cols-1){
                        btnCase.setBorder(BorderFactory.createMatteBorder(0,0, borderWidth, 0,Color.BLACK));
                    }
                    
                    else {
                        // Neither top edge nor left edge, skip both top and left lines
                        btnCase.setBorder(BorderFactory.createMatteBorder(0, 0, borderWidth, borderWidth, Color.BLACK));
                    }
                }


                
                
            }
        }
                panelMorp.setOpaque(false);
    }
}
