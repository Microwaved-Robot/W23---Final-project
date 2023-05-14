package Code;

import java.io.File;
// import java.io.FileInputStream;
import java.io.IOException;
// import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

// import Code.Cinema.Admin;
import Code.Cinema.Cinema;
// import Code.Cinema.Staff;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // To import the cinema file that is already existing
        Cinema cinema = onStart();

        if (cinema == null) {
            cinema = new Cinema(false);
        }

        boolean flag = true;
        int choice = 0;

        do {
            try {
                System.out.println("1) Client");
                System.out.println("2) Admin");
                System.out.println("3) Staff");
                System.out.println("4) Exit program");
                System.out.print("Chose your option: ");
                choice = input.nextInt();
                flag = false;
            } catch (InputMismatchException e) {
                System.out.println("You must enter a number between 1 & 4.");
                flag = true;
            }
        } while (flag);
        switch (choice) {
            case 1:
                int age = -1;
                do {
                    try {
                        System.out.print("Enter you age: ");
                        age = input.nextInt();
                        if (age > 116) {
                            System.out.println("Wow you are the oldest human alive o_o");
                        }
                        if (age <= 0) {
                            throw new IllegalArgumentException("Negative age");
                        }
                        flag = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Enter a integer");
                        flag = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Enter a number above 0");
                        flag = true;
                    }
                } while (flag);
                if (age < 18) {
                    
                }
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
        // ask if staff or client

        // ask for name
        // ask for pin
        // onStart();

        // userIdentifier();

        // onEnd();
    }

    static void userIdentifier() {
        boolean flag = true;
        int reply = 0;
        do {
            try {
                System.out.printf("%s\n%s\n%s\n%s\n", "Welcome, are you a:", "1) Client", "2) Staff member",
                        "3) Administrator");
                reply = input.nextInt();
                if (reply < 1 || reply > 3) {
                    throw new IllegalArgumentException();
                } else {
                    flag = false;
                }
            } catch (InputMismatchException ime) {
                System.out.println("What you entered was not an integer.");
                System.out.println("Please try again.");
                flag = true;
            } catch (IllegalArgumentException iae) {
                System.out.println("You must enter an integer between 1 and 3.");
                System.out.println("Please try again.");
                flag = true;
            }
        } while (flag);
        switch (reply) {
            case (1):
                break;
            case (2):
                Cinema.staffLogIn().staffUI();
                break;
            case (3):
                Cinema.AdminLogIn().adminUI();
        }
        input.nextLine();
    }

    static Cinema onStart() {
        // theOnlyCinema.setMovie_List(movieList_DataRead());
        // theOnlyCinema.setRoom_List(roomList_DataRead());
        // theOnlyCinema.setStaffArray(staffList_DataRead());
        // theOnlyCinema.bubbleSort(Cinema.getStaffArray());
        // theOnlyCinema.setAdminArray(adminList_DataRead());
        // theOnlyCinema.adminBubbleSort(Cinema.getAdminArray());
        File file = new File(
                "C:\\Users\\zeze3\\OneDrive\\Documents\\GitHub\\W23---Final-project\\Code\\Json\\cinema.json");
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        Cinema cinema = null;
        try {
            cinema = om.readValue(file, Cinema.class);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
        return cinema;
    }

    // static void onEnd() {
    // staffList_DataWrite();
    // adminList_DataWrite();
    // }

    // change return tipe to Array of admins
    // private static ArrayList<Admin> adminList_DataRead() {
    // // this part's EZ
    // ArrayList<Admin> Ad = new ArrayList<>();
    // try {
    // FileInputStream fin = new FileInputStream("Code\\textFiles\\AdminInfo.txt");
    // int i = 0;
    // while ((i = fin.read()) != -1) {
    // String str = "";
    // if ((char) i == '(') {

    // while ((char) (i = fin.read()) != ')') {
    // str += (char) i;

    // }
    // if ((char) i == ')') {
    // String[] parts = str.split(",");

    // for (String part : parts) {
    // part.trim();
    // }
    // // make a new int incrementor that increases for every admin that you add to
    // the
    // // array starting at 0,
    // // then add to array by using array[incrementor] = ...
    // Ad.add(new Admin(parts[0], Integer.parseInt(parts[1]), theOnlyCinema,
    // Integer.parseInt(parts[3])));

    // }
    // }

    // }
    // fin.close();

    // } catch (Exception e) {
    // // TODO: handle exception
    // System.out.println(e);
    // System.out.println("something went wrong...");
    // }
    // return Ad;
    // }

    // // do same here as did in admin dataRead
    // private static ArrayList<Staff> staffList_DataRead() {
    // ArrayList<Staff> staffList = new ArrayList<>();
    // try {
    // FileInputStream fin = new FileInputStream("Code\\textFiles\\StaffInfo.txt");

    // int i = 0;
    // while ((i = fin.read()) != -1) {
    // String str = "";
    // if ((char) i == '(') {

    // while ((char) (i = fin.read()) != ')') {
    // str += (char) i;

    // }
    // if ((char) i == ')') {
    // String[] parts = str.split(",");

    // for (String part : parts) {
    // part.trim();
    // }
    // staffList.add(
    // new Staff(parts[0], Integer.parseInt(parts[1]), theOnlyCinema,
    // Integer.parseInt(parts[3])));

    // }
    // }

    // }
    // fin.close();

    // } catch (Exception e) {
    // // TODO: handle exception
    // System.out.println(e);
    // System.out.println("something went wrong...");
    // }
    // return staffList;
    // }

    // private static void staffList_DataWrite() {
    // try {
    // FileOutputStream fout = new
    // FileOutputStream("Code\\textFiles\\StaffInfo.txt");
    // // Can I convert an array to string in one comant like I did with staff_List?
    // // once I figure out how to convert to string the rest of the code will
    // remain
    // // the same

    // String adminString = theOnlyCinema.getStaffArray().toString();

    // System.out.println(adminString);
    // String[] adminArray = adminString.split(",");
    // System.out.println(adminArray);
    // String adminMember = "";
    // for (int i = 0; i < adminArray.length; i++) {
    // adminMember = adminArray[i].substring(adminArray[i].indexOf("("),
    // adminArray[i].indexOf(")"));
    // adminMember = adminMember + ")";
    // System.out.println(adminMember);
    // for (int k = 0; k < adminMember.length(); k++) {
    // fout.write((int) adminMember.charAt(k));
    // }
    // }
    // fout.close();
    // } catch (Exception e) {
    // // TODO: handle exception
    // System.out.println(e);
    // System.out.println("something went wrong...");
    // }
    // }

    // private static void adminList_DataWrite() {
    // try {
    // FileOutputStream fout = new
    // FileOutputStream("Code\\textFiles\\AdminInfo.txt");
    // // same as staffList_darawrite

    // String adminString = theOnlyCinema.getAdminArray().toString();

    // String[] adminArray = adminString.split("=");
    // String adminMember = "";
    // for (int i = 1; i < adminArray.length; i++) {
    // adminMember = adminArray[i].substring(adminArray[i].indexOf("("),
    // adminArray[i].indexOf(")"));
    // adminMember = adminMember + ")";
    // for (int k = 0; k < adminMember.length(); k++) {
    // fout.write((int) adminMember.charAt(k));
    // }
    // }
    // fout.close();
    // } catch (Exception e) {
    // // TODO: handle exception
    // System.out.println(e);
    // System.out.println("something went wrong...");
    // }
    // }

    /*----------------------------------JSON Converter Method----------------------------------*/
    public static void cinemaConverter(Cinema cinema) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File file = new File(
                "C:\\Users\\zeze3\\OneDrive\\Documents\\GitHub\\W23---Final-project\\Code\\Json\\cinema.json");
        try {
            mapper.writeValue(file, cinema);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // public static void StaffConverter(ArrayList<Staff> staff_List) {
    // ObjectMapper mapper = new ObjectMapper();
    // mapper.registerModule(new JavaTimeModule());
    // File file = new
    // File("C:\\Users\\zeze3\\OneDrive\\Documents\\GitHub\\W23---Final-project\\Code\\Json\\Staff.json");
    // try {
    // mapper.writeValue(file, staff_List);
    // } catch (IOException e) {
    // System.out.println(e);
    // }
    // }

    // public static void AdminConverter(ArrayList<Admin> Admin_List) {
    // ObjectMapper mapper = new ObjectMapper();
    // mapper.registerModule(new JavaTimeModule());
    // File file = new
    // File("C:\\Users\\zeze3\\OneDrive\\Documents\\GitHub\\W23---Final-project\\Code\\Json\\Admin.json");
    // try {
    // mapper.writeValue(file, Admin_List);
    // } catch (IOException e) {
    // System.out.println(e);
    // }
    // }

    // public static void AdultClientConverter(HashMap<String, AdultClient>
    // client_Map) {
    // ObjectMapper mapper = new ObjectMapper();
    // mapper.registerModule(new JavaTimeModule());
    // File file = new
    // File("C:\\Users\\zeze3\\OneDrive\\Documents\\GitHub\\W23---Final-project\\Code\\Json\\AdultClient.json");
    // try {
    // mapper.writeValue(file, client_Map);
    // } catch (IOException e) {
    // System.out.println(e);
    // }
    // }
}
