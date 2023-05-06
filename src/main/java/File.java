import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

public class File {
    private static final String FILENAME = "src/main/resources/airports.csv";

    // get treemap  key - name airport  value - position in file
    public Map<String, Long> readNameAirports(Map<String,Long> airportMap) {
        try (RandomAccessFile raf = new RandomAccessFile(FILENAME, "r")) {
            String row;
            long output = 0L;
            while ((row = raf.readLine()) != null) {
                airportMap.put(row.split(",")[1].replaceAll("\"", ""), output);
                output = raf.getFilePointer();
            }
        } catch (IOException e) {
            readFileError(e);
        }
        return airportMap;
    }
    // get row file  by value position in file
    public String getRowFromFile(Map<String,Long> airportMap, String nameAirport) {
        String row = "";
        try (RandomAccessFile raf = new RandomAccessFile(FILENAME, "r")) {
            raf.seek(airportMap.get(nameAirport));
            row = raf.readLine();
        } catch (IOException e) {
            readFileError(e);
        }
        return row;
    }
    private void readFileError(IOException e){
        System.err.println("Ошибка при чтении данных из файла: " + e.getMessage());
        System.exit(1);
    }
}
