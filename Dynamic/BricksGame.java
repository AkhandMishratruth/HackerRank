import java.io.*;
import java.util.*;

public class BricksGame {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            long[] ar = new long[n];
            for (int i = 0; i < n; i++)
                ar[i] = in.nextLong();
            long[] rev = new long[n];
            for(int i =0;i<n;i++)
                rev[rev.length-1-i] = ar[i];
            long[] sum = new long[n];
            sum[0]=rev[0];
            for(int i=1;i<n;i++)
                sum[i]=rev[i]+sum[i-1];
            ar = new long[n];
            if(n>0)
                ar[0]=sum[0];
            if(n>1)
                ar[1]=sum[1];
            if(n>2)
                ar[2]=sum[2];
            for(int i =3;i<n;i++)
                ar[i]=sum[i]-Math.min(ar[i-1], Math.min( ar[i-2], ar[i-3]));
            System.out.println(ar[n-1]);
        }
    }
}