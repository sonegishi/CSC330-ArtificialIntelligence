/**
 * Defines a computer player that just makes random moves. A random move will be
 * repeatedly attempted until a valid move is found.
 * @author Steven Bogaerts
 */
public class RandomDef extends RetryPlayerDef {
    
    private final java.util.Random rng;
    
    /**
     * Create a RandomDef object.
     * Note that setSymbol and setTime must be called before the object can be used.
     * This is done in the Game constructor.
     */
    public RandomDef() {
        super(); // call super class constructor
        
        rng = new java.util.Random();
    }
    
    /**
     * Returns a random move, without worrying about whether the column is
     * full or not.
     */
    @Override
    protected int getMoveTry() {
        return rng.nextInt(State.NUM_COLS); // get a random move
    }
    
    /**
     * Does nothing. In the event of an invalid move, the
     * random player just tries again.
     */
    @Override
    protected void invalidMoveResponse() {
        // No response to invalid move - just try again.
    }
    
}