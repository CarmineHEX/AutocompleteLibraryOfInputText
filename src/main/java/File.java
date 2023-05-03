import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

public class File {
    private static final String FILENAME = "src/main/resources/airports.csv";


    public Map<String, Long> readNameAirports(Map<String,Long> airportMap) {
        try (RandomAccessFile raf = new RandomAccessFile(FILENAME, "r")) {
            String row;
            Long output = 0L;
            while ((row = raf.readLine()) != null) {
                airportMap.put(row.split(",")[1].replaceAll("\"", ""), output);
                output = raf.getFilePointer();
            }
        } catch (IOException e) {
            System.err.println("Error reading data from file: " + e.getMessage());
            }
        return airportMap;
    }
    public String getRowFromFile(Map<String,Long> airportMap, String nameAirport) {
        String row = "";
        try (RandomAccessFile raf = new RandomAccessFile(FILENAME, "r")) {
            raf.seek(airportMap.get(nameAirport));
            row = raf.readLine();
        } catch (IOException e) {
            System.err.println("Error reading data from file: " + e.getMessage());
        }
        return row;
    }
}
