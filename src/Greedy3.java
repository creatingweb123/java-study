
// 백준 16496
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Greedy3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<String>[] numbers = new ArrayList[10];
        boolean notPrinted = true;
        for (int i = 0; i < 10; i++) {
            numbers[i] = new ArrayList<String>();
        }
        for (int i = 0; i < N; i++) {
            String number = st.nextToken();
            int numberIndex = number.charAt(0) - '0';
            numbers[numberIndex].add(number);
        }
        for (int i = 1; i < 10; i++) {
            if (numbers[i].size() != 0) {
                sortList(numbers[i]);
            }
        }
        for (int i = 9; i >= 1; i--) {
            for (int j = 0; j < numbers[i].size(); j++) {
                System.out.print(numbers[i].get(j));
                notPrinted = false;
            }
        }
        if (notPrinted) {
            System.out.println(0);
        } else {
            for (int j = 0; j < numbers[0].size(); j++) {
                System.out.print(numbers[0].get(j));
            }
        }
    }

    public static void sortList(List<String> numbers) {
        Collections.sort(numbers, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int fIndex = 1;
                int sIndex = 1;
                int s1Index = s1.length() - 1;
                int s2Index = s2.length() - 1;
                char fC, sC;
                if (s1Index < s2Index) {
                    for (; fIndex <= s1Index; fIndex += 1, sIndex += 1) {
                        fC = s1.charAt(fIndex);
                        sC = s2.charAt(sIndex);
                        if (sC > fC) {
                            return 1;
                        } else if (sC < fC) {
                            return -1;
                        }
                    }
                    fIndex = 0;
                    for (; sIndex <= s2Index; fIndex += 1, sIndex += 1) {
                        fC = s2.charAt(fIndex);
                        sC = s2.charAt(sIndex);
                        if (sC > fC) {
                            return 1;
                        } else if (sC < fC) {
                            return -1;
                        }
                    }
                    sIndex = 0;
                    for (; fIndex <= s1Index; fIndex += 1, sIndex += 1) {
                        fC = s2.charAt(fIndex);
                        sC = s1.charAt(sIndex);
                        if (sC > fC) {
                            return 1;
                        } else if (sC < fC) {
                            return -1;
                        }
                    }
                    return -1;
                } else if (s1Index > s2Index) {
                    for (; sIndex <= s2Index; fIndex += 1, sIndex += 1) {
                        fC = s1.charAt(fIndex);
                        sC = s2.charAt(sIndex);
                        if (sC > fC) {
                            return 1;
                        } else if (sC < fC) {
                            return -1;
                        }
                    }
                    sIndex = 0;
                    for (; fIndex <= s1Index; fIndex += 1, sIndex += 1) {
                        fC = s1.charAt(fIndex);
                        sC = s1.charAt(sIndex);
                        if (sC > fC) {
                            return 1;
                        } else if (sC < fC) {
                            return -1;
                        }
                    }
                    fIndex = 0;
                    for (; fIndex <= s2Index; fIndex += 1, sIndex += 1) {
                        fC = s2.charAt(fIndex);
                        sC = s1.charAt(sIndex);
                        if (sC > fC) {
                            return 1;
                        } else if (sC < fC) {
                            return -1;
                        }
                    }
                    return -1;
                } else {
                    for (; fIndex <= s1Index; fIndex += 1, sIndex += 1) {
                        fC = s1.charAt(fIndex);
                        sC = s2.charAt(sIndex);
                        if (sC > fC) {
                            return 1;
                        } else if (sC < fC) {
                            return -1;
                        }
                    }
                    return -1;
                }
            }
        });
    }

}