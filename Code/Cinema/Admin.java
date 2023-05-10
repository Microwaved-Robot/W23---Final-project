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
            if (pinChecker()) {
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
                        case (11):
                            option11();
                            break;
                    }
                    System.out.println("Do you want to perform another action?:(y or n) ");
                    answer = input.nextLine();
                } while (answer.toLowerCase().equals("y"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String adminOptions() {
        String str = options();
        str += String.format("%s %s\n", "7) ", "Hire Staff.");
        str += String.format("%s %s\n", "8) ", "Fire Staff.");
        str += String.format("%s %s\n", "9) ", "destroy Cinema."); // set cinema to null
        str += String.format("%s %s\n", "9) ", "Create Cinema.");
        str += String.format("%s %s\n", "10) ", "Reset Cinema.");
        str += String.format("%s %s\n", "11) ", "Search for Employee with Highest Sallary.");
        return str;
    }

    // option 7 is used to hire staff
    // how do I create an object with a random name?
    // ex staff (insert name) = new Staff();
    // until database created cannot progress further
    void option7() {

    }

    // option 8 is used to fire staff
    // until database created cannot progress further
    void option8() {

    }

    // option 9 is used to create a new cinema
    // problem, it is impossible to create a cinema with a different variable name
    // for each iteration of this method
    // using a collection would allow for an easier way to create new cinema's
    void option9() {

        System.out.println("Enter the branch number of the new cinema: ");
        int branchNumber = input.nextInt();

        System.out.println("Enter the name of the cinema: ");

        Cinema c = new Cinema(branchNumber);

    }

    // option 10 resets the cinema
    // can't specify which cinema to reset withought it being in a collection.
    void option10() {
        System.out.println("Enter the ");
    }

    // option 11 searches through database for employee with highest salary
    // this will require binary search.
    // this is also unacomplishable withough a database
    void option11() {

    }

    // -------------------------------------- end admin UI
    public static boolean isAdmin() {
        return isAdmin;
    }

    public static void setAdmin(boolean isAdmin) {
        Admin.isAdmin = isAdmin;
    }

    public void hireStaff(Staff staff) {
        Staff NS = new Staff();
        NS = staff;
    }

    private void destroyCinema() {
        cinemaOfEmployement.setMovie_List(null);
        // cinemaOfEmployement.setroom_List(null);
        // the setroom_List method doesn't exist yet.
    }

    private void createCinema(Cinema cinema) {
        Cinema NC = new Cinema();
        NC = cinema;
    }

    private void resetCinema() {
        cinemaOfEmployement.setMovie_List(null);
        // cinemaOfEmployement.setroom_List(null);
    }
}
