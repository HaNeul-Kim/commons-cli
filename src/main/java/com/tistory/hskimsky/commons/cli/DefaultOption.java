package com.tistory.hskimsky.commons.cli;

import org.apache.commons.cli.Option;

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
    this(opt, longOpt, description, defaultValue, (char) 0);
  }

  public DefaultOption(String opt, String longOpt, String description, String defaultValue, char valueSeparator) throws IllegalArgumentException {
    super(opt, longOpt, true, description);
    this.defaultValue = defaultValue;

    setRequired(false);
    setOptionalArg(false);
    setValueSeparator(valueSeparator);

    this.values = new ArrayList<>();
    if (hasValueSeparator()) {
      String value = this.defaultValue;

      char sep = getValueSeparator();
      int index = value.indexOf(sep);
      while (index != -1) {
        this.values.add(value.substring(0, index));
        setArgs(getArgs() + 1);
        value = value.substring(index + 1);
        index = value.indexOf(sep);
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
    return this.values;
  }

  @Override
  public String getDescription() {
    return super.getDescription() + " default value is \"" + getValue() + "\"";
  }
}
