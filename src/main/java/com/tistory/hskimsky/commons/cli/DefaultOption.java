package com.tistory.hskimsky.commons.cli;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * cli default option
 *
 * @author Haneul, Kim
 */
public class DefaultOption implements Option, Serializable {

  private static final long serialVersionUID = -2862383218496235978L;

  public static final String SHORT_PREFIX = "-";

  public static final String LONG_PREFIX = "--";

  private boolean required;

  private String shortName;

  private String longName;

  private boolean hasArgument;

  private List<Argument> defaultArguments;

  private List<Argument> arguments;

  private String defaultValue;

  private String value;

  private String arraySeparator;

  private boolean isArray;

  private String description;

  public DefaultOption(boolean required, String shortName, String longName,
                       boolean hasArgument,
                       String defaultValue, String arraySeparator,
                       String description) {
    this.required = required;
    this.shortName = shortName;
    this.longName = longName;
    this.hasArgument = hasArgument;
    this.defaultArguments = new ArrayList<>();
    this.arguments = new ArrayList<>();
    this.defaultValue = defaultValue;
    this.value = "";
    this.arraySeparator = arraySeparator;
    this.isArray = StringUtils.isNotEmpty(this.arraySeparator);
    this.description = description;
  }

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
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

  public boolean hasArgument() {
    return hasArgument;
  }

  public void hasArgument(boolean hasArgument) {
    this.hasArgument = hasArgument;
  }

  public List<Argument> getDefaultArguments() {
    return defaultArguments;
  }

  public void setDefaultArguments(List<Argument> defaultArguments) {
    this.defaultArguments = defaultArguments;
  }

  public List<Argument> getArguments() {
    return arguments;
  }

  public void setArguments(List<Argument> arguments) {
    this.arguments = arguments;
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

  public String getArraySeparator() {
    return arraySeparator;
  }

  public void setArraySeparator(String arraySeparator) {
    this.arraySeparator = arraySeparator;
  }

  public boolean isArray() {
    return isArray;
  }

  public void isArray(boolean isArray) {
    this.isArray = isArray;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DefaultOption that = (DefaultOption) o;

    if (required != that.required) {
      return false;
    }
    if (hasArgument != that.hasArgument) {
      return false;
    }
    if (isArray != that.isArray) {
      return false;
    }
    if (!shortName.equals(that.shortName)) {
      return false;
    }
    if (!Objects.equals(longName, that.longName)) {
      return false;
    }
    if (!defaultArguments.equals(that.defaultArguments)) {
      return false;
    }
    if (!arguments.equals(that.arguments)) {
      return false;
    }
    if (!defaultValue.equals(that.defaultValue)) {
      return false;
    }
    if (!value.equals(that.value)) {
      return false;
    }
    if (!arraySeparator.equals(that.arraySeparator)) {
      return false;
    }
    return description.equals(that.description);
  }

  @Override
  public int hashCode() {
    int result = (required ? 1 : 0);
    result = 31 * result + shortName.hashCode();
    result = 31 * result + (longName != null ? longName.hashCode() : 0);
    result = 31 * result + (hasArgument ? 1 : 0);
    result = 31 * result + defaultArguments.hashCode();
    result = 31 * result + arguments.hashCode();
    result = 31 * result + defaultValue.hashCode();
    result = 31 * result + value.hashCode();
    result = 31 * result + arraySeparator.hashCode();
    result = 31 * result + (isArray ? 1 : 0);
    result = 31 * result + description.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "DefaultOption{" +
        "required=" + required +
        ", shortName='" + shortName + '\'' +
        ", longName='" + longName + '\'' +
        ", hasArgument=" + hasArgument +
        ", defaultArguments=" + defaultArguments +
        ", arguments=" + arguments +
        ", defaultValue='" + defaultValue + '\'' +
        ", value='" + value + '\'' +
        ", arraySeparator='" + arraySeparator + '\'' +
        ", isArray=" + isArray +
        ", description='" + description + '\'' +
        '}';
  }

  @Override
  public String helpString() {
    StringBuilder sb = new StringBuilder();
    sb.append(DefaultOption.SHORT_PREFIX).append(this.shortName).append("|").append(DefaultOption.LONG_PREFIX).append(this.longName);
    if (this.hasArgument) {
      sb.append(" <arg:").append(this.defaultValue).append(">");
    }
    return sb.toString();
  }
}
