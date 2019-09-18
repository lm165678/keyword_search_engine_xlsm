package keyword_search_seedsprint;

/**
 * @author Zhongjie Shen
 */
public class MessageHandler {

  private static String successText = "[Success]";
  private static String errorText = "[ERROR]";
  private static String infoText = "[INFO]";
  private static String debugText = "[DEBUG]";

  public MessageHandler() {}

  public static String getSuccessMessage(String msg) {
    return successText + " " + msg;
  }

  public static String getErrorMessage(String msg) {
    return errorText + " " + msg;
  }

  public static String getInfoMessage(String msg) {
    return infoText + " " + msg;
  }

  public static String getDebugMessage(String msg) {
    return debugText + " " + msg;
  }

  public static void printSuccessMessage(String msg) {
    String outputMsg = getSuccessMessage(msg);
    System.out.println(outputMsg);
  }

  public static void printErrorMessage(String msg) {
    String outputMsg = getErrorMessage(msg);
    System.out.println(outputMsg);
  }

  public static void printInfoMessage(String msg) {
    String outputMsg = getInfoMessage(msg);
    System.out.println(outputMsg);
  }

  public static void printDebugMessage(String msg) {
    String outputMsg = getDebugMessage(msg);
    System.out.println(outputMsg);
  }
}
