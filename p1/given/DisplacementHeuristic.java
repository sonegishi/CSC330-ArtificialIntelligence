/**
 * This defines the displacement heuristic for the 8-Puzzle.
 * @author Steven Bogaerts
 */
public class DisplacementHeuristic implements Heuristic {
    
    private PuzzleState goalState;
    
    public DisplacementHeuristic(PuzzleState goalState) {
        this.goalState = goalState;
    }
    
    public int distance(PuzzleState state) {
        // *************************** TO DO
        return -1;
    }
    
}