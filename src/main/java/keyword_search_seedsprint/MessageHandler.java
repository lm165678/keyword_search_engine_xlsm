package keyword_search_seedsprint;

/**
 * @author Zhongjie Shen
 */
public class MessageHandler {

    private static String successText = "[Success]";
    private static String errorText = "[ERROR]";
    private static String infoText = "[INFO]";

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

    public static void printSuccessMessage(String msg) {
        String outputMsg = getSuccessMessage(msg);
        System.out.println(msg);
    }

    public static void printErrorMessage(String msg) {
        String outputMsg = getErrorMessage(msg);
        System.out.println(msg);
    }

    public static void printInfoMessage(String msg) {
        String outputMsg = getInfoMessage(msg);
        System.out.println(msg);
    }
}