package KeywordSearchEngine.util;

import KeywordSearchEngine.util.MessageHandler;
import KeywordSearchEngine.util.TimeTrackerTaskNotFoundException;

import java.util.Map;
import java.util.HashMap;

public class TimeTracker {
    private static Map<String, Long> record = new HashMap<>();

    // Constructor
    public TimeTracker() {}

    public static void start(String taskName) throws TimeTrackerTaskAlreadyExistException {
        if (record.get(taskName) != null) {
            throw new TimeTrackerTaskAlreadyExistException("task already exist, please make sure input name is correct");
        }
        record.put(taskName, System.nanoTime());
    }

    public static long stop(String taskName) throws TimeTrackerTaskNotFoundException {
        if (record.get(taskName) == null) {
            throw new TimeTrackerTaskNotFoundException("no such tracker task, please make sure input name is correct");
        }
        long start = record.get(taskName);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        record.put(taskName, timeElapsed);

        return timeElapsed;
    }

    public static Map<String, Long> getRecord() {
        return record;
    }

    public static long getRecordByName(String taskName) {
        return record.get(taskName);
    } 
    
}