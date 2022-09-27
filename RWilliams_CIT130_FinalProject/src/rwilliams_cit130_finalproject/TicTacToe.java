
package rwilliams_cit130_finalproject;

import java.util.Scanner;

/*Orginally a homework assignment. Slightly altered to support the Player class
and use properties of class Player in methods */

public class TicTacToe {
    public static char[][] Board = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}} ;
    public static boolean[][] ShadowBoard = {{false,false,false},
        {false,false,false,},{false,false,false}};  //legal move validation
    public static int Turn = 0;
    
    public void ticTacToe(Player p1, Player p2) {
        boolean conclusion = false;        
        Scanner input = new Scanner(System.in);
        Player goFirst;
        Player goSecond;
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Let's decide who goes first with a roll of the dice!");
        Yahtzee fakeGame = new Yahtzee();
        int p1Roll = fakeGame.rollBegin();        
        System.out.println(p1.getName() + " rolled a total of " + p1Roll); 
        int p2Roll = fakeGame.rollBegin();
        System.out.println(p2.getName() + " rolled a total of " + p2Roll);       
        if (p1Roll >= p2Roll){
            goFirst = p1;            
            goSecond = p2;
            System.out.println(goFirst.getName() + " will go first and "
            + goSecond.getName() + " will go second.");
        } else {
            goFirst = p2;
            goSecond = p1;
            System.out.println(goFirst.getName() + " will go first and "
            + goSecond.getName() + " will go second.");
        }
        goFirst.symbol = 'X';
        goSecond.symbol = 'O';
        do{
            playTicTacToe(goFirst, goSecond);
            System.out.print("Would you like to play again? Y/N: ");
            String play = input.next();
            play = play.toLowerCase();
            if (play.charAt(0) == 'n'){
                conclusion = true;
            }
            else {
                conclusion = false;
                Turn = 0;
                for(int x = 0; x < Board.length; x++){
                    for(int y = 0; y < Board[x].length; y++){
                        Board[x][y] = ' ';
                        ShadowBoard[x][y] = false;
                    }
                }
            }
            
        }while (!conclusion);
    }
    
    public static void playTicTacToe(Player goFirst, Player goSecond){
        System.out.println("Welcome to TicTacToe!");
        do{
            displayBoard();
            System.out.println("Please make a move");
            makeAMove(goFirst, goSecond);
        } while (!determineWin());
    }
    
    public static void displayBoard(){
        for (int x = 0; x <Board.length; x++){
            System.out.println();
            System.out.println("===========");
            for(int y = 0; y <Board[x].length; y++){
                System.out.print("|" + Board[x][y] + "|" + " ");
            }
            System.out.println();
            System.out.println("===========");
        }
    }
    
    public static void makeAMove(Player goFirst, Player goSecond){
        Scanner input = new Scanner(System.in);
        System.out.println("It is " + player(goFirst, goSecond) + "'s turn.");
        int x = 0;
        int y = 0;
        boolean legal = false;
        do{
            System.out.print("Please enter the row where you would like to "
                    + "place your mark: ");
            try{
                x = input.nextInt();
            }catch(Exception ex){
                System.out.println("Entry not valid, please try again");
                break;
            }
            System.out.print("Please enter the column where you would like to "
                    + "place your mark: ");
            try{
                y = input.nextInt();
            }catch(Exception ex){
                System.out.println("Entry not valid, please try again");
                break;
            }
            try{
                if(!ShadowBoard[x][y]){
                    Board[x][y] = playerSymbol(goFirst, goSecond);
                    ShadowBoard[x][y] = true;
                    Turn++;
                    legal = true;
                }else {
                    System.out.println("That space is occupied, pick another");
                }
            }catch(Exception ex){
                System.out.println("Entry out of bounds");
                System.out.println("Please note that columns and rows are"
                        + "numbered 0-2, not 1-3");
                System.out.println("Please try again");
                legal = false;
            }
        } while (!legal);
    }
    
    public static String player(Player goFirst, Player goSecond){
        if (Turn%2 == 0){
            return goFirst.getName();
        }
        else{
            return goSecond.getName();
        }
    }
    
    public static char playerSymbol(Player goFirst, Player goSecond){
        if (Turn%2 == 0){
            return goFirst.symbol;
        }
        else{
            return goSecond.symbol;
        }
    }
    
    public static boolean determineWin(){           
        //declare determinates
        int row1 = 0;
        int row2 = 0;
        int row3 = 0;
        int col1 = 0;
        int col2 = 0;
        int col3 = 0;       
        int diag1 = 0;        
        int diag2 = 0;
        int fullShadow = 0;
        //add row1
        for (int x = 0; x < Board[0].length; x++){
            row1 += Board[0][x];
        }
        //add row2
        for (int x  = 0; x <Board[1].length; x++){
            row2 += Board[1][x];
        }
        //add row3
        for(int x = 0; x < Board[2].length; x++){
            row3 += Board[2][x];
        }
        //add col1
        for(int x = 0; x < Board.length; x++){
            col1 += Board[x][0];
        }
        //add col2
        for(int x = 0; x < Board.length; x++){
            col2 += Board[x][1];
        }
        //add col3
        for(int x = 0; x < Board.length; x++){
            col3 += Board[x][2];
        }
        //add diag1
        for(int x = 0; x < Board.length; x++){
            for(int y = 0; y < Board[x].length; y++){
                if ( x == 0 && y == 0){
                    diag1+= Board[x][y];
                }
                if( x==1 && y ==1){
                    diag1+= Board[x][y];
                }
                if (x == 2 && y ==2){
                    diag1+= Board[x][y];
                }
            }
        };
        //add diag2
        for(int x = 0; x < Board.length; x++){
            for(int y = 0; y < Board[x].length; y++){
                if ( x == 0 && y == 2){
                    diag1+= Board[x][y];
                }
                if( x==1 && y ==1){
                    diag1+= Board[x][y];
                }
                if (x == 2 && y ==0){
                    diag1+= Board[x][y];
                }
            }
        };
        //add ShadowBoard
        for(int x = 0; x < ShadowBoard.length; x++){
            for(int y = 0; y < ShadowBoard[x].length; y++){
                if(ShadowBoard[x][y]){
                    fullShadow++;
                };
            }
        }
       
        //determine win on row1
        if (row1 == 264 | row1 == 237){
            if(row1 == 264){
                displayBoard();
                System.out.println("Player X wins!");
                return true;
            } else{
                displayBoard();
                System.out.println("Player O wins!");
                return true;
            }
        }
        //determine win on row 2
        if (row2 == 264 | row2 == 237){
            if(row2 == 264){
                displayBoard();
                System.out.println("Player X wins!");
                return true;
            } else{
                displayBoard();
                System.out.println("Player O wins!");
                return true;
            }
        }
        //determine win on row 3
        if (row3 == 264 | row3 == 237){
            if(row3 == 264){
                displayBoard();
                System.out.println("Player X wins!");
                return true;
            } else{
                displayBoard();
                System.out.println("Player O wins!");
                return true;
            }
        }
        //determine win on col 1
        if (col1 == 264 | col1 == 237){
            if(col1 == 264){
                displayBoard();
                System.out.println("Player X wins!");
                return true;
            } else{
                displayBoard();
                System.out.println("Player O wins!");
                return true;
            }
        }
        //determine win on col 2
        if (col2 == 264 | col2 == 237){
            if(col2 == 264){
                displayBoard();
                System.out.println("Player X wins!");
                return true;
            } else{
                displayBoard();
                System.out.println("Player O wins!");
                return true;
            }
        }
        //determine win on col 3
        if (col3 == 264 | col3 == 237){
            if(col3 == 264){
                displayBoard();
                System.out.println("Player X wins!");
                return true;
            } else{
                displayBoard();
                System.out.println("Player O wins!");
                return true;
            }
        }
        //deteremine diag 1
        if (diag1 == 264 | diag1 == 237){
            if(diag1 == 264){
                displayBoard();
                System.out.println("Player X wins!");
                return true;
            } else{
                displayBoard();
                System.out.println("Player O wins!");
                return true;
            }
        }
        //determine diag 2
        if (diag2 == 264 | diag2 == 237){
            if(diag2 == 264){
                displayBoard();
                System.out.println("Player X wins!");
                return true;
            } else{
                displayBoard();
                System.out.println("Player O wins!");
                return true;
            }
        }
        //determine cat's game
        if (fullShadow == 9){
            displayBoard();
            System.out.println("No winner! Cat's Game! =^_^=");
            return true;
        }
        return false;
    }
}
