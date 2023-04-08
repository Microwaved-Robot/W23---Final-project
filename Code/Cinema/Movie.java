package Code.Cinema;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Movie {
    private String name;
    private int length;
    private LocalDateTime time;

    protected Movie(String name, int length, int year ,int month, int day, int hour, int min) {
        this.name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        this.length = length;
        this.time = LocalDateTime.of(year, month, day, hour, min);
    }

    //Check if the time of the movie is repeated by checking for the whole movie duration
    public boolean isTimeRepeated(Movie m) {
        if (this.getTime().isBefore(m.getTime()) && this.getTime().plusMinutes(length).isBefore(m.getTime())){
            this.getTime().minusMinutes(length);
            return true;
        } else {
            this.getTime().minusMinutes(length);
            return false;
        }
    }

    //Create a new movie obj
    protected static Movie createMovie() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter : Name, length in minutes, date (YYYY MM DD) and time of the movie (hour, min). "
        + "Every information must be separated by a space : ");
        String name = input.nextLine();
        int length = input.nextInt();
        int year = input.nextInt();
        int month = input.nextInt();
        int day = input.nextInt();
        int hour = input.nextInt();
        int min = input.nextInt();
        input.nextLine();
        return new Movie(name, length, year, month, day, hour, min);
    }

    //Check if it is equal by first checking the time then the name
    public boolean equals(Movie m) {
        if (this.isTimeRepeated(m)) {
            if(m.getName().equals(this.getName())) {
                System.out.println("Movie already during this time. ");
                return true;
            } else {
                System.out.println("There is already a movie playing during that time. ");
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getName() + " is playing for " + getLength() 
            + " minutes during " + getTime();
    }

    /* Getters & Setters */
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
