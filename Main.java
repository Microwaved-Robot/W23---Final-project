import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Code.Cinema.Admin;
import Code.Cinema.Cinema;
import Code.Cinema.CinemaRoom;
import Code.Cinema.Movie;
import Code.Cinema.Staff;

public class Main {
    private static final Cinema theOnlyCinema = new Cinema();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // ask if staff or client

        // ask for name
        // ask for pin
        onStart();

        // staffLogIn().staffUI();

        onEnd();
    }

    static void onStart() {
        theOnlyCinema.setMovie_List(movieList_DataRead());
        theOnlyCinema.setRoom_List(roomList_DataRead());
        theOnlyCinema.setStaff_List(staffList_DataRead());
        theOnlyCinema.setAdmin_List(adminList_DataRead());
    }

    static Staff staffLogIn() {
        // since I only want to test staff class rn I will assume user is staff

        int realPin;
        int pin;
        String answer;
        String userName;

        System.out.print("Enter your userName: ");
        userName = input.nextLine();

        realPin = theOnlyCinema.getStaff_List().get(userName).getPin();
        do {
            System.out.println("Enter your pin: ");
            pin = input.nextInt();
            input.nextLine();

            if (realPin != pin) {
                System.out.println("The pin you entered is incorrect...");
                System.out.println("Would you like to try again(Y or N)?");
                answer = input.nextLine();
                if (answer.toLowerCase().equals("n")) {
                    System.exit(1);
                }
            }

        } while (realPin != pin);
        System.out.println("Welcome back " + userName);
        return theOnlyCinema.getStaff_List().get(userName);
    }

    static Staff AdminLogIn() {
        // since I only want to test staff class rn I will assume user is staff
        int pin;
        String answer;

        System.out.print("Enter your userName: ");
        String userName = input.nextLine();

        int realPin = theOnlyCinema.getAdmin_List().get(userName).getPin();

        do {
            System.out.println("Enter your pin: ");
            pin = input.nextInt();
            input.nextLine();

            if (realPin != pin) {
                System.out.println("The pin you entered is incorrect...");
                System.out.println("Would you like to try again(Y or N)?");
                answer = input.nextLine();
                if (answer.toLowerCase().equals("n")) {
                    System.exit(1);
                }
            }

        } while (realPin != pin);
        System.out.println("Welcome back " + userName);
        return theOnlyCinema.getAdmin_List().get(userName);
    }

    private static HashMap<String, Admin> adminList_DataRead() {
        HashMap<String, Admin> Ad = new HashMap<>();
        try {
            FileInputStream fin = new FileInputStream("AdminInfo.txt");

            int i = 0;
            while ((i = fin.read()) != -1) {
                String str = "";
                if ((char) i == '(') {

                    while ((char) (i = fin.read()) != ')') {
                        str += (char) i;

                    }
                    if ((char) i == ')') {
                        String[] parts = str.split(",");

                        for (String part : parts) {
                            part.trim();
                        }
                        Ad.put(parts[0],
                                new Admin(parts[0], Integer.parseInt(parts[1]), theOnlyCinema,
                                        Integer.parseInt(parts[3])));

                    }
                }

            }
            fin.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            System.out.println("something went wrong...");
        }
        return Ad;
    }

    private static HashMap<String, Staff> staffList_DataRead() {
        HashMap<String, Staff> Ad = new HashMap<>();
        try {
            FileInputStream fin = new FileInputStream("StaffInfo.txt");

            int i = 0;
            while ((i = fin.read()) != -1) {
                String str = "";
                if ((char) i == '(') {

                    while ((char) (i = fin.read()) != ')') {
                        str += (char) i;

                    }
                    if ((char) i == ')') {
                        String[] parts = str.split(",");

                        for (String part : parts) {
                            part.trim();
                        }
                        Ad.put(parts[0],
                                new Staff(parts[0], Integer.parseInt(parts[1]), theOnlyCinema,
                                        Integer.parseInt(parts[3])));

                    }
                }

            }
            fin.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            System.out.println("something went wrong...");
        }
        return Ad;
    }

    private static ArrayList<CinemaRoom> roomList_DataRead() {
        return null;
    }

    private static ArrayList<Movie> movieList_DataRead() {
        return null;
    }

    static void onEnd() {
        adminList_DataWrite();
        staffList_DataWrite();
        roomList_DataWrite();
        movieList_DataWrite();
    }

    private static void movieList_DataWrite() {

    }

    private static void roomList_DataWrite() {

    }

    private static void staffList_DataWrite() {
        try {
            FileOutputStream fout = new FileOutputStream("StaffInfo.txt");
            // goal convert each staff member into string of values seperated by only comas
            // and () at beginning and end
            // I'll just do this in a loop and then write each character and then println
            // and repeat

            String adminString = theOnlyCinema.getStaff_List().toString();

            String[] adminArray = adminString.split("=");
            String adminMember = "";
            for (int i = 1; i < adminArray.length; i++) {
                adminMember = adminArray[i].substring(adminArray[i].indexOf("("),
                        adminArray[i].indexOf(")"));
                adminMember = adminMember + ")";
                System.out.println(adminMember);
                for (int k = 0; k < adminMember.length(); k++) {
                    fout.write((int) adminMember.charAt(k));
                }
            }
            fout.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            System.out.println("something went wrong...");
        }
    }

    private static void adminList_DataWrite() {
        try {
            FileOutputStream fout = new FileOutputStream("StaffInfo.txt");
            // goal convert each staff member into string of values seperated by only comas
            // and () at beginning and end
            // I'll just do this in a loop and then write each character and then println
            // and repeat

            String adminString = theOnlyCinema.getAdmin_List().toString();

            String[] adminArray = adminString.split("=");
            String adminMember = "";
            for (int i = 1; i < adminArray.length; i++) {
                adminMember = adminArray[i].substring(adminArray[i].indexOf("("),
                        adminArray[i].indexOf(")"));
                adminMember = adminMember + ")";
                for (int k = 0; k < adminMember.length(); k++) {
                    fout.write((int) adminMember.charAt(k));
                }
            }
            fout.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            System.out.println("something went wrong...");
        }
    }

}
