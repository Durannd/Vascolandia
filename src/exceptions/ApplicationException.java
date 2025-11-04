package exceptions;

/**
 * Exceção base da aplicação — unchecked (RuntimeException).
 * Todas as exceções customizadas podem estender esta classe.
 */
public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}

