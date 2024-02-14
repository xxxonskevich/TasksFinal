import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //в терминале
        // cd src
        // javac Main.java
        // java Main numbers.txt
        if (args.length != 1) {
            System.out.println("Usage: java Main numbers.txt");
            return;
        }

        try {
            int[] nums = readArrayFromFile(args[0]);
            int minMoves = minMoves(nums);
            System.out.println(minMoves);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] readArrayFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        String[] values = line.split(" ");
        int[] nums = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            nums[i] = Integer.parseInt(values[i]);
        }
        reader.close();
        return nums;
    }

    private static int minMoves(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int median;
        if (n % 2 == 0) {
            // Четное количество элементов
            int middle1 = nums[n / 2 - 1];
            int middle2 = nums[n / 2];
            median = (middle1 + middle2) / 2;
        } else {
            // Нечетное количество элементов
            median = nums[n / 2];
        }

        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }

        return moves;
    }
}
