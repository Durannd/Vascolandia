package exceptions;

/**
 * Exceção para indicar que um recurso (ex: Cliente, Pedido) não foi encontrado.
 */
public class ResourceNotFoundException extends ApplicationException {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}

