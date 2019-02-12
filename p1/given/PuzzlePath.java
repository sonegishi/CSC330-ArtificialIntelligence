import java.util.LinkedList;

/**
 * A path is a list of states, from start to finish.
 * You'll also want to keep track of the cost of the path...
 * @author Steven Bogaerts
 */
public class PuzzlePath {
    
    private LinkedList<PuzzleState> states;
    private Heuristic h;
    private int cost;
    
    /**
     * Construct a new path, starting at the given state,
     * that will use the given heuristic.
     */
    public PuzzlePath(PuzzleState startState, Heuristic initialH) {
        states = new LinkedList<PuzzleState>();
        states.add(startState);
        h = initialH;
        updateCost();
    }
    
    /**
     * Adds a state to the end of this path, and updates the cost
     * of the path accordingly.
     */
    public void addState(PuzzleState stateToAdd) {
        states.add(stateToAdd);
        updateCost();
    }
    
    /**
     * Update the total cost of this path (presumably because a new state has been
     * added). The total cost is path cost plus heuristic cost.
     */
    private void updateCost() {
        PuzzleState lastState = stateAtEndOfPath();
        if (lastState != null) // make sure not the start of a makeCopy
            cost = (states.size()-1) + h.distance(lastState);
    }
    
    /**
     * Returns the cost of this path.
     */
    public int getCost() {
        return cost;
    }
    
    /**
     * Returns the state at the end of this path.
     */
    public PuzzleState stateAtEndOfPath() {
        return states.get(states.size()-1);
    }
    
    /**
     * Makes a copy of this path.
     */
    public PuzzlePath makeCopy() {
        PuzzlePath copy = new PuzzlePath(null, h);
        copy.states = new LinkedList<PuzzleState>(this.states); // make a copy
        copy.cost = this.cost;
        copy.h = this.h;
        return copy;
    }
    
    /**
     * Returns a String representation of this path.
     */
    public String toString() {
        return "\n(" + cost + ": " + states.toString() + ")";
    }
    
    /**
     * Returns the length of this path.
     */
    public int length() {
        return states.size();
    }
    
}