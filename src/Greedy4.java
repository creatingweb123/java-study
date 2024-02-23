import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Greedy4 {
    static final int MAX = 10;
    static final int INF = (int) 1e9;

    static boolean[][] arr = new boolean[15][15];
    static boolean[][] tmp_arr = new boolean[15][15];
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int ans = INF;

    static boolean outrange(int x, int y) {
        return x < 0 || x >= MAX || y < 0 || y >= MAX;
    }

    static void toggle(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!outrange(nx, ny))
                tmp_arr[nx][ny] = !tmp_arr[nx][ny];
        }
        tmp_arr[x][y] = !tmp_arr[x][y];
    }

    static void init() {
        for (int i = 0; i < MAX; i++)
            System.arraycopy(arr[i], 0, tmp_arr[i], 0, MAX);
    }

    static boolean islight() {
        for (int i = 0; i < MAX; i++)
            for (int j = 0; j < MAX; j++)
                if (tmp_arr[i][j])
                    return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < MAX; i++) {
            String s = br.readLine();
            for (int j = 0; j < MAX; j++) {
                char c = s.charAt(j);
                arr[i][j] = c == 'O';
            }
        }

        for (int step = 0; step < (1 << MAX); step++) {
            int cnt = 0;
            init();

            for (int bit = 0; bit < MAX; bit++) {
                if ((step & (1 << bit)) != 0) {
                    cnt++;
                    toggle(0, bit);
                }
            }

            for (int x = 1; x < MAX; x++) {
                for (int y = 0; y < MAX; y++) {
                    if (tmp_arr[x - 1][y]) {
                        toggle(x, y);
                        cnt++;
                    }
                }
            }

            if (!islight())
                ans = Math.min(cnt, ans);
        }

        if (ans == INF)
            System.out.println(-1);
        else
            System.out.println(ans);
    }
}
