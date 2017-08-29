import java.io.*;
import java.util.*;

public class LongestIncreasingSubsequence {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter p = new PrintWriter(System.out);
        int n = in.nextInt();
        int[] ar = new int[n];
        for(int i=0;i<n;i++)
            ar[i]=in.nextInt();
        int[] tailNo = new int[n];
        int len=1;
        tailNo[0]=ar[0];
        for(int i=1;i<n;i++){
            if(ar[i]<tailNo[0])
                tailNo[0]=ar[i];
            else if(ar[i]>tailNo[len-1])
                tailNo[len++]=ar[i];
            else
                tailNo[ceilIndex(tailNo,0,len-1,ar[i])]=ar[i];
        }
        p.println(len);
        p.flush();
        p.close();

    }
    static int ceilIndex(int[] ar, int l, int r, int key){
        int ind = Arrays.binarySearch(ar,l,r,key);
        if(ind<0)
            ind = -ind-1;
        return ind;
    }
}