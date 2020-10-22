public class ViolenceMatch {
    public static void main(String[] args) {
        String s = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String s1 = "尚硅谷你尚硅你~";
        System.out.println(violenceMatch(s, s1));
    }
    //暴力匹配算法
    public static int violenceMatch(String str1, String str2){
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        int str1Len = char1.length;
        int str2Len = char2.length;
        int i = 0;
        int j = 0;
        while (i < str1Len && j < str2Len) {
            if (char1[i] == char2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == str2Len) {
            return i - j;
        }
        return -1;
    }
}
