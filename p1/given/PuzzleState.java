import java.util.LinkedList;
import java.util.Arrays;

/**
 * Represents a state (placement of tiles) of the puzzle.
 * Starter code.
 * @author Steven Bogaerts
 */
public class PuzzleState {
    
    /**
     * A 1-D array representation of the tile placement.
     */
    private int[] tiles;
    
    /**
     * Assumes that initialTiles is the valid format:
     * length 9,
     * containing the integers 0 through 8 inclusive in some order
     * with no repeats
     * where 0 represents the blank.
     */
    public PuzzleState(int[] initialTiles) {
        tiles = initialTiles;
    }

    /**
     * Returns the position of a given tile in the puzzle state.
     * Returns -1 if not found. (Should never happen for valid input.)
     */
    public int posOf(int tile) {
        for(int i = 0; i < tiles.length; i++) {
            if(tiles[i] == tile) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Returns the tile value at the given index (0 through 8, inclusive).
     */
    public int getTileAt(int index) {
        // *************************** TO DO
        return -1;
    }
    
    /**
     * Returns a list of PuzzleStates that can be reached from this PuzzleState
     * in one move. It's easiest to think of this as moving the blank.
     */
    public LinkedList<PuzzleState> expand() {
        // *************************** TO DO
        return null;
    }
    
    /**
     * Returns true if two PuzzleState objects are equivalent,
     * false otherwise. Required for proper operation of a HashSet of PuzzleStates.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PuzzleState))
            return false;
        else
            return Arrays.equals(this.tiles, ((PuzzleState) other).tiles);
    }
    
    /**
     * Required to be able to have a HashSet of PuzzleState objects.
     * Objects for which .equals() returns true must have the same hashCode.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(tiles);
    }
    
    /**
     * Returns a String representation of the state.
     */
    @Override
    public String toString() {
        String result = "\n";
        for(int i = 0; i < tiles.length; i++) {
            if ((i > 0) && i % 3 == 0)
                result += "\n";
                
            if (tiles[i] == 0)
                result += "_ ";
            else
                result += tiles[i] + " ";
        }
                
        return result + "\n";
    }
}