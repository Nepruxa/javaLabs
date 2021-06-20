import java.util.HashMap;

public class Task3 {

    public static String getWinner(String[] clearInput) {
        HashMap<String, Integer> board = new HashMap<>();
        String result ="";
        int firstMax = 0;
        for (String input : clearInput) {
            String[] players = input.trim().split(" ");
            int score = Integer.parseInt(players[1]);
            int next = board.getOrDefault(players[0], 0) + score;
            if (next > firstMax) {
                firstMax = next;
                result = players[0] + " " + firstMax;
            }
            board.put(players[0], next);
        }
        return result;
    }

    public static void runTask3(String input) {
        String[] clearInput = input.replaceAll("\"", "").trim().split(",");
        System.out.println("Input: " + input);
        System.out.println("Winner: " + getWinner(clearInput));
    }
}