import java.util.*;

public class Task1 {

    public static <T> Collection<T> createCollection() {
        List<Integer> startCollection = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Random random = new Random();
            int num = 3 + random.nextInt(10);
            startCollection.add(num);
        }
        System.out.println("Start collection: " + startCollection);
        return (Collection<T>) startCollection;
    }

    public static <T> Collection<T> removeDuplicates(Collection<T> startCollection) {
        Collection<T> result = new HashSet<>(startCollection);
        return result;
    }

    public static  void runTask1() {
        System.out.println("Result collection: " + removeDuplicates(createCollection()));
    }
}




