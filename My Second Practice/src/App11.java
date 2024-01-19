import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 백준 9935
public class App11{
	public static boolean bombCheck(StringBuilder result,String second){
		int secondIndex =second.length()-1;
		int resultIndex =result.length()-1;
		while(resultIndex>=0 && secondIndex>=0){
			if(result.charAt(resultIndex)!=second.charAt(secondIndex)){
				return false;
			}
			resultIndex--;
			secondIndex--;
		}
		return secondIndex<0;
	}
	public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String first = bf.readLine();
		String second= bf.readLine();
		StringBuilder result = new StringBuilder();
		boolean check;
		char f;
		char s = second.charAt(second.length()-1);
		int sLen= second.length();

		for(int i=0;i<first.length();i++){
			check=false;
			f = first.charAt(i);
			result.append(f);
			if(f == s){
				check=bombCheck(result, second);
			}
			if(check) result.delete(result.length()-sLen, result.length());
		}
		if(result.length()==0){
			System.out.println("FRULA");
		}else{
			System.out.println(result);
		}


	}

}