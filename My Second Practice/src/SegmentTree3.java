
// 백준 10999
// segment tree with lazy propagation
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SegmentTree3 {
	public static long[] datas;
	public static long[] tree;
	public static long[] lazy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		datas = new long[N + 1];
		tree = new long[N * 4];
		lazy = new long[N * 4];
		for (int i = 1; i <= N; i += 1) {
			datas[i] = Long.parseLong(br.readLine());
		}
		initTree(1, N, 1);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 1) {
				long d = Long.parseLong(st.nextToken());
				updateRange(1, N, 1, b, c, d);
			} else {
				sb.append(sum(1, N, 1, b, c) + "\n");
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

	public static long sum(int start, int end, int node, int left, int right) {
		lazyUpdate(start, end, node);
		if (left > end || right < start)
			return 0;
		if (left <= start && right >= end)
			return tree[node];
		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}
}