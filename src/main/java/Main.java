public class Main {
    public static void main(String[] args) {

        Airports airports = new Airports();
        File file =new File();
        UserInput userInput = new UserInput();
        Filter filter = new Filter();

        file.readNameAirports(airports.airportsMap);

        while(true) {
            // (column[1]<10&(column[3]<>'Godthaab'&(column[4]='Greenland'||column[7]>80)))&(Column[11]='E'||column[12]='America/Godthab')
            System.out.println("Enter filter for columns 3-14 (leave empty for no filter): ");
            userInput.filter = userInput.enterFilter();
            if (!userInput.filter.equals("!return")) {
                System.out.println("Enter start of airport name to search for: ");
                userInput.request = userInput.enterString();
                long start = System.currentTimeMillis();
                int numberFoundAirport = airports.outputAirports(userInput.request, userInput.filter, filter, file);
                long finish = System.currentTimeMillis();
                long elapsed = finish - start;
                System.out.println("Количество найденных строк: " + numberFoundAirport + " Время, затраченное поиск время : " + elapsed + "мс");
            }
            else System.err.println("Неправильно введен фильтр");
        }
    }
}