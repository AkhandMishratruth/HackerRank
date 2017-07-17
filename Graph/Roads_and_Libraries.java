import java.io.*;
import java.util.*;

public class Roads_and_Libraries {
    public static void main(String args[]) throws Exception {
        InputReader in = new InputReader(System.in);
        PrintWriter p = new PrintWriter(System.out);
        Graph the;
        int v, e, t = in.nextInt();
        long temp=0, tem, clib, cr;
        while (t-- > 0) {
            temp = 0;
            v = in.nextInt();
            e = in.nextInt();
            clib = in.nextLong();
            cr = in.nextLong();
            the = new Graph(v);

            while (e-- > 0)
                the.setEdge(in.nextInt(), in.nextInt());
            if(cr<clib) {
                for (int i = 1; i <= v; i++) {
                    if (!the.visit[i]) {
                        tem = the.nodesAtLev(i);
                        temp += clib + (tem - 1) * cr;
                        //System.out.print(temp + " ");
                    }
                }
                p.println(temp);
            }
            else
                p.println(v*clib);
        }
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
    Map<Integer, List<Integer>> gra;
    boolean[] visit;

    Graph(int vertex) {
        visit = new boolean[vertex + 1];
        gra = new HashMap<Integer, List<Integer>>();
        for (int i = 1; i <= vertex; i++)
            gra.put(i, new LinkedList<Integer>());
    }

    void setEdge(int s, int d) {
        List<Integer> th = gra.get(s);
        th.add(d);
        th = gra.get(d);
        th.add(s);
    }

    /*
        int DFSM(int a){
            if(visit[a])
                return 0;
            else
                return DFS(a);
        }
    */
    /*
    int DFS(int s,int toret) {
        toret++;
            visit[s]=true;
            Iterator it = gra.get(s).iterator();
            int temp;
            while (it.hasNext()) {
                temp = (int) it.next();
                if (!visit[temp]) {
                    //visit[temp] = true;
                    //System.out.println(temp);
                    toret = DFS(temp, toret);
                }
            }
        return toret;
    }*/
    long nodesAtLev(int s) {
        long toret = 0;
        Stack<Integer> q = new Stack<>();
        q.push(s);
        visit[s] = true;
        toret++;
        int temp;

        while (!q.isEmpty()) {
            temp = q.pop();
            //System.out.print(q.size()+" ");
            Iterator it = gra.get(temp).iterator();
            while (it.hasNext()) {
                temp = (Integer) it.next();
                if (visit[temp] == false) {
                    q.push(temp);
                    visit[temp] = true;
                    toret++;
                    //System.out.print(toret+" ");
                }
            }
        }
        //System.out.print("'"+toret+"'");
        return toret;
    }
}