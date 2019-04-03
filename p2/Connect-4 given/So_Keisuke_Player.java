import java.util.ArrayList;



/**
 * This is where you'll write your code for this project. Be sure to rename the class
 * to something unique just for your team.
 */
public class So_Keisuke_Player extends PlayerDef {
    public static void main(String [] args)
	{
		humanVsComputer();
    }
    
    public static void humanVsComputer() {
        Game g = new Game(new HumanDef(), new So_Keisuke_Player(), -1);
        g.play();
    }

    public static final int WINDOW_SCORE_1 = 1;
    public static final int WINDOW_SCORE_2 = 8;
    public static final int WINDOW_SCORE_3 = 27;
    public static final int WINDOW_SCORE_4 = 2019;
    public static final int NUM_COLS = 7;
    public static final int NUM_ROWS = 6;

    public static final int DEPTH_LIMIT = 5;

    private State currState;
    private char opponentSymbol;
    /**
     * Create a so_keisuke_Player object.
     * Note that setSymbol and setTime must be called before the object can be used.
     * This is done in the Game constructor.
     */
    public So_Keisuke_Player() {
        super(); // call super class constructor (must be first line of this method)
        if (this.playerSymbol == 'O')
            opponentSymbol = 'X';
        else 
            opponentSymbol = 'O';
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
        return minimaxDecision(currState);
    }

    private int minimaxDecision(State state){
        if(state.isTerminal()){
            return eval(state); 
        }
        else{
            int max = -10000;
            int value = 0;
            int index = 0;
            int depth = 0;
            ArrayList<State> arr = expand(state, this.playerSymbol);
            for (int i = 0; i < arr.size(); i++){
                value = minValue(arr.get(i), depth+1);
                if (arr.get(i) != null){
                    if (value > max){
                        max = value;
                        index = i;
                    }
                }
            }
            return index;
        }
    }
        

    private int maxValue(State state, int depth){
        if(state.isTerminal() || depth > DEPTH_LIMIT){
            return eval(state); 
        }
        else{
            int max = -10000;
            int value = 0;
            ArrayList<State> arr = expand(state, this.playerSymbol);
            for (State s : arr){
                if (s != null){
                    value = minValue(s, depth+1);
                    if (value > max)
                        max = value;
                }
            }
            return max;
        }
    }

    private int minValue(State state, int depth){
        if(state.isTerminal() || depth > DEPTH_LIMIT){
            return eval(state); 
        }
        else{
            int min = 10000;
            int value = 0;
            ArrayList<State> arr = expand(state, opponentSymbol);
            for (State s : arr){
                if (s != null){
                    value = maxValue(s, depth+1);
                    if (value < min)
                        min = value;
                }
            }
            return min;
        }
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
        ArrayList<State> states = new ArrayList<State>();
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
                // top to bottom
                total_score += checkWindow(rowID, colID, 1, 0);
                // left to right 
                total_score += checkWindow(rowID, colID, 0, 1);
                // bottom left to top right
                total_score += checkWindow(rowID, colID, -1, 1);
                // top left to bottom right
                total_score += checkWindow(rowID, colID, 1, 1);
            }
        }
        return total_score;
    }

    /**
     * Returns scores in the given range of rows and columns.
     * @param startRowID: Starting 
     * @param startColID: 
     * @param rowChange: 
     * @param colChange: 
     */
    protected int checkWindow(int startRowID, int startColID, int rowChange, int colChange) {
        char currPiece;
        // int[] window_count_arr = new int[3];
        // set to default false value for boolean
        int score = 0;
        boolean[] is_window_connected_player = new boolean[3];
        boolean[] is_window_connected_opponent = new boolean[3];   
        for(int i = 0;
            (startRowID + i*rowChange >= 0) && (startColID + i*colChange >= 0) &&
            (startRowID + i*rowChange < NUM_ROWS) && (startColID + i*colChange < NUM_COLS) && (i < 4);
            i++) {
            currPiece = this.currState.getPiece(startRowID + i*rowChange, startColID + i*colChange);
            if (currPiece == this.playerSymbol)
            {
                if (is_window_connected_player[2])
                {
                    score += WINDOW_SCORE_4;
                } 
                else if (is_window_connected_player[1]) 
                {
                    score += WINDOW_SCORE_3;
                    is_window_connected_player[2] = true;
                } 
                else if (is_window_connected_player[0]) 
                {
                    score += WINDOW_SCORE_2;
                    is_window_connected_player[1] = true;
                } 
                else 
                {
                    score += WINDOW_SCORE_1;
                    is_window_connected_player[0] = true;
                }
                is_window_connected_opponent = new boolean[3];
            }
            else if (currPiece != '_')
            {
                if (is_window_connected_opponent[2])
                {
                    score -= WINDOW_SCORE_4;
                } 
                else if (is_window_connected_opponent[1])
                {
                    score -= WINDOW_SCORE_3;
                    is_window_connected_opponent[2] = true;
                } 
                else if (is_window_connected_opponent[0]) 
                {
                    score -= WINDOW_SCORE_2;
                    is_window_connected_opponent[1] = true;
                } 
                else 
                {
                    score -= WINDOW_SCORE_1;
                    is_window_connected_opponent[0] = true;
                }
                is_window_connected_player = new boolean[3];
            }
            // System.out.print("ROW: " + (startRowID + i*rowChange));
            // System.out.print("  COL: " + (startColID + i*colChange)); 
            // System.out.print("  SCORE: " + score);

        }
        // System.out.println("");
        // System.out.println("-------------------");
        // score += window_count_arr[0];
        // score += window_count_arr[1];
        // score += window_count_arr[2];
        return score;
    }

}