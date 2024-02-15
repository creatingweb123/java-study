
// 백준 5419
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sweeping {

    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        tree = new int[N * 4];

        long result = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int data = Integer.parseInt(st.nextToken());
            result += sum(1, N, 1, data, N);
            update(1, N, 1, data);
        }
        System.out.println(result);

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