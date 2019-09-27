package KeywordSearchEngine.model;

import KeywordSearchEngine.util.MessageHandler;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import java.util.List;
import java.util.Set;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Zhongjie Shen
 */
public class DBHandler {
  private String dbName;
  private String colName;
  private MongoClient mongoClient;
  private MongoDatabase database;
  private MongoCollection collection;

  /**
   * constructor
   * @return null
   */
  public DBHandler(String dbName, String colName) {
    this.dbName = dbName;
    this.colName = colName;

    return;
  }

  /**
   * init data base with dbName and colName
   * FIXME: invalid dbName or colName will also returns true. need to find some new validation method
   * @return true if connection is built
   */
  public boolean init() {
    try{
      this.mongoClient = new MongoClient("localhost", 27017);
      this.database = mongoClient.getDatabase(this.dbName);
      this.collection = database.getCollection(this.colName);
    } catch (IllegalArgumentException e) {
      MessageHandler.errorMessage(e.getMessage());
      return false;
    }
    
    MessageHandler.successMessage("database " + database.getName() + " connected. collection chosen: " + collection.getNamespace());
    return true;
  }

  /**
   * this method ends all handler instances.
   */
  public void end() {
    this.mongoClient.close();
    MessageHandler.successMessage("database closed");
  }

}
