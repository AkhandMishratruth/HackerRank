import java.util.*;
import java.io.*;
public class Equal {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] temp = new int[n];
        int[] inc = {0, 1, 2, 5};
        for (int i = 0; i < n; i++)
            temp[i] = in.nextInt();
        Ar[][][] ar = new Ar[n][4][4];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 4; j++)
                for (int k = 0; k < 4; k++)
                    ar[i][j][k] = new Ar(temp);
        ar[0][0][0].fr++;
        for (int j = 1; j < 4; j++) {
            ar[0][j][0].decrement(0, inc[j]);
            ar[0][j][0].fr = 1;
            ar[0][j][0].isEqual();
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 4; j++)
                for (int k = 0; k < 4; k++) {
                    ar[i][j][k] = ar[i - 1][j][k];
                    ar[i][j][k].decrement(i, inc[k]);
                    ar[i][j][k].isEqual();
                    if (i != 0)
                        ar[i][j][k].fr++;
                }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 4; j++)
                if (ar[i][j].isEqu == true && min > ar[i][j].fr)
                    min = ar[i][j].fr;
        System.out.println(min);
        for (int i : ar[2][1].ar)
            System.out.print(i + " ");

    }
}
class Ar {
    int[] ar;
    int fr = -1;
    boolean isEqu = false;

    Ar(int[] a) {
        ar = a;
    }

    void decrement(int ind, int val) {
        ar[ind]-=val;
        return;
    }

    void isEqual() {
        int first = ar[0];
        for (int i = 1; i < ar.length; i++) {
            if (ar[i] != first) {
                isEqu = false;
                break;
            }
        }
        isEqu = true;
        return;
    }
}