package Code.Cinema;

import java.util.HashMap;

public class Admin extends Staff {
    private static boolean isAdmin = true;
    // incrementor for creating different staff members?
    // ex: everytime you create a staff member the count goes up and each staff
    // member has the name staff(i)?

    protected Admin() {
        super();
    }

    public Admin(String name, int age, Cinema cinemaOfEmployment, int PIN) {
        super(name, age, cinemaOfEmployment, PIN);
    }

    // ------------------------------------- start admin UI
    public void adminUI() {
        int reply = 0;
        String answer = "";
        String cont = "";
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
                    case (10):
                        option10();
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
        str += String.format("%s %s\n", "10) ", "Search for Employee with Highest Sallary."); // this won't work as
                                                                                              // using binary search to
                                                                                              // look for the highest
                                                                                              // value in a collection
                                                                                              // is pointless
        return str;
    }
    // maybe I can add a promote to admin method?

    // option 7 is used to hire staff
    // all that's left is to test and exception handling
    void option7() {
        System.out.println("Enter the staff member's name: ");
        String name = input.nextLine();
        cinemaOfEmployement.getStaff_List().put(name, new Staff(name));
        input.nextLine();
    }

    // option 8 is used to fire staff
    // until database created cannot progress further
    void option8() {
        System.out.println(cinemaOfEmployement.getStaff_List());
        System.out.println("Enter the staff member's username: ");
        cinemaOfEmployement.getStaff_List().remove(input.nextLine());
        input.nextLine();
    }

    // option 9 resets the cinema
    // All that's left is test and exception handling
    void option9() {
        cinemaOfEmployement.setAdmin_List(null);
        cinemaOfEmployement.setStaff_List(null);
        cinemaOfEmployement.setMovie_List(null);
        cinemaOfEmployement.setRoom_List(null);
    }

    // option 11 searches through database for employee with highest salary
    // this will require binary search.
    // this is also unacomplishable withough a database
    void option10() {

    }

    // -------------------------------------- end admin UI
    public static boolean isAdmin() {
        return isAdmin;
    }

    public static void setAdmin(boolean isAdmin) {
        Admin.isAdmin = isAdmin;
    }

    // this method could potentialy cause something to go wrong
    public String toString() {
        return "(" + name + "," + age + "," + "theOnlyCinema" + "," + PIN + ")";
    }
}
