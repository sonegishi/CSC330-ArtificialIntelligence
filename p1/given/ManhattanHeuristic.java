/**
 * This defines the Manhattan heuristic for the 8-Puzzle.
 * @author Steven Bogaerts
 */
public class ManhattanHeuristic implements Heuristic {
    
    private PuzzleState goalState;
    
    public ManhattanHeuristic(PuzzleState goalState) {
        this.goalState = goalState;
    }
    
    public int distance(PuzzleState state) {
        // *************************** TO DO
        return -1;
    }
    
}