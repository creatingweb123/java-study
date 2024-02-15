
import java.util.Scanner;
import java.util.Stack;

// 2493
public class App {
    public static int[] findNGE(int[] A) {
        int[] NGE = new int[A.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < A.length; i++) {
            while (!stack.isEmpty() && A[i] > A[stack.peek()]) {
                int index = stack.pop();
                NGE[index] = A[i];
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            NGE[index] = -1;
        }
        return NGE;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] A1 = new int[n];
        StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < n; i++) {
			A1[i] = in.nextInt();
		}
        int[] NGE1 = findNGE(A1);
        for (int i : NGE1) {
            sb.append(i + " ");
        }
        System.out.print(sb);

        in.close();
    }
}

