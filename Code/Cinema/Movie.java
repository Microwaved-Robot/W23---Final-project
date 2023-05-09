package Code.Cinema;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Movie {
    private String name;
    private int length;
    private LocalDateTime time;

    Scanner input = new Scanner(System.in);

    /*----------------------------------Constructors----------------------------------*/
    protected Movie(String name, int length, int year, int month, int day, int hour, int min) {
        this.name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        this.length = length;
        this.time = LocalDateTime.of(year, month, day, hour, min);
    }

    // Create a new movie obj
    protected Movie() {
        boolean flag = false;

        do {
            try {
                System.out.print("Enter the name of the movie: ");
                name = input.nextLine();
                flag = false;
            } catch (InputMismatchException ex) {
                System.out.println("The name is wrong. :( ");
                flag = true;
            }
        } while (flag);

        do {
            try {
                System.out.print("Enter the length of the movie: ");
                length = input.nextInt();
                flag = false;
                if (length <= 0) {
                    throw new IllegalArgumentException("Negative number");
                }
            } catch (InputMismatchException ex) {
                System.out.println("The length needs to be a number");
                flag = true;
            } catch (IllegalArgumentException ex) {
                System.out.println("The length needs to be higher than 0. ");
                flag = true;
            }
        } while (flag);

        do {
            try {
                System.out.print("Enter date that the movie is going to show (YYYY MM DD): ");
                int year = input.nextInt();
                int month = input.nextInt();
                int day = input.nextInt();

                if (month > 12 || month <= 0) {
                    throw new IllegalArgumentException("The month needs to be between 1 and 12.");
                }

                // Check for the NB of day in the month
                if (day > 29 && month == 2 && year % 4 == 0) {
                    throw new IllegalArgumentException("During a leep year february has 29 days.\n");
                } else if (day > 28 && month == 2) {
                    throw new IllegalArgumentException("February has 28 days.\n");
                } else if (day > 30 && month <= 7 && month % 2 == 0) {
                    throw new IllegalArgumentException("This month has 30 days.\n");
                } else if (day > 30 && month <= 12 && month % 2 == 1) {
                    throw new IllegalArgumentException("This month has 30 days.\n");
                } else if (day > 31) {
                    throw new IllegalArgumentException("This month has 31 days.\n");
                }

                System.out.print("Enter the time of day that the movie is going to be shown (hour min)");
                int hour = input.nextInt();
                int min = input.nextInt();
                input.nextLine();

                if (hour > 24 || hour < 0) {
                    throw new IllegalArgumentException("The hour neeeds to be between 0 and 24\n");
                } else if (min > 60 || min < 0) {
                    throw new IllegalArgumentException("The min neeeds to be between 0 and 60\n");
                }

                time = LocalDateTime.of(year, month, day, hour, min);
                flag = false;
            } catch (InputMismatchException ex) {
                System.out.println("The date or the time of day needs to be a number. ");
                flag = true;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex);
                flag = true;
            }
        } while (flag);
    }

    /*----------------------------------Methods----------------------------------*/

    // Check if the time of the movie is repeated by checking for the whole movie
    // duration
    protected boolean isTimeRepeated(Movie m) {
        if (this.getTime().isBefore(m.getTime()) && this.getTime().plusMinutes(length).isBefore(m.getTime())) {
            this.getTime().minusMinutes(length);
            return true;
        } else {
            this.getTime().minusMinutes(length);
            return false;
        }
    }

    // Check if it is equal by first checking the time then the name
    public boolean equals(Movie m) {
        if (this.isTimeRepeated(m)) {
            if (m.getName().equals(this.getName())) {
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

    /*----------------------------------Getter and Setters----------------------------------*/
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

    public void setTime(int year, int month, int day, int hour, int min) {
        this.time = LocalDateTime.of(year, month, day, hour, min);
    }
}
