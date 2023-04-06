package Code;

import java.util.Date;

public class Movie {
    private String name;
    private int length;
    private Date time;

    public Movie() {
    }

    public Movie(String name, int length, Date time) {
        this.name = name;
        this.length = length;
        this.time = time;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return getName() + " is playing for " + getLength() 
            + " minutes at " + getTime() + " o'clock";
    }

    public boolean equals(Movie m) {
        return m.getName().equals(this.getName()) 
            && m.length == this.length 
            && m.time.equals(this.time);
    }
}
