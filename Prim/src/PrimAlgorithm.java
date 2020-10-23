import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A','B', 'C','D','E' ,'F','G'};
        int verxs = data.length;
        int[][] weight = {
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        MGraph mGraph = new MGraph(verxs);
        MiniTree miniTree = new MiniTree();
        miniTree.create(mGraph, verxs, data, weight);
        miniTree.show(mGraph);
        miniTree.prim(mGraph, 1);
    }
}
class MiniTree{
    public void create(MGraph graph, int verxs, char[] data, int[][] weight){
        int i,j;
        for (i = 0; i < verxs; i++) {
            graph.setData(i, data[i]);
            for (j = 0; j < verxs; j++) {
                graph.setWeight(i, j, weight[i][j]);
            }
        }
    }
    public void show(MGraph graph){
        for (int[] link : graph.getWeight()) {
            System.out.println(Arrays.toString(link));
        }
    }
    public void prim(MGraph graph, int v){
        //表示顶点是否被访问过
        int[] visit = new int[graph.getVerxs()];
        visit[v] = 1;
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        //重点理解
        for (int i = 1; i < graph.getVerxs(); i++) {
            for (int j = 0; j < graph.getVerxs(); j++) {
                for (int k = 0; k < graph.getVerxs(); k++) {
                    if (visit[j] == 1 && visit[k] == 0 && graph.getWeight(j, k) < minWeight) {
                        minWeight = graph.getWeight(j, k);
                        h1 = j;
                        h2 = k;
                    }
                }
            }
            System.out.println(graph.getData(h1) + "   " + graph.getData(h2) + "        " + minWeight);
            visit[h2] = 1;
            minWeight = 10000;
        }
    }
}
class MGraph{
    private int verxs;
    private char[] data;
    private int[][] weight;

    public MGraph(int verxs) {
        this.verxs = verxs;
        this.data = new char[verxs];
        this.weight = new int[verxs][verxs];
    }

    public int getVerxs() {
        return verxs;
    }

    public void setVerxs(int verxs) {
        this.verxs = verxs;
    }

    public char getData(int i) {
        return data[i];
    }

    public void setData(int i, char data) {
        this.data[i] = data;
    }

    public int getWeight(int i, int j) {
        return weight[i][j];
    }
    public int[][] getWeight() {
        return weight;
    }

    public void setWeight(int i, int j, int weight) {
        this.weight[i][j] = weight;
    }
}
