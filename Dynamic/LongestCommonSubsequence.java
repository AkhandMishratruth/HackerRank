import java.io.*;
import java.util.*;

public class LongestCommonSubsequence {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter p = new PrintWriter(System.out);
        int n = in.nextInt(), m = in.nextInt();
        int[] a = new int[n + 1];
        int[] b = new int[m + 1];
        for (int i = 1; i <= n; i++)
            a[i] = in.nextInt();
        for (int j = 1; j <= m; j++)
            b[j] = in.nextInt();
        int[][] dp = new int[n + 1][m + 1];
        ArrayList<Integer>[] st = new ArrayList[Math.min(n,m)+1];
        for(int i=0;i<st.length;i++)
            st[i]=new ArrayList<Integer>();

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else if (a[i] == b[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    st[dp[i][j]]=(ArrayList<Integer>) st[dp[i-1][j-1]].clone();
                    st[dp[i][j]].add(a[i]);
                } else if (dp[i][j - 1] > dp[i - 1][j]) {
                    dp[i][j] = dp[i][j - 1];
                    st[dp[i][j]]=(ArrayList<Integer>)st[dp[i][j-1]].clone();
                } else {
                    dp[i][j] = dp[i - 1][j];
                    st[dp[i][j]]=(ArrayList<Integer>)st[dp[i-1][j]].clone();
                }
            }
        }
        for(int i=0;i<st[dp[n][m]].size();i++)
            p.print(st[dp[n][m]].get(i)+" ");
        p.flush();
        p.close();
    }
}