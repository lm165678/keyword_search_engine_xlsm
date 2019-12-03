package KeywordSearchEngine.model;

import KeywordSearchEngine.util.MessageHandler;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import static com.mongodb.client.model.Filters.*;
import org.bson.Document;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Zhongjie Shen
 */
public class DBHandler {
  private String dbName;
  private String colName;
  private MongoClient mongoClient;
  private MongoDatabase database;

  /**
   * constructor. will not init db connection until run init()
   * 
   * @return null
   */
  public DBHandler() {
    return;
  }

  /**
   * init database with dbname
   * 
   * @param dbName database name
   * @return true if db initialied correctlyt
   */
  public MongoDatabase init(String dbName) {
    this.dbName = dbName;
    // this.colName = colName;

    try {
      this.mongoClient = new MongoClient("localhost", 27017);
      this.database = this.connectDatabase(this.dbName);
    } catch (DatabaseNotFoundException e) {
      MessageHandler.errorMessage(e.getMessage());
      return null;
    } catch (Exception e) {
      MessageHandler.errorMessage(e.getMessage());
      return null;
    }

    MessageHandler.successMessage("database " + database.getName() + " connected.");
    return this.database;
  }

  /**
   * this method ends all handler instances.
   */
  public void end() {
    this.mongoClient.close();
    MessageHandler.successMessage("connection closed");
  }

  /**
   * connect to Collection and return the MongoDatabase if db exists
   * 
   * @param name database name
   * @return MongoDatabase
   */
  private MongoDatabase connectDatabase(String name) throws DatabaseNotFoundException {
    if (!this.isValidDb(name)) {
      throw new DatabaseNotFoundException(
          "database " + this.dbName + " does not exist, please check");
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
    if (!this.isValidCol(name)) {
      throw new CollectionNotFoundException("collection " + name + " does not exist, please check");
    }
    return database.getCollection(name);
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
    return (colNames.contains(name));
  }

  /**
   * create a new document under current opened collection TODO: error prevention for later
   * 
   * @param name document name
   * @return document object
   */
  public Document newDocument(String name) {
    Document doc = new Document("name", name);
    return doc;
  }

  /**
   * insert a document to selected collection, this will add the document without minding
   * duplications
   *
   * @param doc Document
   * @param colName target collection name
   * @return target collection object
   */
  public MongoCollection insertDocument(Document doc, String colName) {
    MongoCollection collection;
    try {
      collection = connectCollection(colName);
    } catch (CollectionNotFoundException e) {
      MessageHandler.errorMessage(e.getMessage());
      return null;
    }
    collection.insertOne(doc);
    return collection;
  }

  /**
   * update a document to selected collection, if document is not found, it will create a new one.
   * FIXME: this implementation assumes there's no duplications of value "name"
   *
   * @param  doc     Document
   * @param  colName target collection name
   * @return         target collection object
   */
  public MongoCollection updateDocument(Document doc, String colName) {
    MongoCollection collection;
    try {
      collection = connectCollection(colName);
    } catch (CollectionNotFoundException e) {
      MessageHandler.errorMessage(e.getMessage());
      return null;
    }
    MongoIterable found = collection.find(eq("name", doc.get("name")));
    while 

    return collection;
  }
}
