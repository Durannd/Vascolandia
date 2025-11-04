package exceptions;

/**
 * Exceção para camadas de acesso a dados (DAO), usada para encapsular
 * SQLExceptions e outros erros de infraestrutura.
 */
public class DataAccessException extends ApplicationException {
    private static final long serialVersionUID = 1L;

    public DataAccessException() {
        super();
    }

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }
}

