import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//1102
public class Bitmasking1 {
    static final int MAX_VALUE = Integer.MAX_VALUE;
    static int[][] datas;
    static int[][] dp;
    static int N, maxCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        datas = new int[N][N];
        dp = new int[N + 1][1 << N];
        StringTokenizer st;
        // 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                datas[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        String state = br.readLine();
        maxCount = Integer.parseInt(br.readLine());
        int cnt = 0;
        // 처음 상태(이진수)
        int firstState = 0;
        for (int i = 0; i < N; i++) {
            if (state.charAt(i) == 'Y') {
                firstState = firstState | (1 << i);
                cnt += 1;
            }
        }
        for (int i = cnt; i <= N; i++) {
            Arrays.fill(dp[i], MAX_VALUE);
        }
        int result = dynamic(cnt, firstState);
        System.out.println(result == MAX_VALUE ? -1 : result);
    }

    static int dynamic(int cnt, int state) {
        if (cnt >= maxCount)
            return 0;
        if (dp[cnt][state] != MAX_VALUE)
            return dp[cnt][state];
        for (int i = 0; i < N; i++) {
            if ((state & (1 << i)) != 0)
                for (int j = 0; j < N; j++) {
                    if (i == j || (state & (1 << j)) != 0)
                        continue;
                    int nextState = state | 1 << j;
                    dp[cnt][state] = Math.min(dp[cnt][state], dynamic(cnt + 1, nextState) + datas[i][j]);
                }
        }
        return dp[cnt][state];
    }

}
