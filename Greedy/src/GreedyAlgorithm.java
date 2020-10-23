import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> hashMap = new HashMap<>();
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("北京");
        hashSet.add("上海");
        hashSet.add("天津");
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("广州");
        hashSet1.add("北京");
        hashSet1.add("深圳");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("成都");
        hashSet2.add("上海");
        hashSet2.add("杭州");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("上海");
        hashSet3.add("天津");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("杭州");
        hashSet4.add("大连");
        hashMap.put("k1",hashSet);
        hashMap.put("k2",hashSet1);
        hashMap.put("k3",hashSet2);
        hashMap.put("k4",hashSet3);
        hashMap.put("k5",hashSet4);
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        ArrayList<String> select = new ArrayList<>();
        HashSet<String> tempKey = new HashSet<>();
        String maxKey = null;
        while (allAreas.size() != 0){
            maxKey = null;
            for (String key:hashMap.keySet()) {
                tempKey.clear();
                HashSet<String> areas = hashMap.get(key);
                tempKey.addAll(areas);
                tempKey.retainAll(allAreas);
                //体现出贪心算法的特点，每次都选最优的解
                if (tempKey.size() > 0 && (maxKey == null || tempKey.size() > hashMap.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                select.add(maxKey);
                allAreas.removeAll(hashMap.get(maxKey));
            }
        }
        System.out.println(select);
    }
}
