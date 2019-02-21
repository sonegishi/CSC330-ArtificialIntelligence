/**
 * This RetryPlayerDef abstract class provides a framework of functionality
 * for a player that is asked to continue retrying until a valid move is provided.
 * It is designed in particular for random and human players.
 * @author Steven Bogaerts
 */
public abstract class RetryPlayerDef extends PlayerDef {
    
    /**
     * Constructs a PlayerDef object.
     * Note that setSymbol and setTime must be called before the object can be used.
     * This is done in the Game constructor.
     */
    public RetryPlayerDef() {
    }

    /**
     * Returns the column ID (0, 1, 2, 3, 4, 5, or 6) of the move
     * this player would like to make.
     * If the player attempts an invalid move, the player is invited to try again.
     */
    @Override
    public int getMove(State currState, int timeLeft) {
        int theMove = getMoveTry();
        while (! currState.isValidMove(theMove)) {
            invalidMoveResponse();
            theMove = getMoveTry();
        }
        return theMove;
    }
    
    /**
     * Returns the column ID that this player will attempt to move in.
     */
    protected abstract int getMoveTry();

    /**
     * Defines what should happen if an invalid move is attempted.
     */
    protected abstract void invalidMoveResponse();
    
}