package Code.Cinema;

import java.util.Scanner;

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
        this.cinemaOfEmployement = null;
    }

    public Staff() {
        // default
    }

    public Staff(boolean ignore) {
        System.out.print("Enter the staff member's name: ");
        this.name = input.nextLine();
        System.out.print("Enter the Staff member's pin: ");
        this.PIN = input.nextInt();
        System.out.print("Enter the staff member's age: ");
        this.age = input.nextInt();
        this.cinemaOfEmployement = null;
    }

    public Staff(String name) {
        System.out.print("Enter the Staff member's pin: ");
        this.PIN = input.nextInt();
        System.out.print("Enter the staff member's age: ");
        this.age = input.nextInt();
        this.cinemaOfEmployement = null;
    }

    // --------------------------------------------------------- UI Options Start
    // provides string of options.
    protected String options() {
        String str = String.format("%s %s\n", "1) ", "Add movie to room queue.");
        str += String.format("%s %s\n", "2) ", "Remove movie from room queue.");
        str += String.format("%s %s\n", "3) ", "Empty seat(s) from room.");
        str += String.format("%s %s\n", "4) ", "Display the room seating.");
        str += String.format("%s %s\n", "5) ", "Change adult ticket price.");
        str += String.format("%s %s\n", "6) ", "Change child ticket price.");
        return str;
    }

    // option 1) add movie to queue options
    // All that's left is to test and exception handling
    void option1() {

        String answer = "";
        do {
            System.out.println("Which room with you be adding the movie to?");
            int roomNum = input.nextInt();

            cinemaOfEmployement.getRoom_List().get(roomNum).addMovieToQueue(new Movie());

            System.out.println("Do you want to add another movie?(Y or N): ");
            answer = input.nextLine();

        } while (answer.toLowerCase().equals("y"));

    }

    // option 2 remove a movie from the queue of movies
    // all that's left is test and exception handling
    void option2() {
        String answer = "";
        do {
            System.out.println("Enter name of movie to be removed: ");
            String nameToBeRemoved = input.nextLine();

            System.out.println("Enter room num of movie to be removed: ");
            int roomNum = input.nextInt();

            cinemaOfEmployement.getRoom_List().get(roomNum).removeMovieInQueue(nameToBeRemoved);
            System.out.println("The movie has been removed from queue");
            System.out.println("Do you want to remove another movie?(y or n): ");
            answer = input.nextLine();

        } while (answer.toLowerCase().equals("y"));
    }

    // option 3 remove seats
    // all that's left is to test and better exception handeling
    void option3() {
        String answer = "";
        do {

            try {
                System.out.println("Enter room nubmer of seat to be removed: ");
                int roomNum = input.nextInt();

                cinemaOfEmployement.getRoom_List().get(roomNum).displaySeat();

                System.out.println("Enter the integer corresponding to the seats row: ");
                int seatRow = input.nextInt();

                System.out.println("Enter the letter corresponding to the seats position: ");
                int seatCollumn = input.nextInt();

                emptySeat(roomNum, seatRow, seatCollumn);
            } catch (Exception e) {
                System.out.println("what you entered was not a number please try again");
                answer = "y";
            }

        } while (answer.toLowerCase().equals("y"));
    }

    // option 4 display room seating
    // all that's left is improve exception handling and test
    void option4() {
        String answer = "";
        do {
            try {
                System.out.print("Enter room nubmer of seating to be viewed: ");
                int roomNum = input.nextInt();

                cinemaOfEmployement.getRoom_List().get(roomNum).displaySeat();
                System.out.print("Would you like to view the seating of another room?(y or n): ");
                answer = input.nextLine();
            } catch (Exception e) {
                System.out.println("there was a mistake while retreving your room's seating, please try again...");
                answer = "y";
            }

        } while (answer.toLowerCase().equals("y"));
    }

    // option 5 change adult ticket price
    // call set price method.
    // only need one set price method as mackenzie has made it so that people
    // recieve discounts rather than adults and children having different prices
    void option5() {
        String answer = "";
        do {
            try {
                System.out.println("Enter new adult ticket price: ");
                int newTicketPrice = input.nextInt();

            } catch (Exception e) {
                System.out.println("Something went wrong please try again...");
                answer = "y";
            }

        } while (answer.toLowerCase().equals("y"));
    }

    // option 6 change child price ticket
    // option 6 has the same issue as option 5 and will be resolved once I discuss
    // done as much as possible
    void option6() {

    }

    // ------------------------------------------------------------ UI Options End

    // user interaction for Staff class
    public void staffUI() {
        String cont = "";
        do {
            int reply = 0;
            System.out.println(options());
            try {
                System.out.println(
                        "Please select the number that corresponds to the action you would like to perform: ");
                options();
                reply = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println();
            }
            switch (reply) {
                case (0):
                    System.out.println("something went wrong...");
                    break;
                case (1):
                    option1();
                    break;
                case (2):
                    option2();
                    break;
                case (3):
                    option3();
                    break;
                case (4):
                    option4();
                    break;
                case (5):
                    option5();
                    break;
                case (6):
                    option6();
                    break;
            }

            System.out.println("do you wish to continue?(Y or N) ");
            cont = input.nextLine();

        } while (cont.toLowerCase().equals("y"));
    }

    private void emptySeat(int roomNum, int row, int column) {
        cinemaOfEmployement.getRoom_List().get(roomNum).emptySeat(row, column);
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

    // Things that go in main

}
