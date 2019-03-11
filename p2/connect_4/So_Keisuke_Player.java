import java.util.ArrayList;

/**
 * This is where you'll write your code for this project. Be sure to rename the class
 * to something unique just for your team.
 */
public class So_Keisuke_Player extends PlayerDef {
    public static final int WINDOW_SCORE_1 = 1;
    public static final int WINDOW_SCORE_2 = 2;
    public static final int WINDOW_SCORE_3 = 3;
    public static final int WINDOW_SCORE_4 = 4;
    public static final int NUM_COLS = 7;
    public static final int NUM_ROWS = 6;

    State currState;
    ArrayList<State> states;
    /**
     * Create a so_keisuke_Player object.
     * Note that setSymbol and setTime must be called before the object can be used.
     * This is done in the Game constructor.
     */
    public So_Keisuke_Player() {
        super(); // call super class constructor (must be first line of this method)
        // Do whatever other initialization you need here
        states = new ArrayList<State>();
    }

    /**
     * This is for part 2 - ignore it for part 1.
     * Ultimately, this will be minimax with alpha-beta pruning, but get plain
     * old minimax working first, and save a copy of it in case you mess something
     * up while working on alpha-beta pruning.
     * 
     * @return the column of the desired move
     */
    @Override
    public int getMove(State currState, int timeLeft) {
        return -1;
    }

    /**
     * Returns an ArrayList of State objects, representing the children
     * of the given state.
     * An ArrayList is used here, in case the constant-time indexing is valuable
     * to you in your work.
     * I recommend using the index in the ArrayList as the column ID. For example, the state
     * resulting from a move in column 0 should be in index 0 of the ArrayList, etc.
     * If a column is full, use a null placeholder in that spot in the ArrayList.
     * @param currState the state to expand
     * @param symbol the symbol of the player that is about to move. Note that this
     * is not necessarily the same as the playerSymbol field inherited from PlayerDef!
     */
    protected ArrayList<State> expand(State currState, char symbol) {
        State copyCurrState;
        for (int colID = 0; colID < 7; colID++){
            copyCurrState = new State(currState);
            if (copyCurrState.move(colID, symbol))
                states.add(copyCurrState);
            else
                states.add(null);
        }
        return states;
    }

    /**
     * Returns an evaluation of the given state from the perspective of playerSymbol
     * (inherited from PlayerDef).<BR>
     * A positive score is good for this player - the higher the better.
     * A negative score is bad for this player - the lower the worse.
     * Always return a score from the perspective of *this* player (playerSymbol),
     * even in the midst of the minimax algorithm.<BR>
     * <BR>
     * Make sure evalState is zero-sum.  That is, if this player evaluates
     * a state as 65, then the other player using the same evaluation
     * function would evaluate it to -65.
     * 
     */
    protected int eval(State currState) {
        this.currState = currState;
        int total_score = 0;
        int count = 0;
        for(int colID = 0; colID < NUM_COLS; colID++) {
            for(int rowID = 0; rowID < NUM_ROWS; rowID++) {
                total_score += checkWindow(rowID, colID, 1, 0);
                total_score += checkWindow(rowID, colID, 0, 1);
                total_score += checkWindow(rowID, colID, -1, 1);
                total_score += checkWindow(rowID, colID, 1, 1);
            }
        }
        return total_score;
    }

    protected int checkWindow(int startRowID, int startColID, int rowChange, int colChange) {
        char currPiece;
        int score = 0;
        int[] window_count_arr = new int[3];
        boolean was_one_window_player = false;
        boolean was_two_window_player = false;
        boolean was_one_window_opponent = false;
        boolean was_two_window_opponent = false;
        for(int i = 0; startRowID + i*rowChange >= 0 && startColID + i*colChange >= 0 && startRowID + i*rowChange < NUM_ROWS && startColID + i*colChange < NUM_COLS; i++) {
            currPiece = currState.getPiece(startRowID + i*rowChange, startColID + i*colChange);
            if (currPiece == super.playerSymbol){
                if(was_two_window_player == true) {
                    window_count_arr[2] += WINDOW_SCORE_3;
                } else if (was_one_window_player == true) {
                    window_count_arr[1] += WINDOW_SCORE_2;
                    was_two_window_player = true;
                } 
                window_count_arr[0] += WINDOW_SCORE_1;
                was_one_window_player = true;
                was_one_window_opponent = false;
                was_two_window_opponent = false;
            }
            else if (currPiece != '_'){
                if(was_two_window_opponent == true) {
                    window_count_arr[2] -= WINDOW_SCORE_3;
                } else if (was_one_window_opponent == true) {
                    window_count_arr[1] -= WINDOW_SCORE_2;
                    was_two_window_opponent = true;
                } 
                window_count_arr[0] -= WINDOW_SCORE_1;
                was_one_window_opponent = true;
                was_one_window_player = false;
                was_two_window_player = false;
            } else {
                // when it is blank
            }
        }
        score += window_count_arr[0];
        score += window_count_arr[1];
        score += window_count_arr[2];
        return score;
    }

}