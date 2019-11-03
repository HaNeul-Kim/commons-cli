package com.tistory.hskimsky.commons.cli.builder;

import com.tistory.hskimsky.commons.cli.Argument;
import com.tistory.hskimsky.commons.cli.OptionGroup;
import com.tistory.hskimsky.commons.cli.option.DefaultOption;
import com.tistory.hskimsky.commons.cli.option.DefaultOptionGroup;

import java.io.Serializable;
import java.util.List;

/**
 * default option builder
 *
 * @author Haneul, Kim
 */
public class DefaultOptionBuilder implements Serializable {

  private static final long serialVersionUID = 4760104204129925429L;

  private boolean required;

  private OptionGroup group;

  private String shortName;

  private String longName;

  private List<Argument> defaultArguments;

  private String defaultValue;

  private String arraySeparator;

  private String description;

  public DefaultOptionBuilder() {
  }

  public DefaultOptionBuilder required(boolean required) {
    this.required = required;
    return this;
  }

  public DefaultOptionBuilder group(OptionGroup group) {
    this.group = group;
    return this;
  }

  public DefaultOptionBuilder group(String name) {
    this.group = new DefaultOptionGroupBuilder().name(name).build();
    return this;
  }

  public DefaultOptionBuilder group(String parent, String name) {
    DefaultOptionGroup parentOptionGroup = null;
    if (parent != null) {
      parentOptionGroup = new DefaultOptionGroupBuilder().name(parent).build();
    }
    this.group = new DefaultOptionGroupBuilder().parent(parentOptionGroup).name(name).build();
    return this;
  }

  public DefaultOptionBuilder shortName(String shortName) {
    this.shortName = shortName;
    return this;
  }

  public DefaultOptionBuilder longName(String longName) {
    this.longName = longName;
    return this;
  }

  public DefaultOptionBuilder defaultArguments(List<Argument> defaultArguments) {
    this.defaultArguments = defaultArguments;
    return this;
  }

  public DefaultOptionBuilder defaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
    return this;
  }

  public DefaultOptionBuilder arraySeparator(String arraySeparator) {
    this.arraySeparator = arraySeparator;
    return this;
  }

  public DefaultOptionBuilder description(String description) {
    this.description = description;
    return this;
  }

  private void clear() {
    this.required = false;
    this.shortName = null;
    this.longName = null;
    this.defaultArguments = null;
    this.defaultValue = null;
    this.arraySeparator = null;
    this.description = null;
  }

  public DefaultOption build() {
    DefaultOption defaultOption = null;
    try {
      defaultOption = (DefaultOption) new DefaultOption(this.required, this.shortName, this.longName,
          this.defaultValue, this.arraySeparator, this.description).clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    this.clear();
    return defaultOption;
  }
}
