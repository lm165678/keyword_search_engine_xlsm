package KeywordSearchEngine.util;

import java.util.Scanner;

/**
 * @author Zhongjie Shen
 */
public class MessageHandler {

  public MessageHandler() {}

  public static String getSuccessMessage(String msg) {
    return "[Success] " + msg;
  }

  public static String getErrorMessage(String msg) {
    return "[ERROR] " + msg;
  }

  public static String getInfoMessage(String msg) {
    return "[INFO] " + msg;
  }

  public static String getDebugMessage(String msg) {
    return "[DEBUG] " + msg;
  }

  public static String getInputMessage(String msg) {
    return "[NEED INPUT] " + msg;
  }

  public static void successMessage(String msg) {
    String outMsg = getSuccessMessage(msg);
    System.out.println(outMsg);
  }

  public static void errorMessage(String msg) {
    String outMsg = getErrorMessage(msg);
    System.out.println(outMsg);
  }

  public static void infoMessage(String msg) {
    String outMsg = getInfoMessage(msg);
    System.out.println(outMsg);
  }

  public static void debugMessage(String msg) {
    String outMsg = getDebugMessage(msg);
    System.out.println(outMsg);
  }

  public static String stringInputMessage(String msg) {
    String outMsg = getInputMessage(msg);
    Scanner sc = new Scanner(System.in);
    System.out.println(outMsg);

    String userInput = sc.nextLine();

    return userInput;
  }
}
