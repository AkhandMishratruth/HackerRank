import java.io.*;
import java.util.*;

public class PlayWithWords {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter p = new PrintWriter(System.out);
        String st=in.next();
        int dp[][] = new int[st.length()][st.length()];
        for(int i=0;i<dp.length;i++)
            dp[i][i]=1;
        int end;
        for(int len = 2;len<=st.length();len++){
            for(int start =0;start<dp.length-len+1;start++) {
                end = start + len - 1;
                if(st.charAt(start)==st.charAt(end))
                    dp[start][end]=(len==2)?2:dp[start+1][end-1]+2;
                else
                    dp[start][end]=Math.max(dp[start][end-1], dp[start+1][end]);
            }
        }
        int max=0;
        for(int i=0;i<dp.length-1;i++)
            max = Math.max(dp[0][i]*dp[i+1][dp.length-1], max);
        p.println(max);
        p.flush();
        p.close();
    }
}