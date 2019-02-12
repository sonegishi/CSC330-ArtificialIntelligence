/**
 * This is a "dummy" heuristic for use by BFS.
 * @author Steven Bogaerts
 */
public class NoHeuristic implements Heuristic {
    public int distance(PuzzleState state) {
        return 0;
    }
}
