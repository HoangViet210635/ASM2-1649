package fgw.Assignment_2;

public class NullArgumentException extends Exception{
    public NullArgumentException(){
        super("Argument cannot be null.");
    }
    public NullArgumentException(String argument){
        super(argument);
    }
}
