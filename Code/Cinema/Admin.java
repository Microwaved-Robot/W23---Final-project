package Code.Cinema;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Admin extends Staff {
    // amind done other than testing

    private static boolean isAdmin = true;

    protected Admin() {
        super();
    }

    public Admin(String name, int age, Cinema cinemaOfEmployment, int PIN) {
        super(name, age, cinemaOfEmployment, PIN);
    }

    public Admin(Cinema cinema) {
        boolean flag = true;

        do {
            try {
                System.out.print("Enter the admin's name: ");
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
                System.out.print("Enter admin's age: ");
                this.age = input.nextInt();
                flag = false;
            } catch (InputMismatchException ime) {
                System.out.println("The age entered must be an integer.");
                flag = true;
            }
        } while (flag);

        this.cinemaOfEmployement = cinema;

        do {
            try {
                System.out.print("Enter admin's pin: ");
                this.PIN = input.nextInt();
                flag = false;
            } catch (InputMismatchException ime) {
                System.out.println("The pin entered must be an intiger.");
                System.out.println("Please try again.");
                flag = true;
            }
        } while (flag);
        input.nextLine();
    }

    public Admin(String name) {
        boolean flag = true;
        do {
            try {
                System.out.println("Enter admin's age: ");
                this.age = input.nextInt();
                flag = false;
            } catch (InputMismatchException ime) {
                System.out.println("The age entered must be an integer.");
                flag = true;
            }
        } while (flag);
        this.cinemaOfEmployement = null;
        do {
            try {
                System.out.println("Enter admin's pin: ");
                this.PIN = input.nextInt();
                flag = false;
            } catch (InputMismatchException ime) {
                System.out.println("The pin entered must be an intiger.");
                System.out.println("Please try again.");
                flag = true;
            }
        } while (flag);
        input.nextLine();
    }

    // ------------------------------------- start admin UI
    public void adminUI(Cinema cinema, ArrayList<Staff> staffArray) {
        int reply = 0;
        String answer = "";
        boolean flag = true;
        do {
            do {

                System.out.println(adminOptions());
                try {
                    System.out
                            .println("Enter the number that corresponds to the action you would like to perform: ");
                    reply = input.nextInt();
                    if (reply < 0 || reply > 11) {
                        throw new IllegalArgumentException();
                    } else {
                        flag = false;
                    }
                } catch (Exception e) {
                    System.out.println("You must enter an integer between 1 and 11");
                    System.out.println("Please try again.");
                    flag = true;
                }
            } while (flag);
            input.nextLine();

            switch (reply) {
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
                case (9):
                    hireStaff(staffArray, cinema);;
                    break;
                case (10):
                    fireStaff(staffArray);
                    break;
                case (11):
                    resetCinema();
                    break;
            }
            do {
                try {
                    System.out.println("do you wish to perform another action(Y or N)? ");
                    answer = input.nextLine();

                    if (!answer.toLowerCase().equals("y") && !answer.toLowerCase().equals("n")) {
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
        } while (answer.toLowerCase().equals("y"));
    }

    private String adminOptions() {
        String str = options();
        str += String.format("%s %s\n", "9) ", "Hire Staff.");
        str += String.format("%s %s\n", "10) ", "Fire Staff.");
        str += String.format("%s %s\n", "11) ", "Reset Cinema.");
        return str;
    }

    // maybe I can add a promote to admin method? (if I have time)

    // option 7 is used to hire staff
    // all that's left is to test and exception handling
    void hireStaff(ArrayList<Staff> staffArray, Cinema cinema) {
        String name = "";
        boolean flag = false;
        do {
            try {
                System.out.println("Enter the staff member's name: ");
                name = input.nextLine();
                flag = false;
            } catch (Exception e) {
                System.out.println("What you entered was not a string please try again.");
                flag = true;
            }
        } while (flag);
        staffArray.add(new Staff(name, cinema));
        input.nextLine();
    }

    // option 8 is used to fire staff
    // all that's left is to test
    void fireStaff(ArrayList<Staff> staffArray) {
        System.out.println(staffArray.toString());
        int index = -1;
        boolean flag = true;
        do {
            try {
                System.out.println("Enter the staff member's username: ");
                index = binarySearch(staffArray, input.nextLine());
                flag = false;

            } catch (InputMismatchException ime) {
                System.out.println("What you entered is not a string.");
                flag = true;
            }
        } while (flag);
        staffArray.remove(index);
        input.nextLine();
    }

    // option 11 resets the cinema
    // All that's left is test and exception handling
    void resetCinema() {
        // cinemaOfEmployement.setAdminArray(null);
        // cinemaOfEmployement.setStaffArray(null);
        cinemaOfEmployement.setMovie_List(null);
        cinemaOfEmployement.setRoom_List(null);
        System.out.println("The cinema has been reset.");
        input.nextLine();
    }

    // -------------------------------------- end admin UI
    public static boolean isAdmin() {
        return isAdmin;
    }

    public static void setAdmin(boolean isAdmin) {
        Admin.isAdmin = isAdmin;
    }

    public int getPin() {
        return PIN;
    }

    public String toString() {
        return "(" + name + "," + age + "," + "theOnlyCinema" + "," + PIN + ")";
    }

    public int binarySearch(ArrayList<Staff> staffArray, String name) {
        int index = -1;
        int first = 0;
        int last = staffArray.size() - 1;
        int mid = (first + last) / 2;
        while (first <= last) {
            if ((staffArray.get(mid).getName().compareTo(name)) < 0) {
                first = mid + 1;
            } else if (staffArray.get(mid).getName().equals(name)) {
                index = mid;
                break;
            } else {
                last = mid - 1;
            }
            mid = (first + last) / 2;
        }
        if (first > last) {
            System.out.println("Element is not found!");
        }
        return index;
    }

}
