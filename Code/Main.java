package Code;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import Code.Cinema.Cinema;
import Code.Client.AdultClient;
import Code.Client.ChildClient;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // To import the cinema file that is already existing
        Cinema cinema = readJsonCinema();
        HashMap<String, ChildClient> child_Data = readJsonChild();
        HashMap<String, AdultClient> adult_Data = readJsonAdult();

        if (cinema == null) {
            cinema = new Cinema(false);
        }

        userIdentifier(cinema, child_Data, adult_Data);
    }

    static void userIdentifier(Cinema cinema, HashMap<String, ChildClient> child_Data,
            HashMap<String, AdultClient> adult_Data) {
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
                        System.out.println("Enter your first name");
                        String name = input.nextLine();
                        if (child_Data.get(name) == null) {
                            System.out.println("You are a new client!!!");
                            ChildClient client = new ChildClient(name, age);
                            child_Data.put(name, client);
                        }
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
                    cinema.staffLogIn().staffUI();
                    break;
                case (3):
                    cinema.AdminLogIn().adminUI(cinema);
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
}