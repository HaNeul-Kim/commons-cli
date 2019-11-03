package com.tistory.hskimsky.commons.cli;

import java.io.Serializable;

/**
 * cli option. group 의 하위 속성. short name, long name 을 설정할 수 있고, 무조건 argument 를 받음.
 *
 * @author Haneul, Kim
 */
public interface Option extends Serializable, Cloneable {

  OptionGroup getParent();

  boolean isRequired();

  String getShortName();

  String getLongName();

  boolean hasDefault();

  boolean isSet();

  boolean setValue(String value);

  String getValue();

  String[] getValues();

  boolean isArray();

  String helpString();
}
