package Code.Cinema;

import java.util.Comparator;

public class MovieNameComparator implements Comparator<Movie>{
    @Override
    public int compare(Movie m1, Movie m2) {
        if (m1.getName().charAt(0) == m2.getName().charAt(0)) {
            return 0;
        } else if (m1.getName().charAt(0) > m2.getName().charAt(0)) {
            return 1;
        } else {
            return -1;
        }
    }
}
