import java.util.HashSet;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Practice {
    
    public static void interfacePractice() {
        System.out.println("========== Interface Practice ==========");
        
        // Heuristic h1 = new Heuristic(); // Why is this an error?
        Heuristic h2 = new NoHeuristic();
        
        // do some stuff...
        
        h2 = new ArbitraryHeuristic();
    }
    
    public static void genericsPractice() {
        System.out.println("========== Generics Practice ==========");
        System.out.println("When using a generic class, always specify the type parameter with < > notation!");
        
        ArrayList<String> listOfStrings = new ArrayList<String>();
        listOfStrings.add("First");
        listOfStrings.add("Second");
        // listOfStrings.add(5); // error
        
        for(int i = 0; i < listOfStrings.size(); i++)
            System.out.println(listOfStrings.get(i).length());
        
        for(String s: listOfStrings)
            System.out.println(s.length());
        
        System.out.println("----- Without generics -----");
            
        ArrayList listOfStrings2 = new ArrayList(); // unsafe operation warning
        listOfStrings2.add("First");
        listOfStrings2.add("Second");
        listOfStrings2.add(5); // ok?
        
        for(int i = 0; i < listOfStrings2.size(); i++) {
            // System.out.println(listOfStrings2.get(i).length()); // error
            System.out.println(  ((String) listOfStrings2.get(i)).length() );  // ok?
        }
        
        for(Object o: listOfStrings2) { // Object, not String!
            System.out.println(  ((String) o).length() );  // ok?
        }
    }
    
    private static final PuzzleState x = new PuzzleState(new int[]     {2,3,4,1,5,6,7,8,0});
    private static final PuzzleState xTwin = new PuzzleState(new int[] {2,3,4,1,5,6,7,8,0});
    private static final PuzzleState y = new PuzzleState(new int[]     {2,3,4,7,8,0,1,5,6});
    private static final PuzzleState z = new PuzzleState(new int[]     {1,2,3,4,5,6,7,8,0});
    
    private static final ArbitraryHeuristic h = new ArbitraryHeuristic();
    // Technically, the following are "paths", but they're only of length 1.
    private static final PuzzlePath xPath = new PuzzlePath(x, h);
    private static final PuzzlePath yPath = new PuzzlePath(y, h);
    private static final PuzzlePath zPath = new PuzzlePath(z, h);
    
    public static void equalsPractice() {
        System.out.println("========== Equals Practice ==========");
        
        System.out.println("\n----------");
        System.out.println("x     : " + x);
        System.out.println("xTwin : " + xTwin);
        System.out.println("y     : " + y);
        
        System.out.println("\n----------");
        System.out.println("x == x     : " + (x == x));
        System.out.println("x == xTwin : " + (x == xTwin));
        System.out.println("x == y     : " + (x == y));
        
        System.out.println("\n----------");
        System.out.println("x.equals(xTwin) : " + (x.equals(xTwin)));
        System.out.println("xTwin.equals(x) : " + (xTwin.equals(x)));
        System.out.println();
        System.out.println("x.equals(y)     : " + (x.equals(y)));
        System.out.println("y.equals(x)     : " + (y.equals(x)));
    }
    
    public static void hashCodePractice() {
        System.out.println("========== Hash Code Practice ==========");
        
        System.out.println("\n----------");
        System.out.println("x hashcode     : " + x.hashCode());
        System.out.println("xTwin hashcode : " + xTwin.hashCode());
        System.out.println("y hashcode     : " + y.hashCode());
        System.out.println("z hashcode     : " + z.hashCode());
        
        System.out.println("\n----------");
        System.out.println("If the objects are .equals(), then the hashcodes must be the same.");
    }
    
    public static void hashSetPractice() {
        System.out.println("========== HashSet Practice ==========");
        HashSet<PuzzleState> states = new HashSet<PuzzleState>();
        
        System.out.println("Adding x and y...");
        states.add(x);
        states.add(y);
        
        System.out.println("  states.contains(x)     : " + states.contains(x));
        System.out.println("  states.contains(y)     : " + states.contains(y));
        System.out.println("  states.contains(z)     : " + states.contains(z));
        System.out.println("  states.contains(xTwin) : " + states.contains(xTwin));
        
        System.out.println("To get 'true' for the last line, we have to have equals and hashCode implemented properly in the PuzzleState class.");
    }
    
    public static void linkedListPractice() {
        System.out.println("========== Linked List Practice ==========");
        LinkedList<PuzzleState> list = new LinkedList<PuzzleState>();

        System.out.println("x: " + x);
        System.out.println("y: " + y);
        
        System.out.println("\nAdding x and y...");
        list.add(x);
        list.add(y);
        
        System.out.println("\nLooping...");
        for(PuzzleState s: list) { // a for-each loop
            System.out.println(s);
        }
    }
    
    public static void linkedListAsAQueuePractice() {
        System.out.println("========== Linked List as a Queue Practice ==========");
        
        System.out.println("xPath: " + xPath);
        System.out.println("yPath: " + yPath);
        
        // Using paths instead of states here, just for something different.
        Queue<PuzzlePath> listAsAQueue = new LinkedList<PuzzlePath>();
        
        // Now use the Queue methods defined at http://docs.oracle.com/javase/7/docs/api/java/util/Queue.html
        System.out.println("\nAdding xPath and yPath...");
        listAsAQueue.offer(xPath);
        listAsAQueue.offer(yPath);
        
        System.out.println("\n----------\nLooping...");
        for(PuzzlePath p: listAsAQueue)
            System.out.println(p);
        System.out.println("Done looping.\n----------");
        
        System.out.println("\nPeek result: " + listAsAQueue.peek());
        
        System.out.println("\n----------\nLooping...");
        for(PuzzlePath p: listAsAQueue)
            System.out.println(p);
        System.out.println("Done looping.\n----------");
        
        System.out.println("\nPoll result: " + listAsAQueue.poll());
        
        System.out.println("\n----------\nLooping...");
        for(PuzzlePath p: listAsAQueue)
            System.out.println(p);
        System.out.println("Done looping.\n----------");
    }
    
    public static void priorityQueuePractice() {
        System.out.println("========== Priority Queue Practice ==========");
        
        System.out.println("xPath: " + xPath);
        System.out.println("yPath: " + yPath);
        System.out.println("zPath: " + zPath);
        
        Comparator<PuzzlePath> pathComparator = new Comparator<PuzzlePath>() {
            public int compare(PuzzlePath p1, PuzzlePath p2) {
                return p1.getCost() - p2.getCost();
            }
        };
        
        Queue<PuzzlePath> priorityQueue = new PriorityQueue<PuzzlePath>(100, pathComparator);
        
        System.out.println("Adding in order yPath, zPath, xPath...");
        priorityQueue.offer(yPath);
        priorityQueue.offer(xPath);
        priorityQueue.offer(zPath);
        
        System.out.println("\n----------\nLooping...");
        for(PuzzlePath p: priorityQueue)
            System.out.println(p);
        System.out.println("Done looping.\n----------");
        
        System.out.println("\n----------\nRemoving (with poll) one at a time...");
        while(!priorityQueue.isEmpty())
            System.out.println(priorityQueue.poll()); // remember, this returns and removes the first item
        System.out.println("Done removing.\n----------");
    }
    
}