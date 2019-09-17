package keyword_search_seedsprint;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;

import java.util.List;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Zhongjie Shen
 */
public class DBHandler {
    private MongoClient mongoClient;
    private DB database;
    private DBCollection collection;

    /**
     * constructor
     */
    public DBHandler() {
	return;
    }

    /**
     * this method init basic objects
     */
    public void init() {
	mongoClient = new MongoClient("localhost", 27017);
	database = mongoClient.getDB("workdata_seedsprint");
	collection = database.getCollection("fullname_skills");
	System.out.println("[SUCCESS] database connected. collection chosen: fullname_skills");
    }

    /**
     * this method ends all handler instances.
     */
    public void end() {
	this.mongoClient.close();
	System.out.println("[SUCCESS] database closed");
    }

}