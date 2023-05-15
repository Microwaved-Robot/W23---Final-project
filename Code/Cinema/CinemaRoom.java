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

        // int nb = 0;
        // do {
        // try {
        // System.out.print("Number of movie to add to the cinema room: ");
        // nb = input.nextInt();
        // if (roomNumber <= 0) {
        // throw new IllegalArgumentException("Negative number");
        // }
        // input.nextLine();
        // flag = false;
        // } catch (InputMismatchException e) {
        // flag = true;
        // System.out.println("The input needs to be a positive number");
        // } catch (IllegalArgumentException e) {
        // System.out.println("The input needs to be bigger than 0.");
        // flag = true;
        // }
        // } while (flag);

        // for (int i = 0; i < nb; i++) {
        // try {
        // do {
        // System.out.println("Movie " + i + ": ");
        // Movie newMovie = new Movie();
        // for (Movie movie : movie_List) {
        // flag = movie.equals(newMovie);
        // if (flag) {
        // System.out.println("The movie is already in the Room");
        // break;
        // }
        // movie_List.add(newMovie);
        // }
        // } while (flag);
        // } catch (Exception e) {
        // System.out.println(e);
        // }
        // }

        // Collections.sort(movie_List, new MovieTimeComparator());
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