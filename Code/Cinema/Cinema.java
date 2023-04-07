package Code.Cinema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Cinema {
    static final String cinemaName = "ThaBest Cinema inc";
    final int branchNumber;
    protected static int numberOfRoom = 0;
    // protected ArrayList<Staff> staff_List;
    protected ArrayList<Movie> movie_List;
    private CinemaRoom room;

    public Cinema(int branchNumber) {
        this.branchNumber = branchNumber;
    }

    public Cinema(int branchNumber,/*ArrayList<Staff> staff_List, */ArrayList<Movie> movie_List, CinemaRoom room) {
        this.branchNumber = branchNumber;
        // this.staff_List = staff_List;
        this.movie_List = movie_List;
        this.room = room;
        numberOfRoom++;
    }
    
    // It shows every movie in the cinema
    public ArrayList<String> showMovies() {
        ArrayList<String> s_List = new ArrayList<>();

        //Sorts by name
        Collections.sort(movie_List, new MovieNameComparator());
        for (Movie movie : movie_List) {
            s_List.add(movie.getName() + " at " + movie.getTime());
        }
        return s_List;
    }

    //Adds ONE!!! movie and check for duplicates
    public void addMovie() {
        
    }

    //Create a new movie obj
    private Movie createMovie() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter : Name, length in minutes, date (YYYY MM DD) and time of the movie (hour, min). "
        + "Every information must be separated by a space : ");
        String name = input.nextLine();
        int length = input.nextInt();
        int year = input.nextInt();
        int month = input.nextInt();
        int day = input.nextInt();
        int hour = input.nextInt();
        int min = input.nextInt();
        input.nextLine();
        return new Movie(name, length, year, month, day, hour, min);
    }

    // public void addStaff(Staff staff) {
    //     staff_List.add(staff);
    // }

    public int getBranchNumber() {
        return this.branchNumber;
    }


    public int getNumberOfRoom() {
        return Cinema.numberOfRoom;
    }

    // public ArrayList<Staff> getStaff_List() {
    //     return this.staff_List;
    // }

    // public void setStaff_List(ArrayList<Staff> staff_List) {
    //     this.staff_List = staff_List;
    // }

    public ArrayList<Movie> getMovie_List() {
        return this.movie_List;
    }

    public void setMovie_List(ArrayList<Movie> movie_List) {
        this.movie_List = movie_List;
    }
}
