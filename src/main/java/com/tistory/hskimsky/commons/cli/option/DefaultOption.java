package com.tistory.hskimsky.commons.cli.option;

import com.tistory.hskimsky.commons.cli.Argument;
import com.tistory.hskimsky.commons.cli.Option;
import com.tistory.hskimsky.commons.cli.OptionGroup;
import com.tistory.hskimsky.commons.cli.builder.DefaultArgumentBuilder;
import com.tistory.hskimsky.commons.cli.builder.DefaultOptionGroupBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * cli default option
 *
 * @author Haneul, Kim
 */
public class DefaultOption implements Option, Comparable<DefaultOption> {

  private static final long serialVersionUID = -2862383218496235978L;

  public static final String SHORT_PREFIX = "-";

  public static final String LONG_PREFIX = "--";

  private OptionGroup parent;

  private boolean required;

  private String shortName;

  private String longName;

  private List<Argument> defaultArguments;

  private List<Argument> arguments;

  private String defaultValue;

  private String value;

  private boolean set;

  private boolean hasDefault;

  private String arraySeparator;

  private boolean array;

  private String description;

  public DefaultOption(boolean required, String shortName, String longName,
                       String defaultValue, String arraySeparator,
                       String description) {
    this(required, "default", shortName, longName,
        defaultValue, arraySeparator, description);
  }

  public DefaultOption(boolean required, String groupName, String shortName, String longName,
                       String defaultValue, String arraySeparator,
                       String description) {
    this(required, new DefaultOptionGroupBuilder().name(groupName).build(), shortName, longName,
        defaultValue, arraySeparator, description);
  }

  public DefaultOption(boolean required, OptionGroup parent, String shortName, String longName,
                       String defaultValue, String arraySeparator,
                       String description) {
    this.required = required;
    this.parent = parent;
    this.shortName = shortName;
    this.longName = longName;
    this.defaultArguments = new ArrayList<>();
    this.arguments = new ArrayList<>();
    this.defaultValue = defaultValue;
    this.hasDefault = StringUtils.isNotEmpty(this.defaultValue);
    this.value = null;
    this.arraySeparator = StringUtils.isEmpty(arraySeparator) ? null : arraySeparator;
    this.array = StringUtils.isNotEmpty(this.arraySeparator);
    this.description = description;

    if (this.hasDefault) {
      if (this.array) {
        String value = this.defaultValue;

        int index = value.indexOf(this.arraySeparator);
        while (index != -1) {
          DefaultArgument argument = new DefaultArgumentBuilder().parent(this).
              shortName(this.shortName).longName(this.longName).value(value.substring(0, index)).build();
          // this.arguments.add(argument);
          this.defaultArguments.add(argument);
          // setArgs(getArgs() + 1);
          value = value.substring(index + 1);
          index = value.indexOf(this.arraySeparator);
        }
        this.defaultArguments.add(new DefaultArgumentBuilder().parent(this).
            shortName(this.shortName).longName(this.longName).value(value).build());
      } else {
        DefaultArgument argument = new DefaultArgumentBuilder().parent(this).
            shortName(this.shortName).longName(this.longName).value(this.defaultValue).build();
        // this.arguments.add(argument);
        this.defaultArguments.add(argument);
      }
    }
  }

  @Override
  public String helpString() {
    StringBuilder sb = new StringBuilder();
    sb.append("OptionGroup: ").append(this.parent.getName()).append("\n");
    if (this.required) {
      sb.append("REQUIRED");
    }
    sb.append(" ").append(DefaultOption.SHORT_PREFIX).append(this.shortName).append("|").append(DefaultOption.LONG_PREFIX).append(this.longName);
    sb.append(" <arg:").append(this.defaultValue).append(">");
    if (this.array) {
      sb.append("[]");
    }
    sb.append(" "/*TODO padding*/).append(this.description);
    return sb.toString();
  }

  @Override
  public OptionGroup getParent() {
    return parent;
  }

  public void setParent(OptionGroup parent) {
    this.parent = parent;
  }

  @Override
  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  @Override
  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  @Override
  public String getLongName() {
    return longName;
  }

  public void setLongName(String longName) {
    this.longName = longName;
  }

  public List<Argument> getDefaultArguments() {
    return defaultArguments;
  }

  public void setDefaultArguments(List<Argument> defaultArguments) {
    this.defaultArguments = defaultArguments;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  @Override
  public String getValue() {
    return this.hasDefault && StringUtils.isEmpty(this.value) ? this.defaultValue : this.value;
  }

  @Override
  public String[] getValues() {
    String[] values = null;
    if (this.hasDefault && StringUtils.isEmpty(this.value)) {
      if (this.array) {
        values = this.defaultArguments.stream().map(Argument::getValue).toArray(String[]::new);
      } else {
        values = new String[]{this.defaultValue};
      }
    } else {
      if (this.array) {
        values = this.arguments.stream().map(Argument::getValue).toArray(String[]::new);
      } else {
        values = new String[]{this.value};
      }
    }
    return values;
  }

  @Override
  public boolean hasDefault() {
    return hasDefault;
  }

  @Override
  public boolean isSet() {
    return set;
  }

  @Override
  public boolean setValue(String value) {
    this.set = true;
    this.value = value;
    if (this.array) {
      int index = value.indexOf(this.arraySeparator);
      while (index != -1) {
        DefaultArgument argument = new DefaultArgumentBuilder().parent(this).
            shortName(this.shortName).longName(this.longName).value(value.substring(0, index)).build();
        this.arguments.add(argument);
        value = value.substring(index + 1);
        index = value.indexOf(this.arraySeparator);
      }
    }
    this.arguments.add(new DefaultArgumentBuilder().parent(this).
        shortName(this.shortName).longName(this.longName).value(value).build());
    return true;
  }

  public String getArraySeparator() {
    return arraySeparator;
  }

  public void setArraySeparator(String arraySeparator) {
    this.arraySeparator = arraySeparator;
  }

  @Override
  public boolean isArray() {
    return array;
  }

  public void isArray(boolean array) {
    this.array = array;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public int compareTo(DefaultOption o) {
    return longName.compareTo(o.longName);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (o instanceof String) {
      String name = (String) o;
      return shortName.equals(name) || longName.equals(name);
    }
    DefaultOption that = (DefaultOption) o;
    return shortName.equals(that.shortName) || longName.equals(that.longName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(shortName, longName);
  }

  @Override
  public String toString() {
    return "DefaultOption{" +
        "parent='" + parent.getName() + '\'' +
        ", required=" + required +
        ", shortName='" + shortName + '\'' +
        ", longName='" + longName + '\'' +
        ", defaultArguments=" + defaultArguments +
        ", arguments=" + arguments +
        ", defaultValue='" + defaultValue + '\'' +
        ", value='" + value + '\'' +
        ", arraySeparator='" + arraySeparator + '\'' +
        ", array=" + array +
        ", description='" + description + '\'' +
        '}';
  }
}
