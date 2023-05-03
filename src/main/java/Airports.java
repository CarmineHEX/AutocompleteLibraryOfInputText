import java.util.*;

public class Airports {
    TreeMap<String,Long> airportsMap = new TreeMap<>();
    String[] airportData;

    void outputAirports(Map<String,Long> map){
        for (Map.Entry<String,Long> entry: map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
    void outputAirportData (String[] ad){
        for(String a: ad){
            System.out.print(a);
        }
    }
    void getSplitRowAirport(String row){
        airportData= row.split(",");
    }
    List<String> searchAirport( String request) {
        List<String> result = new ArrayList<>();

        if ( airportsMap == null || airportsMap.isEmpty() || request == null || request.length() == 0) {
            return result;
        }

        String firstKey = airportsMap.ceilingKey(request);

        if (firstKey == null) {
            return result;
        }

        for (String key : airportsMap.keySet()) {
            if (key.startsWith(request) && key.compareTo(firstKey) >= 0) {
                result.add(key);
            }
        }
        return result;
    }

}

