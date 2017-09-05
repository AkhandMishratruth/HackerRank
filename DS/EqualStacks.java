import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class EqualStacks {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter p = new PrintWriter(System.out);
        int n1=in.nextInt(), n2=in.nextInt(), n3=in.nextInt(), sum1=0,sum2=0,sum3=0;
        ArrayList<Integer> s1 = new ArrayList<Integer>();
        ArrayList<Integer> s2 = new ArrayList<Integer>();
        ArrayList<Integer> s3 = new ArrayList<Integer>();

        for(int i=0;i<n1;i++) {
            s1.add(in.nextInt());
            sum1+=s1.get(i);
        }
        for(int i=0;i<n2;i++) {
            s2.add(in.nextInt());
            sum2 += s2.get(i);
        }
        for(int i=0;i<n3;i++) {
            s3.add(in.nextInt());
            sum3 += s3.get(i);
        }
        int ind1=0,ind2=0,ind3=0;
        while(sum1 !=sum2 || sum2!=sum3 || sum3!=sum1){
            int temp, max = Math.max(sum1,Math.max(sum2,sum3));
            if(max==sum1){
                temp = s1.get(ind1++);
                sum1-=temp;
            }
            if(max==sum2){
                temp = s2.get(ind2++);
                sum2-=temp;
            }
            if(max==sum3){
                temp = s3.get(ind3++);
                sum3-=temp;
            }
        }
        p.println(sum1);
        p.flush();
        p.close();
    }
}