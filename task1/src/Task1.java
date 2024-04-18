import java.util.Arrays;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Task1 <n> <m>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        getCircularArray(n, m);
    }

    public static void getCircularArray(int n, int m) {
        int[] arr = new int[n];
        Arrays.setAll(arr, i -> ++i);

        int current = 0;
        do {
            System.out.print(arr[current]);
            current = (current + m - 1) % n;
        } while (current != 0);
    }
}
