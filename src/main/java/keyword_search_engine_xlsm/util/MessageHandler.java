package KeywordSearchEngine.util;

/**
 * @author Zhongjie Shen
 */
public class MessageHandler {

  private static String successText = "[Success]";
  private static String errorText = "[ERROR]";
  private static String infoText = "[INFO]";
  private static String debugText = "[DEBUG]";

  public MessageHandler() {}

  public static String generateSuccessMessage(String msg) {
    return successText + " " + msg;
  }

  public static String generateErrorMessage(String msg) {
    return errorText + " " + msg;
  }

  public static String generateInfoMessage(String msg) {
    return infoText + " " + msg;
  }

  public static String generateDebugMessage(String msg) {
    return debugText + " " + msg;
  }

  public static void successMessage(String msg) {
    String outputMsg = generateSuccessMessage(msg);
    System.out.println(outputMsg);
  }

  public static void errorMessage(String msg) {
    String outputMsg = generateErrorMessage(msg);
    System.out.println(outputMsg);
  }

  public static void infoMessage(String msg) {
    String outputMsg = generateInfoMessage(msg);
    System.out.println(outputMsg);
  }

  public static void debugMessage(String msg) {
    String outputMsg = generateDebugMessage(msg);
    System.out.println(outputMsg);
  }
}
