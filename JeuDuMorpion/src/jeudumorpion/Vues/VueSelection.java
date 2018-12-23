/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion.Vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jeudumorpion.utilitaires.Actions;
import jeudumorpion.utilitaires.Message;

/**
 *
 * @author mirasl
 */
public class VueSelection extends Observable{

    private final JFrame window;
    private final JButton btnNewPartie;
    private final JButton btnTableauS;
    private final JButton btnTuto;
    private final JButton btnCreation;
            private JButton sombre;
        private JButton colore;

    public VueSelection(){
        window = new JFrame("Morpion");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(600, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        JPanel panelHaut = new JPanel();
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        //Positionnement titre
        //mainPanel.add(new JLabel(), BorderLayout.NORTH);
        JLabel labelTitre = new JLabel("Jeu du morpion") ;
        labelTitre.setForeground(Color.BLACK);
        labelTitre.setFont(new Font(labelTitre.getFont().getName(), labelTitre.getFont().getStyle(), (int) (labelTitre.getFont().getSize() * 2.0)));
        panelHaut.add(labelTitre);
        //mainPanel.add(new JLabel(), BorderLayout.NORTH);
        
        //création zone menu
        JPanel menu= new JPanel(new BorderLayout());
        mainPanel.add(menu,BorderLayout.CENTER);
        //menuHaut parti
        JPanel menuHaut = new JPanel(new GridLayout(5,3));
        menu.add(menuHaut,BorderLayout.NORTH);
        btnNewPartie = new JButton("Nouvelle Partie");
        btnNewPartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new Message(Actions.NEWPARTIE));
                clearChanged();
            }
        });
        btnTableauS = new JButton("Tableau des Scores");
        for(int x =0; x<15;x++){
            if (x==4){
                menuHaut.add(btnNewPartie);
            }
            else if(x==10){
                menuHaut.add(btnTableauS);                
            }
            else {
                menuHaut.add(new JLabel(""));
            }
        }
        JPanel menuBas =new JPanel(new GridLayout(5,5));
        menu.add(menuBas, BorderLayout.CENTER);
        btnTuto = new JButton("Tutoriel");
        btnCreation = new JButton("Création");
        for (int x=0;x<25;x++){
            if (x==1){
                menuBas.add(btnTuto);
            }
            else if(x==3){
                menuBas.add(btnCreation);
            }
            else{
                menuBas.add(new JLabel(""));
            }
        }
                JPanel panelBas = new JPanel(new GridLayout(3,6)) ;
        menu.add(panelBas, BorderLayout.SOUTH);
        sombre = new JButton("Sombre");
        colore = new JButton("Colore");

        for(int x =0; x<18;x++){
            if (x==0){
                panelBas.add(new JLabel("Style ", JLabel.RIGHT));
            }
            else if(x==1){
                panelBas.add(new JLabel("graphique", JLabel.LEFT));             
            }
            else if(x==6){
                panelBas.add(sombre);                
            }
            else if(x==7){
                panelBas.add(colore);                
            }
            else {
                panelBas.add(new JLabel(""));
            }
        }
    }
    public void afficher() {
        this.window.setVisible(true);
    }
//    public void close(){
//        this.window.setDefaultCloseOperation(javax.swing.JFrame.E);
//    }
    
}
