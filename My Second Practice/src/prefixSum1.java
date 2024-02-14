
// 팬위 트리
// 백준 11658
// segment tree with lazy propagation
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class prefixSum1 {
    public static long[][] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        trees = new long[N + 1][N + 1];

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i += 1) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j += 1) {
                update(i, j, Long.parseLong(st.nextToken()));
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (w == 1) {
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                sb.append(findData(x1, y1, x, y) + "\n");
            } else {
                long c = Integer.parseInt(st.nextToken());
                long dif = c - findData(x, y, x, y);
                update(x, y, dif);
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void update(int x, int y, long value) {
        while (x < trees.length) {
            int tempY = y;
            while (tempY < trees[x].length) {
                trees[x][tempY] += value;
                tempY += (tempY & -tempY);
            }
            x += (x & -x);
        }
    }

    public static long sum(int x, int y) {
        long result = 0;
        while (x > 0) {
            int tempY = y;
            while (tempY > 0) {
                result += trees[x][tempY];
                tempY -= (tempY & -tempY);
            }
            x -= (x & -x);
        }
        return result;
    }

    public static long findData(int x, int y, int x1, int y1) {
        return sum(x, y) - sum(x, y1 - 1) - sum(x1 - 1, y) + sum(x1 - 1, y1 - 1);
    }

}