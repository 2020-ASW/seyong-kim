package _2월2주차;

import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);

            String key = String.valueOf(chs);

            if (!map.containsKey(key)) map.put(key, new ArrayList<>());

            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
