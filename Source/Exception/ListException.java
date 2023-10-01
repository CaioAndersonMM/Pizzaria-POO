package Exception;

import java.sql.SQLException;

public class ListException extends Exception {
    public ListException(String mensagem, SQLException causa) {
        super(mensagem, causa);
    }
}
