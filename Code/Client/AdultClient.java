package Code.Client;

import java.util.Scanner;

import Code.Cinema.Cinema;

public class AdultClient extends Client{

    String name;
    String lastName;
    int age;

    AdultClient(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }


    public void runAdultClient(Cinema c) {
        int userChoice = 0;

        Scanner sc = new Scanner(System.in);
        do {

            

            System.out.println("1. View Showtimes");
            System.out.println("2. Purchase Ticket");
            System.out.println("3. Display Purchased Tickets");
            System.out.println("4. Account");
            System.out.println("5. Purchasing Info");
            System.out.println("6. Exit ");

            userChoice = sc.nextInt();

            switch(userChoice) {
                case 1:
                    c.showMovies();
                case 2:
                    purchaseTicket(c);
                case 3:
                    displayPurchasedTickets(tickets);
                case 4:
                    getAcountInfo();
                case 5:
                    getPurchasingInfo();
                default:
                    System.out.println("Please Enter One Of The Options Listed Above!"); 

            }


        } while(userChoice != 6);

        sc.close();
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
