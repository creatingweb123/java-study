
// 백준 3653
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SegmentTree66 {
	public static int[] tree;
	public static int[] dataIndex;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());
		for (int i = 0; i < testCase; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			tree = new int[4 * (N + M)];
			dataIndex = new int[N + 1];
			initData(N, M);
			initTree(1, N + M, 1, M);
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int data = Integer.parseInt(st.nextToken());
				int index = dataIndex[data];
				sb.append(query(1, N + M, 1, 1, index - 1) + " ");
				update(1, N + M, 1, index, -1);
				update(1, N + M, 1, M - j, 1);
				dataIndex[data] = M - j;
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

	public static void initData(int N, int M) {
		for (int i = 1; i <= N; i++) {
			dataIndex[i] = M + i;
		}
	}

	public static int initTree(int start, int end, int node, int M) {
		if (end <= M)
			return 0;
		if (start == end) {
			return tree[node] = 1;
		}
		int mid = (start + end) / 2;
		return tree[node] = initTree(start, mid, node * 2, M) + initTree(mid + 1, end, node * 2 + 1, M);
	}

	public static int query(int start, int end, int node, int left, int right) {
		if (right < start || left > end)
			return 0;
		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) / 2;
		return query(start, mid, node * 2, left, right) + query(mid + 1, end, node * 2 + 1, left, right);
	}

	public static void update(int start, int end, int node, int idx, int dif) {
		if (idx < start || idx > end)
			return;
		tree[node] += dif;
		if (start == end)
			return;
		int mid = (start + end) / 2;
		update(start, mid, node * 2, idx, dif);
		update(mid + 1, end, node * 2 + 1, idx, dif);
	}
}
