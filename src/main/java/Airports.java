import java.util.*;

public class Airports {
    public TreeMap<String,Long> airportsMap = new TreeMap<>();

    //output found airport
    public Integer outputAirports(String userRequest,String userFilter, Filter filter, File file){
        int numberFoundAirport = 0;
        List<String> nameAirports =searchAirport(userRequest);
        for (String nameAirport : nameAirports) {
            String[] airport = getSplitRowAirport(file.getRowFromFile(airportsMap, nameAirport));
            if (!userFilter.isEmpty()) {
                if (filter.isSuitableAirport(userFilter, getSplitRowAirport(file.getRowFromFile(airportsMap, nameAirport)))) {
                    numberFoundAirport++;
                    System.out.println(airport[1] + Arrays.toString(airport));
                }
            }
            else
            {
                numberFoundAirport++;
                System.out.println(airport[1] + Arrays.toString(airport));
            }
        }
        return numberFoundAirport;
    }
    // get massive elements row taken from a file
    private String[] getSplitRowAirport(String row){
        String[] airportData;
        airportData= row.split(",");
        return airportData;
    }
     // search airport at the beginning of the entered line in treemap by key
    private List<String> searchAirport( String request) {
        List<String> result = new ArrayList<>();

        if ( airportsMap == null || airportsMap.isEmpty() || request == null || request.length() == 0) {
            return result;
        }

        String firstKey = airportsMap.ceilingKey(request);

        if (firstKey == null) {
            return result;
        }

        for (String key : airportsMap.keySet()) {
            if (key.startsWith(request)) {
                result.add(key);
            }
        }
        return result;
    }

}

