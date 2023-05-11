package Code.Client;

import Code.Cinema.Movie;

interface TicketPrice {
    public double calculatePrice(Movie m, Client C);
    public double calculateTax(double p);
    public double Daydiscount(Movie m);
    public double ChildDiscount();
    public void displayTicket();
}