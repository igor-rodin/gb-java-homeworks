import java.util.HashMap;
import java.util.Map;

public class UndergroundSystem {
    private final Map<Integer, ChekIn> chekInMap;
    private final Map<String, int[]> pathDuration;

    private record ChekIn(String stationName, int time) {
    }

    public UndergroundSystem() {
        chekInMap = new HashMap<>();
        pathDuration = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        chekInMap.put(id, new ChekIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        String path = String.format("%s->%s", chekInMap.get(id).stationName, stationName);
        int pathTime = t - chekInMap.get(id).time;

        int[] timeAndCounts = pathDuration.getOrDefault(path, new int[] { 0, 0 });
        pathDuration.put(path, new int[] { timeAndCounts[0] += 1, timeAndCounts[1] + pathTime });
    }

    public double getAverageTime(String startStation, String endStation) {
        String path = String.format("%s->%s", startStation, endStation);
        int[] pathTimes = pathDuration.get(path);

        return (double) pathTimes[1] / pathTimes[0];
    }
}
