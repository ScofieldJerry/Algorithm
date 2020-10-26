import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A','B', 'C','D','E' ,'F','G'};
        int vertex = data.length;
        int[][] weight = {
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        MGraph mGraph = new MGraph(vertex);
        MiniTree miniTree = new MiniTree();
        miniTree.create(mGraph, vertex, data, weight);
        miniTree.show(mGraph);
        miniTree.prim(mGraph, 1);
    }
}
class MiniTree{
    public void create(MGraph graph, int vertex, char[] data, int[][] weight){
        int i,j;
        for (i = 0; i < vertex; i++) {
            graph.setData(i, data[i]);
            for (j = 0; j < vertex; j++) {
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
        int[] visit = new int[graph.getVertex()];
        visit[v] = 1;
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        //重点理解
        for (int i = 1; i < graph.getVertex(); i++) {
            for (int j = 0; j < graph.getVertex(); j++) {
                for (int k = 0; k < graph.getVertex(); k++) {
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
    private int vertex;
    private char[] data;
    private int[][] weight;

    public MGraph(int vertex) {
        this.vertex = vertex;
        this.data = new char[vertex];
        this.weight = new int[vertex][vertex];
    }

    public int getVertex() {
        return vertex;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
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
