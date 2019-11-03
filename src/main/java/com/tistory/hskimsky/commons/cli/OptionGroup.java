package com.tistory.hskimsky.commons.cli;

import java.io.Serializable;
import java.util.List;

/**
 * cli option group. 자식 group 을 가질 수도 있고, 자식 option 을 가질 수도 있음.
 *
 * @author Haneul, Kim
 */
public interface OptionGroup extends Serializable, Cloneable {

  String getName();

  boolean isRoot();

  boolean contains(OptionGroup optionGroup);

  boolean contains(Option option);

  OptionGroup getParent();

  List<Option> getOptions();

  String helpString();
}
