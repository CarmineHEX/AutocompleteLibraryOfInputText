import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter {

    private Matcher searchСoincidence (String regex, String filter)
    {
        return  Pattern.compile(regex).matcher(filter);
    }

    private String getFirstCoincidence(Matcher matcher){
        if (matcher.find()){
            return  matcher.group();
        }
        return null;
    }
    // get massive contains [0]- column[i] [1]- sign(< > = <>) [2] - value
    private String[] getSplitExpression(Matcher matcher){
        String[] expression= new String[3];
        if (matcher.find()){
            expression[0]= getFirstCoincidence(searchСoincidence("(\\d+)",matcher.group(1)));
            expression[1]= matcher.group(2);
            expression[2]= matcher.group(3).replaceAll("\\'|\\\"","");
            return  expression;
        }
        return null;
    }

    //replace expression (column[i]'Sign''value') where sign - [<> = < >] on true/false
    private String replaceExpressionBooleanValue(boolean isExpression, String filter, String expression)
    {
        String newFilter;
        if(isExpression){
            newFilter = filter.replace(expression,"true");
        }else newFilter = filter.replace(expression,"false");
        return newFilter;
    }
    //defines equal expression (column[i]'Sign''value') where sign - [<> = < >]
    private boolean getExpression( String [] splitRowAirport,String [] splitExpression)
    {
        if (splitExpression!=null){
            int numberColumn= Integer.parseInt(splitExpression[0]);
            String sign = splitExpression[1];
            String value= splitExpression[2];
            switch (sign){
                case "<":
                    if(numberColumn==1||numberColumn==9||numberColumn==10)
                        return Integer.parseInt(splitRowAirport[numberColumn-1])<Integer.parseInt(value);
                    else if (numberColumn==7||numberColumn==8)
                        return Double.parseDouble(splitRowAirport[numberColumn-1])<Double.parseDouble(value);
                case ">":
                    if(numberColumn==1||numberColumn==9||numberColumn==10)
                        return Integer.parseInt(splitRowAirport[numberColumn-1])>Integer.parseInt(value);
                    else if (numberColumn==7||numberColumn==8)
                        return Double.parseDouble(splitRowAirport[numberColumn-1])>Double.parseDouble(value);
                case "<>":
                    return !Objects.equals(splitRowAirport[numberColumn - 1].replace("\"",""), value);
                case "=":
                    return Objects.equals(splitRowAirport[numberColumn - 1].replace("\"",""), value);
            }
        }
        return false;
    }
    // create new filter format  Example: true&false||true instead of column[1]>10&column[3]<>'Gothaab'||column[5]<20.1
    private String newFilter(String filter, String [] splitRowAirport){
        String expression = "";
        String []splitExpression ;
        while (expression != null)
        {
            expression = getFirstCoincidence(searchСoincidence("(\\w*\\[\\d*\\][<>=]*[\\w'\\\"/]*)",filter));
            if(expression != null) {
                splitExpression = getSplitExpression(searchСoincidence("(\\w*\\[\\d*\\])(<>|[><=])([\\w\\.\\-/']+)", expression));
                filter = replaceExpressionBooleanValue(getExpression(splitRowAirport, splitExpression), filter, expression);
            }
        }
        return filter.replaceAll("[()]","");
    }
    public Boolean isSuitableAirport(String filter, String [] splitRowAirport)
    {
        String newFilter = newFilter(filter,splitRowAirport);
        while (!Objects.equals(newFilter, "true") && !Objects.equals(newFilter, "false")){
            String expression = getFirstCoincidence(searchСoincidence("(\\w*&\\w*)",newFilter));
            if (expression!=null){
                newFilter = newFilter.replace(expression,determineTruthExpression(expression));
            }
            else {
                expression = getFirstCoincidence(searchСoincidence("(\\w*\\|\\|\\w*)", newFilter));
                if (expression!=null){
                    newFilter = newFilter.replace(expression,determineTruthExpression(expression));
                }
            }
        }
        return Boolean.valueOf(newFilter);
    }
    // replace expression on true or false
    private String determineTruthExpression(String expression){
        switch (expression){
            case "true&true":
            case "true||true":
            case "false||true":
            case "true||false":
                return "true";
            default:
                return "false";
        }
    }
    public   Boolean checkColumnNumberInFilter(String str)
    {
        if(!str.equals("")) {
            Matcher matcher = searchСoincidence("(\\w*\\[\\d*\\][<>=]+[\\w'\\\"/]+)", str);
            int numberColumn = 0;
            while (matcher.find()) {
                numberColumn = Integer.parseInt(getFirstCoincidence(searchСoincidence("(\\d+)", matcher.group())));
                if (numberColumn == 2 || numberColumn < 1 || numberColumn > 14) {
                    return false;
                }
            }
            if (numberColumn != 0)
                return true;
            else return false;
        }
        return true;
    }
}