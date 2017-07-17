import java.util.*;
import java.io.*;

public class Maximum_SubArray {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt(), n;
        int[] ar;
        while (t-- > 0) {
            n = in.nextInt();
            ar = new int[n];
            for (int i = 0; i < n; i++)
                ar[i] = in.nextInt();
            System.out.println(Aray.contiguous(ar) + " " + Aray.nonConiguous(ar));
        }
    }

    static class Aray {
        static int contiguous(int[] ar) {
            int sum = 0, ans = 0, max = Integer.MIN_VALUE;
            for (int i = 0; i < ar.length; i++) {
                if (max < ar[i])
                    max = ar[i];
                sum += ar[i];
                ans = Math.max(ans, sum);
                if (sum < 0)
                    sum = 0;
            }
            if (max < 0)
                return max;
            else
                return ans;
        }

        static int nonConiguous(int[] ar) {
            int sum = ar[0];
            for (int i = 1; i < ar.length; i++) {
                if (sum > 0 && ar[i] > 0)
                    sum = sum + ar[i];
                if (sum < 0)
                    sum = Math.max(sum, ar[i]);
            }
            return sum;
        }
    }
}