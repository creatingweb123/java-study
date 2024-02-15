// 백준 10986

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App12{
	
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] inputS = bf.readLine().split(" ");
		int N = Integer.parseInt(inputS[0]);
		int M = Integer.parseInt(inputS[1]);
		long[] sumArr = new long[N+1];
		long result = 0;
		inputS = bf.readLine().split(" ");
		long[] remain = new long[M];
		for(int i=0;i<N;i++){
			sumArr[i+1]=sumArr[i]+Integer.parseInt(inputS[i]);
			int index = (int)(sumArr[i+1]%M);
			remain[index] += 1;
		}
		result += remain[0];
		for(int i=0;i<M;i++){
			if(remain[i]>1){
				result += remain[i]*(remain[i]-1)/2;
			}
		}

		System.out.println(result);
	}

}