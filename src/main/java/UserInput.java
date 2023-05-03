import java.util.Scanner;

public class UserInput {
    String filter;
    String request;

    public String enterString()
    {
        String str;
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();
        return str;
    }
}
