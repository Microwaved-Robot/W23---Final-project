import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {
        LocalDateTime n = LocalDateTime.of(2023, 12, 31, 23, 59, 59);
        System.out.println(n);
        System.out.println(n.plusSeconds(1));
    }
}
