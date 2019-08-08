package keyword_search_seedsprint;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author Zhongjie Shen
 */
class InvertedIndexBuilder {

    Map<String, List> dict_fullname_skill; // dict[fullname] = [skill]
    Map<String, List> dict_skill_fullname; // dict[skill] = [fullname]
    TFIDFCalculator calculator;

    public InvertedIndexBuilder() {
        dict_fullname_skill = new HashMap<String, List>();
        dict_skill_fullname = new HashMap<String, List>();
        this.calculator = new TFIDFCalculator();
        return;
    }

    public void add_token(String fullName, String allSkills) {
        StringTokenizer st = new StringTokenizer(allSkills, ", ");
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            List fullNames = dict_fullname_skill.get(token);
            if (fullNames == null) {
                fullNames = new ArrayList();
                fullNames.add(fullName);
            } else {
                fullNames.add(fullName);
            }
            dict_skill_fullname.put(token, fullNames);
            System.out.println("Item Update {" + token + ": " + fullNames + "}");
        }
        return;
    }

    public void calculate() {
        int doc_total;
        int docs_total;
        int term_occur_in_docs;

        // for (String k : dict_fullname_skill.keySet()) {
        //     List<String> fullnames = dict_fullname_skill.get(k);
        //     term_occur_in_docs = fullNames.size();
        //     doc_total = dict_fullname_skill


        //     double tfidf = calculator.tfIdf(, term_occur_in_docs);
        // }

        return;
    }
}