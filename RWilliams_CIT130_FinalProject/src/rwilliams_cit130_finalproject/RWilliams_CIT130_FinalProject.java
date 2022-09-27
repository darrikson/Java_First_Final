// Name: Rick Williams
// Class: CIT 130
// Date: 4th May 2020
package rwilliams_cit130_finalproject;

import java.util.Scanner;
/*Fulfillment Requirements:
Lopps:
    for loops: used throughout, one example: Yahtzee, line 82
    do/while: used a few, first example in main, line 40
    enhanced for: two or three used, example: YahtzeeScoreCard, line 389

Variable Storage: not sure how to prove that, but I scanned and no literal values
were used, except in comparisons or equations

Main Methods: 
    line 111, returns int
    line 134, accepts int
    line 155, returns int
    line 182, accepts int
    didn't have any need for additional main methods with return or accept, but
    many other examples of methods returning and accepting arguments
    exist outside of the main class

Classes: java files are evident, classes Yahtzee, War, and TicTacToe 
    are utilized in main, lines 137-138, 141-142, 145-146, respectively
    class Player is used in main, lines 76 & 77
    class DeckOfCards is utilized in War, line 32
    class Card is utilizied in DeckOfCards, line 94
    class YahtzeeScoreCard is utilized in Player, line 7
    class ScoreTray is utilized in YahtzeeScoreCard, lines 23-35
    default and overloaded constructor: Player, lines 21 & 24

Arrays: 
    single array: used in ScoreTray, line 5, many others
    2d array: used in TicTacToe, lines 8 & 9
    object array: used in DeckOfCards, line 9

Selections:
    Example if/else: Yahtzee, line 42
    Example switch: YahtzeeScoreCard, line 123

Static Variables: 
    TicTacToe, lines 8, 9, & 11

Exception Handling: Try to break it, I tried unsuccessfully
    Example: main, lines 124-129
*/

public class RWilliams_CIT130_FinalProject {
 /* Multi game. Tic Tac Toe, because it's easy and already written (and the 
    only application I can think of for a 2d array), Card Game
    War, for fun with arrays and methods. Dice Game Yahtzee, for 
    challenge and randomization. Will only support two players, no computer 
    opponent */
    public static void main(String[] args) {
        //To test individual games outside of the menu, 
        //uncomment the appropriate lines below
        //Player me = new Player("Rick");
        //Player notMe = new Player("Qi");
        //Yahtzee yahtzeeGame = new Yahtzee();
        //yahtzeeGame.playYahtzee(me, notMe);*/
        //War warGame = new War();
        //warGame.playWar(me, notMe);
        //TicTacToe ticTacToeGame = new TicTacToe();
        //ticTacToeGame.ticTacToe(me, notMe);
        boolean keepGoing = true;
        System.out.println("Welcome to the mini-game corner!");
        System.out.println("First order of business, let's pick your players.");
        System.out.println("Please enter a name for player 1:");
        Scanner input = new Scanner(System.in);
        String p1Name = input.next();        
        System.out.println("Great, now let's get the name for player 2:");
        String p2Name = input.next();        
        System.out.println("Okay! Let's move on.");
        Player p1 = new Player(p1Name);
        Player p2 = new Player(p2Name);
        do{            
            int response = 6;
            response = displayMenu();
            switch(response){
                case 0:
                    keepGoing = false;
                    break;
                case 1:
                    runMenu(response, p1, p2);
                    break;
                case 2:
                    runMenu(response, p1, p2);
                    break;
                case 3:
                    runMenu(response, p1, p2);
                    break;
                case 4:
                    runMenu(response, p1, p2);
                    break;                
                default:
                    System.out.println("That wasn't a valid number, "
                            + " please try again");
                    keepGoing = true;
                    break;
            }
            System.out.println("Welcome back!");
        }while(keepGoing);
        System.out.println("Thanks for playing! Goodbye!");
        try{
            System.in.read();
        }catch(Exception ex){};
    }
    
    public static int displayMenu(){
        boolean goodInput = false;
        int response = 6;
        do{
            System.out.println("================ ");
            System.out.println("    Main Menu    ");
            System.out.println("================");
            System.out.println("1. Play War");
            System.out.println("2. Play Yahtzee");
            System.out.println("3. Play Tic Tac Toe");
            System.out.println("4. Game Manuals");
            System.out.println("0. Quit");
            Scanner input = new Scanner(System.in);
            try{
                response = input.nextInt();
                goodInput = true;
            }catch(Exception ex){
                System.out.println("Input was invalid, please try again");
            }
        }while(!goodInput);
        return response;
    }
    
    public static void runMenu(int selection, Player p1, Player p2){
        switch(selection){
            case 1:
                War warGame = new War();
                warGame.playWar(p1, p2);
                break;
            case 2:
                Yahtzee yahtzeeGame = new Yahtzee();
                yahtzeeGame.playYahtzee(p1, p2);
                break;
            case 3:
                TicTacToe ticTacToeGame = new TicTacToe();
                ticTacToeGame.ticTacToe(p1, p2);
                break;
            case 4:
                int subSelect = displaySubMenu();
                runSubMenu(subSelect);
                break;
        }
    }
    
    public static int displaySubMenu(){
        boolean goodInput = false;
        int response = 6;
        do{
            System.out.println("================");
            System.out.println("  Game Manuals  ");
            System.out.println("================");
            System.out.println("1. War");
            System.out.println("2. Yathzee");
            System.out.println("3. Tic Tac Toe");
            System.out.println("0. Exit");
            Scanner input = new Scanner(System.in);
            try{
                response = input.nextInt();
                goodInput = true;
            }catch(Exception ex){
                System.out.println("Input was invalid, please try again");
            }
            if(response < 0 || response > 3){
                System.out.println("That wasn't a valid number,"
                        + " please try again");
                goodInput = false;
            }
        }while (!goodInput);
        return response;
    }
    
    public static void runSubMenu(int subSelect){
        switch(subSelect){
            case 0:                
                break;
            case 1:
                showWarRules();
                break;
            case 2:
                showYahtzeeRules();
                break;
            case 3:
                showTicTacToeRules();
                break;
        }
    }
    
    public static void showWarRules(){
        System.out.println("Basic rules:  A deck of cards is shuffled and dealt,"
                + "\neach player receives half of the cards available. "
                + "\nPlayers then deal the first card from their deck. "
                + "\nThe value of the cards is compared and the player who dealt "
                + "\nthe highest value card takes possession of both cards."
                + "\n(Aces High) If cards are of equal value, war is declared."
                + "\nWhen war is declared, each player will deal three more cards "
                + "\nface down (to expedite the speed of the game), "
                + "\nthen the fourth cards will be compared as before. "
                + "\nWar can go into multiple declared rounds, and it is winner"
                + "\ntakes all. The game continues until one player has won all "
                + "\nthe cards. If a player does not have enough cards to "
                + "\nparticipate when war is delcared, then the war will be"
                + "\ndecided off the players last remaining card");
        System.out.println("Press Enter to continue");
        try{
            System.in.read();
        }catch(Exception ex){}
    }
    
    public static void showYahtzeeRules(){
        System.out.println("Basic rules: players will roll to determine who "
                + "\ngoes first. Highest roll goes first. Each player will roll "
                + "\nfive dice, and may then choose to set the dice into any "
                + "\nscore tray as they see fit. A score tray must be filled"
                + "\nduring each turn. Play continues until each player has taken "
                + "\n13 turns (26 total) and all score trays are filled. "
                + "\nScores are then calculated and the player with the highest "
                + "\nscore wins.");
        System.out.println("Press Enter to continue");
        try{
            System.in.read();
        }catch(Exception ex){}
        System.out.println("Special rules: if a player rolls a yahtzee (5 matching)"
                + "\non their turn but have already filled their yahtzee score "
                + "\ntray with a genuine yahtzee, they may receive an additional "
                + "\n100 points. If the upper section matching the rolled yahtzee "
                + "\nis empty, it must be filled with the roll. Else it may be"
                + "\nplaced in any lower section score tray");
        System.out.println("Press Enter to continue");
        try{
            System.in.read();            
        }catch(Exception ex){}
        System.out.println("Scoring:"
                + "Upper Card:"
                + "\nOnes: One point for each 1 in the tray"
                + "\nTwos: Two points for each 2 in the tray"
                + "\nThrees: Three points for each 3 in the tray"
                + "\nFours: Four points for each 4 in the tray"
                + "\nFives: Five points for each 5 in the tray"
                + "\nSixes: Six points for each 6 in the tray"
                + "\nUpper Bonus: if the player scores 63 or more"
                + "\npoints in the Upper Card, the player receives 35 bonus points");
        System.out.println("Press Enter to continue");
        try{
            System.in.read();
        }catch(Exception ex){}
        System.out.println("Lower Card:"
                + "\n3 of a Kind: If 3 matching numbers, then score is the "
                + "\ntotal of all dice in the tray"
                + "\n4 of a Kind: If 4 matching numbers, then score is the"
                + "\ntotal of all dice in the tray"
                + "\nFull House: Must have 3 matches of one number and 2 matches"
                + "\nof a different number. If met, then score is an additonal 25 points"
                + "\nSmall Straight: Need any 4 numbers in order. If met"
                + "\nthen score is an additional 30 points"
                + "\nLarge Straight: Need all 5 dice in order. If met"
                + "\nthen score is an additional 40 points"
                + "\nYahtzee: All five dice must match. If met, then score"
                + "\nis an additional 50 points"
                + "\nChance: The total of all dice in this tray is equal to "
                + "\nthe points gained");
        System.out.println("Press Senter to continue");
        try{
            System.in.read();
        }catch(Exception ex){}
    }
    
    public static void showTicTacToeRules(){
        System.out.println("Basic Rules:"
                + "\nPlayers take turns placing their mark in any"
                + "\nunoccupied space on the board. X always goes first."
                + "\nPlayers continue until one player has placed three of"
                + "\ntheir marks in any row, column, or diagonal, or all"
                + "\nspaces on the board are filled");
        System.out.println("Press Enter to continue");
        try{
            System.in.read();
        }catch(Exception ex){}
    }
}
