package com.tistory.hskimsky.commons.cli.builder;

import com.tistory.hskimsky.commons.cli.Option;
import com.tistory.hskimsky.commons.cli.option.DefaultArgument;

import java.io.Serializable;

/**
 * default argument builder
 *
 * @author Haneul, Kim
 */
public class DefaultArgumentBuilder implements Serializable {

  private static final long serialVersionUID = 4005441469097942022L;

  private Option parent;

  private String shortName;

  private String longName;

  private String value;

  public DefaultArgumentBuilder() {
  }

  public DefaultArgumentBuilder parent(Option parent) {
    this.parent = parent;
    return this;
  }

  public DefaultArgumentBuilder shortName(String shortName) {
    this.shortName = shortName;
    return this;
  }

  public DefaultArgumentBuilder longName(String longName) {
    this.longName = longName;
    return this;
  }

  public DefaultArgumentBuilder value(String value) {
    this.value = value;
    return this;
  }

  private void clear() {
    this.parent = null;
    this.shortName = null;
    this.longName = null;
    this.value = null;
  }

  public DefaultArgument build() {
    DefaultArgument defaultArgument = null;
    try {
      defaultArgument = (DefaultArgument) new DefaultArgument(this.parent, this.shortName, this.longName, this.value).clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    this.clear();
    return defaultArgument;
  }
}
