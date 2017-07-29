import java.util.*;
import java.io.*;
public class Matrix {
    static Edge[] edge;
    static boolean[] machine;
    static int[] root;

    public static void main(String args[]) throws Exception {
        InputReader in = new InputReader(System.in);
        PrintWriter p = new PrintWriter(System.out);
        int v = in.nextInt(), e = in.nextInt();
        edge = new Edge[v - 1];
        machine = new boolean[v];
        root = new int[v];
        for (int i = 0; i < root.length; i++)
            root[i] = i;
        for (int i = 0; i < v - 1; i++)
            edge[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
        Arrays.sort(edge);
        while (e-- > 0)
            machine[in.nextInt()] = true;
        int r1, r2, cost = 0;
        for (int i = 0; i < edge.length; i++) {
            r1 = Root(edge[i].fir);
            r2 = Root(edge[i].se);
            if (r1 != r2 && (!machine[r1] || !machine[r2]))
                union(edge[i].fir, edge[i].se);
            else if (machine[r1] && machine[r2])
                cost += edge[i].wt;
            //p.println(cost);
        }
        p.println(cost);
        p.flush();
        p.close();
    }

    static void union(int a, int b) {
        int p = Root(a);
        int q = Root(b);
        if(!machine[q])
            machine[q] = machine[p];
        if(!machine[p])
            machine[p] = machine[q];
        root[p] = root[q];
    }

    static int Root(int x) {
        while (root[x] != x) {
            if(!machine[root[x]])
                machine[root[x]] = machine[x];
            root[x] = root[root[x]];
            x = root[x];
        }
        return x;
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
class Edge implements Comparable<Edge>{
    int fir, se, wt;
    Edge(int f, int s, int w){
        fir = f;
        se = s;
        wt = w;
    }

    @Override
    public int compareTo(Edge o) {
        return -1*Integer.compare(this.wt, o.wt);
    }
}