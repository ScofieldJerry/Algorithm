import java.util.Arrays;

public class FloydAlgorithm {
    public static void main(String[] args) {
        final int N = 65535;
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0,5,7,N,N,N,2},
                {5,0,N,9,N,N,3},
                {7,N,0,N,8,N,N},
                {N,9,N,0,N,4,N},
                {N,N,8,N,0,5,4},
                {N,N,N,4,5,0,6},
                {2,3,N,N,4,6,0}
        };
        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show();
    }
}
class Graph{
    private char[] vertex;
    private int[][] dis;
    private int[][] pre;
    public Graph(int length, int[][] matrix, char[] vertex){
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        for (int i = 0; i < this.pre.length; i++) {
            Arrays.fill(this.pre[i], i);
        }
    }
    public void show(){
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]]+ " ");
            }
            System.out.println();
            for (int j = 0; j < dis.length; j++) {
                System.out.print("（"+vertex[i]+"到"+vertex[j]+"的最短路径"+dis[i][j]+ "） ");
            }
            System.out.println();
            System.out.println();
        }
    }
    public void floyd(){
        int len = 0;
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                for (int k = 0; k < dis.length; k++) {
                    len = dis[i][j] + dis[i][k];
                    if (dis[j][k] > len) {
                        dis[j][k] = len;
                        pre[j][k] = pre[i][k];
                    }
                }
            }
        }
    }
}