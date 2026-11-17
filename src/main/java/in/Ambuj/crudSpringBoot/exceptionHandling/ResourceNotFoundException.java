package in.Ambuj.crudSpringBoot.exceptionHandling;

public class ResourceNotFoundException extends RuntimeException{
    public  ResourceNotFoundException(String message){
        super(message);
    }
}
