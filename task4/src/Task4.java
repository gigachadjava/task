import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input_file>");
            System.exit(1);
        }

        String inputFile = args[0];
        List<Integer> nums = readInput(inputFile);
        if (nums == null) {
            System.err.println("Error reading input file.");
            System.exit(1);
        }

        int result = minMoves(nums);
        System.out.println("Minimum number of moves: " + result);
    }

    private static List<Integer> readInput(String inputFile) {
        List<Integer> nums = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                nums.add(Integer.parseInt(line));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        return nums;
    }

    private static int minMoves(List<Integer> nums) {
        if (nums.size() == 0) return 0;
        int min = Collections.min(nums);
        int max = Collections.max(nums);
        int moves = Integer.MAX_VALUE;

        for (int target = min; target <= max; target++) {
            int currentMoves = 0;
            for (int num : nums) {
                currentMoves += Math.abs(num - target);
            }
            moves = Math.min(moves, currentMoves);
        }
        return moves;
    }
}
