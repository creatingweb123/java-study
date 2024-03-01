import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Dynamic {

    static String inputString;
    static boolean[][] palindrome;
    static int[] minValue;
    static int N;

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputString = br.readLine();
        N = inputString.length();
        inputString = "." + inputString;

        palindrome = new boolean[N + 1][N + 1];
        checkPalindrome(inputString);
        minValue = new int[N + 1];

        Arrays.fill(minValue, Integer.MAX_VALUE);
        minValue[0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                if (palindrome[j][i]) {
                    minValue[i] = Math.min(minValue[i], minValue[j - 1] + 1);
                }
            }
        }
        System.out.println(minValue[N]);
    }

    public static void checkPalindrome(String str) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                boolean flag = true;
                int from = j;
                int to = i;
                while (from <= to) {
                    if (str.charAt(from++) != str.charAt(to--)) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    palindrome[j][i] = true;
            }
        }
    }
}
