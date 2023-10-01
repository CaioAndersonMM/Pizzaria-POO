package Exception;

public class AutenticationEmptyException extends Exception{
    public AutenticationEmptyException(){
        super("É NECESSÁRIO PREENCHER TODOS OS CAMPOS");
    }
}
