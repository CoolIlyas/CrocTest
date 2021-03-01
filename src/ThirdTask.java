import java.util.ArrayList;
import java.util.Scanner;

public class ThirdTask {
    static ArrayList<String> commands = new ArrayList<>();
    static ArrayList<String> ip = new ArrayList();
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        int commandAmount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < commandAmount; i++) {
            commands.add(scanner.nextLine());
        }
        int ipAmount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < ipAmount; i++) {
            ip.add(scanner.nextLine());
        }
        ip.forEach(a ->System.out.println(getAnswer(a)));
    }


    public static String[] networkToRange (String network) {
        String[] newNetwork = network.split("/");
        String[] answer = new String[2];
        newNetwork[0] = ipTo2IP(newNetwork[0]);
        String[] helper = new String[2];
        try {
            helper[0] = newNetwork[0].substring(0, Integer.parseInt(newNetwork[1]) + Integer.parseInt(newNetwork[1]) / 8);
            helper[1] = newNetwork[0].substring(Integer.parseInt(newNetwork[1]) + Integer.parseInt(newNetwork[1]) / 8);
            answer[0] = helper[0].concat(helper[1].replaceAll("1", "0"));
            helper[1] = helper[1].replaceAll("0", "1");
            answer[1] = helper[0].concat(helper[1]);
        } catch (StringIndexOutOfBoundsException e) {
            answer[1] = newNetwork[0];
        }
        return answer;
    }
    public static Long Dot2LongIP(String dottedIP) {
        String[] addrArray = dottedIP.split("\\.");
        long num = 0;
        for (int i=0;i<addrArray.length;i++) {
            int power = 3-i;
            num += ((Integer.parseInt(addrArray[i], 2) % 256) * Math.pow(256,power));
        }
        return num;
    }
    public static String ipTo2IP (String dottedIP) {
        String[] arrayhelper = dottedIP.split("\\.");
        arrayhelper[0] = Integer.toBinaryString(Integer.parseInt(arrayhelper[0]));
        while (arrayhelper[0].length() <8) {
            arrayhelper[0] = "0".concat(arrayhelper[0]);
        }
        String answer = arrayhelper[0];
        for (int i = 1; i < arrayhelper.length; i++) {
            arrayhelper[i] = Integer.toBinaryString(Integer.parseInt(arrayhelper[i]));
            while (arrayhelper[i].length() <8) {
                arrayhelper[i] = "0".concat(arrayhelper[i]);
            }
            answer = answer.concat("." + arrayhelper[i]);
        }
        return answer;
    }
    public static String getAnswer (String ip){
        ThirdTask.Access answer = ThirdTask.Access.GRANTED;
        boolean helper;
        for(String command : commands) {
            String[] commandArray = command.split(" from ");
            if (commandArray[1].contains("/")) {
                String[] range = networkToRange(commandArray[1]);
                helper = Dot2LongIP(range[0]) < Dot2LongIP(ipTo2IP(ip)) && Dot2LongIP(ipTo2IP(ip)) < Dot2LongIP(range[1]);
            } else {
                helper = ip.equals(commandArray[1]);
            }
            if (helper) {
                switch (commandArray[0]) {
                    case "allow":
                        answer = ThirdTask.Access.GRANTED;
                        break;
                    case "deny":
                        answer = ThirdTask.Access.DENIED;
                        break;
                }
            }
        }
        return answer.toString();
    }
    enum Access{
        GRANTED,
        DENIED
    }
}
