//NET ID: AXE210038 Name: Ahmed Elghazi
public class Node {
    private Seat Payload;
    private Node Next;
    private Node Down;

    public Node(){
        Payload = new Seat();
        Next = null;
        Down = null;
    }

    public Node(char col, char tick, int r){
        Payload = new Seat(col, tick, r);
        Next = null;
        Down = null;
    }

    public void setNext(Node A){
        Next = A;
    }

    public void setDown(Node D){
        Down = D;
    }

    public String toString(){
        return Payload.toString();
    }
}

