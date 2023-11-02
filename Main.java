import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
public class Main {
    public static int convertCol(char seat) {
        return seat - 'A' + 1;
    }
    public static void main(String[] args) throws IOException, InputMismatchException {
        // Create a Scanner object for user input
        Scanner sc = new Scanner(System.in);

        // Prompt the user to enter the name of the file containing auditorium details
        System.out.println("Enter file Name: ");
        String fileName = sc.nextLine();

        // Create an Auditorium object using the provided file name
        Auditorium test = new Auditorium(fileName);

        // Variable to control the main loop
        boolean cont = true;
        int choice;

        // Main loop for the user menu
        while (cont) {
            System.out.println("1. Reserve Seats\n2. Exit");

            // Input loop to ensure a valid choice is made (1 or 2)
            while (true) {
                try {
                    choice = sc.nextInt();
                    if (choice == 1 || choice == 2) {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid choice (1 or 2).");
                    sc.next(); // Clear the invalid input
                }
            }

            // Logic for "Reserve Seats" option
            if (choice == 1) {
                int adultT, childT, seniorT = 0;
                int chosenRow = -1;
                char seat;

                // Input loop to get a valid row number
                System.out.print("Enter starting row: ");
                while (true) {
                    try {
                        chosenRow = sc.nextInt();
                        if (chosenRow > 0 && chosenRow <= test.getRows()) {
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid row number.");
                        sc.next(); // Clear the invalid input
                    }
                }

                // Input loop to get a valid starting seat
                System.out.print("Enter starting seat: ");
                while (true) {
                    try {
                        seat = sc.next().charAt(0);
                        if (convertCol(seat) >= 1 && convertCol(seat) <= test.getCol()) {
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid seat.");
                        sc.next(); // Clear the invalid input
                    }
                }

                // Collect ticket information for adults, children, and seniors
                // Each has its own loop to ensure valid input
                System.out.print("Enter number of adult tickets: ");
                while (true) {
                    try {
                        adultT = sc.nextInt();
                        if (adultT >= 0) {
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid number of adult tickets.");
                        sc.next();
                    }
                }

                System.out.print("Enter number of child tickets: ");
                while (true) {
                    try {
                        childT = sc.nextInt();
                        if (childT >= 0) {
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid number of child tickets.");
                        sc.next();
                    }
                }

                System.out.print("Enter number of senior tickets: ");
                while (true) {
                    try {
                        seniorT = sc.nextInt();
                        if (seniorT >= 0) {
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid number of senior tickets.");
                        sc.next();
                    }
                }

                // Check if the chosen seats are available for reservation
                if(test.checkSeat(adultT + seniorT + childT, chosenRow - 1, convertCol(seat)- 1)){
                    // Reserve the seats and display the updated auditorium
                    test.reserveSeat(chosenRow-1, adultT, seniorT, childT, convertCol(seat)-1);
                    test.displayAudi();
                } else {
                    System.out.println("seats not available");

                    // If chosen seats are not available, recommend best available seats
                    Node newSeat = test.bestSeats(adultT + seniorT + childT);
                    Node lastSeat = newSeat;

                    // Get the last seat of the recommended range
                    for(int i = 0; i < adultT + seniorT + childT - 1; i++) {
                        lastSeat = lastSeat.getNext();
                    }

                    // If recommended seats found, display them and ask if the user wants to reserve them
                    if (newSeat != null) {
                        if (adultT + childT + seniorT == 1) {
                            System.out.println(newSeat.toString2());
                        } else {
                            System.out.println(newSeat.toString2() + " - " + lastSeat.toString2());
                        }
                        System.out.println("Would you like to reserve these seats? Yes or No");
                        char response = sc.next().charAt(0);

                        if (response == 'Y' || response == 'y') {
                            test.reserveSeat(newSeat.getRow(), adultT, seniorT, childT, convertCol(newSeat.getChar()) - 1);
                            test.displayAudi();
                            System.out.println("\nSeats have been reserved");
                        }
                    }
                }
            }
            // Logic for "Exit" option
            else {
                cont = false; // End the main loop
                test.displayRep(); // Display the auditorium report
                test.writeToFile(); // Write the updated auditorium details to the file
            }
        }
    }

}
