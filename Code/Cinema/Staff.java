package Code.Cinema;

import java.util.InputMismatchException;
import java.util.Scanner;

import Code.Client.Ticket;

public class Staff {
    Scanner input = new Scanner(System.in);

    protected String name;
    protected int age;
    protected int PIN;
    protected Cinema cinemaOfEmployement;

    public Staff(String name, int age, Cinema cinema, int pin) {
        this.PIN = pin;
        this.name = name;
        this.age = age;
        this.cinemaOfEmployement = cinema;
    }

    public Staff() {
        // default
    }

    public Staff(boolean ignore) {
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
        this.cinemaOfEmployement = null;
    }

    public Staff(String name) {
        boolean flag = true;
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
        this.cinemaOfEmployement = null;
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
    void displayMovieQueue() {
        boolean flag = false;
        int roomNum = 0;
        do {

            try {
                System.out.print("Enter room number of movies to be displayed (0 to "
                        + cinemaOfEmployement.getRoom_List().size() + "): ");
                roomNum = input.nextInt();
                cinemaOfEmployement.getRoom_List().get(roomNum).showMoviesInTheRoom();
                flag = false;
            } catch (InputMismatchException ime) {
                System.out.println("What you enetered was not a number, please try again");
                flag = true;
            } catch (NullPointerException npe) {
                System.out.println("The room number you have entered does not exist in the cinema, please try again.");
                flag = true;
            }
        } while (!flag);
        input.nextLine();
    }

    // option 2) add movie to queue options
    // All that's left is to test
    void addMovieToQueue() {
        boolean flag = true;
        String answer = "";
        int roomNum = -1;
        do {
            try {
                System.out.println("Which room with you be adding the movie to? (enter intiger from 0 to "
                        + cinemaOfEmployement.getRoom_List().size() + "): ");
                roomNum = input.nextInt();
                cinemaOfEmployement.getRoom_List().get(roomNum).addMovieToQueue(new Movie());
                if (roomNum < 0) {
                    throw new IllegalArgumentException("Your number must be positive, please try again.");
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            } catch (InputMismatchException ime) {
                System.out.println("You must enter an integer value, please try again.");
            } catch (NullPointerException npe) {

            }
            do {

                try {
                    System.out.println("Do you want to add another movie?(Y or N): ");
                    answer = input.nextLine();
                } catch (InputMismatchException ime) {
                    System.out.println("What you entered was not a 'Y' or a 'N', please try again.");
                    // TODO: handle exception
                    flag = false;
                }
                if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("n")) {
                    flag = true;
                }
            } while (!flag);
        } while (answer.toLowerCase().equals("y"));
        input.nextLine();
    }

    // option 3 remove a movie from the queue of movies
    // all that's left is test and exception handling
    void removeMovieFromQueue() {
        String answer = "";
        int roomNum = -1;
        boolean flag = false;
        String nameToBeRemoved = "";
        do {
            do {

                // input.nextLine() might not throw inputmismatchexception
                try {
                    System.out.println("Enter name of movie to be removed: ");
                    nameToBeRemoved = input.nextLine();
                    flag = false;
                } catch (Exception e) {
                    System.out.println("What you entered was not a string.");
                    System.out.println("Please try again.");
                    flag = true;
                }
            } while (flag);

            do {
                try {
                    System.out.println("Enter room num of movie to be removed: ");
                    roomNum = input.nextInt();
                    flag = true;
                } catch (InputMismatchException ime) {
                    System.out.println("What you entered  was not an integer.");
                    System.out.println("Please try again.");
                    flag = false;
                }
            } while (flag);
            cinemaOfEmployement.getRoom_List().get(roomNum).removeMovieInQueue(nameToBeRemoved);
            System.out.println("The movie has been removed from queue");
            System.out.println("Do you want to remove another movie(y or n)? ");
            answer = input.nextLine();

        } while (answer.toLowerCase().equals("y"));
        input.nextLine();
    }

    // option 4
    void createMovie() {
        cinemaOfEmployement.getMovie_List().add(new Movie(true));
        input.nextLine();
    }

    // option 5 remove seats
    // all that's left is to test and better exception handeling
    void emptyRoomSeat() {
        String answer = "";
        int roomNum = -1;
        int seatCollumn = -1;
        int seatRow = -1;
        boolean flag = false;
        do {
            do {

                try {
                    System.out.println("Enter room nubmer of seat to be removed: ");
                    roomNum = input.nextInt();

                    if (roomNum < 0) {
                        throw new IllegalArgumentException();
                    } else if (roomNum > cinemaOfEmployement.getRoom_List().size()) {
                        throw new IllegalArgumentException();
                    } else {
                        flag = false;
                    }

                } catch (IllegalArgumentException iae) {
                    System.out.println("The integer you entered does not correspond to a room number.");
                    System.out.println(
                            "Please enter an integer between 0 and " + cinemaOfEmployement.getRoom_List().size());
                    flag = true;
                } catch (InputMismatchException ime) {
                    System.out.println("What you entered was not an integer.");
                    System.out.println("Please try again.");
                    flag = true;
                }
            } while (flag);

            cinemaOfEmployement.getRoom_List().get(roomNum).displaySeat();

            do {

                try {

                    System.out.println("Enter the integer corresponding to the seats row: ");
                    seatRow = input.nextInt();

                    if (seatRow < 0) {
                        throw new IllegalArgumentException();
                    } else if (seatRow > cinemaOfEmployement.getRoom_List().get(roomNum).getSeats()[0].length) {
                        throw new IllegalArgumentException();
                    } else {
                        flag = false;
                    }
                } catch (IllegalArgumentException iae) {
                    System.out.println("The integer entered does not correspond to anny seat.");
                    System.out.println("Please enter an integer between 0 and "
                            + cinemaOfEmployement.getRoom_List().get(roomNum).getSeats()[0].length);
                    flag = true;
                } catch (InputMismatchException ime) {
                    System.out.println("What you entered is not an ineger.");
                    System.out.println("please try again.");
                    flag = true;
                }
            } while (flag);
            do {
                try {
                    System.out.println("Enter the letter corresponding to the seats position(A = 1, B = 2, etc...): ");
                    seatCollumn = input.nextInt();
                    if (seatCollumn < 0) {
                        throw new IllegalArgumentException();
                    } else if (seatCollumn > cinemaOfEmployement.getRoom_List().get(roomNum).getSeats().length) {
                        throw new IllegalArgumentException();
                    } else {
                        flag = false;
                    }
                } catch (IllegalArgumentException iae) {
                    System.out.print("The integer entered does not correspond to anny seat.");
                    System.out.println("Please enter an integer between 0 and "
                            + cinemaOfEmployement.getRoom_List().get(roomNum).getSeats().length);
                    flag = true;
                }

            } while (flag);

            emptySeat(roomNum, seatRow, seatCollumn);

            do {

                try {
                    System.out.println("Do you want to empty another seat?");
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
        input.nextLine();
    }

    // option 6
    protected void emptyAllRoomSeats() {
        int roomNum = -1;
        boolean flag = true;
        do {
            try {
                System.out.println("Enter room number of seats to be emptied: ");
                roomNum = input.nextInt();
                if (roomNum < 0) {
                    throw new IllegalArgumentException();
                } else if (roomNum > cinemaOfEmployement.getRoom_List().size()) {
                    throw new IllegalArgumentException();
                } else {
                    flag = false;
                }

            } catch (InputMismatchException e) {
                System.out.println("What you entered is not an intiger: ");
                System.out.println("Please try again.");
                flag = true;
            } catch (IllegalArgumentException iae) {
                System.out.println("The integer you entered does not correspond to a cinema room.");
                System.out.println("Please enter a number between 0 and " + cinemaOfEmployement.getRoom_List().size());
                flag = true;
            }
        } while (flag);
        cinemaOfEmployement.getRoom_List().get(roomNum).emptyAllSeat();
        input.nextLine();
    }

    // option 7 display room seating
    // all that's left is improve exception handling and test
    protected void displayRoomSeating() {
        String answer = "";
        int roomNum = -1;
        boolean flag = true;
        do {
            try {
                System.out.print("Enter room number of seating to be viewed: ");
                roomNum = input.nextInt();
                input.nextLine();

                if (roomNum < 0) {
                    throw new IllegalArgumentException();
                } else if (roomNum > cinemaOfEmployement.getRoom_List().size()) {
                    throw new IllegalArgumentException();
                } else {
                    flag = false;
                }
            } catch (IllegalArgumentException iae) {
                System.out.println("The integer you entered does not correspond to a room number.");
                System.out
                        .println("Please enter an integer between 0 and " + cinemaOfEmployement.getRoom_List().size());
                flag = true;
            } catch (InputMismatchException ime) {
                System.out.println("What you entered was not an integer.");
                System.out.println("Please try again.");
                flag = true;
            }
            while (flag)
                ;

            cinemaOfEmployement.getRoom_List().get(roomNum).displaySeat();
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
        input.nextLine();
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
                flag = false;
            } catch (InputMismatchException ime) {
                System.out.println("What you entered is not a number.");
                System.out.println("Please try again.");
                flag = true;
            }
        } while (flag);
        Ticket.setPrice(newTicketPrice);
        input.nextLine();
    }

    // ------------------------------------------------------------ UI Options End

    // user interaction for Staff class
    public void staffUI() {
        String cont = "";
        boolean flag = true;
        int reply = 0;
        do {

            System.out.println(options());
            do {
                try {
                    System.out.println("Enter the number that corresponds to the action you would like to perform: ");
                    reply = input.nextInt();
                    if (reply < 0 || reply > 8) {
                        throw new IllegalArgumentException();
                    } else {
                        flag = false;
                    }
                } catch (Exception e) {
                    System.out.println("You must enter an integer between 0 and 8");
                    System.out.println("Please try again.");
                    flag = true;
                }
            } while (flag);
            input.nextLine();
            switch (reply) {
                case (0):
                    System.out.println("something went wrong...");
                    break;
                case (1):
                    displayMovieQueue();
                    break;
                case (2):
                    addMovieToQueue();
                    break;
                case (3):
                    removeMovieFromQueue();
                    break;
                case (4):
                    createMovie();
                    ;
                    break;
                case (5):
                    emptyRoomSeat();
                    break;
                case (6):
                    emptyAllRoomSeats();
                    break;
                case (7):
                    displayRoomSeating();
                    break;
                case (8):
                    changeTicketPrice();
                    break;
            }

            do {
                try {
                    System.out.println("do you wish to perform another action(Y or N)? ");
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

    private void emptySeat(int roomNum, int row, int column) {
        cinemaOfEmployement.getRoom_List().get(roomNum).emptySeat(row, column);
        input.nextLine();
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
