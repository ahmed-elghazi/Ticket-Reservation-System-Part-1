//NET ID: AXE210038 Name: Ahmed Elghazi
import java.util.Scanner;
public class Auditorium {
    private Node First;

    // first for loop making the row, second loop making second row, down pointers set,
    public Auditorium(int rows, int col, Scanner B){
        int count = 0;
        First = null;
        Node presentHead = null; // these are never used but how do I
        Node downHead = null;
        while(count < rows) { // Do i need to check for empty line on the first for loop?
            // instead of two 4 loops, make each list then set down pointer later?
            String in = B.nextLine();
            Node Tail = null;
            for (int i = 0; i < col; i++) {
                if (First == null) { // replace this with if(First == null) then set it equal to first head!
                    First = new Node((char) (65 + i), in.charAt(i), count); // count = row count, charAt is tick, how to do col
                    Tail = First;
                    continue;
                }
                Node A = new Node((char) (65 + i), in.charAt(i), count);
//                if(First == null){
//                    First = A;
//                    Tail = First;
//                }
                Tail.setNext(A);
                Tail = A;
                if (!(i + 1 < col)) { // this condition works
                    A.setNext(null);
                }
            }
            count++;
            in = B.nextLine();
            if (!(in.isEmpty())) {
                for(int j = 0; j < col; j++) {
                    Node A = new Node((char) (65 + j), in.charAt(j), count);
                    Tail.setNext(A);
                    Tail = A;
                    if (!(j + 1 < col)) { // this condition works
                        A.setNext(null);
                    }
                }

            }
            count++;
        }
    }
}
