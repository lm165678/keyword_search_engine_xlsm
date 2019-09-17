package keyword_search_seedsprint;

import java.util.Map.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javafx.util.Pair;

/**
 * @author Zhongjie Shen
 */
class InvertedIndexBuilder {

    HashMap<String, ArrayList<String>> dict_fullName_skills; // dict[fullName] = [skill]
    HashMap<String, ArrayList<String>> dict_skill_fullNames; // dict[skill] = [fullName]
    ArrayList<Pair<String, Double>> tfidfList;

    TFIDFCalculator calculator;

    public InvertedIndexBuilder() {
	dict_fullName_skills = new HashMap<String, ArrayList<String>>();
	dict_skill_fullNames = new HashMap<String, ArrayList<String>>();
	tfidfList = new ArrayList<Pair<String, Double>>();

	this.calculator = new TFIDFCalculator();
	return;
    }

    public void add_token(String fullName, String allSkills) {
	StringTokenizer st = new StringTokenizer(allSkills, ",");

	ArrayList<String> skills = new ArrayList<String>();

	while (st.hasMoreTokens()) {
	    String token = st.nextToken().trim();

	    // for dict_fullName_skills
	    skills.add(token);

	    // for dict_skill_fullNames
	    ArrayList<String> fullNames = this.dict_skill_fullNames.get(token);
	    if (fullNames == null)
		fullNames = new ArrayList<String>();

	    fullNames.add(fullName);
	    this.dict_skill_fullNames.put(token, fullNames);
	}
	// for dict_fullName_skills
	dict_fullName_skills.put(fullName, skills);
	return;
    }

    public ArrayList<Pair<String, Double>> calculate() {
	System.out.println("[SUCCESS] Start calculating...");

	int doc_total; // total skill count under one entry
	int docs_total = this.calculateDocsTotal(); // total skill count under all entries
	int term_occur_in_docs; // total skill appearence under all entries

	for (Entry<String, ArrayList<String>> entry : this.dict_skill_fullNames.entrySet()) {
	    String skill = entry.getKey();
	    ArrayList<String> names = entry.getValue();

	    term_occur_in_docs = names.size();

	    double tfidf = 0;
	    double totalTermCount = names.size();

	    for (String name : names) {
		ArrayList<String> skills = this.dict_fullName_skills.get(name);
		doc_total = skills.size();
		double temp = calculator.tfIdf(doc_total, docs_total, term_occur_in_docs);
		tfidf = tfidf + temp;
	    }

	    tfidf = tfidf / totalTermCount;

	    Pair<String, Double> temp = new Pair<String, Double>(skill, tfidf);
	    this.tfidfList.add(temp);
	}

	return this.tfidfList;
    }

    public void print_tfidfList() {
	for (Pair<String, Double> p : this.tfidfList) {
	    System.out.println("[INFO] term: " + p.getKey() + ": tfidf " + p.getValue());
	}
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

    private int calculateDocsTotal() {
	int docs_total = 0;

	for (Entry<String, ArrayList<String>> entry : this.dict_fullName_skills.entrySet()) {
	    for (String skill : entry.getValue())
		docs_total++;
	}

	return docs_total;
    }
}