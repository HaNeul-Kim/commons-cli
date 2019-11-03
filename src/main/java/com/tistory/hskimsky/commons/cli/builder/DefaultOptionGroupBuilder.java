package com.tistory.hskimsky.commons.cli.builder;

import com.tistory.hskimsky.commons.cli.Option;
import com.tistory.hskimsky.commons.cli.OptionGroup;
import com.tistory.hskimsky.commons.cli.option.DefaultOptionGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * default group builder
 *
 * @author Haneul, Kim
 */
public class DefaultOptionGroupBuilder implements Serializable {

  private static final long serialVersionUID = 5698600312725310410L;

  private OptionGroup parent;

  private String name;

  private Set<OptionGroup> children;// List ?

  private List<Option> options;

  public DefaultOptionGroupBuilder() {
    this.clear();
  }

  public DefaultOptionGroupBuilder parent(OptionGroup parent) {
    this.parent = parent;
    return this;
  }

  public DefaultOptionGroupBuilder name(String name) {
    this.name = name;
    return this;
  }

  public DefaultOptionGroupBuilder child(OptionGroup child) {
    this.children.add(child);
    return this;
  }

  public DefaultOptionGroupBuilder children(Set<OptionGroup> children) {
    this.children = children;
    return this;
  }

  public DefaultOptionGroupBuilder option(Option option) {
    this.options.add(option);
    return this;
  }

  public DefaultOptionGroupBuilder options(List<Option> options) {
    this.options = options;
    return this;
  }

  private void clear() {
    this.parent = null;
    this.name = null;
    this.children = new TreeSet<>(Comparator.comparing(OptionGroup::getName));
    this.options = new ArrayList<>();
  }

  public DefaultOptionGroup build() {
    DefaultOptionGroup defaultOptionGroupTemp = new DefaultOptionGroup(this.name);
    if (this.parent != null) {
      defaultOptionGroupTemp.setParent(this.parent);
    }
    if (this.children != null) {
      defaultOptionGroupTemp.setChildren(this.children);
    }
    if (this.options != null) {
      defaultOptionGroupTemp.setOptions(this.options);
    }
    DefaultOptionGroup defaultOptionGroup = null;
    try {
      defaultOptionGroup = (DefaultOptionGroup) defaultOptionGroupTemp.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    this.clear();
    return defaultOptionGroup;
  }
}
