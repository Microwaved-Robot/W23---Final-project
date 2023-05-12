package Code.Cinema;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cinema {
    private final String cinemaName = "ThaBest Cinema inc";
    private final int branchNumber;
    protected int numberOfRoom = 0;
    protected ArrayList<Movie> movie_List;
    protected ArrayList<CinemaRoom> room_List;
    protected HashMap<String, Staff> staff_List;
    protected HashMap<String, Admin> Admin_List;

    Scanner input = new Scanner(System.in);

    /*----------------------------------Constructors----------------------------------*/
    public Cinema() {
        this.branchNumber = 0;
        this.movie_List = null;
        this.room_List = null;
        System.out.println("Created a empty cinema. ");
    }

    // Manual adding
    protected Cinema(int branchNumber) {
        this.branchNumber = branchNumber;
        int number = 0;
        boolean flag = false;

        do {
            try {
                System.out.print("Enter the number of staff: ");
                number = input.nextInt();
                if (number <= 0) {
                    throw new IllegalArgumentException("Negative number");
                }
                flag = false;
            } catch (IllegalArgumentException e) {
                System.out.println("The input needs to be bigger than 0.");
                flag = true;
            } catch (InputMismatchException e) {
                System.out.println("The input needs to be a number.");
                flag = true;
            }
        } while (flag);

        for (int i = 0; i < number; i++) {
            Staff staff = new Staff();
            staff_List.put(staff.getName(), staff);
        }

        do {
            try {
                System.out.print("Enter the number of room: ");
                numberOfRoom = input.nextInt();
                if (numberOfRoom <= 0) {
                    throw new IllegalArgumentException("Negative number");
                }
                flag = false;
            } catch (IllegalArgumentException e) {
                System.out.println("The input needs to be bigger than 0.");
                flag = true;
            } catch (InputMismatchException e) {
                System.out.println("The input needs to be a number.");
                flag = true;
            }
        } while (flag);
      
        for (int i = 0; i < numberOfRoom; i++) {
            room_List.add(new CinemaRoom(numberOfRoom));
        }

        do {
            try {
                System.out.print("Enter the number of Movie: ");
                number = input.nextInt();
                if (numberOfRoom <= 0) {
                    throw new IllegalArgumentException("Negative number");
                }
                flag = false;
            } catch (IllegalArgumentException e) {
                System.out.println("The input needs to be bigger than 0.");
                flag = true;
            } catch (InputMismatchException e) {
                System.out.println("The input needs to be a number.");
                flag = true;
            }
        } while (flag);

        for (int i = 0; i < number; i++) {
            movie_List.add(new Movie());
        }

        do {
            try {
                System.out.print("Enter the number of Staff: ");
                number = input.nextInt();
                if (numberOfRoom <= 0) {
                    throw new IllegalArgumentException("Negative number");
                }
                flag = false;
            } catch (IllegalArgumentException e) {
                System.out.println("The input needs to be bigger than 0.");
                flag = true;
            } catch (InputMismatchException e) {
                System.out.println("The input needs to be a number.");
                flag = true;
            }
        } while (flag);

        // Make sure next line is not skipped
        input.nextLine();
    }

    // Bulk Adding for CinemaRoom, movie_List and staff_List assuming that there is
    // no problem in element of the parameter
    protected Cinema(int branchNumber, ArrayList<Movie> movie_List, ArrayList<CinemaRoom> room_List, HashMap<String, Staff> staff_List, HashMap<String, Admin> Admin_List) {
  
        this.branchNumber = branchNumber;
        this.staff_List = staff_List;
        this.movie_List = movie_List;
        this.room_List = room_List;
        this.Admin_List = Admin_List;
        numberOfRoom = room_List.size();
    }

    /*----------------------------------Methods----------------------------------*/
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
        Movie m = new Movie();
        boolean flag = false;

        do {
            for (Movie movie : movie_List) {
                flag = movie.getName().equals(m.getName());
                if (flag) {
                    m = new Movie();
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
        for (int i = 0; i < movie_List.size(); i++) {
            if (movie_List.get(i).getName().equalsIgnoreCase(name))
                ;
        }
    }

    protected void addCinemaRoom() {
        room_List.add(new CinemaRoom(numberOfRoom));
        numberOfRoom++;
        //// :3 you got this buddy!
    }

    protected void removeCinemaRoom() {
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
        // To Make sure we can read the nxt line after this method
        input.nextLine();
    }

    protected void addStaff() {
        Staff staff = new Staff();
        staff_List.put(staff.getName(), staff);
    }

    protected void removeStaff() {
        System.out.print("Enter the name of the staff you want to fire: ");
        String name = input.nextLine();
        staff_List.remove(name);
    }

    /*----------------------------------Getter and Setters----------------------------------*/
    public int getBranchNumber() {
        return this.branchNumber;
    }

    public int getNumberOfRoom() {
        return numberOfRoom;
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

    public ArrayList<CinemaRoom> getRoom_List() {
        return this.room_List;
    }

    public void setRoom_List(ArrayList<CinemaRoom> room_List) {
        this.room_List = room_List;
    }

    public HashMap<String, Staff> getStaff_List() {
        return this.staff_List;
    }

    public void setStaff_List(HashMap<String, Staff> staff_List) {
        this.staff_List = staff_List;
    }

    public HashMap<String, Admin> getAdmin_List() {
        return Admin_List;
    }

    public void setAdmin_List(HashMap<String, Admin> admin_List) {
        Admin_List = admin_List;
    }
}
