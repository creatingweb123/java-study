import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//1562
public class Dynamic1 {

    static int[][][] countValue;
    static final int remain = 1000000000;

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        countValue = new int[N][10][1 << 10];
        init();
        System.out.println(result(N));
    }

    public static void init() {
        for (int i = 1; i <= 9; i++) {
            countValue[0][i][1 << i] = 1;
        }
    }

    public static int result(int N) {
        for (int i = 1; i < N; i++) {
            plusCount(i, 1, 0);
            for (int j = 1; j <= 8; j++) {
                plusCount(i, j - 1, j);
                plusCount(i, j + 1, j);
            }
            plusCount(i, 8, 9);
        }
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += countValue[N - 1][i][1023];
            result %= remain;
        }
        return result % remain;
    }

    public static void plusCount(int N, int num1, int num2) {
        for (int i = 0; i < (1 << 10); i++) {
            countValue[N][num1][(i | 1 << num1)] += countValue[N - 1][num2][i];
            countValue[N][num1][(i | 1 << num1)] %= remain;
        }
    }
}
