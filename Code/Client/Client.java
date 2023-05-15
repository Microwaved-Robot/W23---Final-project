package Code.Client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import Code.Cinema.Cinema;
import Code.Cinema.CinemaRoom;
import Code.Cinema.Movie;

abstract class Client {
    protected String name;
    protected int age;
    protected ArrayList<Ticket> tickets = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    /*
     * protected static int LinearSearchForTicketsByMovieName(ArrayList<Ticket> t,
     * String target) {
     * 
     * try {
     * for(int i = 0; i < t.size(); i++) {
     * if(t.get(i).getName() == target) {
     * return i;
     * }
     * }
     * } catch (IndexOutOfBoundsException e) {
     * System.out.println("Index Trying To Be Accessed Is Out Of Bounds");
     * } catch (NullPointerException m) {
     * System.out.println("ArrayList Is Null");
     * }
     * 
     * System.out.println("No Tickets Have Been Purchased");
     * 
     * 
     * return -1;
     * 
     * 
     * }
     * 
     */

     protected void displayShowtimes(Cinema c) {
        System.out.println("Showtimes: \n");
        System.out.println(c.showMovies());
     }

    protected void purchaseTicket(Cinema c) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Currently The Standard Movie Rate is $" + Ticket.price);

        System.out.println("Showtimes: ");

        System.out.println(c.showMovies());

        int movieSelection = 0;

        // gets todays date
        Date d = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String todaysDate = formatter.format(d);


        System.out.println("Enter Movie Index To Purchase Ticket: ");



        try {
            movieSelection = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Enter a valid Number Next Time!");
            return;
        }

        
        Movie m = null;

        int selectedRoomNumber = 0;

        try {


            m = c.searchMovieList(c.getMovie_List(), movieSelection);
            

            System.out.println("Movie Selected: " + m.getName());

            // the room that the movie is playing in
            selectedRoomNumber = c.searchCinemaRooms(m);

            System.out.println("Your Movie Will Be In Cinema: " + selectedRoomNumber);

            System.out.println();

            System.out.println("Choose Seat: ");

            CinemaRoom r = c.getRoom_List().get(selectedRoomNumber - 1);
            r.displaySeat();
        } catch (InputMismatchException e) {
            System.out.println("You Have Entered An Incorrect Option (Code 600)");
            return;
        } catch (RuntimeException e) {
            System.out.println("An Error Has Occured! (Code 601)");
            return;
        }

        System.out.println("Enter Row Number Of The Seat You Would Like To Purchase: ");

        String seat = "";

        try {
            int row = sc.nextInt();

            if(row > 26) {
                return;
            }

            System.out.println("Enter the Colomn Letter Of the Seat You Would Like To Purchase");
            // gets first letter user types
            char column = sc.next().charAt(0);

            c.getRoom_List().get(selectedRoomNumber - 1).selectSeat(row, column);
            seat = row + "" + column;

            if (seat.length() > 3) {
                return;
            }

            System.out.println("Seat Selected: " + seat);
        } catch (InputMismatchException e) {
            System.out.println("You Have Entered An Incorrect Option (Code 600)");
        }

        try {
            
                if(seat == "") {
                    return;
                }
                System.out.println("Ticket Purchased");

                

                tickets.add(new Ticket(todaysDate, m, m.getTime(), seat));
                System.out.println("Your Ticket: \n");

                tickets.get(tickets.size() - 1).displayTicket(age);
            } catch (Exception e) {
                System.out.println("An Error Has Occcured (Code 600)");
                return;
            }

        } 

    

    protected void displayPurchasedTickets(ArrayList<Ticket> t) {
        
        if(t.size() == 0) {
            System.out.println();
            System.out.println("No Tickets Purchased");
            System.out.println();
        }

        for(int i = 0; i < t.size(); i++) {
            
            System.out.println(i+1 + ".");
            System.out.println();
            t.get(i).displayTicket(this.age);
            System.out.println();
            

            

        }
    }

    protected boolean isAdult(int age) {
        if (age < 18) {
            return false;
        } else {
            return true;
        }
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

}
