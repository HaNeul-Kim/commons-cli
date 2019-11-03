package com.tistory.hskimsky.commons.cli.option;

import com.tistory.hskimsky.commons.cli.Option;
import com.tistory.hskimsky.commons.cli.OptionGroup;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * cli default group
 *
 * @author Haneul, Kim
 */
public class DefaultOptionGroup implements OptionGroup, Comparable<DefaultOptionGroup> {

  private static final long serialVersionUID = 4737485832672656490L;

  private OptionGroup parent;

  private String name;

  private Set<OptionGroup> children;

  private List<Option> options;

  public DefaultOptionGroup(String name) {
    this.name = name;
  }

  @Override
  public boolean isRoot() {
    return this.parent == null;
  }

  @Override
  public boolean contains(OptionGroup optionGroup) {
    return children.contains(optionGroup);
  }

  @Override
  public boolean contains(Option opt) {
    for (Option option : options) {
      if (option.equals(opt)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String helpString() {
    return "OptionGroup: " + this.name;
  }

  public OptionGroup getParent() {
    return parent;
  }

  public void setParent(OptionGroup parent) {
    this.parent = parent;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<OptionGroup> getChildren() {
    return children;
  }

  public void setChildren(Set<OptionGroup> children) {
    this.children = children;
  }

  public List<Option> getOptions() {
    return options;
  }

  public void setOptions(List<Option> options) {
    this.options = options;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public int compareTo(DefaultOptionGroup o) {
    return name.compareTo(o.name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DefaultOptionGroup that = (DefaultOptionGroup) o;
    return Objects.equals(parent, that.parent) &&
        name.equals(that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parent, name);
  }

  @Override
  public String toString() {
    return "DefaultGroup{" +
        "parent='" + parent.getName() + '\'' +
        ", name='" + name + '\'' +
        ", children=" + children +
        '}';
  }
}
