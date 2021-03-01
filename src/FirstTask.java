import java.util.Scanner;

public class FirstTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lenght = scanner.nextInt();
        int[] array = new int[lenght];
        for (int i = 0; i < lenght;i++){
            array[i] = scanner.nextInt();
        }
        int[] result = new int[lenght];

        int count = 1;

        result[0] = 1;

        for (int i = 1; i < lenght; i ++) {
            result[i] = array[count - 1];
            count = array[count - 1];
        }

        for (int i = lenght - 1;i >= 0; i--) {
            System.out.print( result[i] + " ");
        }
    }
}
