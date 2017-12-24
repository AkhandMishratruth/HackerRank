import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String a = in.next();
            String b = in.next();
            String result = abbreviation(a, b);
            System.out.println(result);
        }
        in.close();
    }
    static String abbreviation(String a, String b){
        int al = a.length(), bl = b.length();
        boolean dp[][] = new boolean[al+1][bl+1];
        for(int i=0;i<=al;i++){
            for(int j=0;j<=bl;j++){
                if(i==0 && j==0)
                    dp[i][j]=true;
                else if(i==0)
                    dp[i][j]=false;
                else if(j==0){
                    if(Character.isUpperCase(a.charAt(i-1)))
                        dp[i][j]=false;
                    else
                        dp[i][j] = dp[i-1][j];
                }
                else{
                    if(Character.isUpperCase(a.charAt(i-1)) && a.charAt(i-1)==b.charAt(j-1))
                        dp[i][j] = dp[i-1][j-1];
                    else if(Character.toUpperCase(a.charAt(i-1))==b.charAt(j-1))
                        dp[i][j] = dp[i-1][j-1] || dp[i-1][j];
                    else{
                        if(Character.isUpperCase(a.charAt(i-1)))
                            dp[i][j] = false;
                        else
                            dp[i][j] = dp[i-1][j];
                    }
                }
            }
        }
        return dp[al][bl] ? "YES" : "NO";
    }
}
