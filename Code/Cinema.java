package Code;

import java.util.ArrayList;
import java.util.Collections;

public class Cinema {
    static final String cinemaName = "ThaBest Cinema inc";
    final int branchNumber;
    protected static int numberOfRoom = 0;
    protected ArrayList<Staff> staff_List;
    protected ArrayList<Movie> movie_List;
    private CinemaRoom room;

    public Cinema() {
    }

    public Cinema(int branchNumber) {
        this.branchNumber = branchNumber;
    }

    public Cinema(int branchNumber, ArrayList<Staff> staff_List, ArrayList<Movie> movie_List, CinemaRoom room) {
        this.branchNumber = branchNumber;
        this.staff_List = staff_List;
        this.movie_List = movie_List;
        this.room = room;
        numberOfRoom++;
    }
    
    public ArrayList<String> showMovies() {
        ArrayList<String> s_List = new ArrayList<>();
        Collections.sort(movie_List, new MovieComparator());
        for (Movie movie : movie_List) {
            s_List.add(movie.getName());
        }
        return s_List;
    }

    public void addMovie(Movie movie) {
        movie_List.add(movie);
    }

    public void addStaff(Staff staff) {
        staff_List.add(staff);
    }

    public int getBranchNumber() {
        return this.branchNumber;
    }


    public int getNumberOfRoom() {
        return this.numberOfRoom;
    }

    public void setNumberOfRoom(int numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public ArrayList<Staff> getStaff_List() {
        return this.staff_List;
    }

    public void setStaff_List(ArrayList<Staff> staff_List) {
        this.staff_List = staff_List;
    }

    public ArrayList<Movie> getMovie_List() {
        return this.movie_List;
    }

    public void setMovie_List(ArrayList<Movie> movie_List) {
        this.movie_List = movie_List;
    }

}
