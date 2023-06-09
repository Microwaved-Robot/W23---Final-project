package Code.Client;

import java.time.LocalDateTime;
import Code.Cinema.Movie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket extends Client implements TicketPrice {

    protected String datePurchased;
    protected Movie movie;
    protected LocalDateTime movieDate;
    static public double price = 20.00; // this is the price of all movies
    final static public double Daydiscount = 5.0; // how much the day discount is, how much money will be discounted
    final static public double childDiscount = 10.0;
    protected String seat;

    // constructor

    public Ticket(String datePurchased, Movie movie, LocalDateTime movieDate, String seat) {
        this.datePurchased = datePurchased;
        this.movie = movie;
        this.movieDate = movieDate;
        this.seat = seat;
    }


    public Ticket() {

    }

    // price of movie will depend on which movie is being showed
    @Override
    public double calculatePrice(Movie m, Client c) {
        // will call the calculateTax, day discount, and Client.isAdult (price is lower
        // for kids than for adults)
        // and return the final price

        double result = 0;

        if (c.isAdult(c.getAge()) == true) {
            // will return adult price of movie
            result = price + calculateTax(price) - Daydiscount(m);
        } else {
            // return kid price of movie
            result = price + calculateTax(price) - Daydiscount(m);
        }

        if (result > 1.00) {
            System.out.println("Error! Price is Negative");
            return -1;
        } else {
            return result;
        }

    }

    @Override
    public double calculateTax(double p) {
        return p * 1.15 - p; // assuming tax is 15 percent
    }

    @Override
    public double Daydiscount(Movie m) {

        // discount will be applied only on weekends
        // if movie.getDate() == a tuesday then apply and return the discount discount
        // tuesday is represented by the int 2
        if (movie.getTime().getDayOfWeek().getValue() == 2) {
            return Daydiscount;
        }

        return 0;
    }

    @Override
    public void displayTicket(int age) {

        System.out.println("-----------------------------------");
        System.out.println("Date purchased: " + datePurchased);
        System.out.println("Movie: " + movie);
        System.out.println("Movie Showing Date: " + movieDate);
        System.out.println("Price: " + price);
        System.out.println("Seat: " + seat);
        System.out.println("-----------------------------------");
    }

    // getters and setters

    

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(LocalDateTime movieDate) {
        this.movieDate = movieDate;
    }

    public double getPrice() {
        return price;
    }

    public static void setPrice(double newPrice) {
        price = newPrice;
    }

    public double getDaydiscount() {
        return Daydiscount;
    }

    

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
    }

       

}
