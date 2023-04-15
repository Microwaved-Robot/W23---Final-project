import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {
        LocalDateTime n = LocalDateTime.of(2023, 12, 31, 23, 59, 59);

        boolean[][] seats = new boolean[5][5];
        seats[3][2] = true;

        boolean flag = true, flag2 = true;

        for (int i = 0; i < seats.length + 1; i++) {
            if (i == 0) {
                System.out.print("#");
            } else {
                // Prints a letter in the first vertical column
                System.out.print((char) (65 + i - 1));
            }
            if (i == seats.length) {
                i--;
                flag2 = false;
            }
            for (int j = 0; j < seats[i].length + 1; j++) {
                flag = true;
                if (i == 0) {
                    System.out.print(" " + j);
                    continue;
                } else if (j == seats[i].length) {
                    j--;
                    flag = false;
                }
                //Print X or O depending on the seat available : X meaning not available and O the contrary
                if (seats[i][j] == false) {
                    System.out.print(" X");
                } else {
                    System.out.print(" O");
                }
                if (flag == false) {
                    j++;
                }
            }
            System.out.println();
            if (flag2 == false) {
                i++;
            }
        }
        System.out.println("Where X is taken and O is vacant.");
    }
}
