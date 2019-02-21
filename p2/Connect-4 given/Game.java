/**
 * This class represents a Connect-4 game.
 * @author Steven Bogaerts
 */
public class Game {
    
    private int totalTime;
    
    private State currentState;
    
    private char currPlayerSymbol;
    private int currPlayerTimeLeft;
    private int otherPlayerTimeLeft;
    
    private PlayerDef playerXDef;
    private PlayerDef playerODef;
    
    /**
     * A convenience method for starting a game between two random players.
     */
    public static void randomVsRandom() {
        Game g = new Game(new RandomDef(), new RandomDef(), -1);
        g.play();
    }
    
    /**
     * A convenience method for starting a game between two human players.
     */
    public static void humanVsHuman() {
        Game g = new Game(new HumanDef(), new HumanDef(), -1);
        g.play();
    }
    
    /**
     * A convenience method for starting a game between two human players,
     * with a time limit.
     */
    public static void humanVsHuman(int totalTime) {
        Game g = new Game(new HumanDef(), new HumanDef(), totalTime);
        g.play();
    }
    
    /**
     * A convenience method for starting a game between a human and a random player.
     */
    public static void humanVsRandom() {
        Game g = new Game(new HumanDef(), new RandomDef(), -1);
        g.play();
    }
    
    /**
     * Creates a new Connect-4 Game object.
     * 
     * @param totalTime the time in seconds allowed for one player. Use -1 to indicate
     * no time limit. Otherwise, each player gets totalTime seconds across the entire game.)  
     * It's like a chess clock.  Each
     * time your program is asked for a move, the remaining time is passed as a
     * parameter. Suppose you're playing a game with totalTime = 128 per player,
     * your program has already used 20 seconds (timeLeft = 108), it is asked for
     * a move, and takes 10 seconds to respond. Then the next time your program is
     * asked for a move, timeLeft would be 98. If your program then used, say, 100
     * seconds for its next move, then it would forfeit the game.
     */
    public Game(PlayerDef playerXDef, PlayerDef playerODef, int totalTime) {
        this.totalTime = totalTime;
        this.currPlayerSymbol = 'X';
        
        this.playerXDef = playerXDef;
        this.playerXDef.setSymbol('X');
        
        this.playerODef = playerODef;
        this.playerODef.setSymbol('O');
        
        // Allow each player to also know how much time remains
        this.playerXDef.setTotalTime(totalTime);
        this.playerODef.setTotalTime(totalTime);
        
        reset();
    }
    
    /**
     * Resest this Game object, in preparation for another game between the same players.
     */
    protected void reset() {
        // Start with an empty game board
        this.currentState = new State();
        
        // Keep track of time in this Game object.
        this.currPlayerTimeLeft = totalTime;
        this.otherPlayerTimeLeft = totalTime;
    }
    
    /**
     * Manages one entire game, calling the appropriate player definition for a move,
     * handling display of information, timing, etc.
     * The game always starts with player 'X'.
     */
    public void play() {
        reset();
        
        boolean gameOver = false;
        long start, end;
        State copiedState;
        int playerMove, newCurrPlayerTimeLeft;
        char terminalStatus = '_';
        
        while (!gameOver) {
            // ------------------------------------------------------------------
            // Display game information
            System.out.println("======================================================");
            System.out.println(currentState);
            if (totalTime != -1)
                System.out.println(currPlayerSymbol + ", " + currPlayerTimeLeft + " seconds left.");
            else
                System.out.println(currPlayerSymbol + ", your turn.");
                
            // ------------------------------------------------------------------
            // Get player move
            copiedState = new State(currentState); // Copy the state, so the playerDef getMove function can change it if it wants
            start = System.currentTimeMillis(); // Get the starting time in milliseconds
            
            // Ask the correct player for a move
            if (currPlayerSymbol == 'X')
                playerMove = playerXDef.getMove(copiedState, currPlayerTimeLeft);
            else
                playerMove = playerODef.getMove(copiedState, currPlayerTimeLeft);
            
            end = System.currentTimeMillis(); // Get the ending time in milliseconds
            newCurrPlayerTimeLeft = currPlayerTimeLeft - ((int) (end-start))/1000; // Update time left
            
            // ------------------------------------------------------------------
            // Check if game is over...
            if (! currentState.isValidMove(playerMove)) { // ... by invalid move...
                System.out.println("Invalid move by computer player " + currPlayerSymbol + ". " + currPlayerSymbol + " forfeits.");
                terminalStatus = otherPlayer();
                gameOver = true;
            }
            else if ((newCurrPlayerTimeLeft < 0) && (totalTime != -1)) { // ... by running out of time...
                System.out.println("Player " + currPlayerSymbol + " ran out of time. " + currPlayerSymbol + " forfeits.");
                terminalStatus = otherPlayer();
                gameOver = true;
            }
            else {
                // Make the move actually occur
                currentState.move(playerMove, currPlayerSymbol);
                
                // Check if game is over
                terminalStatus = currentState.getTerminalStatus();
                if (terminalStatus != '_')
                    gameOver = true;
                
                // Switch whose turn it is
                currPlayerSymbol = otherPlayer();
                currPlayerTimeLeft = otherPlayerTimeLeft;
                otherPlayerTimeLeft = newCurrPlayerTimeLeft;
            }
        }
        
        // Game is over now!
        showResults(terminalStatus);
    }
    
    /**
     * Returns the symbol for the "other" player - the one that isn't
     * in currPlayerSymbol.
     */
    private char otherPlayer() {
        if (currPlayerSymbol == 'X')
            return 'O';
        else
            return 'X';
    }
    
    /**
     * Shows results of a complete game.
     * @param resultSymbol 'X', 'O', or 'C' (for tie game)
     */
    private void showResults(char resultSymbol) {
        System.out.println(currentState);
        if (resultSymbol == 'C')
            System.out.println("Cat game! No winner.");
        else
            System.out.println(resultSymbol + " wins!");
    }
    
    
    
}