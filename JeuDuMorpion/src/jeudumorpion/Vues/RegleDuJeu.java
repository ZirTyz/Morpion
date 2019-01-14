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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import jeudumorpion.utilitaires.Actions;
import jeudumorpion.utilitaires.Message;
/**
 *
 * @author rose
 */
public class RegleDuJeu extends Observable {
    private JFrame window;
    private Color fond;
    private JPanel mainPanel;
    private JButton retour;
    public RegleDuJeu(){
        this.fond = fond;
        window = new JFrame("Morpion");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(500, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        
        mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        
        JLabel titre = new JLabel(" Règles du jeu", JLabel.CENTER);
        titre.setFont(new Font(titre.getName(), titre.getFont().getStyle(), titre.getFont().getSize()*2));
        
        mainPanel.add(titre, BorderLayout.NORTH);
        JLabel corpsDeTexte = new JLabel("  Les joueurs inscrivent tour à tour leur symbole sur une grille \n"
                + " en cliquant sur les cases réalisées à cet effet. Le premier qui parvient à aligner trois \n"
                + "\n de ses symboles horizontalement, verticalement ou en diagonale gagne un point.\n"
                + " (inspirer de : https://fr.wikipedia.org/wiki/Morpion_(jeu))"); //n'arrive pas à retourner à la ligne
        mainPanel.add(corpsDeTexte, BorderLayout.CENTER);
        
        JPanel panelFooter = new JPanel(new GridLayout(3,1));
        mainPanel.add(panelFooter, BorderLayout.SOUTH);
        retour = new JButton("Retour à la page d'acceuil");
        retour.addMouseListener( new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                retour.setBorderPainted(true);
                retour.setContentAreaFilled(true);
                Color btn = new Color(179, 12, 254);
                TextBubbleBorder abB = new TextBubbleBorder(Color.BLACK,1,5,0);
                retour.setBorder(abB);
                retour.setOpaque(false);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                retour.setBorderPainted(false);
                retour.setContentAreaFilled(false);
            }
        });
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setChanged();
                notifyObservers(new Message(Actions.ACCUEIL));
                clearChanged();
            }
        });
        
        panelFooter.add(new JLabel(""));
        panelFooter.add(retour);
        panelFooter.add(new JLabel(""));
        
        
    }
    
            public void afficher() {
        this.window.setVisible(true);
    }
                public void fermer(){
        this.window.setVisible(false);
    }
}
