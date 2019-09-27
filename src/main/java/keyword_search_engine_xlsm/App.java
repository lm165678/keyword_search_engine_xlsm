package KeywordSearchEngine.model;

import KeywordSearchEngine.util.MessageHandler;

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
    String colName = "fullname_skills";
    DBHandler db = new DBHandler(dbName, colName);
    boolean dbInitCheck = db.init();
    if (!dbInitCheck) {
        MessageHandler.errorMessage("DB connection failed. Quitting...");
        return;
    }
    db.end();

    List pair_list = new ArrayList<Pair<String, String>>();
    XlsmHandler handler = new XlsmHandler();
    InvertedIndexBuilder indexBuilder = new InvertedIndexBuilder();
    String folderDir = "src/main/resources/";

    handler.init(folderDir);
    pair_list = handler.extractWbs();

    Iterator<Pair<String, String>> pairsIterator = pair_list.iterator();

    while (pairsIterator.hasNext()) {
      Pair<String, String> p = pairsIterator.next();
      indexBuilder.add_token(p.getKey(), p.getValue());
    }

    // indexBuilder.print_fullName_skill();
    // indexBuilder.print_skill_fullName();
    indexBuilder.calculate();
    // indexBuilder.print_tfidfList();

    MessageHandler.successMessage("Program ending...");
  }
}
