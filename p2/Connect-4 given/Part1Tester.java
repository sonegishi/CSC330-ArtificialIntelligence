import java.util.ArrayList;

public class Part1Tester {
    
    private Part1Tester() {
    }
    
    public static void go() {
        // Of course, change the next two lines based on whatever you name your player class.
        NAME_ME_Player px = new NAME_ME_Player();
        NAME_ME_Player po = new NAME_ME_Player();
        
        px.setSymbol('X');
        px.setTotalTime(-1);
        
        po.setSymbol('O');
        po.setTotalTime(-1);
        
        System.out.println("======================= Test Expand =======================");
        State s0 = new State();
        s0.move(0, 'X');
        s0.move(1, 'X');
        s0.move(1, 'X');
        s0.move(2, 'X');
        s0.move(2, 'X');
        s0.move(2, 'X');
        s0.move(3, 'X');
        s0.move(3, 'X');
        s0.move(3, 'X');
        s0.move(3, 'X');
        s0.move(4, 'X');
        s0.move(4, 'X');
        s0.move(4, 'X');
        s0.move(4, 'X');
        s0.move(4, 'X');
        s0.move(5, 'X');
        s0.move(5, 'X');
        s0.move(5, 'X');
        s0.move(5, 'X');
        s0.move(5, 'X');
        s0.move(5, 'X');
        s0.move(6, 'X');
        s0.move(6, 'X');
        s0.move(6, 'X');
        s0.move(6, 'X');
        s0.move(6, 'X');
        s0.move(6, 'X');
        s0.move(6, 'X');
        
        ArrayList<State> results = px.expand(s0, 'O');
        for(State s : results)
            System.out.println(s);
        
        System.out.println("======================= Test Eval =======================");
        
        System.out.println("===================================================================");
        State s1 = new State();
        s1.move(0, 'X');
        s1.move(1, 'X');
        s1.move(2, 'X');
        System.out.println(s1);
        System.out.println(px.eval(s1) + ", " + po.eval(s1));
        System.out.println();
        System.out.println();
        
        System.out.println("===================================================================");
        State s2 = new State();
        s2.move(1, 'X');
        s2.move(2, 'X');
        s2.move(3, 'X');
        System.out.println(s2);
        System.out.println(px.eval(s2) + ", " + po.eval(s2));
        System.out.println();
        System.out.println();
        
        System.out.println("===================================================================");
        State s3 = new State();
        s3.move(1, 'X');
        s3.move(3, 'X');
        s3.move(4, 'X');
        System.out.println(s3);
        System.out.println(px.eval(s3) + ", " + po.eval(s3));
        System.out.println();
        System.out.println();
        
        System.out.println("===================================================================");
        State s4 = new State();
        s4.move(1, 'X');
        s4.move(1, 'X');
        s4.move(1, 'X');
        System.out.println(s4);
        System.out.println(px.eval(s4) + ", " + po.eval(s4));
        System.out.println();
        System.out.println();
        
        System.out.println("===================================================================");
        State s5 = new State();
        s5.move(1, 'X');
        s5.move(2, 'O');
        s5.move(2, 'X');
        s5.move(3, 'X');
        s5.move(3, 'O');
        s5.move(3, 'X');
        System.out.println(s5);
        System.out.println(px.eval(s5) + ", " + po.eval(s5));
        System.out.println();
        System.out.println();
        
        System.out.println("===================================================================");
        State s6 = new State();
        s6.move(1, 'O');
        s6.move(1, 'X');
        s6.move(2, 'O');
        s6.move(2, 'O');
        s6.move(2, 'X');
        s6.move(3, 'X');
        s6.move(3, 'O');
        s6.move(3, 'X');
        s6.move(3, 'X');
        System.out.println(s6);
        System.out.println(px.eval(s6) + ", " + po.eval(s6));
        System.out.println();
        System.out.println();
        
        System.out.println("===================================================================");
        State s7 = new State();
        s7.move(0, 'X');
        s7.move(1, 'O');
        s7.move(1, 'X');
        s7.move(1, 'O');
        s7.move(1, 'X');
        s7.move(2, 'O');
        s7.move(2, 'O');
        s7.move(2, 'X');
        s7.move(3, 'X');
        s7.move(4, 'X');
        System.out.println(s7);
        System.out.println(px.eval(s7) + ", " + po.eval(s7));
        System.out.println();
        System.out.println();
        
        System.out.println("===================================================================");
        State s11 = new State();
        s11.move(0, 'X');
        s11.move(1, 'O');
        s11.move(1, 'X');
        s11.move(1, 'O');
        s11.move(1, 'X');
        s11.move(2, 'O');
        s11.move(2, 'O');
        s11.move(2, 'X');
        s11.move(3, 'X');
        s11.move(3, 'O');
        s11.move(4, 'X');
        System.out.println(s11);
        System.out.println(px.eval(s11) + ", " + po.eval(s11));
        System.out.println();
        System.out.println();
        
        System.out.println("======================= Test Eval On Terminal =======================");
        
        State s8 = new State();
        s8.move(0, 'O');
        s8.move(0, 'X');
        s8.move(0, 'O');
        s8.move(0, 'X');
        s8.move(0, 'X');
        s8.move(1, 'X');
        s8.move(1, 'O');
        s8.move(1, 'O');
        s8.move(1, 'X');
        s8.move(2, 'O');
        s8.move(2, 'O');
        s8.move(2, 'X');
        s8.move(3, 'O');
        s8.move(3, 'X');
        System.out.println(s8);
        System.out.println(px.eval(s8) + ", " + po.eval(s8));
        System.out.println();
        System.out.println();
        
        System.out.println("===================================================================");
        State s12 = new State();
        s12.move(0, 'X');
        s12.move(1, 'X');
        s12.move(1, 'X');
        s12.move(2, 'X');
        s12.move(2, 'X');
        s12.move(2, 'X');
        s12.move(3, 'O');
        s12.move(3, 'O');
        s12.move(3, 'O');
        s12.move(3, 'X');
        System.out.println(s12);
        System.out.println(px.eval(s12) + ", " + po.eval(s12));
        System.out.println();
        System.out.println();
        
        System.out.println("===================================================================");
        State s9 = new State();
        s9.move(0, 'X');
        s9.move(0, 'X');
        s9.move(0, 'X');
        s9.move(0, 'X');
        System.out.println(s9);
        System.out.println(px.eval(s9) + ", " + po.eval(s9));
        System.out.println();
        System.out.println();
        
        System.out.println("===================================================================");
        State s10 = new State();
        s10.move(0, 'X');
        s10.move(1, 'X');
        s10.move(2, 'X');
        s10.move(3, 'X');
        System.out.println(s10);
        System.out.println(px.eval(s10) + ", " + po.eval(s10));
        System.out.println();
        System.out.println();
    }
    
}
