import java.io.*;
import java.util.*;

public class StockMaximize {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            long[] ar = new long[n];
            for (int i = 0; i < n; i++)
                ar[i] = in.nextLong();
            long[] max = new long[n];
            max[n - 1] = ar[n - 1];
            for (int i = n - 2; i >= 0; i--)
                max[i] = Math.max(max[i + 1], ar[i]);
            long sum = 0, temp;
            for (int i = 0; i < n; i++)
                sum += (max[i] - ar[i] > 0) ? max[i] - ar[i] : 0;
            System.out.println(sum);
        }
    }
}