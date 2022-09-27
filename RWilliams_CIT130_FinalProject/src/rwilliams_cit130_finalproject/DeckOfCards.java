
package rwilliams_cit130_finalproject;

import java.util.Random;

/*Deck of cards. Will have methods for shuffling, dealing, and resetting deck
and card properties. Main property will be an array for the cards, */
public class DeckOfCards {
    Card[] deck = new Card[52];    
    
    String clubs = "Clubs";
    String spades = "Spades";
    String diamonds = "Diamonds";
    String hearts = "Hearts";
    String ace = "Ace";
    String two = "Two";
    String three = "Three";
    String four = "Four";
    String five = "Five";
    String six = "Six";
    String seven = "Seven";
    String eight = "Eight";
    String nine = "Nine";
    String ten = "Ten";
    String jack = "Jack";
    String queen = "Queen";
    String king = "King";
    
    public DeckOfCards(){
        initializeDeck();
    }
    
    public void initializeDeck(){
        String suit = "";
        String face = "";
        int place = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 2; y < 15; y++) {
                switch(x){
                    case 0:
                        suit = clubs;
                        break;
                    case 1:
                        suit = spades;
                        break;
                    case 2:
                        suit = diamonds;
                        break;
                    case 3:
                        suit = hearts;
                        break;
                }
                switch(y){
                    case 2:
                        face = two;
                        break;
                    case 3:
                        face = three;
                        break;
                    case 4:
                        face = four;
                        break;
                    case 5:
                        face = five;
                        break;
                    case 6:
                        face = six;
                        break;
                    case 7:
                        face = seven;
                        break;
                    case 8:
                        face = eight;
                        break;
                    case 9:
                        face = nine;
                        break;
                    case 10:
                        face = ten;
                        break;
                    case 11:
                        face = jack;
                        break;
                    case 12:
                        face = queen;
                        break;
                    case 13:
                        face = king;
                        break;
                    case 14:
                        face = ace;
                        break;
                }
                deck[place] = new Card(face, suit, y);
                deck[place].owned = 0;
                place++;
            }
        }
    }
    
    public void shuffleDeck(){
        Random rand = new Random();
        for (int i = 0; i < deck.length; i++) {
            Card temp; 
            int random = rand.nextInt(52);
            temp = deck[i];
            deck[i] = deck[random];
            deck[random] = temp;
        }
    }
    
    public void dealDeck(){
        for (int i = 0; i < deck.length; i++) {
            if (i %2 == 0) {
                deck[i].owned = 1;
            }else{
                deck[i].owned = 2;
            }
        }
    }
}
