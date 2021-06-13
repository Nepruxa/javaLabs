import java.util.*;

public class Task2 {
    private static <K, V> Map<V, Collection<K>> invertMap(Map<K, V> inMap) {
        Map<V, Collection<K>> resultMap = new HashMap<>();
        for (Map.Entry<K, V> e : inMap.entrySet()) {
            V sValue = e.getValue();
            K sKey = e.getKey();
            Set<V> values = (Set<V>) resultMap.getOrDefault(sValue, new HashSet<>());
            values.add((V) sKey);
            resultMap.put(sValue, (Collection<K>) values);
        }
        return resultMap;
    }

    public static <K, V> Map<V, K> startMap(int len) {
        Map<String, Integer> startMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            Random randomiser = new Random();
            startMap.put("k" + (randomiser.nextInt(len)), randomiser.nextInt(len));
        }
        return (Map<V, K>) startMap;
    }

    public static void runTask2() {
        Map<String, Integer> startMap = startMap(7);
        System.out.println("Start Map: " + startMap);
        System.out.println("Result Map: " + invertMap(startMap));
    }
}



