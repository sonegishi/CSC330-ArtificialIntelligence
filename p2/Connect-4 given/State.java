/**
 * Represents a Connect-4 state as a 2-D array.
 * @author Steven Bogaerts
 */
public class State {
    
    public static final int NUM_COLS = 7;
    public static final int NUM_ROWS = 6;
    
    private char[][] board;
    
    /**
     * Make an empty state.
     */
    public State() {
        board = new char[NUM_ROWS][NUM_COLS];
        for(int r = 0; r < NUM_ROWS; r++)
            for(int c = 0; c < NUM_COLS; c++)
                board[r][c] = '_';
    }
    
    /**
     * A copy constructor.
     */
    public State(State toCopy) {
        this.board = new char[NUM_ROWS][NUM_COLS];
        
        for(int r = 0; r < NUM_ROWS; r++)
            this.board[r] = java.util.Arrays.copyOf(toCopy.board[r], NUM_COLS);
    }
    
    /**
     * Returns true if this State is equal to the argument, or false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State))
            return false;
        else
            return java.util.Arrays.deepEquals(this.board, ((State) other).board);
    }
    
    /**
     * Returns the hash code of this State object.
     */
    @Override
    public int hashCode() {
        return java.util.Arrays.deepHashCode(this.board);
    }
    
    /**
     * Returns the piece ('X', 'O', or '_') at the given column and row.
     */
    public char getPiece(int rowID, int colID) {
        return board[rowID][colID];
    }
    
    /**
     * Returns a String representation of this state.
     */
    public String toString() {
        // Add column headings
        String result = "";
        for(int c = 0; c < NUM_COLS; c++)
            result += c + " ";
        result += "\n";
        
        // Add the pieces
        for(int r = 0; r < NUM_ROWS; r++) {
            for(int c = 0; c < NUM_COLS; c++)
                result += getPiece(r, c) + " ";
            result += "\n";
        }
        
        return result;
    }
    
    /**
     * Changes (mutates) state to reflect the given move (indicated by colID, done by player
     * 'X' or 'O').
     * If the requested column is full, the request is ignored.
     * @return true if the move request was fulfilled, false otherwise
     */
    public boolean move(int colID, char player) {
        int moveRow = checkMove(colID);
        if (moveRow != -1) {
            board[moveRow][colID] = player;
            return true;
        }
        else { // invalid move - request denied
            return false;
        }
    }
    
    /**
     * @return The rowID where a piece can be placed, or -1 if the column is full or invalid.
     */
    public int checkMove(int colID) {
        if ((colID < 0) || (colID > NUM_COLS - 1))
            return -1;
        else { // look for the lowest open row
            char piece;
            int rowID = 0;
            
            boolean foundPiece = false;
            while((!foundPiece) && (rowID < NUM_ROWS)) {
                piece = getPiece(rowID, colID);
                if (piece != '_')
                    foundPiece = true;
                else
                    rowID++;
            }
            
            if (rowID == 0) // if col is full
                return -1; // invalid move
            else
                return rowID-1; // Just above the current row is open.
        }
    }
    
    /**
     * @return true if the move is valid (a valid column number that isn't full)
     * or false otherwise.
     */
    public boolean isValidMove(int colID) {
        return (checkMove(colID) != -1);
    }
    
    /**
     * @return true if the state represents a completed game, or false otherwise.
     */
    public boolean isTerminal() {
        return (getTerminalStatus() != '_');
    }
    
    /**
     * Returns 'X' if X has won, 
     *         'O' if O has won,
     *         'C' if it's a cat (tie) game (full board with no winner),
     *         '_' if the game is still going.
     */
    public char getTerminalStatus() {
        boolean foundBlank = false;
        char windowResult;
        
        // Check all sequences going down (v)
        for(int colID = 0; colID < NUM_COLS; colID++) {
            for(int rowID = 0; rowID <= NUM_ROWS-4; rowID++) {
                windowResult = check4Window(rowID, colID, 1, 0);
                if ((windowResult == 'X') || (windowResult == 'O'))
                    return windowResult;
                else if (windowResult == '_')
                    foundBlank = true; // not a full board, then
                // else found mix of X and O, so just continue
            }
        }
        
        // Check all sequences going right (h)
        for(int colID = 0; colID <= NUM_COLS-4; colID++) {
            for(int rowID = 0; rowID < NUM_ROWS; rowID++) {
                windowResult = check4Window(rowID, colID, 0, 1);
                if ((windowResult == 'X') || (windowResult == 'O'))
                    return windowResult;
                else if (windowResult == '_')
                    foundBlank = true; // not a full board, then
                // else found mix of X and O, so just continue
            }
        }
        
        // Check all sequences going diagonally up-right (u)
        for(int colID = 0; colID <= NUM_COLS-4; colID++) {
            for(int rowID = 3; rowID < NUM_ROWS; rowID++) {
                windowResult = check4Window(rowID, colID, -1, 1);
                if ((windowResult == 'X') || (windowResult == 'O'))
                    return windowResult;
                else if (windowResult == '_')
                    foundBlank = true; // not a full board, then
                // else found mix of X and O, so just continue
            }
        }
        
        // Check all sequences going diagonally down-right (d)
        for(int colID = 0; colID <= NUM_COLS-4; colID++) {
            for(int rowID = 0; rowID <= NUM_ROWS-4; rowID++) {
                windowResult = check4Window(rowID, colID, 1, 1);
                if ((windowResult == 'X') || (windowResult == 'O'))
                    return windowResult;
                else if (windowResult == '_')
                    foundBlank = true; // not a full board, then
                // else found mix of X and O, so just continue
            }
        }
        
        // If we get to this point without having already returned, then there is no winner yet.
        if (foundBlank)
            return '_'; // found a blank, so not terminal
        else
            return 'C'; // never found a blank, and no winner, so cat (tie) game
    }
    
    /**
     * Returns 'X' if found 4 X's. <BR>
     * Returns 'O' if found 4 O's. <BR>
     * Returns '_' if found at least one _. <BR>
     * Returns 'M' if found full mix of X and O, with no _.
     */
    protected char check4Window(int startRowID, int startColID, int rowChange, int colChange) {
        int xCount = 0;
        int oCount = 0;
        char currPiece;
        for(int i = 0; i < 4; i++) {
            currPiece = getPiece(startRowID + i*rowChange, startColID + i*colChange);
            if (currPiece == '_')
                return '_'; // found a _
            else if (currPiece == 'X')
                xCount++;
            else // if (currPiece == 'O')
                oCount++;
        }
        if (xCount == 4)
            return 'X'; // all X
        else if (oCount == 4)
            return 'O'; // all O
        else
            return 'M'; // mix of X and O, no _
    }
    
}