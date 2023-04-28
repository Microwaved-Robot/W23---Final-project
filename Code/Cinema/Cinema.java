package Code.Cinema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Cinema {
    private static final String cinemaName = "ThaBest Cinema inc";
    private final int branchNumber;
    protected static int numberOfRoom = 0;
    protected ArrayList<Movie> movie_List;
    protected ArrayList<CinemaRoom> room_List;

    public ArrayList<CinemaRoom> getroom_List() {
        return room_List;
    }

    public Cinema() {
        this.branchNumber = 0;
        this.numberOfRoom = 0;
        this.movie_List = null;
        this.room_List = null;
    }

    protected Cinema(int branchNumber) {
        this.branchNumber = branchNumber;
    }

    protected Cinema(int branchNumber, ArrayList<Movie> movie_List, ArrayList<CinemaRoom> room_List) {
        this.branchNumber = branchNumber;
        // this.staff_List = staff_List;
        this.movie_List = movie_List;
        this.room_List = room_List;
        numberOfRoom++;
    } // ---------------------------------------------------- change movie list to
      // manual adding

    // It shows every movie in the cinema
    public ArrayList<String> showMovies() {
        ArrayList<String> s_List = new ArrayList<>();

        // Sorts by name
        Collections.sort(movie_List, new MovieNameComparator());
        for (Movie movie : movie_List) {
            s_List.add(movie.getName() + " at " + movie.getTime());
        }
        return s_List;
    }

    // Adds ONE movie and check for duplicates
    protected void addMovie() {
        Movie m = Movie.createMovie();
        boolean flag = false;
        Scanner input = new Scanner(System.in);

        do {
            for (Movie movie : movie_List) {
                flag = movie.getName().equals(m.getName());
                if (flag) {
                    m = Movie.createMovie();
                    break;
                }
                movie_List.add(m);
                System.out.println(m.getName() + " has been added. ");
            }

            // Add the movie in one room of the cinema
            System.out.println("Select a room to be added.");
            for (CinemaRoom room : room_List) {
                System.out.print(room.getRoomNumber() + " ");
            }
            System.out.println();
            int roomNum = input.nextInt();
            room_List.get(roomNum).addMovie(m);
        } while (flag);
    }

    protected void addMovie(int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.println("Movie " + (i + 1) + ":");
            addMovie();
        }
    }

    protected void removeMovie(String name) {
        for (CinemaRoom room : room_List) {
            for (int i = 0; i < room.getMovie_List().size(); i++) {
                if (room.getMovie_List())
            }
            for (Movie movie : room.getMovie_List()) {
                for
            }
        }
    } //------------------------------------------do this one

    public int getBranchNumber() {
        return this.branchNumber;
    }

    public int getNumberOfRoom() {
        return Cinema.numberOfRoom;
    }

    public ArrayList<Movie> getMovie_List() {
        return this.movie_List;
    }

    public void setMovie_List(ArrayList<Movie> movie_List) {
        this.movie_List = movie_List;
    }
}
