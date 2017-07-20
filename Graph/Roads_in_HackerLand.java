import java.io.*;
import java.util.*;

public class Roads_in_HackerLand {
    public static void main(String args[]) throws Exception {
        InputReader in = new InputReader(System.in);
        PrintWriter p = new PrintWriter(System.out);
        int v = in.nextInt(), e = in.nextInt(), q = 0;
        Graph theGra = new Graph(v);
        while (e-- > 0)
            theGra.setEdge(in.nextInt(), in.nextInt(), (int) Math.pow(2, in.nextDouble()));
        for (int i = 1; i < v + 1; i++)
            for (int j = i + 1; j < v + 1; j++)
                q += theGra.DJ(i, j);
        p.println(Integer.toBinaryString(q));
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
    Map<Integer, List<Pair>> gra;

    Graph(int vertex) {
        gra = new HashMap<Integer, List<Pair>>();
        for (int i = 1; i <= vertex; i++)
            gra.put(i, new LinkedList<Pair>());
    }

    void setEdge(int s, int d, int wt) {
        List<Pair> th = gra.get(s);
        th.add(new Pair(d, wt));
        th = gra.get(d);
        th.add(new Pair(s, wt));
    }

    int DJ(int s, int d) {
        int v = gra.size();
        boolean[] visit = new boolean[v + 1];
        int[] dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Pair> q = new PriorityQueue<Pair>(v, Pair.com);
        q.add(new Pair(s, 0));
        dist[s] = 0;
        Pair temp1, temp2;
        while (!q.isEmpty()) {
            temp1 = q.poll();
            if (visit[temp1.ver])
                continue;
            else
                visit[temp1.ver] = true;
            Iterator it = gra.get(temp1.ver).iterator();
            while (it.hasNext()) {
                temp2 = (Pair) it.next();
                if (dist[temp2.ver] > dist[temp1.ver] + temp2.dis) {
                    dist[temp2.ver] = dist[temp1.ver] + temp2.dis;
                    q.add(new Pair(temp2.ver, dist[temp2.ver]));
                }
            }
        }
        return dist[d];
    }
}

class Pair {
    int ver, dis;

    Pair(int v, int d) {
        ver = v;
        dis = d;
    }

    static Comparator<Pair> com = new Comparator<Pair>() {
        @Override
        public int compare(Pair o1, Pair o2) {
            return Integer.compare(o1.dis, o2.dis);
        }
    };
}