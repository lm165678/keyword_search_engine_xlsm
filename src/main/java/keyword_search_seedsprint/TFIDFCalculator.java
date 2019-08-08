package keyword_search_seedsprint;

import java.util.Arrays;
import java.util.List;

/**
 * @author original code concept by Mohamed Guendouz
 * @author modified by Zhongjie Shen
 */
public class TFIDFCalculator {
    /**
     * @param doc  list of strings
     * @param term String represents a term
     * @return term frequency of term in document
     */
    public double tf(int doc, int term) {
        return 1 / doc;
    }

    /**
     * @param docs list of list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     */
    public double idf(int docs, int term_occur_in_docs) {
        double n = term_occur_in_docs;
        return Math.log(docs / n);
    }

    /**
     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
     */
    public double tfIdf(int doc, int docs, int term_occur_in_docs) {
        return tf(doc, term_occur_in_docs) * idf(docs, term_occur_in_docs);

    }

}