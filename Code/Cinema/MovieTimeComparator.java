package Code.Cinema;

import java.util.Comparator;

public class MovieTimeComparator implements Comparator<Movie>{
    @Override
    public int compare(Movie m1, Movie m2) {
        if (m1.isTimeRepeated(m2)) {
            return 0;
        } else if (m1.getTime().isBefore(m2.getTime())) {
            return 1;
        } else {
            return -1;
        }
    }
}
