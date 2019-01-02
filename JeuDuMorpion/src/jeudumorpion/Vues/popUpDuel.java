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
public class popUpDuel extends Observable {
   private JFrame frame;
    
    public popUpDuel(Joueur j){
    frame = new JFrame("Gagnant du Duel");
    frame.setSize(400, 100);  
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel mainPanel = new JPanel();
    frame.add(mainPanel);
    frame.setUndecorated(true);
    mainPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.BLACK));
    
    JLabel nmGagnant = new JLabel("Le gagnant du duel est : " + j.getPseudo(), JLabel.CENTER);
    
    mainPanel.add(nmGagnant, BorderLayout.CENTER);
    JButton retour = new JButton("Retour page d'acceuil");
    JButton nvxTournois = new JButton("Nouvelle partie");
    
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
    
    panelBas.add(nvxTournois);
    nvxTournois.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            setChanged();
                notifyObservers(new Message(Actions.NOUVELLE_PARTIE));
                clearChanged();
        }
    });
    

}
    public void afficher(){
        this.frame.setVisible(true);
    }
        public void fermer(){
        this.frame.setVisible(false);
    }
}
