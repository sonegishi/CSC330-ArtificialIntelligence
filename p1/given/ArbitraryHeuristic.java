/**
 * Just for testing purposes, this heuristic gives an arbitrary (but consistent)
 * value for distance. It's based on the hash code of the given state.
 * 
 * @author Steven Bogaerts
 */
public class ArbitraryHeuristic implements Heuristic {
    
    public int distance(PuzzleState state) {
        return Math.abs(state.hashCode() % 13);
    }
    
}
