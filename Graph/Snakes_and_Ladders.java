import java.io.*;
import java.util.*;

public class Snakes_and_Ladders {
    public static void main(String args[]) throws Exception {
        InputReader in = new InputReader(System.in);
        PrintWriter p = new PrintWriter(System.out);
        int t = in.nextInt(), l, s;
        Graph the;
        while (t-->0) {
            the = new Graph(100);
            for(int i=1;i<100;i++){
                for(int j=1;j<7;j++){
                    if(i+j<=100)
                        the.setEdge(i,i+j);
                }
            }
            l = in.nextInt();
            while(l-->0)
                the.addLadderSnake(in.nextInt(),in.nextInt());
            s=in.nextInt();
            while(s-->0)
                the.addLadderSnake(in.nextInt(),in.nextInt());
            /*
            Iterator it = th.gra.keySet().iterator();
            int t1;
            while(it.hasNext()){
                t1 = (int)it.next();
                Iterator i = th.gra.get(t1).iterator();
                while(i.hasNext())
                    System.out.print((int) i.next()+" ");
                System.out.println();
            }*/
            p.println(the.minBFS());
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
    int[] visit;

    Graph(int vertex) {
        visit = new int[vertex+1];
        Arrays.fill(visit, -1);
        gra = new HashMap<Integer, List<Integer>>();
        for (int i = 1; i <= vertex; i++)
            gra.put(i, new LinkedList<Integer>());
    }

    void setEdge(int s, int d) {
        List<Integer> th = gra.get(s);
        th.add(d);
    }
    void addLadderSnake(int sor, int des){
        for(int i =sor-6;i<sor;i++){
            if(i>0){
                gra.get(i).remove(new Integer(sor));
                gra.get(i).add(new Integer(des));
            }
        }
    }

    int minBFS(){
        visit[1] = 0;
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(1);
        int temp, t2;
        while(!q.isEmpty()){
            temp = q.removeFirst();
            //System.out.print(temp+" ");
            Iterator it = gra.get(temp).iterator();
            while(it.hasNext()) {
                t2 = (int) it.next();
                if (visit[t2] == -1) {
                    visit[t2] = visit[temp] + 1;
                    q.add(t2);
                }
            }
        }
        /*
        for(int i : visit)
            System.out.print(i+" ");*/
        return visit[100];
    }
}