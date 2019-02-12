import java.util.HashSet;

/**
 * This class provides several methods to try out various pieces of the code.
 * I've tried to make the tests fairly intensive, however I do not guarantee that 
 * correct performance on these tests means 100% correct code.
 * @author Steven Bogaerts
 */
public class PuzzleTester {
    
    private static final PuzzleState goalState     = new PuzzleState(new int[]{1,2,3,4,5,6,7,8,0});
    private static final PuzzleState extremelyEasy = new PuzzleState(new int[]{1,2,3,4,5,0,7,8,6});
    private static final PuzzleState easy          = new PuzzleState(new int[]{1,2,3,7,0,5,8,4,6});
    private static final PuzzleState harder        = new PuzzleState(new int[]{4,2,3,5,1,6,7,8,0});
    
    public static void testExpand() {
        System.out.println("===============================================");
        System.out.println("Test expand");
        
        PuzzleState a = new PuzzleState(new int[]{1,2,3,4,0,5,6,7,8});
        PuzzleState b = new PuzzleState(new int[]{1,2,3,0,4,5,6,7,8});
        PuzzleState c = new PuzzleState(new int[]{0,1,2,3,4,5,6,7,8});
        
        System.out.println("\n----------------- a");
        System.out.println(a.expand());
        
        System.out.println("\n----------------- b");
        System.out.println(b.expand());
        
        System.out.println("\n----------------- c");
        System.out.println(c.expand());
        /*
         Expected answers:
            ----------------- a
            [
            1 _ 3 
            4 2 5 
            6 7 8 
            , 
            1 2 3 
            4 7 5 
            6 _ 8 
            , 
            1 2 3 
            _ 4 5 
            6 7 8 
            , 
            1 2 3 
            4 5 _ 
            6 7 8 
            ]
            
            ----------------- b
            [
            _ 2 3 
            1 4 5 
            6 7 8 
            , 
            1 2 3 
            6 4 5 
            _ 7 8 
            , 
            1 2 3 
            4 _ 5 
            6 7 8 
            ]
            
            ----------------- c
            [
            3 1 2 
            _ 4 5 
            6 7 8 
            , 
            1 _ 2 
            3 4 5 
            6 7 8 
            ]
         */
    }
    
    public static void testDisplacement() {
        System.out.println("===============================================");
        System.out.println("Test displacement");
        
        DisplacementHeuristic dh = new DisplacementHeuristic(goalState);
        System.out.println("Expected answers are provided.");
        System.out.println("0: " + dh.distance(new PuzzleState(new int[]{1,2,3,4,5,6,7,8,0})));
        System.out.println("1: " + dh.distance(new PuzzleState(new int[]{1,2,3,4,5,0,7,8,6})));
        System.out.println("1: " + dh.distance(new PuzzleState(new int[]{1,2,3,4,5,6,7,0,8})));
        System.out.println("7: " + dh.distance(new PuzzleState(new int[]{7,2,4,1,3,8,0,5,6})));
    }
    
    public static void testManhattan() {
        System.out.println("===============================================");
        System.out.println("Test Manhattan");
        
        ManhattanHeuristic mh = new ManhattanHeuristic(goalState);
        System.out.println("Expected answers are provided.");
        System.out.println("0: " + mh.distance(new PuzzleState(new int[]{1,2,3,4,5,6,7,8,0})));
        System.out.println("1: " + mh.distance(new PuzzleState(new int[]{1,2,3,4,5,0,7,8,6})));
        System.out.println("1: " + mh.distance(new PuzzleState(new int[]{1,2,3,4,5,6,7,0,8})));
        System.out.println("12: " + mh.distance(new PuzzleState(new int[]{7,2,4,1,3,8,0,5,6})));
    }
    
    public static void testBFS() {
        System.out.println("===============================================");
        System.out.println("Test BFS");
        
        System.out.println("------------------------------");
        (new SearchProblem(extremelyEasy, goalState, "FIFO", 1000, new NoHeuristic())).solve();
        
        System.out.println("------------------------------");
        (new SearchProblem(easy, goalState, "FIFO", 1000, new NoHeuristic())).solve();
        
        System.out.println("------------------------------");
        (new SearchProblem(harder, goalState, "FIFO", 1000, new NoHeuristic())).solve();
    }
    
    public static void testAStarDisplacement() {
        System.out.println("===============================================");
        System.out.println("Test A* with displacement heuristic");
        
        System.out.println("------------------------------");
        (new SearchProblem(extremelyEasy, goalState, "Ordered", 1000, new DisplacementHeuristic(goalState))).solve();
        
        System.out.println("------------------------------");
        (new SearchProblem(easy, goalState, "Ordered", 1000, new DisplacementHeuristic(goalState))).solve();
        
        System.out.println("------------------------------");
        (new SearchProblem(harder, goalState, "Ordered", 1000, new DisplacementHeuristic(goalState))).solve();
    }
    
    public static void testAStarManhattan() {
        System.out.println("===============================================");
        System.out.println("Test A* with Manhattan heuristic");
        
        System.out.println("------------------------------");
        (new SearchProblem(extremelyEasy, goalState, "Ordered", 1000, new ManhattanHeuristic(goalState))).solve();
        
        System.out.println("------------------------------");
        (new SearchProblem(easy, goalState, "Ordered", 1000, new ManhattanHeuristic(goalState))).solve();
       
        System.out.println("------------------------------");
        (new SearchProblem(harder, goalState, "Ordered", 1000, new ManhattanHeuristic(goalState))).solve();
    }
    
    public static void testAll() {
        testExpand();
        testDisplacement();
        testManhattan();
        testBFS();
        testAStarDisplacement();
        testAStarManhattan();
    }
    
}