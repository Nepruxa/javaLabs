import java.util.*;

public class Task2 {

    public static <K, V> Map<V, K> createMap(int len) {
        Map<String, Integer> startMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            Random randomiser = new Random();
            startMap.put("k" + (randomiser.nextInt(len)), randomiser.nextInt(len));
        }
        System.out.println("Start Map: " + startMap);
        return (Map<V, K>) startMap;
    }

    private static <K, V> Map<V, Collection<K>> invertMap(Map<K, V> startMap) {
        Map<V, Collection<K>> resultMap = new HashMap<>();
        for (Map.Entry<K, V> e : startMap.entrySet()) {
            V sValue = e.getValue();
            K sKey = e.getKey();
            Set<V> values = (Set<V>) resultMap.getOrDefault(sValue, new HashSet<>());
            values.add((V) sKey);
            resultMap.put(sValue, (Collection<K>) values);
        }
        return resultMap;
    }

    public static void runTask2() {
        System.out.println("Result Map: " + invertMap(createMap(10)));
    }
}



