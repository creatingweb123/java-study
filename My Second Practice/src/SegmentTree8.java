
// 백준 16975
// segment tree with lazy propagation
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SegmentTree8 {
	public static long[] datas;
	public static long[] tree;
	public static long[] lazy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		datas = new long[N + 1];
		tree = new long[N * 4];
		lazy = new long[N * 4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i += 1) {
			datas[i] = Integer.parseInt(st.nextToken());
		}
		initTree(1, N, 1);
		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == 1) {
				int c = Integer.parseInt(st.nextToken());
				long d = Long.parseLong(st.nextToken());
				updateRange(1, N, 1, b, c, d);
			} else {
				sb.append(search(1, N, 1, b) + "\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static void initTree(int start, int end, int node) {
		if (start == end)
			tree[node] = datas[start];
		else {
			int mid = (start + end) / 2;
			initTree(start, mid, node * 2);
			initTree(mid + 1, end, node * 2 + 1);
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
		}
	}

	public static void lazyUpdate(int start, int end, int node) {
		if (lazy[node] != 0) {
			tree[node] += (end - start + 1) * lazy[node];
			if (start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}

	public static void updateRange(int start, int end, int node, int left, int right, long dif) {
		lazyUpdate(start, end, node);
		if (right < start || end < left)
			return;
		if (left <= start && end <= right) {
			tree[node] += (end - start + 1) * dif;
			if (start != end) {
				lazy[node * 2] += dif;
				lazy[node * 2 + 1] += dif;
			}
			return;
		}
		int mid = (start + end) / 2;
		updateRange(start, mid, node * 2, left, right, dif);
		updateRange(mid + 1, end, node * 2 + 1, left, right, dif);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	public static long search(int start, int end, int node, int idx) {
		lazyUpdate(start, end, node);
		if (start == end)
			return tree[node];
		int mid = (start + end) / 2;
		if (idx > mid) {
			return search(mid + 1, end, node * 2 + 1, idx);
		} else {
			return search(start, mid, node * 2, idx);
		}
	}
}