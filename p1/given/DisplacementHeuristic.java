/**
 * This defines the displacement heuristic for the 8-Puzzle.
 * @author Steven Bogaerts
 */
public class DisplacementHeuristic implements Heuristic {

    private PuzzleState goalState;

    public DisplacementHeuristic(PuzzleState goalState) {
        this.goalState = goalState;
    }

    /**
     * Returns a distance based on the displacement heuristic which is given
     * by counting the number of tiles if a tile is not in the right position.
     */
    public int distance(PuzzleState state) {
        int count = 0;
        for(int i = 1; i < 9; i++) {
            if(state.getTileAt(i - 1) != i) {
                count++;
            }
        }
        return count;
    }
    
}