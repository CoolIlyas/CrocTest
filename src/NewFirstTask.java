import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NewFirstTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lenght = scanner.nextInt();
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < lenght; i++) {
            array.add(scanner.nextInt());
        }

        int helper = 1;
        ArrayList<Integer> result = new ArrayList<>();
        do {
            result.add(helper);
            helper = array.get(helper-1);
        }while (helper!=0);
        Collections.reverse(result);
        System.out.println(result);
    }
}
