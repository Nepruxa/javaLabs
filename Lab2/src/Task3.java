import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Task3 {

    public static String getWinner(String[] clearInput) {
        Map<Integer, String> tree = new TreeMap<>(Collections.reverseOrder());
        for (String cont : clearInput) {
            String[] contAndScore = cont.trim().split(" ");
            tree.putIfAbsent(Integer.parseInt(contAndScore[1]), contAndScore[0]);
        }
        return tree.get(tree.keySet().toArray()[0]);
    }

    public static void runTask3(String input) {
        String[] clearInput = input.replaceAll("\"", "").trim().split(",");
        System.out.println("Input: " + input);
        System.out.println("Winner: " + getWinner(clearInput));
    }
}