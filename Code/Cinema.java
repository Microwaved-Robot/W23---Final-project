package Code;

import java.util.ArrayList;
import java.util.Collections;

public class Cinema {
    static final String cinemaName = "ThaBest Cinema inc";
    final int branchNumber;
    protected int numberOfRoom;
    // protected ArrayList<Staff> staff_List;
    protected ArrayList<Movie> movie_List;


    // public Cinema() {
    // }

    public Cinema(int branchNumber) {
        this.branchNumber = branchNumber;
    }

    public Cinema(int branchNumber, int numberOfRoom, /*ArrayList<Staff> staff_List,*/ ArrayList<Movie> movie_List) {
        this.branchNumber = branchNumber;
        this.numberOfRoom = numberOfRoom;
        // this.staff_List = staff_List;
        this.movie_List = movie_List;
    }
    
    public ArrayList<String> showMovies() {
        ArrayList<String> s_List = new ArrayList<>();
        Collections.sort(movie_List, new MovieComparator());
        for (Movie movie : movie_List) {
            s_List.add(movie.getName());
        }
        return s_List;
    }

    public static void main(String[] args) {
        Movie m1 = new Movie("Hello w", 2, 2023, 4, 6, 12, 30);
        Movie m2 = new Movie("allo z", 1, 2023, 4, 6, 13, 30);
        Movie m3 = new Movie("ello p", 3, 2023, 4, 6, 14, 30);
        Movie m4 = new Movie("zllo m", 5, 2023, 4, 6, 15, 30);
        ArrayList<Movie> m = new ArrayList<>(); 
        m.add(m1); m.add(m2); m.add(m3); m.add(m4);
        Cinema c = new Cinema(1, 4, m);
        
        System.out.println(c.showMovies());
    }
}
