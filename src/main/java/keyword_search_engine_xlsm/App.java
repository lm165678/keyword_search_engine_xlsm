package KeywordSearchEngine;

import KeywordSearchEngine.model.DBHandler;
import KeywordSearchEngine.model.InvertedIndexBuilder;
import KeywordSearchEngine.model.XlsmHandler;
import KeywordSearchEngine.util.MessageHandler;
import com.mongodb.client.MongoDatabase;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.*;
import java.util.HashSet;
import java.util.AbstractMap;

/**
 * @author Zhongjie Shen
 */
public class App {

  public static void runWithDB() {
    String dbName = "workdata_seedsprint";

    // connect to database
    DBHandler dbHandler = new DBHandler();
    MongoDatabase db = dbHandler.init(dbName);
    if (db == null) {
      MessageHandler.errorMessage("Cannot connect to MongoDB instance. Quitting...");
      return;
    }

    // extracting tokens
    String folderDir = "src/main/resources/";
    XlsmHandler handler = new XlsmHandler();

    handler.init(folderDir);
    Map<String, String> data_map = handler.extractWbs();

    // adding tokens to indexBuilder
    InvertedIndexBuilder indexBuilder = new InvertedIndexBuilder();

    for (Map.Entry<String, String> entry : data_map.entrySet()) {
        indexBuilder.add_token(entry.getKey(), entry.getValue(), dbHandler);
    }

    // caculating tdidfs
    indexBuilder.calculate(dbHandler);
    dbHandler.end();
  }

  public static void runWithoutDB() {
    // extracting tokens
    String folderDir = "src/main/resources/";
    XlsmHandler handler = new XlsmHandler();

    handler.init(folderDir);
    Map<String, String> data_map = handler.extractWbs();

    // adding tokens to indexBuilder
    InvertedIndexBuilder indexBuilder = new InvertedIndexBuilder();

    for (Map.Entry<String, String> entry : data_map.entrySet()) {
        indexBuilder.add_token(entry.getKey(), entry.getValue());
    }

    // caculating tdidfs
    indexBuilder.calculate();

    // print results
    indexBuilder.print_fullName_skill();
    indexBuilder.print_skill_fullName();
    indexBuilder.print_tfidfList();
  }

  public static void showMenu() {
    String input = MessageHandler.stringInputMessage(
        "Do you want to use Mongodb for record? or the result will be printed directly in terminal [y/n]");

    if (input.equals("y")) {
      runWithDB();
    } else if (input.equals("n")) {
      runWithoutDB();
    } else {
      MessageHandler.errorMessage("Wrong input, please try again");
      showMenu();
    }

    return;
  }

  public static void main(String[] args) {
    MessageHandler.successMessage("Program starting...");

    showMenu();
    // runWithDB();
    // runWithoutDB();

    MessageHandler.successMessage("Program ending...");
  }
}
