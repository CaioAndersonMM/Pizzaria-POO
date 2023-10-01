package Exception;

public class AutenticationException extends Exception{
    public AutenticationException(){
        super("CPF OU SENHA NÃO ENCONTRADOS!");
    }
}
