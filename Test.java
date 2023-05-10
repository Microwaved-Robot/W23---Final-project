import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // LocalDateTime n = LocalDateTime.of(2023, 12, 31, 23, 59, 59);

        // boolean[][] seats = new boolean[10][5];
        // seats[3][2] = true;

        // boolean flag = true, flag2 = true;

        // for (int i = 0; i < seats.length + 1; i++) {
        // if (i == 0) {
        // System.out.print("#");
        // } else {
        // // Prints a letter in the first vertical column
        // System.out.print((char) (65 + i - 1));
        // }
        // if (i == seats.length) {
        // i--;
        // flag2 = false;
        // }
        // for (int j = 0; j < seats[i].length + 1; j++) {
        // flag = true;
        // if (i == 0) {
        // System.out.print(" " + j);
        // continue;
        // } else if (j == seats[i].length) {
        // j--;
        // flag = false;
        // }
        // //Print X or O depending on the seat available : X meaning not available and
        // O the contrary
        // if (seats[i][j] == false) {
        // System.out.print(" X");
        // } else {
        // System.out.print(" O");
        // }
        // if (flag == false) {
        // j++;
        // }
        // }
        // System.out.println();
        // if (flag2 == false) {
        // i++;
        // }
        // }
        // System.out.println("Where X is taken and O is vacant.");

        // int number = 0;
        // boolean flag = false;

        
        // do {
        //     try {
        //         System.out.print("Enter the number of staff: ");
        //         number = input.nextInt();
        //         if (number < 0) {
        //             throw new IllegalArgumentException("Negative number");
        //         }
        //         flag = false;
        //     } catch (IllegalArgumentException e) {
        //         System.out.println("The number needs to be bigger than 0.");
        //         flag = true;
        //     }
        // } while (flag);

        LinkedList<LocalDateTime> list = new LinkedList<>();
        // list.add(LocalDateTime.of(2005, 200, 31, 9, 7, 6));
        list.add(LocalDateTime.of(2005, 1, 31, 9, 7, 6));
        list.add(LocalDateTime.of(2005, 4, 5, 9, 7, 6));
        list.add(LocalDateTime.of(2005, 7, 31, 9, 7, 6));
        for (int i = 0; i < list.size(); i++) {
            if (LocalDateTime.of(2005, 5, 31, 9, 7, 6).isAfter(list.get(i))) {
                list.remove(i);
                i--;
            }
        }
        System.out.println(list);
    }
}
