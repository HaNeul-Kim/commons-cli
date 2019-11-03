package com.tistory.hskimsky.commons.cli.option;

import com.tistory.hskimsky.commons.cli.Argument;
import com.tistory.hskimsky.commons.cli.Option;

/**
 * default argument of cli default option
 *
 * @author Haneul, Kim
 */
public class DefaultArgument implements Argument {

  private static final long serialVersionUID = 3134313555773078456L;

  private Option parent;

  private String shortName;

  private String longName;

  private String defaultValue;

  public DefaultArgument(Option parent, String shortName, String longName, String defaultValue) {
    this.parent = parent;
    this.shortName = shortName;
    this.longName = longName;
    this.defaultValue = defaultValue;
  }

  public Option getParent() {
    return parent;
  }

  @Override
  public String getValue() {
    return this.defaultValue;
  }

  public void setParent(Option parent) {
    this.parent = parent;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getLongName() {
    return longName;
  }

  public void setLongName(String longName) {
    this.longName = longName;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DefaultArgument that = (DefaultArgument) o;
    return parent.equals(that.parent) &&
        shortName.equals(that.shortName) &&
        longName.equals(that.longName);
  }

  @Override
  public int hashCode() {
    int result = parent.hashCode();
    result = 31 * result + shortName.hashCode();
    result = 31 * result + longName.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "DefaultArgument{" +
        "parent='" + parent.getLongName() + '\'' +
        ", shortName='" + shortName + '\'' +
        ", longName='" + longName + '\'' +
        ", defaultValue='" + defaultValue + '\'' +
        '}';
  }

  @Override
  public String helpString() {
    return DefaultOption.SHORT_PREFIX + this.shortName + "|" + DefaultOption.LONG_PREFIX + this.longName +
        " <arg:" + this.defaultValue + ">";
  }
}
