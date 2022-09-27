
package rwilliams_cit130_finalproject;


public class Player {
    private String Name;
    public YahtzeeScoreCard playerCard;
    public int topCard;
    public int cardsOwned;
    public int ownerValue;
    public char symbol;
    
    public void setName(String name){
        Name = name;
    }
    
    public String getName(){
        return Name;
    }
    
    public Player(){        
    }
    
    public Player(String name){
        setName(name);
        playerCard = new YahtzeeScoreCard();
    }
}
