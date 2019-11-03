package com.tistory.hskimsky.commons.cli;

import java.io.Serializable;
import java.util.Collection;

/**
 * options
 *
 * @author Haneul, Kim
 */
public interface Options extends Serializable {

  void addOption(Option option);

  boolean hasOption(String name);

  Collection<Option> get();

  String getValue(String name);

  String[] getValues(String name);

  void deleteOption(String name);

  void setOption(String name, String value);
}
