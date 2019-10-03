package KeywordSearchEngine.model;

public class DatabaseNotFoundException extends Exception {
    public DatabaseNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}