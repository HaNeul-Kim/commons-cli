package com.tistory.hskimsky.commons.cli;

import com.tistory.hskimsky.commons.cli.builder.DefaultOptionBuilder;
import com.tistory.hskimsky.commons.cli.exception.DefaultParsingException;
import com.tistory.hskimsky.commons.cli.option.DefaultOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * AbstractJob
 *
 * @author Haneul, Kim
 */
public abstract class AbstractJob implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(AbstractJob.class);

  // private List<Option> options;
  private Options options;

  protected void addOption(boolean required, String shortName, String longName, String description) {
    addOption(required, shortName, longName, null, description);
  }

  protected void addOption(boolean required, String shortName, String longName, String defaultValue, String description) {
    addOption(required, shortName, longName, defaultValue, null, description);
  }

  protected void addOption(boolean required, String shortName, String longName,
                           String defaultValue, String arraySeparator, String description) {
    this.options.addOption(new DefaultOptionBuilder().required(required).shortName(shortName).longName(longName).
        defaultValue(defaultValue).arraySeparator(arraySeparator).description(description).build());
  }

  protected abstract void setOptions() throws Exception;

  protected abstract void setup(CommandLine cli) throws Exception;

  protected abstract void cleanup() throws Exception;

  protected abstract int process() throws Exception;

  public int run(String[] args) throws Exception {
    this.options = new DefaultOptions();
    addOption(false, "h", "help", "print usage");
    setOptions();

    // HelpFormatter h = new HelpFormatter();
    DefaultCommandLineParser parser = new DefaultCommandLineParser();
    CommandLine cli = null;
    try {
      cli = parser.parse(this.options, args);
    } catch (DefaultParsingException e) {
      e.printStackTrace();
      // h.printHelp(this.getClass().getSimpleName(), this.options, true);
      return Constants.JOB_FAIL;
    }
    if (cli.fromCliOption("help")) {
      // h.printHelp(this.getClass().getSimpleName(), this.options, true);
      return Constants.JOB_FAIL;
    }

    setup(cli);
    int exitCode = process();
    cleanup();
    return exitCode;
  }
}
