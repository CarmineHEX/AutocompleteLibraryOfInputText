public class Main {


    public static void main(String[] args) {

        Airports airports = new Airports();
        File file =new File();
        UserInput userInput = new UserInput();

        file.readNameAirports(airports.airportsMap);
        airports.outputAirports(airports.airportsMap);


        System.out.print("Enter filter for columns 3-14 (leave empty for no filter): ");
        userInput.filter= userInput.enterString();
        System.out.print("Enter start of airport name to search for: ");
        userInput.request = userInput.enterString();
        for(String foundNameAirport: airports.searchAirport(userInput.request)){
            airports.getSplitRowAirport(file.getRowFromFile(airports.airportsMap,foundNameAirport));
        }
    }
}