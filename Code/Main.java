package Code;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import Code.Cinema.Admin;
import Code.Cinema.Cinema;
import Code.Cinema.Staff;
import Code.Client.AdultClient;
import Code.Client.ChildClient;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // To import the cinema file that is already existing
        Cinema cinema = readJsonCinema();
        HashMap<String, ChildClient> child_Data = readJsonChild();
        HashMap<String, AdultClient> adult_Data = readJsonAdult();
        ArrayList<Admin> adminArray = readJsonAdmin();
        ArrayList<Staff> staffArray = readJsonStaff();

        if (cinema == null) {
            cinema = new Cinema(false, adminArray, staffArray);
        }
        sorter(adminArray, staffArray);

        userIdentifier(cinema, child_Data, adult_Data, adminArray, staffArray);
    }

    public static Staff staffLogIn(ArrayList<Staff> staffArray) {
        int realPin = 0;
        int pin = 0;
        String answer = "";
        String userName = "";
        boolean flag = false;
        do {
            try {
                System.out.print("Enter your userName: ");
                userName = input.nextLine();
                flag = false;
                realPin = staffArray.get(Cinema.binarySearch(staffArray, userName)).getPin();
            } catch (IndexOutOfBoundsException iobe) {
                System.out.println("The name you entered does not match that of any staff member at the cinema.");
                System.out.println("Please try again.");
                flag = true;
            }
        } while (flag);
        do {
            do {

                try {
                    System.out.println("Enter your pin: ");
                    pin = input.nextInt();
                    flag = false;
                } catch (InputMismatchException ime) {
                    System.out.println("What you entered is not an integer.");
                    System.out.println("Please try again.");
                    flag = true;
                }
                input.nextLine();
            } while (flag);

            if (realPin != pin) {
                System.out.println("The pin you entered is incorrect...");
                do {

                    try {
                        System.out.println("Would you like to try again(Y or N)?");
                        answer = input.nextLine();
                        if (!answer.toLowerCase().equals("y") && !answer.toLowerCase().equals("n")) {
                            throw new IllegalArgumentException();
                        } else {
                            flag = false;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("You must enter a 'Y' or a 'N'.");
                        System.out.println("Please try again.");
                        flag = true;
                    }
                } while (flag);
                if (answer.toLowerCase().equals("n")) {
                    System.exit(1);
                }
            }

        } while (realPin != pin);
        System.out.println("Welcome back " + userName);
        return staffArray.get(Cinema.binarySearch(staffArray, userName));
    }

    public static Admin AdminLogIn(ArrayList<Admin> adminArray) {
        int pin = 0;
        String answer = "";
        String userName = "";
        boolean flag = true;
        int realPin = 0;
        do {
            do {
                try {
                    System.out.print("Enter your userName: ");
                    userName = input.nextLine();
                    flag = false;
                    realPin = adminArray.get(Cinema.AdminBinarySearch(adminArray, userName)).getPin();
                } catch (IndexOutOfBoundsException iobe) {
                    System.out.println("The name you entered does not match that of any admin at the cinema.");
                    System.out.println("Please try again.");
                    flag = true;
                } catch (InputMismatchException ime) {
                    System.out.println("What you entered is not an intiger.");
                    System.out.println("Please try again.");
                    flag = true;
                }
            } while (flag);

            do {

                try {
                    System.out.println("Enter your pin: ");
                    pin = input.nextInt();
                    flag = false;
                } catch (InputMismatchException ime) {
                    System.out.println("What you entered is not an integer.");
                    System.out.println("Please try again.");
                    flag = true;
                }
                input.nextLine();
            } while (flag);

            if (realPin != pin) {
                System.out.println("The pin you entered is incorrect...");
                do {

                    try {
                        System.out.println("Would you like to try again(Y or N)?");
                        answer = input.nextLine();
                        if (!answer.toLowerCase().equals("y") && !answer.toLowerCase().equals("n")) {
                            throw new IllegalArgumentException();
                        } else {
                            flag = false;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("You must enter a 'Y' or a 'N'.");
                        System.out.println("Please try again.");
                        flag = true;
                    }
                } while (flag);
                if (answer.toLowerCase().equals("n")) {
                    System.exit(1);
                }
            }
        } while (realPin != pin);
        System.out.println("Welcome back " + userName);
        return adminArray.get(Cinema.AdminBinarySearch(adminArray, userName));
    }

    static void sorter(ArrayList<Admin> admins, ArrayList<Staff> staff) {
        Cinema.adminBubbleSort(admins);
        Cinema.staffBubbleSort(staff);
    }

    static void userIdentifier(Cinema cinema, HashMap<String, ChildClient> child_Data,
            HashMap<String, AdultClient> adult_Data, ArrayList<Admin> adminArray, ArrayList<Staff> staffArray) {
        boolean flag = true, flag2 = true;
        int reply = 0;
        do {
            do {
                try {
                    System.out.printf("%s\n%s\n%s\n%s\n%s\n", "Welcome, are you a:", "1) Client", "2) Staff member",
                            "3) Administrator", "4) Exit");
                    reply = input.nextInt();
                    if (reply < 1 || reply > 4) {
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
                    int age = -1;
                    do {
                        try {
                            System.out.print("Enter you age: ");
                            age = input.nextInt();
                            input.nextLine();
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
                        System.out.print("Enter your first name: ");
                        String name = input.nextLine();
                        if (child_Data.get(name) == null) {
                            System.out.println("You are a new client!!!");
                            ChildClient client = new ChildClient(name, age);
                            child_Data.put(name, client);
                        }
                        System.out.println();
                        child_Data.get(name).runChildClient(cinema);
                    }
                    if (age > 18) {
                        System.out.println("Enter your first name");
                        String name = input.nextLine();
                        if (child_Data.get(name) == null) {
                            System.out.println("You are a new client!!!");
                            AdultClient client = new AdultClient(name, age);
                            adult_Data.put(name, client);
                        }
                        adult_Data.get(name).runAdultClient(cinema);
                    }
                    break;
                case (2):
                    staffLogIn(staffArray).staffUI();
                    break;
                case (3):
                    AdminLogIn(adminArray).adminUI(cinema, staffArray);
                    break;
                case 4:
                    flag2 = false;
                    break;
            }
        } while (flag2);
        input.nextLine();
    }

    static Cinema readJsonCinema() {
        File file = new File(
                "Code\\Json\\cinema.json");
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

    static HashMap<String, AdultClient> readJsonAdult() {
        File file = new File(
                "Code\\Json\\AdultClient.json");
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        HashMap<String, AdultClient> client_Map = null;
        try {
            TypeReference<HashMap<String, AdultClient>> typeRef = new TypeReference<HashMap<String, AdultClient>>() {
            };
            client_Map = om.readValue(file, typeRef);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
        return client_Map;
    }

    static HashMap<String, ChildClient> readJsonChild() {
        File file = new File(
                "Code\\Json\\ChildClient.json");
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        HashMap<String, ChildClient> client_Map = null;
        try {
            TypeReference<HashMap<String, ChildClient>> typeRef = new TypeReference<HashMap<String, ChildClient>>() {
            };
            client_Map = om.readValue(file, typeRef);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
        return client_Map;
    }

    static ArrayList<Admin> readJsonAdmin() {
        File file = new File(
                "Code\\Json\\Admin.json");
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        ArrayList<Admin> list = null;
        try {
            TypeReference<ArrayList<Admin>> typeRef = new TypeReference<ArrayList<Admin>>() {
            };
            list = om.readValue(file, typeRef);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
        return list;
    }

    static ArrayList<Staff> readJsonStaff() {
        File file = new File(
                "Code\\Json\\Staff.json");
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        ArrayList<Staff> list = null;
        try {
            TypeReference<ArrayList<Staff>> typeRef = new TypeReference<ArrayList<Staff>>() {
            };
            list = om.readValue(file, typeRef);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
        return list;
    }

    /*----------------------------------JSON Converter Method----------------------------------*/
    public static void cinemaConverter(Cinema cinema) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File file = new File(
                "Code\\Json\\cinema.json");
        try {
            mapper.writeValue(file, cinema);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void adultClientConverter(HashMap<String, AdultClient> client_Map) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File file = new File(
                "Code\\Json\\AdultClient.json");
        try {
            mapper.writeValue(file, client_Map);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void childClientConverter(HashMap<String, ChildClient> client_Map) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File file = new File(
                "Code\\Json\\ChildClient.json");
        try {
            mapper.writeValue(file, client_Map);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void StaffConverter(ArrayList<Staff> staff_List) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File file = new File(
                "C:\\Users\\zeze3\\OneDrive\\Documents\\GitHub\\W23---Final-project\\Code\\Json\\Staff.json");
        try {
            mapper.writeValue(file, staff_List);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void AdminConverter(ArrayList<Admin> Admin_List) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File file = new File(
                "C:\\Users\\zeze3\\OneDrive\\Documents\\GitHub\\W23---Final-project\\Code\\Json\\Admin.json");
        try {
            mapper.writeValue(file, Admin_List);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}