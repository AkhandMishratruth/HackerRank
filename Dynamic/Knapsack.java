import java.io.*;
import java.util.*;

public class Knapsack {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter p = new PrintWriter(System.out);
        int t=in.nextInt();
        int n, ar[], dp[][], k;
        while (t-->0){
            n = in.nextInt();
            k = in.nextInt();
            ar = new int[n];
            for(int i=0;i<n;i++)
                ar[i]=in.nextInt();
            dp = new int[n+1][k+1];
            for(int i=0;i<=n;i++){
                for(int j=0;j<=k;j++){
                    if(i==0 || j==0)
                        dp[i][j]=0;
                    else if(j>=ar[i-1])
                        dp[i][j]= Math.max(dp[i-1][j], ar[i-1] + dp[i][j-ar[i-1]]);
                    else
                        dp[i][j] = dp[i-1][j];
                }
            }
            System.out.println(dp[n][k]);
        }
    }
}