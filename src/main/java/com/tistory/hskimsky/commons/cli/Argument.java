package com.tistory.hskimsky.commons.cli;

import java.io.Serializable;

/**
 * argument of cli option. option 의 하위 속성.
 *
 * @author Haneul, Kim
 */
public interface Argument extends Serializable, Cloneable {

  Option getParent();

  String getValue();

  String helpString();
}
