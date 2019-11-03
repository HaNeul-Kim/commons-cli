package com.tistory.hskimsky.commons.cli.option;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import com.tistory.hskimsky.commons.cli.Option;
import com.tistory.hskimsky.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * default options
 *
 * @author Haneul, Kim
 */
public class DefaultOptions implements Options {

  private static final long serialVersionUID = -1707127498316502247L;

  private static final Logger logger = LoggerFactory.getLogger(DefaultOptions.class);

  // private List<Option> requiredOptions;

  private Multimap<String, DefaultOption> optionGroups;

  private Map<String, Option> shortOptions;

  private Map<String, Option> longOptions;

  public DefaultOptions() {
    // this.requiredOptions = new ArrayList<>();
    this.shortOptions = new TreeMap<>();
    this.longOptions = new TreeMap<>();
    this.optionGroups = TreeMultimap.create();
  }

  @Override
  public void addOption(Option option) {
    // if (option.isRequired()) {
    //   this.requiredOptions.add(option);
    // }
    this.optionGroups.put(option.getParent().getName(), (DefaultOption) option);
    this.shortOptions.put(option.getShortName(), option);
    this.longOptions.put(option.getLongName(), option);
  }

  @Override
  public boolean hasOption(String name) {
    return this.shortOptions.containsKey(name) || this.longOptions.containsKey(name);
  }

  @Override
  public Collection<Option> get() {
    return shortOptions.values();
  }

  private Option getOption(String name) {
    return this.longOptions.containsKey(name) ? this.longOptions.get(name) : this.shortOptions.get(name);
  }

  @Override
  public String getValue(String name) {
    Option option = this.getOption(name);
    return option == null ? null : option.getValue();
  }

  @Override
  public String[] getValues(String name) {
    Option option = this.getOption(name);
    return option == null ? null : option.getValues();
  }

  @Override
  public void deleteOption(String name) {
    this.shortOptions.remove(name);
    this.longOptions.remove(name);
  }

  @Override
  public void setOption(String name, String value) {
    Option option = this.getOption(name);
    option.setValue(value);
    this.shortOptions.put(option.getShortName(), option);
    this.longOptions.put(option.getLongName(), option);
  }
}
