import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("n = ");
        int n = scanner.nextInt();

        System.out.print("m = ");
        int m = scanner.nextInt();

        int[] circularArray = new int[n];
        for (int i = 1; i <= n; i++) {
            circularArray[i - 1] = i;
        }
        if (n==1){
            System.out.print(circularArray[0]);
        }else{
            int c = 0;
            int last = 1;

            while( 1 != circularArray[last]){
                System.out.print(circularArray[c]);
                c = (c + m - 1) % n;
                last = c;
            }
        }

    }
}

