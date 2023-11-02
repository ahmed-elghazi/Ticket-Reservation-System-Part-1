import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.File;
import java.lang.Math;

public class Auditorium {

    private Node First; // The first node representing the top-left seat
    private int rowCount; // Total number of rows
    private int colSize; // Total number of seats per row

    // Constructor: initializes the auditorium by reading from a file
    public Auditorium(String FileName) throws IOException {
        rowCount = 0;
        colSize = 0;
        First = null;
        Node tail = null;
        Node prevHead = null;
        Node currHead = null;

        // Reading from the provided file
        File A = new File(FileName);
        Scanner in = new Scanner(A);

        // Check if the file exists
        if (!(A.exists())) {
            System.out.println("File failed");
        } else {
            int count = 0;

            // Loop through each line (row) in the file
            while (in.hasNextLine()) {
                rowCount++;
                String line = in.nextLine();
                colSize = line.length();

                // Create a Node for each seat in the current row
                for (int i = 0; i < line.length(); i++) {
                    Node curr = new Node((char) (65 + i), line.charAt(i), count);
                    if (i == 0 && First == null) {
                        First = curr;
                        tail = curr;
                        prevHead = curr;
                        currHead = curr;
                    } else if (i == 0) {
                        currHead = curr;
                    }

                    // Linking nodes horizontally
                    tail.setNext(curr);
                    tail = curr;

                    // Linking nodes vertically
                    if (prevHead != null) {
                        prevHead.setDown(curr);
                        prevHead = prevHead.getNext();
                    }
                }

                // Moving to the next row
                if (currHead != null) {
                    prevHead = currHead;
                    currHead = null;
                }
                count++;
            }
        }
in.close();
    }

    // Method to display the auditorium seating status
    public void displayAudi() {
        String period = ".";
        Node curr = First;
        int count = 1;

        System.out.print("  ");
        // Print seat column headers
        for (int i = 65; i < colSize + 65; i++) {
            char c = (char) i;
            System.out.print(c);
        }
        System.out.println();

        // Print seats row-wise, using '.' for empty and '#' for occupied
        int row = First.getRow();
        System.out.print(count + " ");
        count++;
        while (curr != null) {
            if (!(period.equals(curr.toString()))) {
                System.out.print('#');
            } else {
                System.out.print('.');
            }

            curr = curr.getNext();
            if (curr != null && row != curr.getRow()) {
                System.out.println();
                System.out.print(count + " ");
                count++;
                row = curr.getRow();
            }
        }
        System.out.println();
    }

    // Method to write the current auditorium state back to a file
    public void writeToFile() throws FileNotFoundException {
        File Out = new File("A1.txt");
        PrintWriter c = new PrintWriter(Out);
        Node curr = First;
        int row = First.getRow();

        // Loop through nodes and write them to the file
        while (curr != null) {
            c.print(curr.toString());
            curr = curr.getNext();
            if (curr != null && row != curr.getRow()) {
                c.println();
                row = curr.getRow();
            }
        }
        c.close();
    }

    // Method to reserve seats
    public void reserveSeat(int row, int A, int S, int C, int seat) {
        int total = A + S + C; // Total number of seats to reserve
        Node Curr = First;
        char seatNum = (char) ((char) 65 + seat);

        // Find the starting seat
        while (Curr.getRow() != row || Curr.getChar() != seatNum) {
            Curr = Curr.getNext();
        }

        // Reserve seats based on the type (A: adult, S: senior, C: child)
        while (total != 0) {
            if (A != 0) {
                Curr.setSeat('A');
                A--;
            } else if (C != 0) {
                Curr.setSeat('C');
                C--;
            } else if (S != 0) {
                Curr.setSeat('S');
                S--;
            }

            Curr = Curr.getNext();
            total--;
        }
    }

    // Method to check if a specific group of seats are available
    public Boolean checkSeat(int total, int row, int seat) {
        Node Curr = First;
        char seatNum = (char) ((char) 65 + seat);

        // Find the starting seat
        while (Curr.getRow() != row || Curr.getChar() != seatNum) {
            Curr = Curr.getNext();
        }

        // Check if all seats in the range are available
        while (total != 0) {
            if (Curr.getTicketType() != '.') {
                return false;
            }

            Curr = Curr.getNext();
            total--;
        }
        return true;
    }

    // Method to display sales report
    public void displayRep() {
        int adult = 0;
        int child = 0;
        int senior = 0;
        int count = 0;
        Node curr = First;

        // Count the number of each ticket type
        while (curr != null) {
            char T = curr.getTicketType();
            if (T == 'A') {
                adult++;
            } else if (T == 'C') {
                child++;
            } else if (T == 'S') {
                senior++;
            }

            curr = curr.getNext();
            count++;
        }

        DecimalFormat df = new DecimalFormat("0.00"); // For formatting the sales output
        // Print the report
        System.out.println("Total Seats:\t" + count);
        System.out.println("Total Tickets:\t" + (adult + senior + child));
        System.out.println("Adult Tickets:\t" + adult);
        System.out.println("Child Tickets:\t" + child);
        System.out.println("Senior Tickets:\t" + senior);
        System.out.println("Total Sales:\t$" + df.format((senior * 7.50) + (child * 5) + (adult * 10)));
    }

    // Method to find the best available group of seats
    public Node bestSeats(int total) {

        // Calculate the center position of the auditorium in terms of rows and columns.
        int middleRow = (rowCount / 2) + 1;   // This determines the middle row of the auditorium.
        double middleCol = colSize / 2.0;     // This determines the middle column of the auditorium.

        // Initialize the smallest distance to the maximum possible value and a variable to store the row difference.
        double minDistance = Double.MAX_VALUE;  // This will store the minimum distance from the center.
        int closestRowDifference = Integer.MAX_VALUE;  // This will store the row difference from the center.

        Node startSeat = null;   // This will store the starting seat of the best available block of seats.

        Node currentRow = First; // Start from the first row.
        int currentRowIndex = 1; // Keep track of the current row's index.

        // Go through each row.
        while (currentRow != null) {
            Node currentSeat = currentRow;  // Start from the first seat in the current row.

            // Loop to check all possible seat blocks of the given size in the current row.
            for (int i = 0; i <= colSize - total; i++) {
                boolean allSeatsEmpty = true;  // Flag to check if all seats in the current block are empty.
                Node checker = currentSeat;

                // Check the current block of seats.
                for (int j = 0; j < total; j++) {
                    // If the current seat is not empty, update the flag.
                    if (checker.getTicketType() != '.') {
                        allSeatsEmpty = false;
                        break;
                    }
                    checker = checker.getNext(); // Move to the next seat.
                }

                // If all seats in the current block are empty, calculate its distance from the center.
                if (allSeatsEmpty) {
                    double midSeatOfSelection = (i + (total / 2.0)); // Find the middle seat of the current block.
                    // Calculate the Euclidean distance from the center of the auditorium.
                    double distance = Math.sqrt(Math.pow(middleRow - currentRowIndex, 2) + Math.pow(middleCol - midSeatOfSelection, 2));
                    int rowDifference = Math.abs(currentRowIndex - middleRow);  // Calculate the difference in rows from the center.

                    // If the current block is closer to the center than previous blocks, update the best seat.
                    if (distance < minDistance || (distance == minDistance && rowDifference < closestRowDifference)) {
                        minDistance = distance;
                        closestRowDifference = rowDifference;
                        startSeat = currentSeat;
                    }
                }

                currentSeat = currentSeat.getNext();  // Move to the next seat.
            }

            currentRow = currentRow.getDown();  // Move to the next row.
            currentRowIndex++;  // Update the row index.
        }

        return startSeat;  // Return the starting seat of the best block of seats.
    }

    // This function returns the total number of rows in the auditorium.
    public int getRows() {
        return rowCount;
    }

    // This function returns the total number of columns in the auditorium.
    public int getCol() {
        return colSize;
    }
}
