
package rwilliams_cit130_finalproject;

import java.util.Random;
import java.util.Scanner;

/* Methods for playing a game of yahtzee. Necessary objects will be associated
with the player. The class will have a single member, a round counter. */
public class Yahtzee {
    public int round = 0;
    /* Basic rules: players will roll to determine who goes first. Highest roll
    goes first. Each player will roll five dice, and may then choose to set
    the dice into any score tray as they see fit. A score tray must be filled
    during each turn. Play continues until each player has taken 13 turns (26
    total) and all score trays are filled. Scores are then calculated and the
    player with the highest score wins.
    Special rules: if a player rolls a yahtzee (5 matching) on their turn but 
    have already filled their yahtzee score tray with a genuine yahtzee, they
    may receive an additional 100 points. If the upper section matching the 
    rolled yahtzee is empty, it must be filled with the roll. Else it may be 
    placed in any lower section score tray */
    /*Each round will be split up into separate actions. Rolling, 
    display roll, and set score tray. Set score tray will be further broken
    into separate actions, determine yahtzee bonus, & fill tray. While in the
    set score tray action, display card will be utilized to help visualisation
    and decision making*/
    
    public void playYahtzee(Player player1, Player player2){
        //Determine who goes first
        System.out.println("Welcome to Yahtzee!");
        System.out.println("Your basic understanding of the rules is assumed.");
        /*Sorry, I'm lazy. Don't want to make a context menu for a game you
        can look up or peep the rules above */
        System.out.println("We will now roll to determine who goes first.");
        int p1Roll = rollBegin();
        System.out.println(player1.getName() + " rolled a total of " + p1Roll);
        int p2Roll = rollBegin();
        System.out.println(player2.getName() + " rolled a total of " + p2Roll);
        Player goFirst;
        Player goSecond;
        if (p1Roll >= p2Roll){
            goFirst = player1;
            goSecond = player2;
            System.out.println(goFirst.getName() + " will go first and "
            + goSecond.getName() + " will go second.");
        } else {
            goFirst = player2;
            goSecond = player1;
            System.out.println(goFirst.getName() + " will go first and "
            + goSecond.getName() + " will go second.");
        }
        do{
        turn(goFirst);
        System.out.println("Round: " + round);
        turn(goSecond);
        System.out.println("Round: " + round);
        }while(round < 26);
        goFirst.playerCard.determineScore();
        goSecond.playerCard.determineScore();
        goFirst.playerCard.displayCard(goFirst);       
        goSecond.playerCard.displayCard(goSecond);
        System.out.println(goFirst.getName() + "'s score is: " + 
                goFirst.playerCard.totalScore);
        System.out.println(goSecond.getName() + "'s score is: " + 
                goSecond.playerCard.totalScore);
        if (goFirst.playerCard.totalScore == goSecond.playerCard.totalScore) {
            System.out.println("It's a tie!");            
        }else if (goSecond.playerCard.totalScore > goFirst.playerCard.totalScore) {
            System.out.println(goSecond.getName() + " wins!");
        }else if(goFirst.playerCard.totalScore > goSecond.playerCard.totalScore){
            System.out.println(goFirst.getName() + " wins!");
        }
        goFirst.playerCard.clearCard();
        goSecond.playerCard.clearCard();
        round = 0;
    }
    
    public int rollBegin(){
        int[] roll = new int[5];
        Random rand = new Random();
        for (int i = 0; i < roll.length; i++) {
            roll[i] = rand.nextInt(6)+1;
        }
        displayRoll(roll);
        int total = 0;
        for (int i = 0; i < roll.length; i++) {
            total += roll[i];
        }
        return total;
    }
    
    public void displayRoll(int[] roll){
        System.out.println("===================");
        for (int i = 0; i < roll.length; i++) {
            System.out.print("|" + roll[i] + "| ");
        }
        System.out.println();
        System.out.println("===================");
    }
    
    public int[] RollDice(){
        int[] roll = new int[5];
        Random rand = new Random();
        for (int i = 0; i < roll.length; i++) {
            roll[i] = rand.nextInt(6)+1;
        }
        return roll;
    }
    
    public void determineTray(Player PL, int[] roll){
        PL.playerCard.displayCard(PL);
        boolean bonus = determineYahtzeeBonus(PL, roll);
        if (bonus) {
            System.out.println("Yahtzee bonus!");
            selectBonus(PL, roll);
            PL.playerCard.displayCard(PL);
        }else{
            selectTray(PL, roll);                        
        }
    }
    
    public void selectBonus(Player PL, int[] roll){
        int ones = 0;
        int twos = 0;
        int threes = 0;
        int fours = 0;
        int fives = 0;
        int sixes = 0;
        for (int i = 0; i < roll.length; i++) {
            int value = roll[i];
            switch(value){
                case 1:
                    ones++;
                    break;
                case 2:
                    twos++;
                    break;
                case 3:
                    threes++;
                    break;
                case 4:
                    fours++;
                    break;
                case 5:
                    fives++;
                    break;
                case 6:
                    sixes++;
                    break;
            }
        }
        int[] counts = new int[6];
        counts[0] = ones;
        counts[1] = twos;
        counts[2] = threes;
        counts[3] = fours;
        counts[4] = fives;
        counts[5] = sixes;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 4 && !PL.playerCard.trayHolder[i+1].Full) {
                PL.playerCard.trayHolder[i+1].fillTray(roll);
                System.out.println("Saved to the " + 
                    PL.playerCard.trayHolder[i+1].name + " tray.");
                PL.playerCard.trayHolder[i+1].displayTray();
            }else{
            selectTray(PL, roll);
            }
        }
    }
    
    public void selectTray(Player PL, int[] roll){
        boolean select = false;
            int selection = 0;
            do
            {    
                Scanner input = new Scanner(System.in);
                input.reset();
                System.out.println("Please select where you would like to save"
                        + " this roll:");
                System.out.println("1. Ones\n2. Twos\n3. Threes\n4. Fours\n"
                        + "5. Fives\n6. Sixes\n7. 3 of a Kind\n8. 4 of a Kind"
                        + "\n9. Full House\n10. Small Straight\n"
                        + "11. Large Straight\n12. Yahtzee\n13. Chance"); 
                displayRoll(roll);
                try{                                        
                    selection = input.nextInt();
                    if (selection < 1 || selection > 13){
                        System.out.println("Input out of range, please try again");
                        select = false;
                        break;
                    }
                    selection = convertSelection(selection);
                    PL.playerCard.trayHolder[selection].displayTray();
                    input.reset();                    
                    select = saveRoll(PL, roll, selection);                   
                }catch(Exception ex){
                    System.out.println("Input not valid, please select a number");
                }            
            }while(!select);
    }
    
    public boolean saveRoll(Player PL, int[] roll, int selection){
        System.out.println("Save roll here? Y/N?");
        Scanner input = new Scanner(System.in);
        String response = input.next();
        if(response.charAt(0) == 'y' || response.charAt(0) == 'Y'){
            boolean trayFill = false;
            trayFill = PL.playerCard.trayHolder[selection].fillTray(roll);
            if (trayFill) {
                PL.playerCard.displayCard(PL);
                return true;
            }
            else{
                return false;
            }
        }
        if(response.charAt(0) == 'n' || response.charAt(0) == 'N'){
            return false;
        }
        else{
            System.out.println("Input not recognized, try again");
            return false;                 
        }
    }
    
    public int convertSelection(int selection){
        if(selection == 13){
            selection = 0;
            return selection;
        }else{
            return selection;
        }
    }
    
    public boolean determineYahtzeeBonus(Player PL, int[] roll){
        int ones = 0;
        int twos = 0;
        int threes = 0;
        int fours = 0;
        int fives = 0;
        int sixes = 0;
        for (int i = 0; i < roll.length; i++) {
            int value = roll[i];
            switch(value){
                case 1:
                    ones++;
                    break;
                case 2:
                    twos++;
                    break;
                case 3:
                    threes++;
                    break;
                case 4:
                    fours++;
                    break;
                case 5:
                    fives++;
                    break;
                case 6:
                    sixes++;
                    break;
            }
        }
        if (ones > 4 || twos > 4 || threes > 4 
                || fours > 4 || fives > 4 || sixes > 4) { 
            if (PL.playerCard.trayHolder[12].Full) {
                if (PL.playerCard.scoreYahtzee() == 50) {
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }            
        }else{
            return false;
        }
    }
    
    public void turn(Player PL){
        System.out.println(PL.getName() + " rolls the dice.");
        int[] holder = RollDice();
        displayRoll(holder);
        determineTray(PL, holder);
        round++;
    }
}
