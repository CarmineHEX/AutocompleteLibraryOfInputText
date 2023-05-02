import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class File {
    private static final String FILENAME = "src/main/resources/airports.csv";
    private static final String CSV_DELIMITER = ",";

    public Map<Integer,String> fileRead (Map<Integer,String> airportMap) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] airportData = line.split(CSV_DELIMITER);
                airportMap.put(Integer.valueOf(airportData[0]), airportData[1]);
            }
        } catch (IOException e) {
            System.err.println("Error reading data from file: " + e.getMessage());
        }
        return airportMap;
    }
}
