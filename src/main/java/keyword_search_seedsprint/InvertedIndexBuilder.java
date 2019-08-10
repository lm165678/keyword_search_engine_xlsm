package keyword_search_seedsprint;

import java.util.Map.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author Zhongjie Shen
 */
class InvertedIndexBuilder {

    HashMap<String, ArrayList<String>> dict_fullName_skills; // dict[fullName] = [skill]
    HashMap<String, ArrayList<String>> dict_skill_fullNames; // dict[skill] = [fullName]
    TFIDFCalculator calculator;

    public InvertedIndexBuilder() {
        dict_fullName_skills = new HashMap<String, ArrayList<String>>();
        dict_skill_fullNames = new HashMap<String, ArrayList<String>>();
        this.calculator = new TFIDFCalculator();
        return;
    }

    public void add_token(String fullName, String allSkills) {
        StringTokenizer st = new StringTokenizer(allSkills, ", ");

        ArrayList<String> skills = new ArrayList<String>();

        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            // for dict_fullName_skills
            skills.add(token);

            // for dict_skill_fullNames
            ArrayList<String> fullNames = this.dict_fullName_skills.get(token);
            if (fullNames == null) {
                fullNames = new ArrayList<String>();
                fullNames.add(fullName);
                this.dict_skill_fullNames.put(token, fullNames);
            } else {
                // FIXME: the fullNames list is never updated
                fullNames.add(fullName);
                this.dict_skill_fullNames.replace(token, fullNames);
            }
            // System.out.println("Item Update {" + token + ": " + fullNames + "}");
        }

        dict_fullName_skills.put(fullName, skills);
        return;
    }

    public void print_fullName_skill() {
        for (Entry<String, ArrayList<String>> entry : this.dict_fullName_skills.entrySet()) {
            System.out.println("fullName: " + entry.getKey());
            System.out.println("skills: " + entry.getValue());
        }
    }

    public void print_skill_fullName() {
        for (Entry<String, ArrayList<String>> entry : this.dict_skill_fullNames.entrySet()) {
            System.out.println("skill: " + entry.getKey());
            System.out.println("names: " + entry.getValue());
        }
    }

    public void calculate() {
        System.out.println("[SUCCESS] Start calculating...");

        int doc_total; // total skill count under one entry
        int docs_total = this.calculateDocsTotal(); // total skill count under all entries
        int term_occur_in_docs; // total skill appearence under all entries

        for (Entry<String, ArrayList<String>> entry : this.dict_skill_fullNames.entrySet()) {
            String skill = entry.getKey();
            ArrayList<String> names = entry.getValue();

            term_occur_in_docs = names.size();
            System.out.println("[INFO] skill: " + skill + " term_occur_in_docs = " + term_occur_in_docs);

            double tfidf = 0;
            double totalTermCount = names.size();

            for (String name : names) {
                ArrayList<String> skills = this.dict_fullName_skills.get(name);
                doc_total = skills.size();
                double temp = calculator.tfIdf(doc_total, docs_total, term_occur_in_docs);
                tfidf = tfidf + temp;
            }
            System.out.println(tfidf);
            System.out.println(this.dict_skill_fullNames.get(skill));

            tfidf = tfidf / totalTermCount;

            System.out.println(skill + ": " + tfidf);

        }

        return;
    }
    
    private int calculateDocsTotal() {
        int docs_total = 0;

        for (Entry<String, ArrayList<String>> entry : this.dict_fullName_skills.entrySet()) {
            for (String skill : entry.getValue())
                docs_total++;
        }

        System.out.println("[INFO] docs_total = " + docs_total);
        
        return docs_total;
    }
}