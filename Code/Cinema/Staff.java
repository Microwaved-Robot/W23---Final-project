package Code.Cinema;

public class Staff {

    protected String name;
    protected int age;
    private String password;
    private static boolean isAdmin = false;
    protected Cinema cinemaOfEmployement;

    public Staff() {
        this.name = null;
        this.age = 0;
        this.cinemaOfEmployement = null;
    }

    public Staff(String n, int a, Cinema c) {
        this.name = n;
        this.age = a;
        this.cinemaOfEmployement = c;
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
