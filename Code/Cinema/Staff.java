package Code.Cinema;

import java.util.Scanner;

public class Staff {

    protected String name;
    protected int age;
    private String password;
    private static boolean isAdmin = false;
    protected Cinema cinemaOfEmployement;

    public Staff(String cinema) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the staffName: ");
        name = input.nextLine();
        
        System.out.print("Enter his age: ");
        age = input.nextInt();

        // to make sure the scanner works next time
        input.nextLine();
    }

    public Staff(String name, int age, Cinema cinema) {
        this.name = name;
        this.age = age;
        this.cinemaOfEmployement = cinema;
    }

    // to empty a seat I need to know:
    // the cinema that the staff member is a part of
    // the cinema room that the staff wants to empty the seat of
    // the location of the seat in the room.
    private void emptySeat(int roomNum, int row, int column) {
        cinemaOfEmployement.getRooms().get(roomNum)[row][column] = false;
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
