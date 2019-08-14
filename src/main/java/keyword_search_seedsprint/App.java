/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package keyword_search_seedsprint;

import java.util.Iterator;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 * @author Zhongjie Shen
 */
public class App {
    public String getGreeting() {
        return "[SUCCESS] Program started";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        DBHandler db = new DBHandler();
        db.init();
        // db.end();

        ArrayList pair_list = new ArrayList<Pair<String, String>>();
        XlsxHandler handler = new XlsxHandler();
        // INFO: strict OOXML format is not supported for apache poi currently
        // file must be in other format such as xlsm
        String fileDir = "src/main/resources/worddata1.xlsm";

        handler.init(fileDir);
        pair_list = handler.read();

        InvertedIndexBuilder indexBuilder = new InvertedIndexBuilder();
        Iterator<Pair<String, String>> pairsIterator = pair_list.iterator();

        while (pairsIterator.hasNext()) {
            Pair<String, String> p = pairsIterator.next();
            indexBuilder.add_token(p.getKey(), p.getValue());
        }

        // indexBuilder.print_fullName_skill();
        // indexBuilder.print_skill_fullName();
        indexBuilder.calculate();
        indexBuilder.print_tfidfList();
    }
}
