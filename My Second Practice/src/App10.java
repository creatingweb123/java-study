import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 백준 9251
public class App10{
	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String first = bf.readLine();
		String second= bf.readLine();
		int firstLen = first.length();
		int secondLen= second.length();

		int[][] arr = new int[secondLen+1][firstLen+1];
		for(int i=1;i<=firstLen;i++){
			for(int j=1;j<=secondLen;j++){
				if(first.charAt(i-1)==second.charAt(j-1)){
					arr[j][i] =arr[j-1][i-1]+1;
				}else{
					arr[j][i] = Math.max(arr[j][i-1],arr[j-1][i]);
				}
			}
		}
		System.out.println(arr[secondLen][firstLen]);

	}

}