import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessBoard {
    private static int X;//棋盘的列数
    private static int Y;//棋盘的行数
    private static boolean[] visited;
    private static boolean finish;
    private static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }
    public static void traver(int[][] chessBoard, int row, int column, int step){
        chessBoard[row][column] = step;
        visited[row * X + column] = true;
        ArrayList<Point> ps = next(new Point(column, row));
        sort(ps);
        while (!ps.isEmpty()){
            Point remove = ps.remove(0);
            if (visited[remove.y * X + remove.x] == false) {
                traver(chessBoard, remove.y, remove.x, step + 1);
            }
        }
        if (step < X * Y && !finish) {
            chessBoard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finish = true;
        }
    }
    public static void main(String[] args) {
        System.out.println("++++++++++++");
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        int[][] chess = new int[X][Y];
        visited = new boolean[X * Y];
        long l = System.currentTimeMillis();
        traver(chess, row - 1, column - 1, 1);
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);
        for (int[] rows : chess) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int size = next(o1).size();
                int size1 = next(o2).size();
                if (size1 > size) {
                    return -1;
                } else if (size == size1){
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}
