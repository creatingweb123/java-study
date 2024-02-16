
// 백준 3653
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SegmentTreeA {
    public static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        tree = new int[4 * N];
        for (int i = 1; i <= N; i++) {
            update(1, N, 1, i);
        }
        int nowIndex = 1;
        int maxIndex = N;
        sb.append("<");
        for (int i = 1; i <= N; i++) {
            nowIndex = (nowIndex - 1 + M) % maxIndex;
            if (nowIndex == 0) {
                sb.append(query(1, N, 1, maxIndex) + ", ");
                nowIndex = 1;
            } else {
                sb.append(query(1, N, 1, nowIndex) + ", ");
            }
            maxIndex -= 1;
        }
        int index = sb.length();
        sb.replace(index - 2, index - 1, ">");
        System.out.println(sb.toString());
    }

    public static int query(int start, int end, int node, int num) {
        tree[node] -= 1;
        if (start == end) {
            return start;
        }
        int mid = (start + end) / 2;
        int left = node * 2;
        int right = node * 2 + 1;
        if (tree[left] >= num) {
            return query(start, mid, left, num);
        } else {
            return query(mid + 1, end, right, num - tree[left]);
        }

    }

    public static void update(int start, int end, int node, int idx) {
        if (idx < start || idx > end)
            return;
        tree[node] += 1;
        if (start == end)
            return;
        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx);
        update(mid + 1, end, node * 2 + 1, idx);
    }
}