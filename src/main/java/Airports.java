import java.util.HashMap;
import java.util.Map;

public class Airports {
    Map<Integer, String> airportsMap = new HashMap<>();

    void outputAirports(Map<Integer, String> map){
        for (Map.Entry<Integer,String> entry: map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
    void sortNameAirports(Map<Integer, String> map){

    }
}

