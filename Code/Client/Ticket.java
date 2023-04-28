package Code.Client;

import java.util.Date;
import Code.Cinema.Movie;

public class Ticket extends Client implements TicketPrice{

    
    protected Date datePurchased;
    protected Movie movie;
    protected int movieDate;
    protected int price;

    //price of movie will depend on which movie is being showed 
    @Override
    public double calculatePrice(Movie m, Client c) {
        //will call the calculateTax, Discount, and Client.isAdult (price is lower for kids than for adults) 
        //and return the final price

        
        if(c.isAdult(c.getAge()) == true) {
            //will return adult price of movie 
            return 0;
        } else {
            //return kid price of movie
            return 0;
        }


    }

    @Override
    public double calculateTax(int p) {
        return p*1.15; //assuming tax is 15 percent
    }

    @Override
    public double discount(int p, Movie m) {
        
        //discount will be applied only on weekends 
        //if movie.getDate() == a weekend then apply and return the discount discount

        return 0;
    }

    //getters and setters 

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(int movieDate) {
        this.movieDate = movieDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    
    
}
