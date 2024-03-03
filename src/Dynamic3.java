import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2618
public class Dynamic3 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Pair first, second;
    static StringBuilder sb = new StringBuilder();
    static int[][] dp;
    static Pair[] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int W = Integer.parseInt(br.readLine());
        dp = new int[1002][1002];
        data = new Pair[1002];
        first = new Pair(1, 1);
        second = new Pair(N, N);

        for (int i = 1; i <= W; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            data[i] = new Pair(x, y);
        }

        sb.append(dynamic(first, second, 0, 0, 1, W)).append("\n");
        int firstIdx = 0, sIdx = 0;

        for (int i = 1; i <= W; i++) {
            int firstDistance = calculate(first, data[i]);
            if (dp[i][sIdx] + firstDistance == dp[firstIdx][sIdx]) {
                sb.append(1);
                first = data[i];
                firstIdx = i;
            } else {
                sb.append(2);
                second = data[i];
                sIdx = i;
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int dynamic(Pair first, Pair second, int firstNum, int secondNum, int num, int last) {
        if (num > last || dp[firstNum][secondNum] != 0)
            return 0;

        Pair next = data[num];
        int firstValue = calculate(first, next) + dynamic(next, second, num, secondNum, num + 1, last);
        int secondValue = calculate(second, next) + dynamic(first, next, firstNum, num, num + 1, last);
        return dp[firstNum][secondNum] = Math.min(firstValue, secondValue);
    }

    public static int calculate(Pair now, Pair before) {
        return Math.abs(now.x - before.x) + Math.abs(now.y - before.y);
    }
}
