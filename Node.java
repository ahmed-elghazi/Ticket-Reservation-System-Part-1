// This class represents a node in a linked list used in the Auditorium class.
public class Node {

    // Attributes
    private Seat Payload;  // The seat data stored in the node.
    private Node Next;     // Pointer to the next node in the row.
    private Node Down;     // Pointer to the node below in the next row.

    // Default constructor: initializes an empty seat and sets the next and down pointers to null.
    public Node(){
        Payload = new Seat();
        Next = null;
        Down = null;
    }

    // Parameterized constructor: initializes a seat with the provided data and sets the next and down pointers to null.
    public Node(char col, char tick, int r){
        Payload = new Seat(col, tick, r);
        Next = null;
        Down = null;
    }

    // Sets the next node pointer.
    public void setNext(Node A){
        Next = A;
    }

    // Sets the down (node below) pointer.
    public void setDown(Node D){
        Down = D;
    }

    // Returns the next node.
    public Node getNext(){
        return Next;
    }

    // Returns the node below.
    public Node getDown(){
        return Down;
    }

    // Returns a string representation of the seat (e.g., "A1").
    public String getSeat(){
        String row = String.valueOf(Payload.getRow() + 1); // Adjusting for 0-based indexing.
        row += Character.toString(Payload.getColChar());
        return row;
    }

    // Returns the row number of the seat.
    public int getRow(){
        return Payload.getRow();
    }

    // Returns the string representation of the seat's Payload.
    public String toString(){
        return Payload.toString();
    }

    // Returns the character representation of the seat's column.
    public char getChar(){
        return Payload.getColChar();
    }

    // Returns the type of ticket for the seat (e.g., 'A' for adult).
    public char getTicketType(){
        return Payload.getSeat();
    }

    // Sets the seat's ticket type (e.g., 'A' for adult).
    public void setSeat(char X){
        Payload.setSeat(X);
    }

    // Returns a formatted string representation of the seat (e.g., "1A" for row 1, column A).
    public String toString2(){
        return "" + (Payload.getRow() + 1) + Payload.getColChar();
    }
}
