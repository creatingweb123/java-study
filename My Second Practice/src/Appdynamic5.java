import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
// 4195
public class Appdynamic5 {
    public static int[] parents = new int[200000]; 
    public static HashMap<String, Integer> numbers = new HashMap<>();
    public static int number = 0;
    public static void initParent(){
        for(int i=0;i<200000;i++){parents[i] = -1;}
    }
    public static int collapsFind(int target){
        int child  = target;
        int parent = parents[child];
        while(parent>=0){
            child  = parent; 
            parent = parents[child];
        }
        while(parents[target]>=0){
            int temp = parents[target];
            parents[target]=child;
            target = temp;
        }
        return child;
    }
    public static int union(int a, int b){
        int x = collapsFind(a);
        int y = collapsFind(b);
        if(x<y){
            parents[x]+=parents[y];
            parents[y]=x;
            return parents[x];
        }
        else if(x>y){
            parents[y]+=parents[x];
            parents[x]=y;
            return parents[y];
        }
        else{
            return parents[y];
        }
    }
    public static int calculate(String a, String b){
        if(numbers.containsKey(a)==false){numbers.put(a, number++);}
        if(numbers.containsKey(b)==false){numbers.put(b, number++);}
        int number1 = numbers.get(a);
        int number2 = numbers.get(b);
        int result = union(collapsFind(number1),collapsFind(number2));
        return result;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        
        int T = Integer.parseInt(bf.readLine());
        for(int i=0;i<T;i++){
            int N = Integer.parseInt(bf.readLine());
            numbers.clear();
            number = 0;
            initParent();
            for(int j=0;j<N;j++){
                StringTokenizer st = new StringTokenizer(bf.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                System.out.println((-1)*calculate(a, b));
            }
        }


    }
}