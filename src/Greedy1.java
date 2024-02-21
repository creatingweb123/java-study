
// 백준 2437
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Greedy1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> array = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(array);
        int sum = 1;
        for (int i = 0; i < array.size(); i++) {
            int value = array.get(i);
            if (sum < value)
                break;
            sum += value;
        }
        System.out.println(sum);
    }
}