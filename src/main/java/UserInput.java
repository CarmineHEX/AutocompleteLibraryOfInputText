import java.util.Scanner;

public class UserInput {
    String filter;
    String search;

    public String enterString()
    {
        String str;
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();
        return str;
    }
}
