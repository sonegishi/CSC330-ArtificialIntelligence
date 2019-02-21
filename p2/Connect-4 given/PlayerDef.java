/**
 * This abstract class represents a Connect-4 player. The getMove method
 * should be implemented to define a player.
 * @author Steven Bogaerts
 */
public abstract class PlayerDef {

    protected char playerSymbol;
    protected int totalTime;
    
    /**
     * Constructs a PlayerDef object.
     * Note that setSymbol and setTime must be called before the PlayerDef object can be used.
     * This is done in the Game constructor.
     */
    public PlayerDef() {
    }
    
    /**
     * Set the symbol ('X' or 'O') for this player.
     * @param playerSymbol should be either 'X' or 'O'.
     */
    public void setSymbol(char playerSymbol) {
        this.playerSymbol = playerSymbol;
    }
    
    /**
     * Set the total time this player will have in a game.
     * @param the total time allowed in a game, or -1 if time is unlimited.
     */
    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }
    
    /**
     * Returns the column ID (0, 1, 2, 3, 4, 5, or 6) of the move
     * this player would like to make.
     */
    public abstract int getMove(State currState, int timeLeft);
    
}
