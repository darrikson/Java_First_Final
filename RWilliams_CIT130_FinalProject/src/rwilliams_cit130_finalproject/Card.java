
package rwilliams_cit130_finalproject;

/*Individual cards. Each card has four properties, a suit (unused except for
naming), a face (ace through king), a value (2-14, aces high), and a ownership
flag ( 1 for player1, 2 for player2, denoting who has possession of the card).
*/
public class Card {
    public String suit;
    public String face;
    public int value;
    public int owned;
    
    public Card(String face, String suit, int value){
        this.face = face;
        this.suit = suit;
        this.value = value;
    }
    
    @Override
    public String toString(){
        return face + " of " + suit;
    }
}
