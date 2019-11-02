package com.tistory.hskimsky.commons.cli.experimental;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * default value 를 해석할 수 있는 Parser
 *
 * @author Haneul, Kim
 */
public class DefaultSettableParser extends Parser {

  @Override
  protected String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) {
    // TODO defaultValue 가 arguments 에 들어가기 때문에 required, arg 를 무조건 false 로 하게 되니 옵션 설정이 복잡해짐
    List<String> args = new ArrayList<>(Arrays.asList(arguments));
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
      } else if (option instanceof Option) {

      }
    }
    System.out.println("args = " + args);
    return args.toArray(new String[0]);
  }
}
