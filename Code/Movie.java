package Code;

import java.time.LocalDateTime;

public class Movie {
    private String name;
    private int length;
    private LocalDateTime time;

    public Movie() {
    }

    public Movie(String name, int length, int year ,int month, int day, int hour, int min) {
        this.name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        this.length = length;
        this.time = LocalDateTime.of(year, month, day, hour, min);
    }

    @Override
    public String toString() {
        return getName() + " is playing for " + getLength() 
            + " minutes during " + getTime();
    }

    public boolean equals(Movie m) {
        return m.getName().equals(this.getName()) 
            && m.length == this.length
            && m.time.equals(this.time);
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

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setTime(int year ,int month, int day, int hour, int min) {
        this.time = LocalDateTime.of(year, month, day, hour, min);
    }
}
