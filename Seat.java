// This class represents a seat within an auditorium.
public class Seat {

    // Attributes of the Seat class
    private char colChar;    // Represents the column character (e.g., 'A', 'B', etc.).
    private char ticketType; // Represents the type of ticket for the seat (e.g., 'A' for adult).
    private int row;         // Represents the row number of the seat.

    // Default constructor: Initializes the seat with default values.
    public Seat(){
        ticketType = 'X';  // Represents an uninitialized or invalid ticket type.
        colChar = '!';     // Represents an uninitialized or invalid column character.
        row = -1;          // Represents an uninitialized or invalid row number.
    }

    // Parameterized constructor: Initializes the seat with the provided data.
    public Seat(char seat, char ticket, int r){
        ticketType = ticket;  // Sets the ticket type for the seat.
        colChar = seat;       // Sets the column character for the seat.
        row = r;              // Sets the row number for the seat.
    }

    // Method to set the ticket type for the seat.
    public void setSeat(char t) {
        ticketType = t;
    }

    // Returns the ticket type for the seat.
    public char getSeat(){
        return ticketType;
    }

    // Returns the column character of the seat.
    public char getColChar(){
        return colChar;
    }

    // Returns the row number of the seat.
    public int getRow(){
        return row;
    }

    // Returns a string representation of the ticket type for the seat.
    public String toString() {
        return String.valueOf(ticketType);
    }
}
