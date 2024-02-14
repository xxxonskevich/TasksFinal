public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Main n and m");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int[] circularArray = new int[n];
        for (int i = 1; i <= n; i++) {
            circularArray[i - 1] = i;
        }
        if (n == 1) {
            System.out.print(circularArray[0]);
        } else {
            int c = 0;
            int last = 1;

            while (1 != circularArray[last]) {
                System.out.print(circularArray[c]);
                c = (c + m - 1) % n;
                last = c;
            }
        }

    }
}

