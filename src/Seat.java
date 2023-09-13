//NET ID: AXE210038 Name: Ahmed Elghazi
public class Seat {
    private char colChar;
    private char ticketType;
    private int row;


    public Seat(){
        ticketType = 'X';
        colChar = '!';
        row = -1;

    }
    public Seat(char seat, char ticket, int r){
        ticketType = ticket;
        colChar = seat;
        row = r;
    }
    public void setSeat(char t) {
        ticketType = t;
    }

    public char getSeat(){
        return ticketType;
    }
    public char getColChar(){
        return colChar;
    }

    public int getRow(){
        return row;
    }

    public String toString() {
        return "" + row + colChar;
    }
}
