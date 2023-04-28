package Code.Client;

import Code.Cinema.Movie;

interface TicketPrice {
    public double calculatePrice(Movie m, Client C);
    public double calculateTax(int p);
    public double discount(int p, Movie m);
}