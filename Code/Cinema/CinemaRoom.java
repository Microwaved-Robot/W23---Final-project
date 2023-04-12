package Code.Cinema;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

 class CinemaRoom {
    private boolean[][] seats;
    private LinkedList<Movie> movie_List;
    private int roomNumber;

    protected CinemaRoom(int row, int column, int maxOccupancy, int roomNumber) {
        Scanner input = new Scanner(System.in);
        this.seats = new boolean[row][column];
        this.roomNumber = roomNumber;
        this.movie_List = new LinkedList<>();
        boolean flag = true;

        System.out.println("Number of movie to add: ");
        int nb = input.nextInt();
        input.nextLine();

        for (int i = 0; i < nb; i++) {
            while (flag) {
                System.out.println("Movie " + i + ": ");
                Movie newMovie = Movie.createMovie();
                for (Movie movie : movie_List) {
                    flag = movie.equals(newMovie);
                }
            }
        }
        Collections.sort(movie_List, new MovieTimeComparator());
    }

    protected void addMovieToQueue() {
        Movie newMovie = Movie.createMovie();
        boolean flag = false;
        for (Movie movie : movie_List) {
            flag = movie.equals(newMovie);
            if (flag) {
                break;
            }
        }
        if (flag == false) {
            movie_List.add(newMovie);
            System.out.println("Movie added to queue. ");
        }
    }

    protected void removeMovie() {
        Movie movie = Movie.createMovie();
        for (int i = 0; i < movie_List.size(); i++) {
            if (movie_List.get(i).equals(movie)) {
                movie_List.remove(i);
            }
        }
    }

    public void selectSeat(int row, int column) {
        if (isFull()) {
            System.out.println("The room is full :( ");
            return;
        } else if (seats[row][column] == false) {
            seats[row][column] = true;
            System.out.println("Seat has been reserved :)");
        } else {
            System.out.println("The seat has been taken ;-; ");
        }
    }

    private boolean isFull() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public int seatRemaining() {
        int remaining = 0;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == false) {
                    remaining++;
                }
            }
        }
        return remaining;
    }

    public void displaySeat() {
        for (int i = -1; i < seats.length; i++) {
            if (i == -1) {
                System.out.print("# ");
            } else {
                // Prints a letter in the first vertical column
                System.out.print((char) (97 + i));
            }
            for (int j = 0; j < seats[i].length; j++) {
                //Print X or O depending on the seat available : X meaning not available and O the contrary
                if (j == -1) {
                    System.out.print((j + 1));
                } else if (seats[i][j] == false) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
                System.out.println();
            }
        }
        System.out.println("Where X is taken and O is vacant.");
    }

    //Show the name and time of the movie in the room
    public void showMoviesInTheRoom() {
        if (movie_List.size() == 0) {
            System.out.println("No movie presenting in this room.");
        } else if (movie_List.size() == 1) {
            System.out.println("The movie in the room is gonna be : " + movie_List.getFirst().getName()
                    + " at " + movie_List.getFirst().getTime());
        } else {
            System.out.println("The movies are : ");
            for (int i = 0; i < movie_List.size(); i++) {
                if (i != movie_List.size() - 1) {
                    System.out.print(movie_List.get(i).getName() + " at " + movie_List.get(i).getTime() + " && ");
                } else {
                    System.out.println(movie_List.get(i).getName() + " at " + movie_List.getLast().getTime());
                }
            }
        }
    }
    
    /* Getters & Setters */
    public LinkedList<Movie> getMovie_List() {
        return this.movie_List;
    }

    public void setMovie_List(LinkedList<Movie> movie_List) {
        this.movie_List = movie_List;
    }

    public void addMovie(Movie movie) {
        movie_List.add(movie);
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }
}