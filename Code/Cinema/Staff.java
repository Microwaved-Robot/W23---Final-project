package Code.Cinema;

import java.time.LocalDateTime;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

public class Staff {
    Scanner input = new Scanner(System.in);

    private String name;
    private int age;
    private int PIN;
    private static boolean isAdmin = false;
    private Cinema cinemaOfEmployement;

    public Staff(String cinema) {

        System.out.print("Enter the staffName: ");
        name = input.nextLine();

        System.out.print("Enter his age: ");
        age = input.nextInt();

        // to make sure the scanner works next time
        input.nextLine();
    }

    public Staff(String name, int age, Cinema cinema, int pin) {
        this.PIN = pin;
        this.name = name;
        this.age = age;
        this.cinemaOfEmployement = cinema;
    }

    public Staff() {
        this.PIN = 0;
        this.name = null;
        this.age = 0;
        this.cinemaOfEmployement = null;
    }

    // --------------------------------------------------------- UI Options Start
    // provides string of options.
    String options() {
        String str = String.format("%s %s\n", "1) ", "Add movie to room queue.");
        str += String.format("%s %s\n", "2) ", "Remove movie from room queue.");
        str += String.format("%s %s\n", "3) ", "Empty seat(s) from room.");
        str += String.format("%s %s\n", "4) ", "Display the room seating.");
        str += String.format("%s %s\n", "5) ", "Change adult ticket price.");
        str += String.format("%s %s\n", "6) ", "Change child ticket price.");
        return str;
    }

    // option 1) add movie to queue options
    void option1() {

        String answer = "";
        do {
            Movie movieToBeAdded = new Movie();

            System.out.println("Which room with you be adding the movie to?");
            int roomNum = input.nextInt();

            cinemaOfEmployement.getroom_List().get(roomNum).addMovieToQueue(movieToBeAdded);

            System.out.println("Do you choose to add another movie?(Y or N): ");
            answer = input.nextLine();

        } while (answer.toLowerCase().equals("y"));

    }

    // option 2 remove a movie from the queue of movies
    void option2() {
        String answer = "";
        do {
            System.out.println("Enter name of movie to be removed: ");
            String nameToBeRemoved = input.nextLine();

            System.out.println("Enter room num of movie to be removed: ");
            int roomNum = input.nextInt();

            cinemaOfEmployement.getroom_List().get(roomNum).removeMovieInQueue(nameToBeRemoved);
            System.out.println("The movie has been removed from queue");
            System.out.println("Do you want to remove another movie?(y or n): ");
            answer = input.nextLine();

        } while (answer.toLowerCase().equals("y"));
    }

    // option 3 remove seats
    void option3() {
        String answer = "";
        do {

            try {
                System.out.println("Enter room nubmer of seat to be removed: ");
                int roomNum = input.nextInt();

                cinemaOfEmployement.getroom_List().get(roomNum).displaySeat();

                System.out.println("Enter the integer corresponding to the seats row: ");
                int seatRow = input.nextInt();

                System.out.println("Enter the letter corresponding to the seats position: ");
                int seatCollumn = input.nextInt();

                cinemaOfEmployement.getroom_List().get(roomNum).emptySeat(seatRow, seatCollumn);
            } catch (Exception e) {
                System.out.println("what you entered was not a number please try again");
                answer = "y";
            }

        } while (answer.toLowerCase().equals("y"));
    }

    // option 4 display room seating
    void option4() {
        String answer = "";
        do {
            try {
                System.out.print("Enter room nubmer of seating to be viewed: ");
                int roomNum = input.nextInt();

                cinemaOfEmployement.getroom_List().get(roomNum).displaySeat();
                System.out.print("Would you like to view the seating of another room?(y or n): ");
                answer = input.nextLine();
            } catch (Exception e) {
                System.out.println("there was a mistake while retreving your room's seating, please try again...");
                answer = "y";
            }

        } while (answer.toLowerCase().equals("y"));
    }

    // option 5 change adult ticket price
    // in order to change the price of a ticket from this class I will need add
    // ticket as a parameter for this class
    // an easier way could be to initialize a ticket as a parameter in the cinema
    // class so that I can change the values from this class without needing to
    // create ticket as a new variable which will only be accessible through this
    // class.
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

    // option 6 has the same issue as option 5 and will be resolved once I discuss
    // with Ze
    void option6() {
        String answer = "";
        do {
            System.out.println("Enter room nubmer of seat to be removed: ");
            int roomNum = input.nextInt();

            try {
                System.out.println("Enter the number corresponding to the seats position: ");
                int seatNum = input.nextInt();

            } catch (Exception e) {

            }

        } while (answer.toLowerCase().equals("y"));
    }

    // ------------------------------------------------------------ UI Options End

    // user interaction for Staff class
    void UI() {
        if (pinChecker()) {
            String cont = "";
            do {
                try {
                    System.out.println(
                            "Please select the number that corresponds to the action you would like to perform: ");
                    options();
                    int reply = input.nextInt();
                } catch (exception e) {
                    System.out.println();
                }
                switch (reply) {
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
    }

    // this pinChecker works for now but a stranger can easily guess someone elses
    // pin by mistake
    // Solution: change pw to String then indicate to new employee that they must
    // include their user name at the beginning of their pw
    // then when we greet the employee we can create a substring of the pw provided.
    boolean pinChecker() {
        int pin = 0;
        do {
            System.out.print("Enter your PIN: ");
            pin = input.nextInt();
            if (pin == this.PIN) {
                System.out.println("Welcome back " + this.name + ".");
                return true;
            } else {
                System.out.println("Would you like to reatempt entering your password? (Y or N)");
                String answer = input.nextLine();
                if (answer.toLowerCase() == "n") {
                    return false;
                }
            }
        } while (this.PIN != pin);
        return false;
    }

    // to empty a seat I need to know:
    // the cinema that the staff member is a part of
    // the cinema room that the staff wants to empty the seat of
    // the location of the seat in the room.
    private void emptySeat(int roomNum, int row, int column) {
        cinemaOfEmployement.getRooms().getNumberOfRoom()[row][column] = false;
    }

    public String getName() {
        return this.name;
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

    @Override
    public String toString() {
        return "staff [name=" + name + ", age=" + age + "]";
    }

}
