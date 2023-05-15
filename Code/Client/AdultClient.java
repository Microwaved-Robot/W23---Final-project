package Code.Client;

import java.util.Scanner;

import Code.Cinema.Cinema;

public class AdultClient extends Client{
    Scanner input = new Scanner(System.in);
    Scanner sc = new Scanner(System.in);

    String name;
    String lastName;
    int age;

    public AdultClient(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }
    public AdultClient() {

    }

    public AdultClient(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.print("Enter your last name: ");
        lastName = input.nextLine();
    }

    public void runAdultClient(Cinema c) {
        int userChoice = 0;
        boolean flag = true;
        
        do {
            System.out.println("1. View Showtimes");
            System.out.println("2. Purchase Ticket");
            System.out.println("3. Display Purchased Tickets");
            System.out.println("4. Account");
            System.out.println("5. Purchasing Info");
            System.out.println("6. Exit ");

            userChoice = sc.nextInt();
            sc.nextLine();

            switch(userChoice) {
                case 1:
                    c.showMovies();
                    break;
                case 2:
                    purchaseTicket(c);
                    break;
                case 3:
                    displayPurchasedTickets(tickets);
                    break;
                case 4:
                    getAcountInfo();
                    break;
                case 5:
                    getPurchasingInfo();
                    break;
                case 6:
                    flag = false;
                default:
                    System.out.println("Please Enter One Of The Options Listed Above!"); 
                    break;
            }
        } while(flag);
    }

    
    protected void getAcountInfo() {
        System.out.println("Name: " + name + " " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Number of Tickets Purchased: " + tickets.size());
    }

    protected void getPurchasingInfo() {
        System.out.println("Standard Rate for Movie: " + Ticket.price);
        System.out.println("Discount on Tuesdays: " + Ticket.Daydiscount);
        System.out.println("Discount For Children Under 18 " + Ticket.childDiscount);
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}
