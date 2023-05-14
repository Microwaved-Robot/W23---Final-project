package Code.Cinema;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends Staff {
    // I should store a set of values in an array to allow for sorting and searching
    // algorithms to
    // be implemented

    private static boolean isAdmin = true;

    protected Admin() {
        super();
    }

    public Admin(String name, int age, Cinema cinemaOfEmployment, int PIN) {
        super(name, age, cinemaOfEmployment, PIN);
    }

    public Admin(String name) {
        this.name = name;
        System.out.println("Enter admin's age: ");
        int age = input.nextInt();
        this.cinemaOfEmployement = null;
        System.out.println("Enter admin's pin: ");
        this.PIN = input.nextInt();
    }

    // ------------------------------------- start admin UI
    public void adminUI() {
        int reply = 0;
        String answer = "";
        try {
            do {
                System.out.println(adminOptions());
                System.out.println("Enter the number that corresponds to the action you would like to perform: ");
                reply = input.nextInt();

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
                    case (7):
                        option7();
                        break;
                    case (8):
                        option8();
                        break;
                    case (9):
                        option9();
                        break;
                }
                System.out.println("Do you want to perform another action?:(y or n) ");
                answer = input.nextLine();
            } while (answer.toLowerCase().equals("y"));
        } catch (Exception e) {
            System.out.println(e);
        }
        input.nextLine();
    }

    private String adminOptions() {
        String str = options();
        str += String.format("%s %s\n", "7) ", "Hire Staff.");
        str += String.format("%s %s\n", "8) ", "Fire Staff.");
        str += String.format("%s %s\n", "9) ", "Reset Cinema.");
        return str;
    }

    // maybe I can add a promote to admin method? (if I have time)

    // option 7 is used to hire staff
    // all that's left is to test and exception handling
    void option7() {
        System.out.println("Enter the staff member's name: ");
        String name = input.nextLine();
        cinemaOfEmployement.getStaffArray().add(new Staff(name));
        input.nextLine();
    }

    // option 8 is used to fire staff
    // all that's left is to test
    void option8() {
        System.out.println(cinemaOfEmployement.getStaffArray().toString());
        System.out.println("Enter the staff member's username: ");
        int index = binarySearch(cinemaOfEmployement.getStaffArray(), input.nextLine());
        cinemaOfEmployement.getStaffArray().remove(index);
        input.nextLine();
    }

    // option 9 resets the cinema
    // All that's left is test and exception handling
    void option9() {
        cinemaOfEmployement.setAdminArray(null);
        cinemaOfEmployement.setStaffArray(null);
        cinemaOfEmployement.setMovie_List(null);
        cinemaOfEmployement.setRoom_List(null);
        System.out.println("The cinema has been reset.");
    }

    // -------------------------------------- end admin UI
    public static boolean isAdmin() {
        return isAdmin;
    }

    public static void setAdmin(boolean isAdmin) {
        Admin.isAdmin = isAdmin;
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
