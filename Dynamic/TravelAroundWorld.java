import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class TravelAroundWorld {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter p = new PrintWriter(System.out);
        int n = in.nextInt();
        long c = in.nextLong();
        long[] a = new long[n], b = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextLong();
        for (int j = 0; j < n; j++)
            b[j] = in.nextLong();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            int j = i == 0 ? n - 1 : i - 1;
            while (j != i) {
                if (dp[i][j] == 0)
                    dp[i][j] = can(a, b, i, j, c, n, dp);
                if (dp[j][i] == 0)
                    dp[j][i] = can(a, b, j, i, c, n, dp);
                if (dp[i][j] == 1 && dp[j][i] == 1)
                    dp[i][i] = 1;
                j = j == 0 ? n - 1 : j - 1;
            }
        }
        int sum = 0;
        for (int i = 0; i < n; i++)
            if (dp[i][i] == 1)
                sum++;
        p.println(sum);
        p.flush();
        p.close();
    }

    static int can(long[] a, long[] b, int s, int e, long c, int N, int[][] dp) {
        BigInteger fuel = BigInteger.ZERO;
        for (int i = s; i % N != e; i++) {
            fuel = fuel.add(BigInteger.valueOf(a[i % N])).compareTo(BigInteger.valueOf(c))<0 ? fuel.add(BigInteger.valueOf(a[i % N])):BigInteger.valueOf(c);
            if (fuel.compareTo(BigInteger.valueOf(b[i % N]))<0 )
                return -1;
            fuel = fuel.subtract(BigInteger.valueOf(b[i % N]));
            dp[s][(i+1) % N] = 1;
        }
        return 1;
    }
}