import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 2565
public class App9 {
	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		int[][]dic=new int[n][2];
		int[] dp = new int[501];
		
		for(int i = 0; i < n; i++){
			String[]s= bf.readLine().split(" ");
			dic[i][0]=Integer.parseInt(s[0]); 
            dic[i][1]=Integer.parseInt(s[1]);
		}
		Arrays.sort(dic,(o1,o2)->{
            return o1[0]-o2[0];
        });
		//전깃줄의 총 개수를 구하기 위해 사용
		int count = 0;
		
		for(int i=0;i<dic.length;i++ ){
            dp[i] = 1;
			count++;
			for(int j=0;j<i;j++){
				if(dic[i][1] > dic[j][1]){
                        // 설치 가능한 최대 개수 찾기
                    dp[i] = dp[i]> dp[j] + 1?dp[i]: dp[j] + 1;
					}
			}
		}
		int mx = 0;
		for(int i = 0; i < 501; i++){
			mx = mx> dp[i]?mx: dp[i];
		}
		//전깃줄 개수에서 최장 증가 부분 수열을 빼면 없애야하는 전깃줄의 최소개수가 나온다.
        System.out.println(count - mx);
	}
}