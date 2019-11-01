package com.tistory.hskimsky.commons.cli;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.Parser;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * default value 를 해석할 수 있는 Parser
 *
 * @author Haneul, Kim
 */
public class DefaultSettableParser extends Parser {

  @Override
  protected String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) {
    Set<String> args = new LinkedHashSet<>(Arrays.asList(arguments));
    String longOptPrefix = "--";
    for (Object option : opts.getOptions()) {
      if (option instanceof DefaultOption) {
        DefaultOption defaultOption = (DefaultOption) option;
        String opt = defaultOption.getOpt();
        String longOpt = defaultOption.getLongOpt();
        if (!args.contains(opt) && !args.contains(longOpt)) {
          String optionName = longOptPrefix + longOpt;
          String optionValue = defaultOption.getValue();
          args.add(optionName);
          args.add(optionValue);
        }
      }
    }
    return args.toArray(new String[0]);
  }
}
