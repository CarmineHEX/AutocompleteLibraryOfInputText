import java.util.Scanner;
import java.util.regex.Pattern;

public class UserInput {
    String filter = "";
    String request = "";

    public String enterString()
    {
        String str = new Scanner(System.in).nextLine();
        checkExit(str);
        return str;
    }
    public String enterFilter()
    {
        String str = enterString();
        if(checkCorrectInputFilter(str))
        {
            return str;
        }
        else return "!return";
    }

    private void checkExit(String str)
    {
        if (str.equals("!quit"))
            System.exit(0);
    }


    private Boolean checkCorrectInputFilter(String str)
    {
        Filter filter = new Filter();
        if (!Pattern.compile("\\][<>][a-zA-Z_'\\\"]+").matcher(str).find()&&filter.checkColumnNumberInFilter(str)) { //check str contains ><[String]
            str = str.replaceAll("\\w*\\[\\d*\\][><=]*[\\w.\\-'\\\"/]*", ""); //delete column[i]
            if(!str.equals(""))
                str = str.replaceAll("\\|{2}|&", "");//delete || &
                str = str.replaceAll("\\(|\\)", "");// delete ( )
                if (str.equals("")) {
                    return true;
                }
            else return false;
        }
        return false;
    }
}
