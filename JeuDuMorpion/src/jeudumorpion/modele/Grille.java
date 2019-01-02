/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion.modele;

import java.util.ArrayList;

/**
 *
 * @author miras
 */
public class Grille {
    private Case cases[][] = new Case[3][3];
    private ArrayList<Case> caseslibre = new ArrayList<Case>(); // cases libre
    private ArrayList<Case> casesCoche = new ArrayList<Case>(); // cases coché
    
    
    
    public Grille(){
        int nb=1;
        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
                this.cases[x][y]=new Case(nb);
                this.cases[x][y].setEtat_case(Signe.NULL);
                nb = nb+1;
            }
        }
    }
    // verification de la case si elle appartient aux cases cochées ou non
    public void addCaseCoché(Case coche){
        this.casesCoche.add(coche);
        this.caseslibre.remove(coche);
    }
    public void reset(){
        int x=0;
        for(int i=0; i<casesCoche.size();i++){
           x=this.casesCoche.get(i).getNumCase();
           this.caseslibre.add(x, this.casesCoche.get(i));
        }
    } 
    public Case getCase(int x, int y){
        return this.cases[x][y];
    }
    //mettre tout les cas possible pour voir si il y a un gagnant
    public boolean Gagnant(Signe s){
        if (this.cases[1][1].getEtat_case().equals(s)){   // changer en getEtat_case !!          
            
            if(s.equals(this.cases[0][0].getEtat_case()) && s.equals(this.cases[2][2].getEtat_case())){ // diagonal haut gauche
                return true;  
            }
            else if(s.equals(this.cases[0][2].getEtat_case()) && s.equals(this.cases[2][0].getEtat_case())){ // diagonal haut droite
                return true;    
            }
            else if(s.equals(this.cases[0][1].getEtat_case()) && s.equals(this.cases[2][1].getEtat_case())){ //verticale milieu
                return true;
            }
            else if( s.equals(this.cases[1][0].getEtat_case()) && s.equals(this.cases[1][2].getEtat_case())){ //horizontal milieu
                return true;
            }            
        }
        else if(this.cases[0][1].getEtat_case().equals(s)){ //horizontal haut
            if(s.equals(this.cases[0][0].getEtat_case()) && s.equals(this.cases[0][2].getEtat_case())){
                return true;
            }
        }
        else if(this.cases[2][1].getEtat_case().equals(s)){ //horizontal bas
            if(s.equals(this.cases[2][0].getEtat_case()) && s.equals(this.cases[2][2].getEtat_case())){
                return true;
            } 
            
        }
        else if(this.cases[1][0].getEtat_case().equals(s)){ //vertical gauche
            if(s.equals(this.cases[0][0].getEtat_case()) && s.equals(this.cases[2][0].getEtat_case())){
                return true;    
            } 
            
        }
        else if(this.cases[1][2].getEtat_case().equals(s)){ // vertical droite
            if(s.equals(this.cases[0][2].getEtat_case()) && s.equals(this.cases[2][2].getEtat_case())){
                return true;
            } 
            
        }
        return false;

    }
    
}
