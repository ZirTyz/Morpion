/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeudumorpion.utilitaires;

/**
 *
 * @author mirasl
 */
public class MessageCase extends Message{
    private Integer y;
    private Integer x;
    
    public MessageCase(Actions action, int y,int x){
        super(action);
        this.setY(y);
        this.setX(x);
    }

    public MessageCase(int col, int row, Actions actions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the y
     */
    public Integer getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * @return the x
     */
    public Integer getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(Integer x) {
        this.x = x;
    }
    
}
