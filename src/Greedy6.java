import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

//1014
public class Greedy5 {
    static int MAX_X;
    static int MAX_Y;
    static int cnt;

    static boolean[][] arr;
    static int[][] dp;
    static int[] dx = { 0, -1, -1, 0 };
    static int[] dy = { -1, -1, 1, 1 };

    static class SeatBit {
        int seatbit;
        int count;

        public SeatBit(int seatbit, int count) {
            this.seatbit = seatbit;
            this.count = count;
        }
    }

    static boolean outrange(int x, int y) {
        return x < 0 || x >= MAX_X || y < 0 || y >= MAX_Y;
    }

    static boolean adjCheck(int bit) {
        for (int i = 0; i < MAX_Y - 1; i++) {
            int val = (3 << i);
            if ((bit & val) == val)
                return true;
        }
        return false;
    }

    static boolean bitCheck(int bBit, int cBit) {
        int tmp = (bBit | cBit);
        return adjCheck(tmp);
    }

    static boolean isPossible(int bit, int x) {
        for (int i = 0; i < MAX_Y; i++) {
            if (arr[x][i] && (bit & (1 << i)) >= 1)
                return false;
        }
        return true;
    }

    static int solve() {
        List<SeatBit> seatBitList = new ArrayList<>();
        int ans = 0;
        for (int seastBit = 0; seastBit < (1 << MAX_Y); seastBit++) {
            if (adjCheck(seastBit))
                continue;
            int cnt = 0;
            for (int i = 0; i < MAX_Y; i++) {
                if (((1 << i) & seastBit) >= 1)
                    cnt += 1;
            }
            seatBitList.add(new SeatBit(seastBit, cnt));
        }
        for (int i = 1; i <= MAX_X; i++) {
            for (SeatBit cur : seatBitList) {
                if (!isPossible(cur.seatbit, i - 1))
                    continue;
                for (SeatBit before : seatBitList) {
                    if (!bitCheck(before.seatbit, cur.seatbit)) {
                        dp[i][cur.seatbit] = Math.max(dp[i][cur.seatbit], dp[i - 1][before.seatbit] + cur.count);
                        ans = Math.max(ans, dp[i][cur.seatbit]);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));// 선언

        int testCase = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            MAX_X = Integer.parseInt(st.nextToken());
            MAX_Y = Integer.parseInt(st.nextToken());
            arr = new boolean[MAX_X][MAX_Y];
            dp = new int[MAX_X + 1][1 << MAX_Y];
            for (int i = 0; i < MAX_X; i++) {
                String s = br.readLine();
                for (int j = 0; j < MAX_Y; j++) {
                    char c = s.charAt(j);
                    arr[i][j] = c == 'x';
                }
            }
            int ans = solve();

            sb.append(ans + "\n");
        }
        bw.write(sb.toString());// 출력
        bw.flush();// 남아있는 데이터를 모두 출력시킴
        bw.close();// 스트림을 닫음
    }

}
