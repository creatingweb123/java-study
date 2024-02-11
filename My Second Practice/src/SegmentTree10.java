
// 백준 12844
// segment tree with lazy propagation
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SegmentTree10 {
	public static int[] datas;
	public static int[] tree;
	public static int[] lazy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(br.readLine());
		datas = new int[N + 1];
		tree = new int[N * 4];
		lazy = new int[N * 4];

		for (int i = 1; i <= N; i++) {
			datas[i] = Integer.parseInt(st.nextToken());
		}
		init(1, N, 1);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 1) {
				int k = Integer.parseInt(st.nextToken());
				updateRange(1, N, 1, b + 1, c + 1, k);
			} else {
				sb.append(xor(1, N, 1, b + 1, c + 1) + "\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static int init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = datas[start];
		}
		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node * 2) ^ init(mid + 1, end, node * 2 + 1);
	}

	public static void lazyUpdate(int start, int end, int node) {
		if (lazy[node] != 0) {
			int evenCheck = end - start + 1;
			if (evenCheck % 2 != 0) {
				tree[node] = tree[node] ^ lazy[node];
			}
			if (start != end) {
				lazy[node * 2] = lazy[node * 2] ^ lazy[node];
				lazy[node * 2 + 1] = lazy[node * 2 + 1] ^ lazy[node];
			}
			lazy[node] = 0;
		}
	}

	public static void updateRange(int start, int end, int node, int left, int right, int k) {
		lazyUpdate(start, end, node);
		if (right < start || end < left)
			return;
		if (left <= start && end <= right) {
			int evenCheck = end - start + 1;
			if (evenCheck % 2 != 0) {
				tree[node] = tree[node] ^ k;
			}
			if (start != end) {
				lazy[node * 2] = lazy[node * 2] ^ k;
				lazy[node * 2 + 1] = lazy[node * 2 + 1] ^ k;
			}
			return;
		}
		int mid = (start + end) / 2;
		updateRange(start, mid, node * 2, left, right, k);
		updateRange(mid + 1, end, node * 2 + 1, left, right, k);
		tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
	}

	public static int xor(int start, int end, int node, int left, int right) {
		lazyUpdate(start, end, node);
		if (right < start || end < left)
			return 0;
		if (left <= start && end <= right)
			return tree[node];
		int mid = (start + end) / 2;
		return xor(start, mid, node * 2, left, right) ^ xor(mid + 1, end, node * 2 + 1, left, right);
	}
}