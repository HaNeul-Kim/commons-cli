package com.tistory.hskimsky.commons.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * default command line
 *
 * @author Haneul, Kim
 */
public class DefaultCommandLine implements CommandLine {

  private static final long serialVersionUID = -3373908776329708189L;

  private static final Logger logger = LoggerFactory.getLogger(DefaultCommandLine.class);

  private Set<Option> options;

  private Map<String, Option> requiredOptions;

  private Map<String, Option> shortNameOptions;

  private Map<String, Option> longNameOptions;

  public DefaultCommandLine(Options options) {
    this.options = new LinkedHashSet<>();
    this.requiredOptions = new LinkedHashMap<>();
    this.shortNameOptions = new LinkedHashMap<>();
    this.longNameOptions = new LinkedHashMap<>();

    Collection<Option> optionSet = options.get();
    this.options.addAll(optionSet);
    for (Option option : optionSet) {
      if (option.isRequired()) {
        this.requiredOptions.put(option.getShortName(), option);
      }
      this.shortNameOptions.put(option.getShortName(), option);
      this.longNameOptions.put(option.getLongName(), option);
    }
  }

  public Option getOption(String name) {
    return shortNameOptions.getOrDefault(name, longNameOptions.getOrDefault(name, null));
  }

  public Set<Option> getOptions() {
    return options;
  }

  @Override
  public boolean hasOption(Option option) {
    return this.hasOption(option.getShortName());
  }

  @Override
  public boolean hasOption(String name) {
    return this.getOption(name) != null;
  }

  @Override
  public boolean fromCliOption(String name) {
    return getValue(name) != null;
  }

  @Override
  public String getValue(Option option) {
    return this.getValue(option.getShortName());
  }

  @Override
  public String getValue(String name) {
    Option option = this.getOption(name);
    return option == null ? null : option.getValue();
  }

  @Override
  public String[] getValues(Option option) {
    return this.getValues(option.getShortName());
  }

  @Override
  public String[] getValues(String name) {
    Option option = this.getOption(name);
    return option != null && option.isArray() ? option.getValues() : null;
  }
}
