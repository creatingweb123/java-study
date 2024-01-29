import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 1976
public class Appdynamic4 {
    public static int[] parents = new int[201]; 
    public static void initParent(int N){
        for(int i=1;i<=N;i++){parents[i] = i;}
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
    public static void union(int a, int b){
        int x = collapsFind(a);
        int y = collapsFind(b);
        if(x<y){parents[y]=x;}
        else if(x>y){parents[x]=y;}
    }
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        
        int N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());

        initParent(N);
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i=1;i<=N;i++){
            for(int j=1;j<=i;j++){st.nextToken();}

            for(int j=i+1;j<=N;j++){
                int connect = Integer.parseInt(st.nextToken());
                if(connect==1){union(i, j);}
            }
            st = new StringTokenizer(bf.readLine());
        }
        int target = parents[Integer.parseInt(st.nextToken())];
        int parent = collapsFind(target);
        boolean check = true;
        for(int i=1;i<M;i++){
            target = parents[Integer.parseInt(st.nextToken())];
            if(parent!=collapsFind(target)){check=false; break;}
        } 
        if(check){System.out.println("YES");}
        else{System.out.println("NO");}
    }
}