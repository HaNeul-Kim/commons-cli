package com.tistory.hskimsky.commons.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.UnrecognizedOptionException;

import java.io.Serializable;

/**
 * AbstractJob
 *
 * @author Haneul, Kim
 * @see com.tistory.hskimsky.TestJob
 */
public abstract class AbstractJob implements Serializable {

  private Options options;

  protected void addOption(boolean required, String opt, String longOpt, boolean hasArg, String description) {
    addOption(required, opt, longOpt, hasArg, String.class, description);
  }

  protected void addOption(boolean required, String opt, String longOpt, boolean hasArg, char valueSeparator, String description) {
    addOption(required, opt, longOpt, hasArg, String.class, valueSeparator, description);
  }

  protected void addOption(boolean required, String opt, String longOpt, boolean hasArg, Class<?> type, String description) {
    addOption(required, opt, longOpt, hasArg, type, (char) 0, description);
  }

  protected void addOption(boolean required, String opt, String longOpt, boolean hasArg, Class<?> type, char valueSeparator, String description) {
    if (!required && hasArg) {
      throw new IllegalArgumentException("use instead of addOption(String, String, String, String) or addOption(String, String, Class<?>, String, char, String)");
    }
    Option option = new Option(opt, longOpt, hasArg, description);
    option.setRequired(required);
    option.setType(type);
    option.setValueSeparator(valueSeparator);
    this.options.addOption(option);
  }

  protected void addOption(String opt, String longOpt, String defaultValue, String description) {
    addOption(opt, longOpt, String.class, defaultValue, (char) 0, description);
  }

  protected void addOption(String opt, String longOpt, Class<?> type, String defaultValue, char valueSeparator, String description) {
    Option option = new DefaultOption(opt, longOpt, description, defaultValue, valueSeparator);
    option.setType(type);
    this.options.addOption(option);
  }

  protected void setOptions(String[] args) throws Exception {

  }

  protected abstract void setup(CommandLine cli) throws Exception;

  protected abstract void cleanup() throws Exception;

  protected abstract int process() throws Exception;

  public int run(String[] args) throws Exception {
    this.options = new Options();
    addOption(false, "h", "help", false, "print usage");
    setOptions(args);

    HelpFormatter h = new HelpFormatter();
    CommandLineParser parser = new DefaultSettableParser();
    CommandLine cli = null;
    try {
      cli = parser.parse(this.options, args);
    } catch (UnrecognizedOptionException | MissingOptionException e) {
      e.printStackTrace();
      h.printHelp(this.getClass().getSimpleName(), this.options, true);
      return Constants.JOB_FAIL;
    }
    if (cli.hasOption("help")) {
      h.printHelp(this.getClass().getSimpleName(), this.options, true);
      return Constants.JOB_FAIL;
    }

    setup(cli);
    int exitCode = process();
    cleanup();
    return exitCode;
  }
}
