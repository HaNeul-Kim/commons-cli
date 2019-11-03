package com.tistory.hskimsky.commons.cli;

import com.tistory.hskimsky.commons.cli.exception.DefaultParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * default command line parser
 *
 * @author Haneul, Kim
 */
public class DefaultCommandLineParser extends CommandLineParser {

  private static final long serialVersionUID = -2156043185779206079L;

  private static final Logger logger = LoggerFactory.getLogger(DefaultCommandLineParser.class);

  @Override
  protected Options setValues(Options options, String[] args) {
    String currentName = "";
    for (String arg : args) {
      boolean isName = CommandLineParser.isName(arg);
      if (isName) {
        boolean isShortName = CommandLineParser.isShortName(arg);
        boolean isLongName = CommandLineParser.isLongName(arg);
        if (!isShortName && !isLongName) {
          logger.warn("Unknown option name '{}'.", currentName);
          // throw new DefaultParsingException("Unknown option name '" + currentName + "'.");
        }
        currentName = CommandLineParser.trimDash(arg);
      } else {
        options.setOption(currentName, arg);
      }
    }
    for (Option option : options.get()) {
      if (option.isRequired() && !option.isSet()) {
        throw new DefaultParsingException(option.helpString());
      }
    }
    return options;
  }
}
