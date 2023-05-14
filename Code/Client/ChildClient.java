package Code.Client;

import java.util.InputMismatchException;
import java.util.Scanner;

import Code.Cinema.Cinema;

public class ChildClient extends Client {

    String name;
    String lastName;
    int age;

    public ChildClient(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public ChildClient() {

    }

    // driver method for childClient
    public void runChildClient(Cinema c) {
        int userChoice = 0;
        boolean flag = true;
        Scanner sc = new Scanner(System.in);

        do {

            System.out.println("1. View Showtimes");
            System.out.println("2. Purchase Ticket");
            System.out.println("3. Display Purchased Tickets");
            System.out.println("4. Account");
            System.out.println("5. Purchasing Info");
            System.out.println("6. Exit");

            try {
                userChoice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please Enter A Valid Input");
            }

            switch (userChoice) {
                case 1:
                    System.out.println(c.showMovies());
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
                    break;
                default:
                    System.out.println("Enter One Of The Options Listed Above!");
                    break;
            }
        } while (flag);

        sc.close();
    }

    protected void getAcountInfo() {
        System.out.println();
        System.out.println("Account Info: ");
        System.out.println("Name: " + name + " " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Number of Tickets Purchased: " + tickets.size());
        System.out.println();
    }

    protected void getPurchasingInfo() {
        System.out.println();
        System.out.println("Purchasing Info: ");
        System.out.println();
        System.out.println("Standard Rate for Movie: " + Ticket.price);
        System.out.println("Discount on Tuesdays: " + Ticket.Daydiscount);
        System.out.println("Discount For Children Under 18: " + Ticket.childDiscount);
        System.out.println();
    }

    // getters and setters
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
