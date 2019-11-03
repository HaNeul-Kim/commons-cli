package com.tistory.hskimsky.commons.cli;

import com.tistory.hskimsky.commons.cli.exception.DefaultParsingException;
import com.tistory.hskimsky.commons.cli.option.DefaultOption;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * command line parser
 *
 * @author Haneul, Kim
 */
public abstract class CommandLineParser implements Serializable {

  private static final long serialVersionUID = 854465916838323860L;

  protected abstract Options setValues(Options options, String[] args);

  public CommandLine parse(Options cliOptions, String[] args) throws DefaultParsingException {
    return new DefaultCommandLine(this.setValues(cliOptions, args));
  }

  public static boolean isName(String arg) {
    return isShortName(arg) || isLongName(arg);
  }

  public static boolean isShortName(String arg) {
    return !isLongName(arg) && arg.startsWith(DefaultOption.SHORT_PREFIX);
  }

  public static boolean isLongName(String arg) {
    if (StringUtils.isEmpty(arg)) {
      return false;
    }
    return arg.startsWith(DefaultOption.LONG_PREFIX);
  }

  public static String trimDash(String dashedName) {
    if (dashedName == null) {
      return null;
    }
    if (isLongName(dashedName)) {
      return dashedName.substring(DefaultOption.LONG_PREFIX.length());
    } else if (isShortName(dashedName)) {
      return dashedName.substring(DefaultOption.SHORT_PREFIX.length());
    }

    return dashedName;
  }
}
