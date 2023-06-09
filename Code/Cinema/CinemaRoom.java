package Code.Cinema;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class CinemaRoom {
    private boolean[][] seats;
    private LinkedList<Movie> movie_List;
    private final int roomNumber;

    Scanner input = new Scanner(System.in);

    /*----------------------------------Constructors----------------------------------*/
    protected CinemaRoom() {
        roomNumber = 0;
    }

    protected CinemaRoom(int roomNumber) {
        boolean flag = false;
        int rowNumber = 0;
        int columnLetter = 0;

        do {
            try {
                System.out.print("Enter the length of the row: ");
                rowNumber = input.nextInt();
                if (roomNumber <= 0) {
                    throw new IllegalArgumentException("Negative number");
                }
                flag = false;
            } catch (InputMismatchException e) {
                System.out.println("The input needs to be a number.");
                flag = true;
            } catch (IllegalArgumentException e) {
                System.out.println("The input needs to be bigger than 0.");
                flag = true;
            }
        } while (flag);

        do {
            try {
                System.out.print("Enter the length of the column: ");
                columnLetter = input.nextInt();
                if (columnLetter > 26 || columnLetter < 0) {
                    throw new IllegalArgumentException("The colum needs to be between 0 and 26");
                }
                flag = false;
            } catch (IllegalArgumentException e) {
                System.out.println("The colum needs to be between 0 and 26.");
                flag = true;
            } catch (InputMismatchException e) {
                System.out.println("The input needs to be a number.");
                flag = true;
            }
        } while (flag);

        input.nextLine();

        this.seats = new boolean[columnLetter][rowNumber];
        this.roomNumber = roomNumber;
        this.movie_List = new LinkedList<>();
    }

    /*----------------------------------Methods----------------------------------*/

    protected boolean addMovieToQueue(Movie newMovie) {
        boolean flag = false;
        for (Movie movie : movie_List) {
            flag = movie.equals(newMovie);
            if (flag) {
                System.out.println("The movie is already playing at that time.");
                return false;
            }
            flag = movie.isTimeRepeated(newMovie);
            if (flag) {
                System.out.println("There is a movie playing at that time already.");
                return false;
            }
        }
        if (flag == false) {
            movie_List.add(newMovie);
            System.out.println("Movie added to queue. ");
        }
        Collections.sort(movie_List, new MovieTimeComparator());
        return true;
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
        Collections.sort(movie_List, new MovieTimeComparator());
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
        Collections.sort(movie_List, new MovieTimeComparator());
    }

    // Call to remove movie that are already shown
    protected void updateMovie(LocalDateTime currentTime) {
        for (int i = 0; i < movie_List.size(); i++) {
            if (currentTime.isAfter(movie_List.get(i).getTime())) {
                movie_List.remove(i);
                i--;
            }
        }
    }

    public boolean selectSeat(int row, char letter) {
        letter = Character.toUpperCase(letter);
        int column = (int) letter - 65;
        row--;
        if (isFull()) {
            System.out.println("The room is full :( ");
            return false;
        } else if (seats[column][row] == false) {
            seats[column][row] = true;
            System.out.println("Seat has been reserved :)");
            return true;
        } else {
            // System.out.println("The seat has been taken ;-; ");
            return false;
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

        char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        System.out.print("  ");
        for (int i = 0; i < seats[0].length; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < seats.length; i++) {
            System.out.print(letters[i] + " ");
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] ? "X " : "O ");
            }
            System.out.println();
        }
        System.out.println("Where X is taken and O is vacant.");
    }

    public void emptySeat(int row, int column) { // will have to be changed to notify the client that had the
                                                    // reserved seat that his ticket is now terminated
        // if (seats[row][column] == true) {
            seats[row][column] = false;
        // }
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
            for (int i = 0; i < movie_List.size() - 1; i++) {
                if (i != movie_List.size() - 2) {
                    System.out.print(movie_List.get(i).getName() + " at " + movie_List.get(i).getTime() + " && ");
                } else {
                    System.out.println(movie_List.get(i).getName() + " at " + movie_List.getLast().getTime());
                }
            }
        }
    }

    /*----------------------------------Getter and Setters----------------------------------*/
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

    public boolean[][] getSeats() {
        return seats;
    }
}