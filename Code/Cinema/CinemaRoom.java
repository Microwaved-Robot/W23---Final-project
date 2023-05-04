package Code.Cinema;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class CinemaRoom {
    private boolean[][] seats;
    private LinkedList<Movie> movie_List;
    private final int roomNumber;

    protected CinemaRoom(int row, int column, int roomNumber) {
        Scanner input = new Scanner(System.in);
        this.seats = new boolean[row][column];
        this.roomNumber = roomNumber;
        this.movie_List = new LinkedList<>();
        boolean flag = true;

        System.out.println("Number of movie to add: ");
        int nb = input.nextInt();
        input.nextLine();

        for (int i = 0; i < nb; i++) {
            do {
                System.out.println("Movie " + i + ": ");
                Movie newMovie = Movie.createMovie();
                for (Movie movie : movie_List) {
                    flag = movie.equals(newMovie);
                    if (flag) {
                        break;
                    }
                }
            } while (flag);
        }
        Collections.sort(movie_List, new MovieTimeComparator());
        input.close();
    }

    protected void addMovieToQueue(Movie newMovie) {
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

    protected void removeMovieInQueue(Movie movie) {
        // break if the movielist is empty
        if (movie_List.size() == 0) {
            System.out.println("The room is empty. ");
            return;
        }
        for (int i = 0; i < movie_List.size(); i++) {
            if (movie_List.get(i).equals(movie)) {
                movie_List.remove(i);
            }
        }
    }

    protected void removeMovieInQueue(String name) {
        if (movie_List.size() == 0) {
            System.out.println("The room is empty. ");
            return;
        }
        for (int i = 0; i < movie_List.size(); i++) {
            if (movie_List.get(i).getName().equals(name)) {
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
        // flag 1 & 2 are so that we can have the number and letter column without
        // changing the array itself
        boolean flag = true, flag2 = true;

        for (int i = 0; i < seats.length + 1; i++) {
            if (i == 0) {
                // '#'' for estetic reason
                System.out.print("#");
            } else {
                // Prints a letter in the first vertical column
                System.out.print((char) (65 + i - 1));
            }
            if (i == seats.length) {
                i--;
                flag2 = false;
            }
            for (int j = 0; j < seats[i].length + 1; j++) {
                flag = true;
                if (i == 0) {
                    // Print the horizontal numbers
                    System.out.print(" " + j);
                    continue;
                } else if (j == seats[i].length) {
                    j--;
                    flag = false;
                }
                // Print X or O depending on the seat available : X meaning not available and O
                // the contrary
                if (seats[i][j] == false) {
                    System.out.print(" X");
                } else {
                    System.out.print(" O");
                }
                if (flag == false) {
                    j++;
                }
            }
            System.out.println();
            if (flag2 == false) {
                i++;
            }
        }
        System.out.println("Where X is taken and O is vacant.");
    }

    protected void emptySeat(int row, int column) { // will have to be changed to notify the client that had the
                                                    // reserved seat that his ticket is now terminated
        if (seats[row][column] == true) {
            seats[row][column] = false;
        }
    }

    protected void emptyAllSeat() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = false;
            }
        }
    }

    // Show the name and time of the movie in the room
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
    public Movie getMovieFromList(int index) {
        return movie_List.get(index);
    }

    public LinkedList<Movie> getMovie_List() {
        return this.movie_List;
    }

    protected void setMovie_List(LinkedList<Movie> movie_List) {
        this.movie_List = movie_List;
    }

    protected int getMovie_ListSize() {
        return movie_List.size();
    }

    protected void addMovie(Movie movie) {
        movie_List.add(movie);
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }
}