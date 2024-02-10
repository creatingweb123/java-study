
// 백준 1395
// segment tree with lazy propagation
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SegmentTree9 {
	public static int[] datas;
	public static int[] tree;
	public static boolean[] lazy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		datas = new int[N + 1];
		tree = new int[N * 4];
		lazy = new boolean[N * 4];

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 0) {
				updateRange(1, N, 1, b, c);
			} else {
				sb.append(sum(1, N, 1, b, c) + "\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static void lazyUpdate(int start, int end, int node) {
		if (lazy[node]) {
			tree[node] = (end - start + 1) - tree[node];
			if (start != end) {
				lazy[node * 2] = !lazy[node * 2];
				lazy[node * 2 + 1] = !lazy[node * 2 + 1];
			}
			lazy[node] = false;
		}
	}

	public static void updateRange(int start, int end, int node, int left, int right) {
		lazyUpdate(start, end, node);
		if (right < start || end < left)
			return;
		if (left <= start && end <= right) {
			tree[node] = (end - start + 1) - tree[node];
			if (start != end) {
				lazy[node * 2] = !lazy[node * 2];
				lazy[node * 2 + 1] = !lazy[node * 2 + 1];
			}
			return;
		}
		int mid = (start + end) / 2;
		updateRange(start, mid, node * 2, left, right);
		updateRange(mid + 1, end, node * 2 + 1, left, right);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	public static int sum(int start, int end, int node, int left, int right) {
		lazyUpdate(start, end, node);
		if (right < start || end < left)
			return 0;
		if (left <= start && end <= right)
			return tree[node];
		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}
}