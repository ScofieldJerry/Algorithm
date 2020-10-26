import java.util.Arrays;

public class KruskalCase {
    private int edgeNum;
    private char[] vertex;
    private int[][] matrix;
    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = {
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0},
        };
        KruskalCase kruskalCase = new KruskalCase(vertex,matrix);
//        kruskalCase.print();
//        EData[] edges = kruskalCase.getEdges();
//        kruskalCase.sortEdge(edges);
//        System.out.println(Arrays.toString(edges));
        kruskalCase.kruskal();
    }
    private KruskalCase(char[] vertex, int[][] matrix){
        this.vertex = vertex;
        this.matrix = matrix;
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    this.edgeNum ++;
                }
            }
        }
    }
    public void print(){
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%20d",matrix[i][j]);
            }
            System.out.println();
        }
    }
    private void sortEdge(EData[] eData){
        for (int i = 0; i < eData.length - 1; i++) {
            for (int j = 0; j < eData.length - 1 - i; j++) {
                if (eData[j].getWeight() > eData[j + 1].getWeight()) {
                    EData temp = eData[j];
                    eData[j] = eData[j + 1];
                    eData[j + 1] = temp;
                }
            }
        }
    }
    private int getPosition(char ch){
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == ch) {
                return i;
            }
        }
        return -1;
    }
    private EData[] getEdges(){
        int index = 0;
        EData[] eData = new EData[edgeNum];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    eData[index++] = new EData(vertex[i],vertex[j],matrix[i][j]);
                }
            }
        }
        return eData;
    }
    private int getEnd(int[] ends, int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }
    private void kruskal(){
        int index = 0;
        int[] ends = new int[this.edgeNum];
        EData[] result = new EData[this.vertex.length - 1];
        EData[] eData = getEdges();
        sortEdge(eData);
        for (int i = 0; i < edgeNum; i++) {
            int p1 = getPosition(eData[i].getStart());
            int p2 = getPosition(eData[i].getEnd());
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            if (m != n) {
                ends[m] = n;
                result[index++] = eData[i];
            }
        }
        System.out.println("最小生成树"+Arrays.toString(result));;
    }
}
class EData{
    private char start;
    private char end;
    private int weight;
    public EData(char start, char end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    public char getStart() {
        return start;
    }

    public void setStart(char start) {
        this.start = start;
    }

    public char getEnd() {
        return end;
    }

    public void setEnd(char end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
