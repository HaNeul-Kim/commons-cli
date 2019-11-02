package com.tistory.hskimsky.commons.cli;

import java.io.Serializable;
import java.util.Objects;

/**
 * default argument of cli default option
 *
 * @author Haneul, Kim
 */
public class DefaultArgument implements Argument, Serializable {

  private static final long serialVersionUID = 3134313555773078456L;

  private String shortName;

  private String longName;

  private String defaultValue;

  private String value;

  public DefaultArgument(String shortName, String longName, String defaultValue, String value) {
    this.shortName = shortName;
    this.longName = longName;
    this.defaultValue = defaultValue;
    this.value = value;
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

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
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

    if (!shortName.equals(that.shortName)) {
      return false;
    }
    if (!longName.equals(that.longName)) {
      return false;
    }
    if (!Objects.equals(defaultValue, that.defaultValue)) {
      return false;
    }
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    int result = shortName.hashCode();
    result = 31 * result + longName.hashCode();
    result = 31 * result + (defaultValue != null ? defaultValue.hashCode() : 0);
    result = 31 * result + value.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "DefaultArgument{" +
        "shortName='" + shortName + '\'' +
        ", longName='" + longName + '\'' +
        ", defaultValue='" + defaultValue + '\'' +
        ", value='" + value + '\'' +
        '}';
  }

  @Override
  public String helpString() {
    return DefaultOption.SHORT_PREFIX + this.shortName + "|" + DefaultOption.LONG_PREFIX + this.longName +
        " <arg:" + this.defaultValue + ">";
  }
}
