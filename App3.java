import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class App3 {

    public static class Pair{
        int x,y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static int dfs(int[][] mapList,int[][]visited, Pair s, int fx, int fy){
        int x,y;
        x = s.x;  y=s.y;
        if(x==fx&&y==fy){return 1;}
        
        if(visited[x][y]==-1){
            visited[x][y] = 0;
        }else if(visited[x][y]==0){
            return 0;
        }else{
            return visited[x][y];
        }
        
        if(mapList[x+1][y]<mapList[x][y]){
            visited[x][y] += dfs(mapList,visited,new Pair(x+1,y),fx,fy);
        }if(mapList[x-1][y]<mapList[x][y]){
            visited[x][y] += dfs(mapList,visited,new Pair(x-1,y),fx,fy);
        }if(mapList[x][y+1]<mapList[x][y]){
            visited[x][y] += dfs(mapList,visited,new Pair(x,y+1),fx,fy);
        }if(mapList[x][y-1]<mapList[x][y]){
            visited[x][y] += dfs(mapList,visited,new Pair(x,y-1),fx,fy);
        }
        return visited[x][y];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        String[] size = bf.readLine().split(" ");
        int x = Integer.parseInt(size[0]);
        int y = Integer.parseInt(size[1]);
        String[] row;
        int[][] map  = new int[x+2][y+2];
        int[][]visited= new int[x+2][y+2];
        for(int i=1;i<=x;i++){
            map[i][0]  = 10001;
            map[i][y+1]= 10001;
        }
        for(int i=1;i<=y;i++){
            map[0][i]  = 10001;
            map[x+1][i]= 10001;
        }
        for(int i=1;i<=x;i++){
            row = bf.readLine().split(" ");
            for(int j=1;j<=y;j++){
                visited[i][j] = -1;
                map[i][j] = Integer.parseInt(row[j-1]);
            }
        }
        int value = dfs(map,visited,new Pair(1,1),x,y);
        System.out.println(value);
    }
}
