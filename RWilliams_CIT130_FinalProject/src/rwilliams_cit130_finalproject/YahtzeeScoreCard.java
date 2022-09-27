
package rwilliams_cit130_finalproject;

public class YahtzeeScoreCard {
    
    ScoreTray ones;
    ScoreTray twos;
    ScoreTray threes;
    ScoreTray fours;
    ScoreTray fives;
    ScoreTray sixes;
    ScoreTray threeOfAKind;
    ScoreTray fourOfAKind;
    ScoreTray fullHouse;
    ScoreTray smallStraight;
    ScoreTray largeStraight;
    ScoreTray yahtzee;
    ScoreTray chance;
    ScoreTray[] trayHolder = new ScoreTray[13]; 
    int totalScore = 0;
    
    public YahtzeeScoreCard(){
        ones = new ScoreTray("Ones");
        twos = new ScoreTray("Twos");
        threes = new ScoreTray("Threes");
        fours = new ScoreTray("Fours");
        fives = new ScoreTray("Fives");
        sixes = new ScoreTray("Sixes");
        threeOfAKind = new ScoreTray("Three of a Kind");
        fourOfAKind = new ScoreTray("Four of a Kind");
        fullHouse = new ScoreTray("Full House");
        smallStraight = new ScoreTray("Small Straight");
        largeStraight = new ScoreTray("Large Straight");
        yahtzee = new ScoreTray("Yahtzee");
        chance = new ScoreTray("Chance");
        trayHolder[0] = chance;
        trayHolder[1] = ones;
        trayHolder[2] = twos;
        trayHolder[3] = threes;
        trayHolder[4] = fours;
        trayHolder[5] = fives;
        trayHolder[6] = sixes;
        trayHolder[7] = threeOfAKind;
        trayHolder[8] = fourOfAKind;
        trayHolder[9] = fullHouse;
        trayHolder[10] = smallStraight;
        trayHolder[11] = largeStraight;
        trayHolder[12] = yahtzee;
        
    }
    
    //All scoring methods are unique. Which was a pain in the butt
    public int scoreOnes(){
        int score = 0;
        for (int i = 0; i < ones.diceRoll.length; i++) {
            if (ones.diceRoll[i] == 1) {
                score++;
            }
        }
        return score;
    }
    
    public int scoreTwos(){
        int score = 0;
        for (int i = 0; i < twos.diceRoll.length; i++) {
            if (twos.diceRoll[i] == 2) {
                score += 2;
            }
        }
        return score;
    }
    
    public int scoreThrees(){
        int score = 0;
        for (int i = 0; i < threes.diceRoll.length; i++) {
            if (threes.diceRoll[i] == 3) {
                score += 3;
            }
        }
        return score;
    }
    
    public int scoreFours(){
        int score = 0;
        for (int i = 0; i < fours.diceRoll.length; i++) {
            if (fours.diceRoll[i] == 4) {
                score += 4;
            }
        }
        return score;
    }
    
    public int scoreFives(){
        int score = 0;
        for (int i = 0; i < fives.diceRoll.length; i++) {
            if (fives.diceRoll[i] == 5) {
                score += 5;
            }
        }
        return score;
    }
    
    public int scoreSixes(){
        int score = 0;
        for (int i = 0; i < sixes.diceRoll.length; i++) {
            if (sixes.diceRoll[i] == 6) {
                score += 6;
            }
        }
        return score;
    }
    
    public int scoreThreeOfAKind(){
        //tabulate occurence of numbers
        int ones = 0;
        int twos = 0;
        int threes = 0;
        int fours = 0;
        int fives = 0;
        int sixes = 0;
        for (int i = 0; i < threeOfAKind.diceRoll.length; i++) {
            int value = threeOfAKind.diceRoll[i];
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
        //determine qualify, any 3 matching results
        if (ones > 2 || twos > 2 || threes > 2 
                || fours > 2 || fives > 2 || sixes > 2) {
            int score = 0;
            for (int i = 0; i < threeOfAKind.diceRoll.length; i++) {
                score += threeOfAKind.diceRoll[i];
            }
            return score;
        }
        else{
            return 0;
        }
    }
    
    public int scoreFourOfAKind(){
        int ones = 0;
        int twos = 0;
        int threes = 0;
        int fours = 0;
        int fives = 0;
        int sixes = 0;
        for (int i = 0; i < fourOfAKind.diceRoll.length; i++) {
            int value = fourOfAKind.diceRoll[i];
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
        if (ones > 3 || twos > 3 || threes > 3 
                || fours > 3 || fives > 3 || sixes > 3) {
            int score = 0;
            for (int i = 0; i < fourOfAKind.diceRoll.length; i++) {
                score += fourOfAKind.diceRoll[i];
            }
            return score;
        }
        else{
            return 0;
        }
    }
    
    public int scoreYahtzee(){
        int ones = 0;
        int twos = 0;
        int threes = 0;
        int fours = 0;
        int fives = 0;
        int sixes = 0;
        for (int i = 0; i < yahtzee.diceRoll.length; i++) {
            int value = yahtzee.diceRoll[i];
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
            return 50;
        }
        else{
            return 0;
        }
    }
    
    public int scoreFullHouse(){
        /*need three of one number and two of a different number. Both 
        conditions must be met to score */
        boolean threeMatch = false;
        boolean twoMatch = false;
        int ones = 0;
        int twos = 0;
        int threes = 0;
        int fours = 0;
        int fives = 0;
        int sixes = 0;
        for (int i = 0; i < fullHouse.diceRoll.length; i++) {
            int value = fullHouse.diceRoll[i];
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
        if (ones == 3 || twos == 3 || threes == 3
                || fours == 3 || fives == 3 || sixes == 3) {
            threeMatch = true;
        }
        if ( ones == 2 || twos == 2 || threes == 2 
                || fours == 2 || fives == 2 || sixes == 2) {
            twoMatch = true;
        }
        if (twoMatch && threeMatch) {
            return 25;
        }else {
            return 0;
        }
    }
    
    public int scoreSmallStraight(){
        // must have four consecutive numbers to qualify for score
        //put dice in order first
        for (int x = 0; x < smallStraight.diceRoll.length; x++) {
            int lowest = smallStraight.diceRoll[x];
            for (int y = x + 1; y < smallStraight.diceRoll.length; y++) {
                if (smallStraight.diceRoll[y] < lowest) {
                    int temp = lowest;
                    smallStraight.diceRoll[x] = smallStraight.diceRoll[y];
                    smallStraight.diceRoll[y] = temp;
                }
            }
        }
        //calculate number of times dice are in numerical order
        int value = smallStraight.diceRoll[0];
        int occurence = 0;
        for (int i = 0; i < smallStraight.diceRoll.length; i++) {
            if (value + 1 == smallStraight.diceRoll[i]) {
                occurence++;
            }
            value = smallStraight.diceRoll[i];
        }
        if (occurence == 4) {
            return 30;
        }else {
            return 0;
        }
    }
    
    public int scoreLargeStraight(){
        for (int x = 0; x < largeStraight.diceRoll.length; x++) {
            int lowest = largeStraight.diceRoll[x];
            for (int y = x + 1; y < largeStraight.diceRoll.length; y++) {
                if (largeStraight.diceRoll[y] < lowest) {
                    int temp = lowest;
                    largeStraight.diceRoll[x] = largeStraight.diceRoll[y];
                    largeStraight.diceRoll[y] = temp;
                }
            }
        }
        int value = largeStraight.diceRoll[0];
        int occurence = 0;
        for (int i = 0; i < largeStraight.diceRoll.length; i++) {
            if (value + 1 == largeStraight.diceRoll[i]) {
                occurence++;
            }
            value = largeStraight.diceRoll[i];
        }
        if (occurence == 5) {
            return 40;
        }else {
            return 0;
        }
    }
    
    public int scoreChance(){
        int score = 0;
        for (int i = 0; i < chance.diceRoll.length; i++) {
            score += chance.diceRoll[i];
        }
        return score;
    }
    
    public int scoreUpper(){
        int score = scoreOnes();
        score += scoreTwos();
        score += scoreThrees();
        score += scoreFours();
        score += scoreFives();
        score += scoreSixes();
        return score;
    }
    
    public boolean determineUpperBonus(){
        if (scoreUpper() > 62) {
            return true;
        }else {
            return false;
        }
    }
    
    public int scoreLower(){
        int score = scoreThreeOfAKind();   
        score += scoreFourOfAKind();
        score += scoreYahtzee();        
        score += scoreFullHouse();     
        score += scoreSmallStraight();    
        score += scoreLargeStraight();     
        return score;
    }
    
    public void determineScore(){
        totalScore += scoreUpper();
        totalScore += scoreLower();
        if (determineUpperBonus()) {
            totalScore += 35;
        }
    }
    
    public void clearCard(){
        for (ScoreTray tray : trayHolder) {
            tray.clearTray();
        }
        totalScore = 0;
    }
    
    public void displayCard(Player PL){
        System.out.println(PL.getName());
        System.out.println("Upper Card");        
        for (int i = 1; i < 7; i++) {
            PL.playerCard.trayHolder[i].displayTray();
        }
        System.out.println("Lower Card");
        for (int i = 8; i < PL.playerCard.trayHolder.length; i++) {
            PL.playerCard.trayHolder[i].displayTray();
        }
        PL.playerCard.trayHolder[0].displayTray();
    }
}
