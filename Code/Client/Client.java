package Code.Client;
import java.util.Comparator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import Code.Cinema.Movie;


abstract class Client {
    protected String name;
    protected int age;
    protected Movie[] moviesViewed;
    protected ArrayList<Ticket> tickets = new ArrayList<>();

    

    protected int findTicket(ArrayList<Ticket> t, Movie m) { 

        //comparator sorting ticket arraylist by alphabetical order

        tickets.sort(new Comparator<Ticket>() {
            @Override
            public int compare(Ticket a, Ticket b) {

                return a.getMovie().getName().compareTo(b.getMovie().getName());
            }
        });

        return LinearSearch(t, m.getName());
    }

    //Lienar seach algorithm

    public static int LinearSearch(ArrayList<Ticket> t, String target) {
        
        try {
            for(int i = 0; i < t.size(); i++) {
                if(t.get(i).getName() == target) {
                    return i;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index Trying To Be Accessed Is Out Of Bounds");
        } catch (NullPointerException m) {
            System.out.println("ArrayList Is Null");
        }
        
        

        return -1;

        
    }

    protected void purchaseTicket(Movie m) {

        Date d = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(formatter.format(d));


        try {
            Ticket t = new Ticket(d, m, m.getTime());
            tickets.add(t);
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong parameters entered");
        }

    }

    protected boolean isAdult(int age) {
        if (age < 18) {
            return false;
        }else {
            return true;
        }
    }

    //getters and setters

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

    public Movie[] getMoviesViewed() {
        return moviesViewed;
    }

    public void setMoviesViewed(Movie[] moviesViewed) {
        this.moviesViewed = moviesViewed;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    
}
