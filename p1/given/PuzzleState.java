import java.util.LinkedList;
import java.util.Arrays;

/**
 * Represents a state (placement of tiles) of the puzzle.
 * Starter code.
 * 
 * @author Steven Bogaerts
 */
public class PuzzleState {

    /**
     * A 1-D array representation of the tile placement.
     */
    private int[] tiles;

    /**
     * Assumes that initialTiles is the valid format:
     * length 9,
     * containing the integers 0 through 8 inclusive in some order
     * with no repeats
     * where 0 represents the blank.
     */
    public PuzzleState(int[] initialTiles) {
        tiles = initialTiles;
    }

    /**
     * Returns the position of a given tile in the puzzle state.
     * Returns -1 if not found. (Should never happen for valid input.)
     */
    public int posOf(int tile) {
        for(int i = 0; i < tiles.length; i++) {
            if(tiles[i] == tile) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the tile value at the given index (0 through 8, inclusive).
     */
    public int getTileAt(int index) {
        return tiles[index];
    }

    /**
     * Returns a list of PuzzleStates that can be reached from this PuzzleState
     * in one move. It's easiest to think of this as moving the blank.
     */
    public LinkedList<PuzzleState> expand() {
        LinkedList<Integer> idxList = oneMoveIdxList();
        LinkedList<PuzzleState> list = new LinkedList<>();
        int zero_idx = posOf(0);
        for(int idx: idxList) {
            int[] ints = Arrays.copyOf(tiles, 9);
            ints[zero_idx] = ints[idx];
            ints[idx] = 0;
            PuzzleState state = new PuzzleState(ints);
            list.add(state);
        }
        return list;
    }

    /**
     * Returns a list of integers which are going to be swapped with 0.
     */
    private LinkedList<Integer> oneMoveIdxList() {
        LinkedList<Integer> indexList = new LinkedList<>();
        int zero_idx = posOf(0);
        if(zero_idx == -1) {
            return indexList;
        }

        int row = zero_idx / 3;
        int col = zero_idx % 3;
        if(0 <= row - 1) { // Up
            indexList.add(calcIdx(row - 1, col));
        }
        if(row + 1 < 3) {  // Down
            indexList.add(calcIdx(row + 1, col));
        }
        if(0 <= col - 1) { // Left 
            indexList.add(calcIdx(row, col - 1));
        }
        if(col + 1 < 3) { // Right
            indexList.add(calcIdx(row, col + 1));
        } 
        return indexList;
    }

    /**
     * Returns an index of a 1-D array calculated from a given row and
     * column values.
     */
    private int calcIdx(int row_idx, int col_idx) {
        return row_idx * 3 + col_idx;
    }

    /**
     * Returns true if two PuzzleState objects are equivalent,
     * false otherwise. Required for proper operation of a HashSet of PuzzleStates.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PuzzleState))
            return false;
        else
            return Arrays.equals(this.tiles, ((PuzzleState) other).tiles);
    }

    /**
     * Required to be able to have a HashSet of PuzzleState objects.
     * Objects for which .equals() returns true must have the same hashCode.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(tiles);
    }

    /**
     * Returns a String representation of the state.
     */
    @Override
    public String toString() {
        String result = "\n";
        for(int i = 0; i < tiles.length; i++) {
            if ((i > 0) && i % 3 == 0)
                result += "\n";

            if (tiles[i] == 0)
                result += "_ ";
            else
                result += tiles[i] + " ";
        }

        return result + "\n";
    }

}