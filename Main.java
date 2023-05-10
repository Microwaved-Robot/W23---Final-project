import java.util.ArrayList;
import java.util.HashMap;

import Code.Cinema.Admin;
import Code.Cinema.Cinema;
import Code.Cinema.CinemaRoom;
import Code.Cinema.Movie;
import Code.Cinema.Staff;

public class Main {
    public static void main(String[] args) {
        // first we need to find a way to retreave all of the data from text files
        // everytime the program is initialized. this will allow all data entered to be
        // stored and reusable.
    }

    void onStart() {
        Cinema theOnlyCinema = new Cinema(1, movieList_DataRead(), roomList_DataRead(),
                staffList_DataRead(), adminList_DataRead());
    }

    private HashMap<String, Admin> adminList_DataRead() {
        return null;
    }

    private HashMap<String, Staff> staffList_DataRead() {
        return null;
    }

    private ArrayList<CinemaRoom> roomList_DataRead() {
        return null;
    }

    private ArrayList<Movie> movieList_DataRead() {
        return null;
    }

    
}
