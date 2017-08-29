import java.io.*;
import java.util.*;

public class RedJohnBack {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter p = new PrintWriter(System.out);
        int[] dp = new int[41];
        dp[1]=1;dp[2]=1;dp[3]=1;dp[4]=2;
        for(int i=5;i<=40;i++)
            dp[i]=dp[i-1]+dp[i-4];
        int[] lastPrime = primelist();
        int t=in.nextInt(), N;
        while (t-->0){
            N = in.nextInt();
            p.println(lastPrime[dp[N]]);
        }
        p.flush();
        p.close();

    }
    static int[] primelist(){
        int n=217286;
        int[] ar = new int[n+1];
        ar[0]=0;
        ar[1]=0;
        ar[2]=1;
        int lastPrime = 2;
        for(int i=3;i<ar.length;i++) {
            ar[i]=ar[i-1];
            for (int j = lastPrime + 1; j <= i; j++) {
                if(isPrime(j)){
                    lastPrime = j;
                    ar[i]++;
                }
            }
        }
        return ar;
    }
    static boolean isPrime(int n){
        if(n<=1)
            return false;
        if(n<=3)
            return true;
        if(n%2==0 || n%3==0)
            return false;
        for(int i=5;i*i<=n;i+=6)
            if(n%i==0 || n%(i+2)==0)
                return false;
        return true;
    }
}