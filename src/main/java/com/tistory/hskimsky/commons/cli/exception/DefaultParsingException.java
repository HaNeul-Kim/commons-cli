package com.tistory.hskimsky.commons.cli.exception;

/**
 * default parsing exception
 *
 * @author Haneul, Kim
 */
public class DefaultParsingException extends RuntimeException {

  private static final long serialVersionUID = 143420519378314950L;

  public DefaultParsingException() {
    super();
  }

  public DefaultParsingException(String message) {
    super(message);
  }

  public DefaultParsingException(String message, Throwable cause) {
    super(message, cause);
  }

  public DefaultParsingException(Throwable cause) {
    super(cause);
  }

  protected DefaultParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
