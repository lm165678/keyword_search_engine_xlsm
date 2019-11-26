package KeywordSearchEngine.util;

public class TimeTrackerTaskAlreadyExistException extends TimeTrackerException{
    
    // Constructor
    public TimeTrackerTaskAlreadyExistException(String errorMessage){
        super(errorMessage);
    }
    
}