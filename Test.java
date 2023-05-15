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

        // HashMap<String, AdultClient> map = new HashMap<>();
        // AdultClient c = new AdultClient("Zeyu", "Huang", 19);
        // AdultClient c2 = new AdultClient("Machk", "Donaldo", 21);
        // map.put(c.getName(), c);
        // map.put(c2.getName(), c2);
        // AdultClientConverter(map);

        // HashMap<String, ChildClient> map2 = new HashMap<>();
        // ChildClient a = new ChildClient("Samuel", "Boulay", 2);
        // ChildClient a2 = new ChildClient("Ligma", "bowls", 14);
        // map2.put(a.getName(), a);
        // map2.put(a2.getName(), a2);
        // childClientConverter(map2);

        File file = new File("C:\\Users\\zeze3\\OneDrive\\Documents\\GitHub\\W23---Final-project\\Code\\Json\\cinema.json");
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        Cinema cinema2 = null;
        try {
            cinema2 = om.readValue(file, Cinema.class);
        } catch (Exception e) {
            System.out.println(e);
        }
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
