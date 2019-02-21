/**
 * This class defines a human Connect-4 player.
 * @author Steven Bogaerts
 */
public class HumanDef extends RetryPlayerDef {
    
    private final java.util.Scanner input;
    
    /**
     * Create a HumanDef object.
     * Note that setSymbol and setTime must be called before the object can be used.
     * This is done in the Game constructor.
     */
    public HumanDef() {
        super(); // call super class constructor
        
        input = new java.util.Scanner(System.in);
    }
    
    /**
     * Asks the player to enter a move throw the keyboard.
     * Erroneous input leads to an invalid move to be returned.
     */
    @Override
    protected int getMoveTry() {
        System.out.print("Enter move: ");
        
        int col;
        try {
            col = input.nextInt(); // Get input from the keyboard
        }
        catch (java.util.InputMismatchException e) { // If invalid type is entered,
            input.nextLine(); // clear the buffer of erroneous input,
            return -1;        // and just return an invalid move.
        }
        
        return col;
    }
    
    /**
     * Prints a message asking the human player to try again, 
     * in the event that an invalid move is attempted.
     */
    @Override
    protected void invalidMoveResponse() {
        System.out.print("Invalid move, human. ");
    }
    
    
}