package Code.Cinema;

import java.util.InputMismatchException;
import java.util.Scanner;

import Code.Client.Ticket;

public class Staff {
    Scanner input = new Scanner(System.in);

    protected String name;
    protected int age;
    protected int PIN;

    public Staff(String name, int age, int pin) {
        this.PIN = pin;
        this.name = name;
        this.age = age;
    }

    public Staff() {
        // default
    }

    public Staff(Cinema cinema) {
        boolean flag = true;
        do {
            try {
                System.out.print("Enter the staff member's name: ");
                this.name = input.nextLine();
                flag = false;
            } catch (Exception e) {
                System.out.println("What you entered is not a string.");
                System.out.println("Please try again.");
                flag = true;
            }
        } while (flag);
        do {
            try {

                System.out.print("Enter the Staff member's pin: ");
                this.PIN = input.nextInt();
                flag = false;
            } catch (InputMismatchException ime) {
                System.out.println("What you entered is not an integer.");
                System.out.println("Please try again.");
                flag = true;
            }
        } while (flag);
        do {
            try {
                System.out.print("Enter the staff member's age: ");
                this.age = input.nextInt();
                flag = false;
            } catch (Exception e) {
                System.out.println("What you entered is not an integer.");
                System.out.println("Please try again.");
                flag = true;
            }
        } while (flag);
    }

    public Staff(String name) {
        boolean flag = true;
        this.name = name;
        do {
            try {

                System.out.print("Enter the Staff member's pin: ");
                this.PIN = input.nextInt();
                flag = false;
            } catch (InputMismatchException ime) {
                System.out.println("What you entered is not an integer.");
                System.out.println("Please try again.");
                flag = true;
            }
        } while (flag);
        do {
            try {
                System.out.print("Enter the staff member's age: ");
                this.age = input.nextInt();
                input.nextLine();
                flag = false;
            } catch (Exception e) {
                System.out.println("What you entered is not an integer.");
                System.out.println("Please try again.");
                flag = true;
            }
        } while (flag);
    }

    // --------------------------------------------------------- UI Options Start
    // provides string of options.
    protected String options() {
        String str = String.format("%s %s\n", "1) ", "Display movies in room queue.");
        str += String.format("%s %s\n", "2) ", "Add movie to room queue.");
        str += String.format("%s %s\n", "3) ", "Remove movie from room queue.");
        str += String.format("%s %s\n", "4) ", "Add movie to cinema.");
        str += String.format("%s %s\n", "5) ", "Empty seat(s) from room.");
        str += String.format("%s %s\n", "6) ", "Empty all seats from room.");
        str += String.format("%s %s\n", "7) ", "Display the room seating.");
        str += String.format("%s %s\n", "8) ", "Change ticket price.");
        return str;
    }

    // option 1
    void displayMovieQueue(Cinema cinema) {
        boolean flag = false;
        int roomNum = 0;
        do {

            try {
                System.out.print("Enter room number of movies to be displayed (1 to "
                        + cinema.getRoom_List().size() + "): ");
                roomNum = input.nextInt() - 1;
                
                if(roomNum < 0 || roomNum > cinema.getRoom_List().size()){
                    throw new IllegalArgumentException();
                }
                cinema.getRoom_List().get(roomNum).showMoviesInTheRoom();
                flag = false;
                input.nextLine();
            } catch (InputMismatchException ime) {
                System.out.println("What you enetered was not a number, please try again");
                input.nextLine();
                flag = true;
            } catch (NullPointerException npe) {
                System.out.println("The room number you have entered does not exist in the cinema, please try again.");
                input.nextLine();
                flag = true;
            } catch (IllegalArgumentException iae){
                System.out.println("You must enter an integer from 1 to "
                + cinema.getRoom_List().size() + ".");
                input.nextLine();
                flag = true;
            }
        } while (flag);
    }

    // option 2) add movie to queue options
    // All that's left is to test
    void addMovieToQueue(Cinema cinema, Movie movie) {
        String answer = "";
        int roomNum = -1;
        boolean flag = true;
        do {
            do {
                
                try {
                    System.out.println("Which room with you be adding the movie to? (enter intiger from 1 to "
                    + cinema.getRoom_List().size() + "): ");
                    roomNum = input.nextInt() - 1;
                cinema.getRoom_List().get(roomNum).addMovieToQueue(movie);
                if (roomNum < 0 || roomNum > cinema.getRoom_List().size()) {
                    throw new IllegalArgumentException("Your number must be an integer from 1 to "
                    + cinema.getRoom_List().size() + ".");
                } else {
                    input.nextLine();
                    flag = false;
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                input.nextLine();
                flag = true;
            } catch (InputMismatchException ime) {
                System.out.println("You must enter an integer value, please try again.");
                input.nextLine();
                flag = true;
            } catch (NullPointerException npe) {
                input.nextLine();
                flag = true;
            }
        } while (flag);
        System.out.println("Would you like to add");
        } while (answer.toLowerCase().equals("y"));
        input.nextLine();
    }

    void addMovieToQueue(Cinema cinema) {
        boolean flag = true;
        String answer = "";
        int roomNum = -1;
        do {
            try {
                System.out.println("Which room with you be adding the movie to? (enter intiger from 1 to "
                        + cinema.getRoom_List().size() + "): ");
                roomNum = input.nextInt() - 1;
                Movie movie = new Movie(true);
                cinema.getRoom_List().get(roomNum).addMovieToQueue(movie);
                cinema.getMovie_List().add(movie);
                if (roomNum < 0) {
                    throw new IllegalArgumentException("Your number must be positive, please try again.");
                } else {
                    input.nextLine();
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                input.nextLine();
            } catch (InputMismatchException ime) {
                System.out.println("You must enter an integer value, please try again.");
                input.nextLine();
            } catch (NullPointerException npe) {
                input.nextLine();
            }
            do {

                try {
                    System.out.println("Do you want to add another movie?(Y or N): ");
                    answer = input.nextLine();
                } catch (InputMismatchException ime) {
                    System.out.println("What you entered was not a 'Y' or a 'N', please try again.");
                    flag = false;
                }
                if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("n")) {
                    flag = true;
                }
            } while (!flag);
        } while (answer.toLowerCase().equals("y"));
    }

    // option 3 remove a movie from the queue of movies
    // all that's left is test and exception handling
    void removeMovieFromQueue(Cinema cinema) {
        String answer = "";
        int roomNum = -1;
        boolean flag = false;
        String nameToBeRemoved = "";
        do {

            do {
                try {
                    System.out.println("Enter room number of movie to be removed (enter intiger from 1 to "
                            + cinema.getRoom_List().size() + "): ");
                    roomNum = input.nextInt();
                    input.nextLine();
                    System.out.println(cinema.getRoom_List().get(roomNum).getMovie_List().toString());
                    flag = false;
                } catch (InputMismatchException ime) {
                    System.out.println("What you entered  was not an integer.");
                    System.out.println("Please try again.");
                    input.nextLine();
                    flag = true;
                } catch (IndexOutOfBoundsException iobe) {
                    System.out.println("The integer entered does not correspond to a room.");
                    System.out.println("Please try again.");
                    input.nextLine();
                    flag = true;
                }
            } while (flag);
            do {

                // input.nextLine() might not throw inputmismatchexception
                try {
                    System.out.println("Enter name of movie to be removed: ");
                    nameToBeRemoved = input.nextLine();
                    cinema.getRoom_List().get(roomNum).removeMovieInQueue(nameToBeRemoved);
                    flag = false;
                } catch (InputMismatchException e) {
                    System.out.println("What you entered was not a string.");
                    System.out.println("Please try again.");
                    flag = true;
                } catch (Exception e) {
                    System.out.println("The integer entered does not correspond to a room.");
                    System.out.println("Please try again.");
                    flag = true;
                }
            } while (flag);

            System.out.println("The movie has been removed from queue");
            System.out.println("Do you want to remove another movie(Y or N)? ");
            answer = input.nextLine();

        } while (answer.toLowerCase().equals("y"));
    }

    // option 4
    void createMovie(Cinema cinema) {
        Movie movie = new Movie(true);
        cinema.getMovie_List().add(movie);
        addMovieToQueue(cinema, movie);
    }

    // option 5 remove seats
    // all that's left is to test and better exception handeling
    void emptyRoomSeat(Cinema cinema) {
        String answer = "";
        int roomNum = -1;
        char seatCollumn = '\0';
        int seatRow = -1;
        boolean flag = false;
        int column = 0;
        do {
            do {

                try {
                    System.out.println("Enter room nubmer of seat to be removed (enter intiger from 1 to "
                            + cinema.getRoom_List().size() + "): ");
                    roomNum = input.nextInt() - 1;

                    if (roomNum < 0) {
                        throw new IllegalArgumentException();
                    } else if (roomNum > cinema.getRoom_List().size()) {
                        throw new IllegalArgumentException();
                    } else {
                        input.nextLine();
                        flag = false;
                    }

                } catch (IllegalArgumentException iae) {
                    System.out.println("The integer you entered does not correspond to a room number.");
                    System.out.println(
                            "Please enter an integer between 0 and " + cinema.getRoom_List().size());
                    input.nextLine();
                    flag = true;
                } catch (InputMismatchException ime) {
                    System.out.println("What you entered was not an integer.");
                    System.out.println("Please try again.");
                    input.nextLine();
                    flag = true;
                }
            } while (flag);

            cinema.getRoom_List().get(roomNum).displaySeat();

            do {

                try {

                    System.out.println("Enter the integer corresponding to the seats row: ");
                    seatRow = input.nextInt();
                    input.nextLine();
                    seatRow--;
                    if (seatRow < 0) {
                        throw new IllegalArgumentException();
                    } else if (seatRow > cinema.getRoom_List().get(roomNum).getSeats()[0].length) {
                        throw new IllegalArgumentException();
                    } else {
                        flag = false;
                        input.nextLine();
                    }
                } catch (IllegalArgumentException iae) {
                    System.out.println("The integer entered does not correspond to anny seat.");
                    System.out.println("Please enter an integer between 0 and "
                            + cinema.getRoom_List().get(roomNum).getSeats()[0].length);
                    input.nextLine();
                    flag = true;
                } catch (InputMismatchException ime) {
                    System.out.println("What you entered is not an ineger.");
                    System.out.println("please try again.");
                    input.nextLine();
                    flag = true;
                }
            } while (flag);
            do {
                try {
                    System.out.println("Enter the letter corresponding to the seats position(A = 1, B = 2, etc...): ");
                    seatCollumn = input.nextLine().charAt(0);
                    Character.toUpperCase(seatCollumn);
                    column = (int)seatCollumn - 65;
                    if (column < 0) {
                        throw new IllegalArgumentException();
                    } else if (column > cinema.getRoom_List().get(roomNum).getSeats().length) {
                        throw new IllegalArgumentException();
                    } else {
                        input.nextLine();
                        flag = false;
                    }
                } catch (IllegalArgumentException iae) {
                    System.out.print("The letter entered does not correspond to any seat. ");
                    System.out.println("Please enter an letter between A and "
                            + (char)(cinema.getRoom_List().get(roomNum).getSeats().length) + 65);
                    flag = true;
                }

            } while (flag);

            emptySeat(cinema, roomNum, seatRow, column);

            do {

                try {
                    System.out.println("Do you want to empty another seat (Y or N)?");
                    answer = input.nextLine();

                    if (!answer.toLowerCase().equals("y") && !answer.toLowerCase().equals("n")) {
                        throw new IllegalArgumentException();
                    } else {
                        flag = false;
                    }
                } catch (IllegalArgumentException iae) {
                    System.out.println("You did not enter a 'Y' or 'N'");
                    flag = true;
                }
            } while (flag);
        } while (answer.toLowerCase().equals("y"));
    }

    // option 6
    protected void emptyAllRoomSeats(Cinema cinema) {
        int roomNum = -1;
        boolean flag = true;
        do {
            try {
                System.out.println("Enter room number of seats to be emptied (enter intiger from 1 to "
                        + cinema.getRoom_List().size() + "): ");
                roomNum = input.nextInt() - 1;
                if (roomNum < 0) {
                    throw new IllegalArgumentException();
                } else if (roomNum > cinema.getRoom_List().size()) {
                    throw new IllegalArgumentException();
                } else {
                    input.nextLine();
                    flag = false;
                }
                cinema.getRoom_List().get(roomNum).emptyAllSeat();
            } catch (InputMismatchException e) {
                System.out.println("What you entered is not an intiger: ");
                input.nextLine();
                flag = true;
            } catch (IllegalArgumentException iae) {
                System.out.println("The integer you entered does not correspond to a cinema room.");
                System.out.println("Please enter a number between 0 and " + cinema.getRoom_List().size());
                input.nextLine();
                flag = true;
            }
        } while (flag);

        System.out.println("All seats have been emptied from this room.");
    }

    protected void displayRoomSeating(Cinema cinema) {
        String answer = "";
        int roomNum = -1;
        boolean flag = true;
        do {
            do {
                try {
                    System.out.print("Enter room number of seating to be viewed (enter intiger from 1 to "
                            + cinema.getRoom_List().size() + "): ");
                    roomNum = input.nextInt() - 1;

                    if (roomNum < 0) {
                        throw new IllegalArgumentException();
                    } else if (roomNum > cinema.getRoom_List().size() - 1) {
                        throw new IllegalArgumentException();
                    } else {
                        flag = false;
                        input.nextLine();
                    }
                } catch (IllegalArgumentException iae) {
                    System.out.println("The integer you entered does not correspond to a room number.");
                    System.out
                            .println("Please enter an integer between 1 and " + cinema.getRoom_List().size());
                    input.nextLine();
                    flag = true;
                } catch (InputMismatchException ime) {
                    System.out.println("What you entered was not an integer.");
                    System.out.println("Please try again.");
                    input.nextLine();
                    flag = true;
                }
            } while (flag);

            cinema.getRoom_List().get(roomNum).displaySeat();
            do {
                try {
                    System.out.print("Would you like to view the seating of another room?(y or n): ");
                    answer = input.nextLine();
                    flag = false;
                } catch (Exception e) {
                    System.out.println("What you entered was not a string, please try again.");
                    flag = true;
                }
            } while (flag);

        } while (answer.toLowerCase().equals("y"));
    }

    // option 8 change adult ticket price
    // call set price method.
    // only need one set price method as mackenzie has made it so that people
    // recieve discounts rather than adults and children having different prices
    void changeTicketPrice() {
        boolean flag = false;
        double newTicketPrice = 0;
        do {
            try {
                System.out.println("Enter new ticket price: ");
                newTicketPrice = input.nextDouble();
                input.nextLine();
                flag = false;
            } catch (InputMismatchException ime) {
                System.out.println("What you entered is not a number.");
                System.out.println("Please try again.");
                input.nextLine();
                flag = true;
            }
        } while (flag);
        Ticket.setPrice(newTicketPrice);

        System.out.println("Ticket price has been changed.");
    }

    // ------------------------------------------------------------ UI Options End

    // user interaction for Staff class
    public void staffUI(Cinema cinema) {
        String cont = "";
        boolean flag = true;
        int reply = 0;
        do {

            System.out.println(options());
            do {
                try {
                    System.out.print("Enter the number that corresponds to the action you would like to perform: ");
                    reply = input.nextInt();

                    if (reply < 0 || reply > 8) {
                        throw new IllegalArgumentException();
                    } else {

                        flag = false;
                    }
                } catch (Exception e) {
                    System.out.println("You must enter an integer between 0 and 8");
                    System.out.println("Please try again.");
                    input.nextLine();
                    flag = true;
                }
            } while (flag);
            switch (reply) {
                case (1):
                    displayMovieQueue(cinema);
                    break;
                case (2):
                    addMovieToQueue(cinema);
                    break;
                case (3):
                    removeMovieFromQueue(cinema);
                    break;
                case (4):
                    createMovie(cinema);
                    break;
                case (5):
                    emptyRoomSeat(cinema);
                    break;
                case (6):
                    emptyAllRoomSeats(cinema);
                    break;
                case (7):
                    displayRoomSeating(cinema);
                    break;
                case (8):
                    changeTicketPrice();
                    break;
            }

            do {
                try {
                    System.out.println("Do you wish to perform another action(Y or N)? ");
                    cont = input.nextLine();

                    if (!cont.toLowerCase().equals("y") && !cont.toLowerCase().equals("n")) {
                        throw new IllegalArgumentException();
                    } else {
                        flag = false;
                    }
                } catch (IllegalArgumentException iae) {
                    System.out.println("You did not enter a 'Y' or a 'N'.");
                    System.out.println("Please try again.");
                    flag = true;
                }
            } while (flag);

        } while (cont.toLowerCase().equals("y"));
    }

    private void emptySeat(Cinema cinema, int roomNum, int row, int column) {
        cinema.getRoom_List().get(roomNum).emptySeat(column, row);
        cinema.getRoom_List().get(roomNum).displaySeat();
        System.out.println(row + " " + column);
    }

    // ------------------------- getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPin() {
        return this.PIN;
    }

    public void setPin(int pin) {
        this.PIN = pin;
    }

    @Override
    public String toString() {
        return "(" + name + "," + age + "," + "theOnlyCinema" + "," + PIN + ")";
    }
}
