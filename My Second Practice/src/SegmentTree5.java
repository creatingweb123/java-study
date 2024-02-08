
// 백준 7578
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SegmentTree5 {
	public static long[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		tree = new long[N * 4];

		int[] first = new int[N + 1];
		Map<Integer, Integer> second = new HashMap<>();
		long answer = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			first[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			second.put(Integer.parseInt(st.nextToken()), i);
		}

		for (int i = 1; i <= N; i++) {
			int target = second.get(first[i]);
			answer += sum(1, N, 1, target + 1, N);
			update(1, N, 1, target);
		}
		System.out.println(answer);

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

	public static long sum(int start, int end, int node, int left, int right) {
		if (right < start || left > end)
			return 0;
		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}
}
