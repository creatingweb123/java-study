
// 백준 1931
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Greedy {

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Pair> array = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            array.add(new Pair(x, y));
        }
        Collections.sort(array, (a, b) -> {
            return a.y == b.y ? a.x - b.x : a.y - b.y;
        });
        int end = 0;
        int result = 0;
        for (int i = 0; i < N; i++) {
            Pair tmp = array.get(i);
            if (end <= tmp.x) {
                end = tmp.y;
                result += 1;
            }
        }
        System.out.println(result);

    }
}
