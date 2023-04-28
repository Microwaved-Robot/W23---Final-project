package Code.Cinema;

public class Admin extends Staff {
    private static boolean isAdmin = true;

    Admin() {
        super();
    }

    Admin(int Nip, String name, int age, Cinema cinemaOfEmployment) {
        super(Nip, name, age, cinemaOfEmployment);
    }

    public static boolean isAdmin() {
        return isAdmin;
    }

    public static void setAdmin(boolean isAdmin) {
        Admin.isAdmin = isAdmin;
    }

    public void hireStaff(Staff staff) {
        Staff NS = new Staff();
        NS = staff;
    }

    private void destroyCinema() {
        cinemaOfEmployement.setMovie_List(null);
        cinemaOfEmployement.setRooms(null);
    }

    private void createCinema(Cinema cinema) {
        Cinema NC = new Cinema();
        NC = cinema;
    }

    private void resetCinema() {
        cinemaOfEmployement.setMovie_List(null);
        cinemaOfEmployement.setRooms(null);
    }
}
