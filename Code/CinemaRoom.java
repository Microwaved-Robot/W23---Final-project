package Code;

import java.util.LinkedList;

public class CinemaRoom{
    private boolean[][] seats;
    private LinkedList<Movie> movie_List;
    private int maxOccupancy, roomNumber;

    public CinemaRoom(boolean[][] seats, LinkedList<Movie> movie_List, int maxOccupancy, int roomNumber) {
        this.seats = seats;
        this.movie_List = movie_List;
        this.maxOccupancy = maxOccupancy;
        this.roomNumber = roomNumber;
    }

    public CinemaRoom(int row, int column, LinkedList<Movie> movie_List, int maxOccupancy, int roomNumber) {
        this.seats = new boolean[row][column];
        this.movie_List = movie_List;
        this.maxOccupancy = maxOccupancy;
        this.roomNumber = roomNumber;
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
                System.out.print((char)(97 + i));
            }
            for (int j = 0; j < seats[i].length; j++) {
                if (j ==-1) {
                    System.out.print((j + 1));
                } else if (seats[i][j] == false) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
                System.out.println();
            }
        }
    }

    public void selectSeat(int row, int column) {
        if (isFull()) {
            System.out.println("The room is full :( ");
            return;
        } else {
            
        }
    }
}