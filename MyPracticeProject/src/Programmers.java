import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 프로그래머스 1차 예선
// 비밀코드 해독
class Programmers {
    private List<Set<Integer>> result = new ArrayList<>();
    private int[] now = new int[5];

    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        combination(1, n, 5);

        for (Set<Integer> code : result) {
            boolean match = true;
            for (int i = 0; i < q.length; i++) {
                int count = 0;
                for (int num : q[i]) {
                    if (code.contains(num)) {
                        count++;
                    }
                }
                if (count != ans[i]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                answer++;
            }
        }

        return answer;
    }

    private void combination(int start, int n, int r) {
        if (r == 0) {
            Set<Integer> combo = new HashSet<>();
            for (int i = 0; i < 5; i++) {
                combo.add(now[i]);
            }
            result.add(combo);
            return;
        }

        for (int i = start; i <= n; i++) {
            now[r - 1] = i;
            combination(i + 1, n, r - 1);
        }
    }
}