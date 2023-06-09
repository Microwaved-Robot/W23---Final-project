import java.io.*;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import Code.Cinema.Cinema;
import Code.Client.AdultClient;
import Code.Client.ChildClient;

public class Test {
    public static void main(String[] args) {
        // Cinema cinema = new Cinema(true);
        // cinemaConverter(cinema);
        Cinema cinema = readJsonCinema();
        cinema.getRoom_List().get(1).displaySeat();
        cinema.getRoom_List().get(1).emptySeat(0, 1);
        cinema.getRoom_List().get(1).displaySeat();

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

    public static void cinemaConverter(Cinema cinema) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File file = new File("C:\\Users\\zeze3\\OneDrive\\Documents\\GitHub\\W23---Final-project\\Code\\Json\\cinema.json");
        try {
            mapper.writeValue(file, cinema);
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("yes");
        }
    }

    public static void AdultClientConverter(HashMap<String, AdultClient> client_Map) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        File file = new File("C:\\Users\\zeze3\\OneDrive\\Documents\\GitHub\\W23---Final-project\\Code\\Json\\AdultClient.json");

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
                "C:\\Users\\zeze3\\OneDrive\\Documents\\GitHub\\W23---Final-project\\Code\\Json\\ChildClient.json");
        try {
            mapper.writeValue(file, client_Map);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
