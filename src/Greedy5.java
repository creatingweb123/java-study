import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Greedy5 {
    static int MAX;
    static final int INF = (int) 1e9;

    static boolean[][] arr;
    static boolean[][] tmp_arr;
    static int ans = INF;

    static boolean outrange(int x, int y) {
        return x < 0 || x >= MAX || y < 0 || y >= MAX;
    }

    static void toggle(int y) {
        for (int i = 0; i < MAX; i++) {
            tmp_arr[i][y] = !tmp_arr[i][y];
        }
    }

    static int countValue() {
        int result = 0;
        for (int i = 0; i < MAX; i++) {
            int tmp = 0;
            for (int j = 0; j < MAX; j++) {
                if (tmp_arr[i][j])
                    tmp += 1;
            }
            result += (tmp > MAX - tmp) ? MAX - tmp : tmp;
        }
        return result;
    }

    static void init() {
        for (int i = 0; i < MAX; i++)
            System.arraycopy(arr[i], 0, tmp_arr[i], 0, MAX);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MAX = Integer.parseInt(br.readLine());
        arr = new boolean[MAX][MAX];
        tmp_arr = new boolean[MAX][MAX];

        for (int i = 0; i < MAX; i++) {
            String s = br.readLine();
            for (int j = 0; j < MAX; j++) {
                char c = s.charAt(j);
                arr[i][j] = c == 'T';
            }
        }

        for (int step = 0; step < (1 << MAX); step++) {
            int cnt = 0;
            init();

            for (int bit = 0; bit < MAX; bit++) {
                if ((step & (1 << bit)) != 0) {
                    toggle(bit);
                }
            }
            cnt += countValue();
            ans = Math.min(cnt, ans);
        }
        System.out.println(ans);
    }
}
