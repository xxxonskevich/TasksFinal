import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Math.sqrt;

public class Main {
    public static void main(String[] args) {
        //в терминале
        //javac Main.java
        //java Main file1.txt file2.txt
        if (args.length != 2) {
            System.out.println("Usage: java Main file1.txt file2.txt");
            return;
        }

        float x0, y0, r;

        try (BufferedReader circleReader = new BufferedReader(new FileReader(args[0]));
             BufferedReader pointsReader = new BufferedReader(new FileReader(args[1]))) {

            String line = circleReader.readLine();
            String[] parts = line.trim().split(" ");
            x0 = Float.parseFloat(parts[0]);
            y0 = Float.parseFloat(parts[1]);

            line = circleReader.readLine().trim();
            r = Float.parseFloat(line);

            while ((line = pointsReader.readLine()) != null) {
                String[] kor = line.trim().split(" ");
                float x = Float.parseFloat(kor[0]);
                float y = Float.parseFloat(kor[1]);

                double ur = sqrt(((x - x0) * (x - x0) + (y - y0) * (y - y0)));

                if (ur < r) {
                    System.out.println("1");
                } else if (ur > r) {
                    System.out.println("2");
                } else {
                    System.out.println("0");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
