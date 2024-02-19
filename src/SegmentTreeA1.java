
// 백준 1280
// 개수 트리, 거리 트리
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SegmentTreeA1 {
    static final long MOD = 1000000007;
    static int[] checktree;
    static long[] distanceTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long result = 1;
        checktree = new int[800000];
        distanceTree = new long[800000];
        update(0, 200000, 1, Integer.parseInt(br.readLine()));
        for (int i = 1; i < N; i++) {
            int distance = Integer.parseInt(br.readLine());
            long sumDistance = (-sum(0, 200000, 1, 0, distance, (long) distance)
                    + sum(0, 200000, 1, distance, 200000, (long) distance))
                    % MOD;
            result = result * sumDistance % MOD;
            update(0, 200000, 1, distance);
        }
        System.out.println(result);

    }

    public static long sum(int start, int end, int node, int left, int right, long distance) {
        if (right < start || end < left) {
            return 0;
        }
        if (left <= start && end <= right) {
            return distanceTree[node] - (long) (checktree[node] * distance);
        }
        int mid = (start + end) / 2;
        return (sum(start, mid, node * 2, left, right, distance)
                + sum(mid + 1, end, node * 2 + 1, left, right, distance)) % MOD;
    }

    public static void update(int start, int end, int node, int idx) {
        if (idx < start || idx > end)
            return;
        checktree[node] += 1;
        distanceTree[node] += (long) idx;
        if (start == end)
            return;
        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx);
        update(mid + 1, end, node * 2 + 1, idx);
    }
}