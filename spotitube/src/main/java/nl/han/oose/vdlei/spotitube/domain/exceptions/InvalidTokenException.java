package nl.han.oose.vdlei.spotitube.domain.exceptions;

/**
 * Custom exception to catch the possibility of an invalid token
 * @throws InvalidTokenException
 * */
public class InvalidTokenException extends RuntimeException{
  public InvalidTokenException(String errorMessage) throws InvalidTokenException {
    super(errorMessage);
  }
}
