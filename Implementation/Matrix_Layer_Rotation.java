import java.util.*;
import java.io.*;
public class Matrix_Layer_Rotation {
    public static void main(String args[]) throws Exception {
        InputReader in = new InputReader(System.in);
        PrintWriter p = new PrintWriter(System.out);
        int tempr,min, n = in.nextInt(), m = in.nextInt(), r = in.nextInt();
        int[][] mat = new int[n][m];
        int[][] mat2 = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                mat[i][j] = in.nextInt();
        int mod;
        ij temp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                min = Math.min(Math.min(i, n - i - 1), Math.min(j, m - j - 1));
                mod = 2*(n+m)-8*min-4;
                tempr = r%mod;
                temp = new ij(i, j);
                while (tempr-- > 0) {
                    temp = rot(temp.i, temp.j, n, m);
                }
                //System.out.print(temp.i + "," + temp.j + " ");
                mat2[temp.i][temp.j]=mat[i][j];
            }
            //System.out.println();
        }

        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++)
                p.print(mat2[i][j]+" ");
            p.println();
        }
        p.flush();
        p.close();
    }

    static ij rot(int i, int j, int n, int m) {
        int min = Math.min(Math.min(i, n - i - 1), Math.min(j, m - j - 1));
        i = i - min;
        j = j - min;
        m = m - 2*min;
        n = n - 2*min;
        if (j == 0 && i != n - 1)
            i++;
        else if (i == 0 && j != 0)
            j--;
        else if (i == n - 1 && j != m - 1)
            j++;
        else if (j == m - 1 && i != 0)
            i--;
        return (new ij(i+min, j+min));
    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();

            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }

                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public String nextLine() {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        public int nextInt() {
            int c = read();

            while (isSpaceChar(c))
                c = read();

            int sgn = 1;

            if (c == '-') {
                sgn = -1;
                c = read();
            }

            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));

            return res * sgn;
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;

            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
            return res * sgn;
        }

        public double nextDouble() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, nextInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, nextInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public String readString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            }
            while (!isSpaceChar(c));

            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next() {
            return readString();
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}
class ij {
    int i, j;

    ij(int a, int b) {
        i = a;
        j = b;
    }
}