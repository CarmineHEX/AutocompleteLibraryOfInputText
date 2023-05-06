public class Main {
    public static void main(String[] args) {

        Airports airports = new Airports();
        File file =new File();
        UserInput userInput = new UserInput();
        Filter filter = new Filter();

        file.readNameAirports(airports.airportsMap);

        while(true) {
            System.out.println("Введите фильтр (пустая строка будет означать поиск без фильтра): ");
            userInput.filter = userInput.enterFilter();
            if (!userInput.filter.equals("!return")) {
                System.out.println("Введите начало имени аэропорта: ");
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