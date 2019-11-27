package KeywordSearchEngine.model;

import KeywordSearchEngine.util.MessageHandler;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import java.util.List;
import java.util.ArrayList;
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
   * constructor. will not init db connection until run init()
   * 
   * @return null
   */
  public DBHandler() {
    return;
  }

  /**
   * init data base with dbName and colName
   * 
   * @return true if connection is built
   */
  public boolean init(String dbName, String colName) {
    this.dbName = dbName;
    this.colName = colName;

    try {
      this.mongoClient = new MongoClient("localhost", 27017);
      this.database = this.connectDatabase(this.dbName);
      this.collection = this.connectCollection(this.colName);
    } catch (CollectionNotFoundException e) {
      MessageHandler.errorMessage(e.getMessage());
      return false;
    } catch (DatabaseNotFoundException e) {
      MessageHandler.errorMessage(e.getMessage());
      return false;
    } catch (Exception e) {
      MessageHandler.errorMessage(e.getMessage());
      return false;
    }

    MessageHandler.successMessage("database " + database.getName()
        + " connected. collection chosen: " + collection.getNamespace());
    return true;
  }

  /**
   * this method ends all handler instances.
   */
  public void end() {
    this.mongoClient.close();
    MessageHandler.successMessage("database closed");
  }

  /**
   * connect to Collection and return the MongoDatabase if db exists
   * 
   * @param name database name
   * @return MongoDatabase
   */
  private MongoDatabase connectDatabase(String name) throws DatabaseNotFoundException {
    if (!this.isValidDb(name)) {
      throw new DatabaseNotFoundException("database " + this.dbName + " does not exist, please check");
    }
    return mongoClient.getDatabase(this.dbName);
  }

  /**
   * connect to Collection and return the MongoCollection if col exists
   * 
   * @param name collection name
   * @return MongoCollection
   */
  private MongoCollection connectCollection(String name) throws CollectionNotFoundException {
    if (!this.isValidCol(this.colName)) {
      throw new CollectionNotFoundException("collection " + name + " does not exist, please check");
    }
    return database.getCollection(this.colName);
  }

  /**
   * check if db exists
   * 
   * @param name db name
   * @return true if exist, vise versa
   */
  private boolean isValidDb(String name) {
    List<String> dbNames = this.mongoClient.getDatabaseNames();
    return (dbNames.contains(this.dbName));
  }

  /**
   * check if col exists
   * 
   * @param name col name
   * @return true if exist, vise versa
   */
  private boolean isValidCol(String name) {
    List<String> colNames = this.database.listCollectionNames().into(new ArrayList<String>());
    return (colNames.contains(this.colName));
  }

  /**
   * create a new document under current opened collection
   * TODO: error prevention for later
   * @param  name document name
   * @return      document object
   */
  public Document newDocument(String name) {
    Document doc = new Document("name", name);
    return doc;
  }

  /**
   * insert a document to current collection
   * @param  doc document object
   * @return     collection object
   */
  public MongoCollection insertDocument(Document doc) {
    this.collection.insertOne(doc);
    return this.collection;
  }
}
