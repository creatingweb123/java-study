
// 백준 2243
// segment tree with lazy propagation
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SegmentTree4 {
	public static int[] tree = new int[4000001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == 1) {
				sb.append(search(1, 1000000, 1, b) + "\n");
			} else {
				int c = Integer.parseInt(st.nextToken());
				update(1, 1000000, 1, c, b);
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static void update(int start, int end, int node, int num, int index) {
		if (index < start || index > end)
			return;
		tree[node] += num;
		if (start == end)
			return;
		int mid = (start + end) / 2;
		update(start, mid, node * 2, num, index);
		update(mid + 1, end, node * 2 + 1, num, index);
	}

	public static int search(int start, int end, int node, int value) {
		tree[node] -= 1;
		if (start == end) {
			return start;
		}

		int mid = (start + end) / 2;
		if (value <= tree[node * 2]) {
			return search(start, mid, node * 2, value);
		} else {
			return search(mid + 1, end, node * 2 + 1, value - tree[node * 2]);
		}
	}
}