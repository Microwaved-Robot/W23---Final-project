package Code;

import java.util.LinkedList;

public class CinemaRoom {
    private boolean[][] seats;
    private LinkedList<Movie> movie_List;
    private int maxOccupancy, roomNumber;

    public CinemaRoom(boolean[][] seats, LinkedList<Movie> movie_List, int maxOccupancy, int roomNumber) {
        this.seats = seats;
        this.movie_List = movie_List;
        this.maxOccupancy = maxOccupancy;
        this.roomNumber = roomNumber;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == false) {
                    maxOccupancy++;
                }
            }
        }
    }

    public CinemaRoom(int row, int column, LinkedList<Movie> movie_List, int maxOccupancy, int roomNumber) {
        this.seats = new boolean[row][column];
        this.movie_List = movie_List;
        this.maxOccupancy = maxOccupancy;
        this.roomNumber = roomNumber;
        maxOccupancy = row * column;
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
                System.out.print((char) (97 + i));
            }
            for (int j = 0; j < seats[i].length; j++) {
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

    public void showMoviesInTheRoom() {
        if (movie_List.size() == 1) {
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
    
    public LinkedList<Movie> getMovie_List() {
        return this.movie_List;
    }

    public void setMovie_List(LinkedList<Movie> movie_List) {
        this.movie_List = movie_List;
    }

    public void addMovie(Movie movie) {
        movie_List.add(movie);
    }

    public int getMaxOccupancy() {
        return this.maxOccupancy;
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }
}