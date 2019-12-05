package KeywordSearchEngine.model;

public class DatabaseNotFoundException extends DatabaseException {

  public DatabaseNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
