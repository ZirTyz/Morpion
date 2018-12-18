/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion.Vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import static java.awt.SystemColor.window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import jeudumorpion.modele.Joueur;
import jeudumorpion.utilitaires.Actions;
import jeudumorpion.utilitaires.Message;

/**
 *
 * @author rose
 */
public class popUpPartie extends Observable{
    private JFrame frame;
    
    public popUpPartie(Joueur j){
    frame = new JFrame("Gagnant");
    frame.setSize(400, 100);  
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel mainPanel = new JPanel();
    frame.setUndecorated(true);
    mainPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
    frame.add(mainPanel);
    JLabel nmGagnant = new JLabel("Le gagnant de la partie est : " + j.getPseudo(), JLabel.CENTER);
    
    mainPanel.add(nmGagnant, BorderLayout.CENTER);
    JButton retour = new JButton("Retour page d'acceuil");
    JButton suivant = new JButton("Prochain contre");
    
    JPanel panelBas = new JPanel(new GridLayout(1,2));
    mainPanel.add(panelBas, BorderLayout.SOUTH);
    panelBas.add(retour);
    retour.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            setChanged();
            notifyObservers(new Message(Actions.RETOUR));
            clearChanged();
        }
    });
    
    panelBas.add(suivant);
    suivant.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            setChanged();
                notifyObservers(new Message(Actions.TOUR_SUIVANT));
                clearChanged();
        }
    });
    

}
    public void afficher(){
        this.frame.setVisible(true);
    }
  }
