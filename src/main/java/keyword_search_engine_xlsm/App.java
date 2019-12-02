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
  public static void main(String[] args) {
    MessageHandler.successMessage("Program starting...");

    String dbName = "workdata_seedsprint";
    DBHandler dbHandler = new DBHandler();
    MongoDatabase db = dbHandler.init(dbName);
    if (db == null) {
      MessageHandler.errorMessage("DB connection failed. Quitting...");
      return;
    }

    List<Pair<String, String>> pair_list = new ArrayList<>();
    XlsmHandler handler = new XlsmHandler();
    InvertedIndexBuilder indexBuilder = new InvertedIndexBuilder();
    String folderDir = "src/main/resources/";

    handler.init(folderDir);
    pair_list = handler.extractWbs();

    Iterator<Pair<String, String>> pairsIterator = pair_list.iterator();
    Pair<String, String> p = pairsIterator.next(); // ignore title line

    while (pairsIterator.hasNext()) {
      p = pairsIterator.next();
      indexBuilder.add_token(p.getKey(), p.getValue(), dbHandler);
    }

    // indexBuilder.print_fullName_skill();
    // indexBuilder.print_skill_fullName();
    // indexBuilder.calculate();
    // indexBuilder.print_tfidfList();
    
    dbHandler.end();

    MessageHandler.successMessage("Program ending...");
  }
}
