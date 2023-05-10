import java.io.FileInputStream;
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
        staffLogIn().staffUI();
    }

    static void onStart() {
        theOnlyCinema.setMovie_List(movieList_DataRead());
        theOnlyCinema.setRoom_List(roomList_DataRead());
        theOnlyCinema.setStaff_List(staffList_DataRead());
        theOnlyCinema.setAdmin_List(adminList_DataRead());
    }

    static Staff staffLogIn() {
        // since I only want to test staff class rn I will assume user is staff
        System.out.print("Enter your userName: ");
        String userName = input.nextLine();

        System.out.println("Enter your pin: ");
        int pin = input.nextInt();

        if (theOnlyCinema.getStaff_List().get(userName).getPin() == pin) {
            return theOnlyCinema.getStaff_List().get(userName);
        } else {
            return null;
        }
    }

    private static HashMap<String, Admin> adminList_DataRead() {
        HashMap<String, Admin> Ad = new HashMap<>();
        try {
            FileInputStream fin = new FileInputStream("StaffInfo.txt");

            int i = 0;
            while ((i = fin.read()) != -1) {
                String str = "";
                if ((char) i == '(') {
                    for (int j = i + 1; (char) j != ')'; j++) {
                        str += (char) j;
                        if ((char) j == ')') {
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

            }
            fin.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        return Ad;
    }

    private static HashMap<String, Staff> staffList_DataRead() {
        HashMap<String, Staff> St = new HashMap<>();
        try {
            FileInputStream fin = new FileInputStream("StaffInfo.txt");

            int i = 0;
            while ((i = fin.read()) != -1) {
                String str = "";
                if ((char) i == '(') {
                    for (int j = i + 1; (char) j != ')'; j++) {
                        str += (char) j;
                        if ((char) j == ')') {
                            String[] parts = str.split(",");

                            for (String part : parts) {
                                part.trim();
                            }
                            St.put(parts[0],
                                    new Staff(parts[0], Integer.parseInt(parts[1]), theOnlyCinema,
                                            Integer.parseInt(parts[3])));

                        }
                    }
                }

            }
            fin.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        return St;
    }

    private static ArrayList<CinemaRoom> roomList_DataRead() {
        return null;
    }

    private static ArrayList<Movie> movieList_DataRead() {
        return null;
    }

}
