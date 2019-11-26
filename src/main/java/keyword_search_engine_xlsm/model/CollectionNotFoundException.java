package KeywordSearchEngine.model;

public class CollectionNotFoundException extends DatabaseException {
  public CollectionNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
