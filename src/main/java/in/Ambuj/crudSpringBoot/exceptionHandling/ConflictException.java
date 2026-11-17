package in.Ambuj.crudSpringBoot.exceptionHandling;

public class ConflictException extends RuntimeException{
    public  ConflictException(String mess){
        super(mess);
    }
}
