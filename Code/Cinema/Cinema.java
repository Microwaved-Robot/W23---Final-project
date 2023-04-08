package Code.Cinema;

import java.util.ArrayList;
import java.util.Collections;

public class Cinema {
    private static final String cinemaName = "ThaBest Cinema inc";
    private final int branchNumber;
    protected static int numberOfRoom = 0;
    // protected ArrayList<Staff> staff_List;
    protected ArrayList<Movie> movie_List;
    private CinemaRoom room;

    protected Cinema(int branchNumber) {
        this.branchNumber = branchNumber;
    }

    protected Cinema(int branchNumber,/*ArrayList<Staff> staff_List, */ArrayList<Movie> movie_List, CinemaRoom room) {
        this.branchNumber = branchNumber;
        // this.staff_List = staff_List;
        this.movie_List = movie_List;
        this.room = room;
        numberOfRoom++;
    } //---------------------------------------------------- change movie list to manual adding
    
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

    //Adds ONE movie and check for duplicates
    protected void addMovie() {
        Movie m = Movie.createMovie();
        boolean flag = false;
        for (Movie movie : movie_List) {
            flag = movie.equals(m);
            if (flag) {
                break;
            }
        }
        if (flag == false) {
            movie_List.add(m);
            System.out.println("Movie added. ");
        }
    } // -------------------------------------------  Also need to select a room when done 

    protected void addMovie(int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.println("Movie " + (i + 1) + ":");
            addMovie();
        }
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
