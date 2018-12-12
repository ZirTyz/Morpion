/*
 * Message d'un observé à un observateur
 */
package jeudumorpion.utilitaires;




public class Message {
    private final Actions action ;

    public Message(Actions action) {
        this.action = action ;
    }

    public Actions getAction() {
        return this.action ;
    }

}
