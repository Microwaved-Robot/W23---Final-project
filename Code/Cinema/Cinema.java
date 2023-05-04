package Code.Cinema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Cinema {
    private final String cinemaName = "ThaBest Cinema inc";
    private final int branchNumber;
    protected static int numberOfRoom = 0;
    protected ArrayList<Movie> movie_List;
    protected ArrayList<CinemaRoom> room_List;
    protected ArrayList<Staff> staff_List;

    /* Constructors */
    public Cinema() {
        this.branchNumber = 0;
        this.movie_List = null;
        this.room_List = null;
        System.out.println("Created a empty cinema. ");
    }/*  change movie list and cinema room to manuel adding to manual adding
      /   make sure that the 
      */

     //Manual adding
    protected Cinema(int branchNumber) {
        this.branchNumber = branchNumber;

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of staff: ");
        int number = input.nextInt();

        for (int i = 0; i < number; i++) {
            staff_List.add(new Staff(cinemaName));    
        }

        System.out.println("Enter the number of room: ");
        number = input.nextInt();

        for (int i = 0; i < number; i++) {
            room_List.add(new CinemaRoom(branchNumber, i, number));    
        }
    } //-------------------------------------Finish for movie list ;)

    // Bulk Adding for CinemaRoom, movie_List and staff_List
    protected Cinema(int branchNumber, ArrayList<Movie> movie_List, ArrayList<CinemaRoom> room_List, ArrayList<Staff> staff_List) {
        this.branchNumber = branchNumber;
        this.staff_List = staff_List;
        this.movie_List = movie_List;
        this.room_List = room_List;
        numberOfRoom = room_List.size();
    }

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
                if (room.getMovieFromList(i).getName().equals(name)) {
                    room.removeMovieInQueue(name);
                }
            }
        }
    }

    protected void addCinemaRoom() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the amount of row: ");
        int row = input.nextInt();
        System.out.print("\nEnter the amount of column: ");
        int colum = input.nextInt();
        System.out.print("\nEnter the room number: ");
        int roomNum = input.nextInt();
        input.nextLine();

        room_List.add(new CinemaRoom(row, colum, roomNum));
        numberOfRoom++;
        //// :3 you got this buddy!
    }

    protected void removeCinemaRoom(){
        Scanner input = new Scanner(System.in);

        System.out.print("The room numbers of the rooms are: ");
        for (CinemaRoom room : room_List) {
            System.out.print(room.getRoomNumber() + ", ");
        }
        System.out.println();

        System.out.print("Enter the amount of room to be removed: ");
        int amount = input.nextInt();

        for (int i = 0; i < amount; i++) {
            System.out.print("Enter the room number: ");
            int roomNum = input.nextInt();

            for (CinemaRoom room : room_List) {
                if (room.getRoomNumber() == roomNum) {
                    room_List.remove(room);
                    numberOfRoom--;
                    System.out.println("Room " + roomNum + " has been removed. ");
                }
            }
        }
        //To Make sure we can read the nxt line after this method
        input.nextLine();
    }

    /*----------------------------------Getter and Setters/*----------------------------------*/
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

    public ArrayList<CinemaRoom> getroom_List() {
        return room_List;
    }

    public String getCinemaName() {
        return this.cinemaName;
    }
}
