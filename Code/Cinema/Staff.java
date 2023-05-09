package Code.Cinema;

import java.util.Scanner;

public class Staff {
    Scanner read = new Scanner(System.in);

    private String name;
    private int age;
    private int PIN;
    private static final boolean isAdmin = false;
    private Cinema cinemaOfEmployement;

  
    public Staff(String cinema) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the staffName: ");
        name = input.nextLine();
        
        System.out.print("Enter his age: ");
        age = input.nextInt();

        // to make sure the scanner works next time
        input.nextLine();
    }

    public Staff(String name, int age, Cinema cinema, int pin) {
        this.PIN = pin;
        this.name = name;
        this.age = age;
        this.cinemaOfEmployement = cinema;
    }
    public Staff() {
        this.PIN = 0;
        this.name = null;
        this.age = 0;
        this.cinemaOfEmployement = null;
    }

    String options() {
        String str = String.format("%s %s\n", "1) ", "");
        str += String.format("%s %s\n", "1) ", "");
        return str;
    }

    void UI(){
        if(pinChecker()){
            do{
            System.out.println("Please select the number that corresponds to the action you would like to perform: ");
            options();
            switch (read.nextInt()){
                case(1):
                break;
            }

            System.out.println("do you wish to continue?(Y or N) ");
            String continue = read.nextLine();

        }while(continue.toLowerCase == "y");

        }
    }

    // this pinChecker works for now but a stranger can easily guess someone elses
    // pin by mistake
    // Solution: change pw to String then indicate to new employee that they must
    // include their user name at the beginning of their pw
    // then when we greet the employee we can create a substring of the pw provided.
    boolean pinChecker() {
        do {
            System.out.print("Enter your PIN: ");
            int pin = read.nextInt();
            if (pin == this.PIN) {
                System.out.println("Welcome back " + name + ".");
                return true;
            } else {
                System.out.println("Would you like to reatempt entering your password? (Y or N)");
                String answer = read.nextLine();
                if (answer.toLowerCase() == "n") {
                    return false;
                }
            }
        } while (this.PIN != pin);
        return false;
    }

    // to empty a seat I need to know:
    // the cinema that the staff member is a part of
    // the cinema room that the staff wants to empty the seat of
    // the location of the seat in the room.
    private void emptySeat(int roomNum, int row, int column) {
        cinemaOfEmployement.getRooms().getNumberOfRoom()[row][column] = false;
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

    @Override
    public String toString() {
        return "staff [name=" + name + ", age=" + age + "]";
    }

}
