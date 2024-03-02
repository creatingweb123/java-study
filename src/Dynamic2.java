import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1328
public class Dynamic2 {

    static long[][][] dp;
    static final int remain = 1000000007;

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        dp = new long[N + 1][N + 1][N + 1];
        dp[1][1][1] = 1;
        for (int n = 2; n <= N; n++) {
            dp[n][n][1] = dp[n][1][n] = 1;
            for (int i = 1; i <= n && i <= L; i++) {
                for (int j = 1; j <= n && j <= R; j++) {
                    dp[n][i][j] = discriminant(n, i, j);
                }
            }
        }
        System.out.println(dp[N][L][R]);
    }

    public static long discriminant(int N, int L, int R) {
        return (dp[N - 1][L - 1][R] + dp[N - 1][L][R - 1] + (N - 2) * dp[N - 1][L][R]) % remain;
    }
}
