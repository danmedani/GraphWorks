

public class InvalidInputException extends Exception {
    
    public InvalidInputException(){
    }
    
    public String toString() {
	return super.toString() + ": input file must be formatted correctly!!";
    }
}