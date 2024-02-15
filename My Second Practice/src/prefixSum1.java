
// 백준 5419
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class prefixSum1 {

    static int[] tree;
    static int[] yIndex;

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (testCase-- > 0) {
            int N = Integer.parseInt(br.readLine());
            tree = new int[N * 4];
            yIndex = new int[N];
            long result = 0;
            List<Point> datas = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                datas.add(new Point(x, y));
            }
            // y값 내림차순 정렬
            Collections.sort(datas, (a, b) -> b.y - a.y);
            int cnt = 1;
            for (int i = 0; i < N; i++) {
                if (i > 0 && datas.get(i).y != datas.get(i - 1).y)
                    cnt++;
                // y값이 큰 수부터 1,2,3,4...
                yIndex[i] = cnt;
            }
            for (int i = 0; i < N; i++) {
                datas.get(i).y = yIndex[i];
            }
            Collections.sort(datas, (a, b) -> {
                return (a.x == b.x) ? a.y - b.y : a.x - b.x;
            });
            for (int i = 0; i < N; i++) {
                int y = datas.get(i).y;
                result += sum(1, N, 1, 1, y);
                update(1, N, 1, y);
            }
            sb.append(result + "\n");

        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void update(int start, int end, int node, int index) {
        if (index < start || index > end)
            return;
        tree[node] += 1;
        if (start == end)
            return;
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index);
        update(mid + 1, end, node * 2 + 1, index);
    }

    public static int sum(int start, int end, int node, int left, int right) {
        if (right < start || end < left)
            return 0;
        if (left <= start && end <= right)
            return tree[node];
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }
}