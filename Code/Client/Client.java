package Code.Client;

import Code.Cinema.CinemaRoom;
import Code.Cinema.Movie;


abstract class Client {
    protected String name;
    protected int age;
    protected Movie[] moviesViewed;
    protected Ticket[] tickets; //array of ticket objects

    protected void purchaseTicket() {
    }

    protected boolean isAdult(int age) {
        if (age < 18) {
            return false;
        }else {
            return true;
        }
    }

    //getters and setters

    protected void getSeat(int row, int column, CinemaRoom room){    
    }

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

    public Movie[] getMoviesViewed() {
        return moviesViewed;
    }

    public void setMoviesViewed(Movie[] moviesViewed) {
        this.moviesViewed = moviesViewed;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }

    

    

}
