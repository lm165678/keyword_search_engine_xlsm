package KeywordSearchEngine;

import KeywordSearchEngine.util.MessageHandler;
import KeywordSearchEngine.model.DBHandler;
import KeywordSearchEngine.model.XlsmHandler;
import KeywordSearchEngine.model.InvertedIndexBuilder;
import com.mongodb.client.MongoDatabase;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import javafx.util.Pair;

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
    List<Pair<String, String>> pair_list = handler.extractWbs();

    // adding tokens to indexBuilder
    InvertedIndexBuilder indexBuilder = new InvertedIndexBuilder();

    Iterator<Pair<String, String>> pairsIterator = pair_list.iterator();
    Pair<String, String> p = pairsIterator.next(); // ignore the first title line

    while (pairsIterator.hasNext()) {
      p = pairsIterator.next();
      indexBuilder.add_token(p.getKey(), p.getValue(), dbHandler);
    }

    // caculating tdidfs
    indexBuilder.calculate(dbHandler);
    dbHandler.end();
  }

  public static void runWithoutDB() {
    return;
  }

  public static void main(String[] args) {
    MessageHandler.successMessage("Program starting...");

    runWithDB();

    MessageHandler.successMessage("Program ending...");
  }
}
