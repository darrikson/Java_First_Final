
package rwilliams_cit130_finalproject;

/*Methods for the individual score trays. Will include methods for 
clearing, filling, and displaying trays. */
public class ScoreTray {
    public int[] diceRoll;
    public boolean Full;
    public String name;
    
    public ScoreTray(String name){
        diceRoll = new int[5];
        Full = false;
        this.name = name;
    }
    
    public void clearTray(){
        for (int i = 0; i < diceRoll.length; i++) {
            diceRoll[i] = 0;
        }
        Full = false;
    }
    
    public boolean fillTray(int[] roll){
        if (!Full) {          
            for (int i = 0; i < roll.length; i++) {
            diceRoll[i] = roll[i];
            }
            Full = true;
            return true;
        }else{
            System.out.println("That tray is already full");
            return false;
        }
    }
    
    public void displayTray(){
        System.out.println(name);
        System.out.println("====================");
        for (int i = 0; i < diceRoll.length; i++) {
            System.out.print("|" + diceRoll[i] + "| ");
        }
        System.out.println();
        System.out.println("====================");
    }
}
