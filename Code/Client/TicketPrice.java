package Code.Client;

import Code.Cinema.Movie;

interface TicketPrice {
    public double calculatePrice(Movie m, Client c);

    public double calculateTax(double p);

    public double Daydiscount(Movie m);
    
    public void displayTicket(int age);
}