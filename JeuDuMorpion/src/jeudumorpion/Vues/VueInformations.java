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
import static java.awt.SystemColor.window;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicOptionPaneUI;

/**
 *
 * @author rose
 */
public class VueInformations {
    private JFrame window;
    private int nbJoueurs;
    private JTextField champPseudo;
    private JButton chooseColor = new JButton();
    private JButton valider;
    
    public VueInformations(int nbJoueurs){
        this.nbJoueurs = nbJoueurs;
        //Création de la fenêtre + séparation en différent layout
        window = new JFrame("Informations");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(500, 800);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        Color fond = new Color(179, 204, 255);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(fond);
        window.add(mainPanel) ;
        
        JPanel panelHaut = new JPanel(new BorderLayout());
        mainPanel.add(panelHaut, BorderLayout.NORTH);
        panelHaut.add(new JLabel(" "), BorderLayout.SOUTH );
        panelHaut.add(new JLabel(" "), BorderLayout.NORTH);
        JLabel titre = new JLabel("Informations des joueurs", JLabel.CENTER);
        titre.setFont(new Font("Princetown LET", Font.PLAIN, (int) (titre.getFont().getSize() * 2.5)));
        panelHaut.add(titre, BorderLayout.CENTER);
        panelHaut.setOpaque(false);
        
        //Informations des joueurs
        JPanel panelCentre = new JPanel(new GridLayout((3*nbJoueurs), 2));
        panelCentre.setOpaque(false);
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        ;
        int nb =1;
        while (nb < nbJoueurs+1 && nb <=8){
            panelCentre.add(new JLabel("Surnom du joueur : ", JLabel.RIGHT));
            JPanel alignementTextField = new JPanel(new GridLayout(1,2));
            alignementTextField.setOpaque(false);
            panelCentre.add(alignementTextField);
            champPseudo = new JTextField();
            champPseudo.setText("Joueur " + nb);
            alignementTextField.add(champPseudo);
            alignementTextField.add(new JLabel(""));
            
            panelCentre.add(new JLabel("Couleur du joueur : ", JLabel.RIGHT));
            
            ColorChooserButton ccb = new ColorChooserButton(Color.lightGray);
            ccb.setFocusPainted(false);
            ccb.setContentAreaFilled(false);
            ccb.setBorderPainted(false);
            JPanel alignementCouleur = new JPanel(new GridLayout(1, 6));
            alignementCouleur.setOpaque(false);
            alignementCouleur.add(ccb);
            alignementCouleur.add(new JLabel(""));
            alignementCouleur.add(new JLabel(""));
            alignementCouleur.add(new JLabel(""));
            alignementCouleur.add(new JLabel(""));
            alignementCouleur.add(new JLabel(""));
            panelCentre.add(alignementCouleur, BorderLayout.WEST);
            panelCentre.add(new JLabel(""));
            panelCentre.add(new JLabel(""));
            nb = nb+1;
        }
        
        
        valider = new JButton("Valider");
        valider.setBorderPainted(false);
        valider.setContentAreaFilled(false);
        JPanel panelBas = new JPanel(new GridLayout(1, 3));
        panelBas.setOpaque(false);
        mainPanel.add(panelBas, BorderLayout.SOUTH);
        panelBas.add(new JLabel(""));
        panelBas.add(valider);
        panelBas.add(new JLabel(""));
        
        
        
        
        
}
        public void afficher() {
        this.window.setVisible(true);
    }
}

