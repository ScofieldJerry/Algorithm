import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String s1 = "BBC ABCDAB ABCDABCDABE";
        String s2 = "ABCDABD";
        System.out.println(Arrays.toString(kmpNext("ABCDABD")));
    }
    public static int kmpSearch(String s1, String s2){
        int[] ints = kmpNext(s2);
        for (int i = 0, j = 0; i < s1.length(); i++) {
            //kmp核心点
            while (j > 0 && s1.charAt(i) != s2.charAt(j)) {
                j = ints[j - 1];
            }
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }
            if (j == s2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }
    public static int[] kmpNext(String s){
        int[] next = new int[s.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < s.length(); i++) {
            //核心点
            while (j > 0 && s.charAt(i) != s.charAt(j)){
                j = next[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
