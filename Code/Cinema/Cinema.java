package Code.Cinema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cinema { // do a update all room for cinema room
    private final String cinemaName = "ThaBest Cinema inc";
    private final int branchNumber = 1;
    protected int numberOfRoom = 0;
    protected ArrayList<Movie> movie_List;
    protected ArrayList<CinemaRoom> room_List;
    protected static ArrayList<Staff> StaffArray;
    protected ArrayList<Admin> AdminArray;

    static Scanner input = new Scanner(System.in);

    /*----------------------------------Constructors----------------------------------*/
    public Cinema() {
        // default
    }

    // Manual adding
    public Cinema(int branchNumber) {
        int number = 0;
        boolean flag = false;

        do {
            try {
                System.out.print("Enter the number of Admin: ");
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
            StaffArray.add(staff);
        }
        System.out.println();

        do {
            try {
                System.out.print("Enter the number of Movie the cinema is showing: ");
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

        movie_List = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            movie_List.add(new Movie(true));
        }
        System.out.println();

        do {
            try {
                System.out.print("Enter the number of room in the cinema: ");
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

        room_List = new ArrayList<>();
        for (int i = 0; i < numberOfRoom; i++) {
            room_List.add(new CinemaRoom(numberOfRoom));
        }
        System.out.println();

        System.out.println("There is " + numberOfRoom + " room in the cinema.");
        for (Movie movie : movie_List) {
            System.out.print("In which room do you want to put it? : ");
            number = input.nextInt() - 1;
            room_List.get(number).addMovieToQueue(movie);
        }

        // ????? its the admin class
        // do {
        // try {
        // System.out.print("Enter the number of Staff: ");
        // number = input.nextInt();
        // if (numberOfRoom <= 0) {
        // throw new IllegalArgumentException("Negative number");
        // }
        // flag = false;
        // } catch (IllegalArgumentException e) {
        // System.out.println("The input needs to be bigger than 0.");
        // flag = true;
        // } catch (InputMismatchException e) {
        // System.out.println("The input needs to be a number.");
        // flag = true;
        // }
        // } while (flag);

        // Make sure next line is not skipped
        input.nextLine();
    }

    // Bulk Adding for CinemaRoom, movie_List and StaffArray assuming that there is
    // no problem in element of the parameter

    // change constructor parameters from hashmap to Array
    protected Cinema(int branchNumber, ArrayList<Movie> movie_List, ArrayList<CinemaRoom> room_List,
            ArrayList<Staff> StaffArray, ArrayList<Admin> AdminArray) {

        this.branchNumber = branchNumber;
        Cinema.StaffArray = StaffArray;
        this.movie_List = movie_List;
        this.room_List = room_List;
        this.AdminArray = AdminArray;
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

    public Movie searchMovieListByName(ArrayList<Movie> m, String movieName) {

        for (int i = 0; i < m.size(); i++) {

            if (m.get(i).getName().equalsIgnoreCase(movieName)) {
                return m.get(i);
            }
        }

        return null;
    }

    // Adds ONE movie and check for duplicates
    protected void addMovie() {
        Movie m = new Movie(true);
        boolean flag = false;

        do {
            for (Movie movie : movie_List) {
                flag = movie.getName().equals(m.getName());
                if (flag) {
                    m = new Movie(true);
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
    } // ---------------------------------------------- do this one (not finished)

    // search all rooms to see which room the movie is playing in

    public int searchCinemaRooms(Movie t) {
        for (CinemaRoom m : room_List) {
            for (int i = 0; i < m.getMovie_List().size(); i++) {
                if (m.getMovieFromList(i).equals(t)) {
                    return room_List.get(i).getRoomNumber();
                }
            }
        }

        return -1;
    }

    protected void addStaff() {
        Staff staff = new Staff();
        StaffArray.add(staff);
    }

    // check with google maybe move to main class?
    protected void removeStaff() {
        System.out.print("Enter the name of the staff you want to fire: ");
        String name = input.nextLine();
        int index = binarySearch(StaffArray, name);
        StaffArray.remove(index);
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

    public ArrayList<CinemaRoom> getRoom_List() {
        return room_List;
    }

    public String getCinemaName() {
        return this.cinemaName;
    }

    public void setRoom_List(ArrayList<CinemaRoom> room_List) {
        this.room_List = room_List;
    }

    public static ArrayList<Staff> getStaffArray() {
        return StaffArray;
    }

    public void setStaffArray(ArrayList<Staff> StaffArray) {
        Cinema.StaffArray = StaffArray;
    }

    public ArrayList<Admin> getAdminArray() {
        return AdminArray;
    }

    public void setAdminArray(ArrayList<Admin> AdminArray) {
        this.AdminArray = AdminArray;
    }

    // ------------------------- search and sort methods

    public void bubbleSort(Staff[] staff) {
        for (int i = 1; i < staff.length; i++) {
            for (int j = 0; j < staff.length - 1; j++) {
                if ((staff[j].getName().compareTo(staff[j + 1].getName())) > 0) {
                    Staff temp = staff[j];

                    staff[j] = staff[j + 1];
                    staff[j + 1] = temp;
                }
            }
        }
    }

    public static int binarySearch(ArrayList<Staff> staffArray, String name) {
        int index = -1;
        int first = 0;
        int last = staffArray.size() - 1;
        int mid = (first + last) / 2;
        while (first <= last) {
            if ((staffArray.get(mid).getName().compareTo(name)) < 0) {
                first = mid + 1;
            } else if (staffArray.get(mid).getName().equals(name)) {
                index = mid;
                break;
            } else {
                last = mid - 1;
            }
            mid = (first + last) / 2;
        }
        if (first > last) {
            System.out.println("Element is not found!");
        }
        return index;
    }

    public void bubbleSort(ArrayList<Admin> admin) {
        for (int i = 1; i < admin.size(); i++) {
            for (int j = 0; j < admin.size() - 1; j++) {
                if ((admin.get(j).getName().compareTo(admin.get(j + 1).getName())) > 0) {
                    Admin temp = admin.get(j);

                    admin.set(j, admin.get(j + 1));
                    admin.set(j + 1, temp);
                }
            }
        }
    }

    public int AdminBinarySearch(ArrayList<Admin> admins, String name) {
        int index = -1;
        int first = 0;
        int last = admins.size() - 1;
        int mid = (first + last) / 2;
        while (first <= last) {
            if ((admins.get(mid).getName().compareTo(name)) < 0) {
                first = mid + 1;
            } else if (admins.get(mid).getName().equals(name)) {
                index = mid;
                break;
            } else {
                last = mid - 1;
            }
            mid = (first + last) / 2;
        }
        if (first > last) {
            System.out.println("Element is not found!");
        }
        return index;
    }

    public static Staff staffLogIn() {
        int realPin;
        int pin;
        String answer;
        String userName;

        System.out.print("Enter your userName: ");
        userName = input.nextLine();

        // change to search(username) which will be a binary search method that will
        // find the index of a staff member in the array of staff members using the
        // username
        realPin = getStaffArray().get(binarySearch(getStaffArray(), userName)).getPin();
        do {
            System.out.println("Enter your pin: ");
            pin = input.nextInt();
            input.nextLine();

            if (realPin != pin) {
                System.out.println("The pin you entered is incorrect...");
                System.out.println("Would you like to try again(Y or N)?");
                answer = input.nextLine();
                if (answer.toLowerCase().equals("n")) {
                    System.exit(1);
                }
            }

        } while (realPin != pin);
        System.out.println("Welcome back " + userName);
        return getStaffArray().get(binarySearch(StaffArray, userName));
    }

    public Staff AdminLogIn() {
        int pin;
        String answer;

        System.out.print("Enter your userName: ");
        String userName = input.nextLine();

        int realPin = getAdminArray().get(AdminBinarySearch(getAdminArray(), userName)).getPin();

        do {
            System.out.println("Enter your pin: ");
            pin = input.nextInt();
            input.nextLine();

            if (realPin != pin) {
                System.out.println("The pin you entered is incorrect...");
                System.out.println("Would you like to try again(Y or N)?");
                answer = input.nextLine();
                if (answer.toLowerCase().equals("n")) {
                    System.exit(1);
                }
            }

        } while (realPin != pin);
        System.out.println("Welcome back " + userName);
        return getAdminArray().get(AdminBinarySearch(getAdminArray(), userName));
    }
}
