package com.tistory.hskimsky.commons.cli.experimental;

import org.apache.commons.cli.Option;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * default value 를 설정할 수 있는 Option
 *
 * @author Haneul, Kim
 */
public class DefaultOption extends Option {

  private String defaultValue;

  private List<String> values;

  public DefaultOption(String opt, String description) throws IllegalArgumentException {
    this(opt, null, description);
  }

  public DefaultOption(String opt, String longOpt, String description) throws IllegalArgumentException {
    this(opt, longOpt, description, null);
  }

  public DefaultOption(String opt, String longOpt, String description, String defaultValue) throws IllegalArgumentException {
    this(opt, longOpt, description, defaultValue, "");
  }

  public DefaultOption(String opt, String longOpt, String description, String defaultValue, String valueArraySeparator) throws IllegalArgumentException {
    super(opt, longOpt, true, description);
    this.defaultValue = defaultValue;

    setRequired(false);
    setOptionalArg(false);

    this.values = new ArrayList<>();
    if (StringUtils.isNotEmpty(valueArraySeparator)) {
      String value = this.defaultValue;

      int index = value.indexOf(valueArraySeparator);
      while (index != -1) {
        this.values.add(value.substring(0, index));
        setArgs(getArgs() + 1);
        value = value.substring(index + 1);
        index = value.indexOf(valueArraySeparator);
      }
      this.values.add(value);
    } else {
      this.values.add(this.defaultValue);
    }
  }

  @Override
  public String getValue() {
    return defaultValue;
  }

  @Override
  public List getValuesList() {
    System.out.println("DefaultOption.getValuesList() this.values = " + this.values);
    return this.values;
  }

  @Override
  public String getDescription() {
    return super.getDescription() + " default value is \"" + getValue() + "\"";
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder().append("[ default option: ");

    sb.append(getOpt());

    if (getLongOpt() != null) {
      sb.append(" ").append(getLongOpt());
    }

    sb.append(" ");

    if (hasArgs()) {
      sb.append("[ARG...]");
    } else if (hasArg()) {
      sb.append(" [ARG]");
    }

    sb.append(" :: ").append(getDescription());

    if (getType() != null) {
      sb.append(" :: ").append(getType());
    }

    sb.append(" :: ").append(getValue());

    sb.append(" ]");

    return sb.toString();
  }
}
