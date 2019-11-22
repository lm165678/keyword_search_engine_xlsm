package KeywordSearchEngine.model;

public class CollectionNotFoundException extends Exception {
    public CollectionNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}