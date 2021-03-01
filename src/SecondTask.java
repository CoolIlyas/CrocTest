import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SecondTask {
    static int maxTime;
    static int maxCustomers;
    static HashMap <String, Integer> mapOfCustomers = new HashMap<>();
    static ArrayList<Integer> plusCounter = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < size; i++) {
            String command = scanner.nextLine();
            doWork(command);
        }
        System.out.println(maxCustomers + " " + maxTime);
    }
    static void doWork (String command){
        String[] commandArray = command.split(" ");
        if (commandArray[0].equals("+")) {
            mapOfCustomers.put(commandArray[2],Integer.parseInt(commandArray[1]));
            plusCounter.add(Integer.parseInt(commandArray[1]));
        }
        if (commandArray[0].equals("-")) {
            if (mapOfCustomers.size() > maxCustomers) {
                maxCustomers = mapOfCustomers.size();
            }

            if (plusCounter.size() == 1 ) {
                int timeDiffference = Integer.parseInt(commandArray[1]) - plusCounter.get(0);
                maxTime += timeDiffference;
            }
            mapOfCustomers.remove(commandArray[2]);
            plusCounter.remove(plusCounter.size()-1);
        }
    }
}