import java.util.Arrays;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A','B', 'C','D','E' ,'F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertex, matrix);
//        graph.show();
        graph.dsj(2);
        graph.showDJS();
    }
}
class Graph{
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex vv;
    public Graph(char[] vertex, int[][] matrix){
        this.vertex = vertex;
        this.matrix = matrix;
    }
    public void show(){
        for (int[] temp : this.matrix) {
            System.out.println(Arrays.toString(temp));
        }
    }

    /**
     * 表示出发顶点对应的下标
     * @param index
     */
    public void dsj(int index){
        this.vv = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArr();
            update(index);
        }
    }

    /**
     * 更新index下标节点到周围节点的距离和周围节点的前驱节点
     * @param index
     */
    private void update(int index){
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            len = this.vv.getDis(index) + matrix[index][i];
            if (!this.vv.in(i) && len < this.vv.getDis(i)) {
                this.vv.updatePre(i, index);
                this.vv.updateDis(i, len);
            }
        }
    }
    public void showDJS(){
        vv.show();
    }
}
//已访问顶点集合
class VisitedVertex{
    //记录各个顶点是否访问过 1表示访问过，0表示未访问，会动态更新
    public int[] alreadyArr;
    //每个下标对应的值为前一个顶点下标，会动态更新
    public int[] preVisited;
    //记录出发顶点到其他所有顶点的距离，比如g为出发顶点，就会记录g到其他顶点的距离，会动态更新，求最短的距离就会存放到dis
    public int[] dis;

    /**
     *
     * @param length 表示顶点的个数
     * @param index 出发顶点对应的下标，比如g，下标就是6
     */
    public VisitedVertex(int length, int index){
        this.alreadyArr = new int[length];
        this.preVisited = new int[length];
        this.dis = new int[length];
        Arrays.fill(dis, 65535);
        this.alreadyArr[index] = 1;
        this.dis[index] = 0;
    }

    /**
     * 判断index下标的顶点是否访问过
     * @param index
     * @return
     */
    public boolean in(int index){
        return alreadyArr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     * @param index
     * @param len
     */
    public void updateDis(int index, int len){
        dis[index] = len;
    }

    /**
     * 更新pre顶点的前驱为index节点
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index){
        preVisited[pre] = index;
    }

    /**
     * 返回出发顶点到index节点的距离
     * @param index
     */
    public int getDis(int index){
        return dis[index];
    }
    public int updateArr(){
        int min = 65535;
        int index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        alreadyArr[index] = 1;
        return index;
    }

    public void show(){
        System.out.println("============================");
        for (int i : alreadyArr) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : preVisited) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : dis) {
            System.out.print(i + " ");
        }
    }
}
