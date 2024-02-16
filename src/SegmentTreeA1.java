
// 백준 1280
// 개수 트리, 거리 트리
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SegmentTreeA1 {
    static final long MOD = 1000000007;
    static int[] checktree;
    static int[] distanceTree;
    static int[] indexList;
    static HashMap<Integer, Integer> indexMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long result = 1;
        checktree = new int[4 * N];
        distanceTree = new int[4 * N];
        indexList = new int[N + 1];
        indexMap = new HashMap<>();
        List<Integer> datas = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int distance = Integer.parseInt(br.readLine());
            indexList[i] = distance;
            datas.add(distance);
        }
        Collections.sort(datas);
        for (int i = 0; i < N; i++) {
            indexMap.put(datas.get(i), i + 1);
        }
        update(1, N, 1, indexMap.get(indexList[0]), indexList[0]);
        for (int i = 1; i < N; i++) {

            int dataIndex = indexMap.get(indexList[i]);
            int distance = indexList[i];
            long sumDistance = (sum(1, N, 1, 1, dataIndex, distance) + sum(1, N, 1, dataIndex, N, distance))
                    % MOD;
            result = (result * sumDistance) % MOD;
            update(1, N, 1, dataIndex, distance);
        }
        System.out.println(result);

    }

    public static long sum(int start, int end, int node, int left, int right, int distance) {
        if (right < start || end < left) {
            return 0;
        }
        if (left <= start && end <= right) {
            return Math.abs(distanceTree[node] - checktree[node] * distance) % MOD;
        }
        int mid = (start + end) / 2;
        return (sum(start, mid, node * 2, left, right, distance)
                + sum(mid + 1, end, node * 2 + 1, left, right, distance)) % MOD;
    }

    public static void update(int start, int end, int node, int idx, int distance) {
        if (idx < start || idx > end)
            return;
        checktree[node] += 1;
        distanceTree[node] += distance;
        if (start == end)
            return;
        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, distance);
        update(mid + 1, end, node * 2 + 1, idx, distance);
    }
}