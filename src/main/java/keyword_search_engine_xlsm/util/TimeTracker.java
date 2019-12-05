package KeywordSearchEngine.util;

import java.util.HashMap;
import java.util.Map;

public class TimeTracker {

  private static Map<String, Long> record = new HashMap<>();

  // Constructor
  public TimeTracker() {
  }

  /**
   * TODO: default method, start a new tracker
   */
  public static void start() {
    return;
  }

  /**
   * TODO: default method, stop the latest started tracker
   */
  public static void stop() {
    return;
  }

  /**
   * return recorded list of tracker times, may includ unfinished tracker info
   *
   * @return Map<String, Long>
   */
  public static Map<String, Long> get() {
    return record;
  }

  /**
   * start a new timer with name
   *
   * @param taskName task name
   */
  public static boolean start(String taskName) {
    try {
      startTrackerByName(taskName);
    } catch (TimeTrackerException e) {
      MessageHandler.errorMessage(e.getMessage());
      return false;
    }
    return true;
  }

  /**
   * stop the time which has a specific name
   *
   * @param taskName task name
   */
  public static boolean stop(String taskName) {
    try {
      stopTrackerByName(taskName);
    } catch (TimeTrackerException e) {
      MessageHandler.errorMessage(e.getMessage());
      return false;
    }
    return true;
  }

  /**
   * start a new tracker with specified name
   *
   * @param taskName [description]
   * @throws TimeTrackerTaskAlreadyExistException [description]
   */
  public static void startTrackerByName(String taskName)
      throws TimeTrackerTaskAlreadyExistException {
    if (record.get(taskName) != null) {
      throw new TimeTrackerTaskAlreadyExistException(
          "task already exist, please make sure input name is correct");
    }
    record.put(taskName, System.nanoTime());
  }

  /**
   * stop a existing timer with specified name
   *
   * @param taskName [description]
   * @return [description]
   * @throws TimeTrackerTaskNotFoundException [description]
   */
  public static long stopTrackerByName(String taskName) throws TimeTrackerTaskNotFoundException {
    if (record.get(taskName) == null) {
      throw new TimeTrackerTaskNotFoundException(
          "no such tracker task, please make sure input name is correct");
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
