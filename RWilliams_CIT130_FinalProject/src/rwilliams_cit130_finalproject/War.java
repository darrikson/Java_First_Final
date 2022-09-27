
package rwilliams_cit130_finalproject;

import java.util.Random;

/*Methods for playing a game of war. There will be objects associated with 
players. However, most things will be manipulated off of the properties of the
individual cards*/
public class War {
    /*Basic rules: Deck is shuffled and dealt, each player receives half of the
    cards available. Players then deal the first card their deck. The value of
    the cards is compared and the player who dealt the higher value takes 
    possession of both cards. If cards are of equal value, war is declared. 
    When war is declared, each player will deal three more cards face down
    (to expedite the speed of the game), then the fourth cards will be compared
    as orginally. War can go into multiple declared rounds, and it is winner
    takes all. The game continues until one player has won all the cards. If a 
    player does not have enough cards to participate when war is delcared, then
    the opposing player wins by default */
    
    /*Game starts with shuffle and deal deck. Game phases will be deal, 
    compare, war(recursive) -include error catch for index out range meaning
    a player ran out of cards: auto win, compare(recursive),
    change ownership and top card, check owndership count.
    Challenge, keeping track of what cards are in play. Solution: Card array*/
    
    Card[] inPlay = new Card[52];
    
    
    public void playWar(Player P1, Player P2){

        DeckOfCards playDeck = new DeckOfCards();
        /*for (int i = 0; i < playDeck.deck.length; i++) {
            System.out.println(playDeck.deck[i].toString());
        }*/
        Random rand = new Random();
        for (int i = 0; i < rand.nextInt(256); i++) {
            playDeck.shuffleDeck();
        }        
        playDeck.dealDeck();
        /*for (int i = 0; i < playDeck.deck.length; i++) {
            System.out.println(playDeck.deck[i].toString());
            System.out.println(playDeck.deck[i].owned);
        }*/
        P1.ownerValue = 1;
        P2.ownerValue = 2;        
        P1.topCard = 0;
        P2.topCard = 1;
        System.out.println("Welcome to war! Let's play!");        
        do{            
            dealOut(playDeck, P1, P2);
            checkOwnership(playDeck, P1, P2);
            System.out.println(P1.getName() + " " + P1.cardsOwned);
            System.out.println(P2.getName() + " " + P2.cardsOwned);
            pressKey();
        }while(!gameOver(P1, P2)); 
        if (P1.cardsOwned == 52) {
            System.out.println(P1.getName() + " has won the game!");
        }else{
            System.out.println(P2.getName() + " has won the game!");
        }
    }
          
    public void dealOut(DeckOfCards deck, Player p1, Player p2){
        System.out.println(p1.getName() + " dealt the " +
                deck.deck[p1.topCard].toString());
        System.out.println(p2.getName() + " dealt the " +
                deck.deck[p2.topCard].toString());
        inPlay[0] = deck.deck[p1.topCard];
        inPlay[1] = deck.deck[p2.topCard];
        int winner = compareCards(deck, p1, p2);        
        if (winner != 3) {
            p1.topCard = findNextOwned(deck, p1);
            p2.topCard = findNextOwned(deck, p2);
            for (int i = 0; i < inPlay.length; i++) {
                if (inPlay[i] != null) {
                    inPlay[i].owned = winner;
                }
            }
        }else{
            int winnerValue = 0;
            boolean p1Viable = ownershipViable(deck, p1);
            if (p1Viable) {
                System.out.println(p2.getName() + " has run out of cards!");
                winnerValue = p2.ownerValue;
            }else {
                System.out.println(p1.getName() + " has run out of cards!");
                winnerValue = p1.ownerValue;
            }
            for (Card card : deck.deck){
                card.owned = winnerValue;
            }
        }
        dumpInPlay();        
    }
    
    public int compareCards(DeckOfCards deck, Player p1, Player p2){
        if (deck.deck[p1.topCard].value == deck.deck[p2.topCard].value) {
            System.out.println("It's WAR!");
            return declareWar(deck, p1, p2);
        }else if(deck.deck[p1.topCard].value > deck.deck[p2.topCard].value){
            System.out.println(p1.getName() + " won with the " 
                    + deck.deck[p1.topCard].toString() + " over the " +
                    deck.deck[p2.topCard].toString());
            System.out.print(p1.getName() + " added the ");
            for (int i = 0; i < inPlay.length; i++) {
                if (inPlay[i] != null) {
                    if ( i%3 == 0) {
                        System.out.println();
                    }
                    System.out.print(inPlay[i].toString() + ", ");
                }                
            }
            System.out.print("to their deck.");
            System.out.println();
            return p1.ownerValue;
        }else{
            System.out.println(p2.getName() + " won with the " 
                    + deck.deck[p2.topCard].toString() + " over the " +
                    deck.deck[p1.topCard].toString());
            System.out.print(p2.getName() + " added the ");
            for (int i = 0; i < inPlay.length; i++) {
                if (inPlay[i] != null) {
                    if ( i%3 == 0) {
                        System.out.println();
                    }
                    System.out.print(inPlay[i].toString() + ", ");
                }                
            }
            System.out.print("to their deck.");
            System.out.println();
            return p2.ownerValue;
        }
    }
    
    public int declareWar(DeckOfCards deck, Player p1, Player p2){
        if (ownershipViable(deck, p1)) {
            for (int i = 0; i < 4; i++) {
                p1.topCard = findNextOwned(deck, p1);
                for (int j = 0; j < inPlay.length; j++) {
                    if (inPlay[j] == null) {
                        inPlay[j] = deck.deck[p1.topCard];
                        break;
                    }
                }
            }
        } else{
            return 3;
        }
        if (ownershipViable(deck, p2)) {
            for (int i = 0; i < 4; i++) {
                p2.topCard = findNextOwned(deck, p2);
                for (int j = 0; j < inPlay.length; j++) {
                    if (inPlay[j] == null) {
                        inPlay[j] = deck.deck[p2.topCard];
                        break;
                    }
                }
            }
        } else{
            return 3;
        }
        int winner = dealOutWar(deck, p1, p2);
        return winner;
    }
    
    public int dealOutWar(DeckOfCards deck, Player p1, Player p2){
        System.out.println(p1.getName() + " dealt the " +
                deck.deck[p1.topCard].toString());
        System.out.println(p2.getName() + " dealt the " +
                deck.deck[p2.topCard].toString());        
        int winner = compareCards(deck, p1, p2);
        return winner;
    }
    
    public int findNextOwned(DeckOfCards deck, Player pl){
        /*Scan from topCard + 1 to end of deck for viability. 
        If viable, run for loop. If false,
        scan from front of deck to the previous starting point for viability. 
        If viable, run for loop.
        If false again, player has no cards, auto win for other player.
        Default top card to 0, set ownership for the deck to opposite player
        - will use literal value for this operation, passing a second player
        to this method to grab the value is potentially confusing and 
        unnecessary, however the passed players owner value can be used to
        determine the opposite value to be used*/
        boolean backEnd = scanBackEnd(deck, pl);
        boolean frontEnd = scanFrontEnd(deck, pl);
        if (backEnd) {
            for (int i = pl.topCard + 1; i < deck.deck.length; i++) {
                if (deck.deck[i].owned == pl.ownerValue) {
                    return i;
                }
            }
        }
        if (!backEnd && frontEnd) {
            for (int i = 0; i < pl.topCard +1; i++) {
                if (deck.deck[i].owned == pl.ownerValue) {
                    return i;
                }
            }
        }        
        return 0; //default
    }
    
    public boolean scanBackEnd(DeckOfCards deck, Player pl){
        for (int i = pl.topCard + 1; i < deck.deck.length; i++) {
            if (deck.deck[i].owned == pl.ownerValue) {
                return true;
            }
        }
        return false;
    }
    
    public boolean scanFrontEnd(DeckOfCards deck, Player pl){
        for (int i = 0; i < pl.topCard +1; i++) {
            if (deck.deck[i].owned == pl.ownerValue) {
                return true;
            }
        }
        return false;
    }
    
    public void dumpInPlay(){
        for (int i = 0; i < inPlay.length; i++) {
            inPlay[i] = null;
        }
    }
    
    public void checkOwnership(DeckOfCards deck, Player p1, Player p2){
        int p1Owned = 0;
        int p2Owned = 0;
        for (int i = 0; i < deck.deck.length; i++) {
            if (deck.deck[i].owned == p1.ownerValue) {
                p1Owned++;
            }
            if (deck.deck[i].owned == p2.ownerValue) {
                p2Owned++;
            }
        }
        p1.cardsOwned = p1Owned;
        p2.cardsOwned = p2Owned;
    }
    
    public boolean ownershipViable(DeckOfCards deck, Player pl){
        boolean backEnd = scanBackEnd(deck, pl);
        boolean frontEnd = scanFrontEnd(deck, pl);
        if (backEnd || frontEnd) {
            return true;
        }else{
            return false;
        }
    }
    
    public void pressKey(){
        System.out.println("Press Enter to continue");
        try{
            System.in.read();
        }catch(Exception ex){}
    }
    
    public boolean gameOver(Player p1, Player p2){
        if (p1.cardsOwned == 52 || p2.cardsOwned == 52) {
            return true;
        }else{
            return false;
        }
    }
}
