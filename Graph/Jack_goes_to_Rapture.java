import java.util.*;
import java.io.*;
public class Jack_goes_to_Rapture {
    public static void main(String args[]) throws Exception {
        InputReader in = new InputReader(System.in);
        PrintWriter p = new PrintWriter(System.out);
        long l1;
        int v = in.nextInt(), e=in.nextInt();
        long[] dist = new long[v+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        boolean[] visit = new boolean[v+1];
        Graph TheGra = new Graph(v);
        while(e-->0)
            TheGra.setEdge(in.nextInt(), in.nextInt(), in.nextLong());
        PriorityQueue<pair> q = new PriorityQueue<pair>(v, pair.com);
        q.add(new pair(0L,1));
        dist[1]=0L;
        pair temp1, temp2;
        while(!q.isEmpty()){
            temp1 = q.poll();
            if(visit[temp1.ver])
                continue;
            else
                visit[temp1.ver]=true;
            Iterator it = TheGra.gra.get(temp1.ver).iterator();
            while(it.hasNext()){
                temp2 = (pair) it.next();
                l1=Math.max(0L,temp2.dis - dist[temp1.ver]);
                if(l1 + dist[temp1.ver]<dist[temp2.ver]) {
                    dist[temp2.ver] = l1 + dist[temp1.ver];
                    q.add(new pair(dist[temp2.ver], temp2.ver));
                }
            }
        }
        if(dist[v]==Long.MAX_VALUE)
            p.println("NO PATH EXISTS");
        else
            p.println(dist[v]);
        p.flush();
        p.close();
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
class Graph {
    Map<Integer, List<pair>> gra;

    Graph(int vertex) {
        gra = new HashMap<Integer, List<pair>>();
        for (int i = 1; i <= vertex; i++)
            gra.put(i, new LinkedList<pair>());
    }

    void setEdge(int s, int d, long wt) {
        List<pair> th = gra.get(s);
        th.add(new pair(wt, d));
        th = gra.get(d);
        th.add(new pair(wt, s));
    }
}
class pair {
    int ver;
    long dis;

    pair(long d, int v) {
        dis = d;
        ver = v;
    }

    static Comparator<pair> com = new Comparator<pair>() {
        @Override
        public int compare(pair o1, pair o2) {
            return Long.compare(o1.dis, o2.dis);
        }
    };
}