import java.lang.Math;

/**
 * This defines the Manhattan heuristic for the 8-Puzzle.
 * @author Steven Bogaerts
 */
public class ManhattanHeuristic implements Heuristic {
    
    private PuzzleState goalState;
    
    public ManhattanHeuristic(PuzzleState goalState) {
        this.goalState = goalState;
    }
    
    /**
     * Returns a distance based on the Manhattan heuristic which is given
     * by counting the number of movements each tile has to make in order
     * to get all the tiles to the appropriate positions.
     */
    public int distance(PuzzleState state) {
        int count = 0;
        for(int i = 1; i < 9; i++) {
            if(state.getTileAt(i - 1) != i) {
                int row = (i - 1) / 3;
                int col = (i - 1) % 3;
                int currNum = state.posOf(i);
                int currRow = currNum / 3;
                int currCol = currNum % 3;
                int diff = Math.abs(row - currRow) + Math.abs(col - currCol);
                count += diff;
            }
        }
        return count;
    }
    
}