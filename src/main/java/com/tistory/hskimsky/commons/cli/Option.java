package com.tistory.hskimsky.commons.cli;

import java.util.List;

/**
 * cli option
 *
 * @author Haneul, Kim
 */
public interface Option {

  String getShortName();

  String getLongName();

  boolean hasArgument();

  List<Argument> getArguments();

  String getValue();

  String helpString();
}
