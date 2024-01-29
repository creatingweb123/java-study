import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 1976
public class Appdynamic6 {
    public static int[] parents = new int[500000]; 
    public static void initParent(int N){
        for(int i=0;i<N;i++){parents[i] = i;}
    }
    public static int collapsFind(int target){
        int parent = parents[target];
        while(parent!=parents[parent]){
            parent = parents[parent];
        }
        while(target!=parents[target]){
            int temp = parents[target];
            parents[target]=parent;
            target = temp;
        }
        return parent;
    }
    public static boolean union(int a, int b){
        int x = collapsFind(a);
        int y = collapsFind(b);
        if(x<y){parents[y]=x;}
        else if(x>y){parents[x]=y;}
        else{return true;}
        return false;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        initParent(N);
        boolean answer = true;
        for(int i=1;i<=M;i++){
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(answer){
                boolean check=union(a, b);
                if(check){
                    answer = false;
                    System.out.println(i);
                }
            }
        }
        if(answer){
            System.out.println(0);
        }
    }
}