package com.tistory.hskimsky;

import com.tistory.hskimsky.commons.cli.AbstractJob;
import org.apache.commons.cli.CommandLine;

import java.util.Arrays;

/**
 * AbstractJob example
 *
 * @author Haneul, Kim
 */
public class TestJob extends AbstractJob {

  private static final long serialVersionUID = 6007570799467354348L;

  public static void main(String[] args) throws Exception {
    args = new String[]{"-r", "--requiredParameter", "REQUIRED_PARAMETER"};
    new TestJob().run(args);
  }

  @Override
  protected void setOptions(String[] args) throws Exception {
    addOption(true, "r", "requiredOption", false, "required option.");
    addOption(true, "rp", "requiredParameter", true, "required parameter.");
    addOption(false, "oi", "optionalParameterInt", false, Integer.TYPE, "optional parameter int.");
    // addOption(false, "ol", "optionalParameterLong", true, Long.class, "optional parameter long.");// use instead of addOption(String, String, String, String) or addOption(String, String, Class<?>, String, char, String)
    addOption("d", "defaultValue", "DEFAULT_VALUE", "default value.");
    addOption("di", "defaultInt", Integer.TYPE, "1,2,3,4", ',', "default int 1,2,3,4.");
    addOption("ds", "defaultString", String.class, "s1|s2|s3", '|', "default string s1,s2,s3.");
  }

  @Override
  protected void setup(CommandLine cli) throws Exception {
    boolean requiredOption = cli.hasOption("r");
    System.out.println("requiredOption = " + requiredOption);
    String requiredParameter = cli.getOptionValue("requiredParameter");
    System.out.println("requiredParameter = " + requiredParameter);
    if (cli.hasOption("optionalParameterInt")) {
      int optionalParameterInt = Integer.parseInt(cli.getOptionValue("optionalParameterInt"));
      System.out.println("optionalParameterInt = " + optionalParameterInt);
    }
    String defaultValue = cli.getOptionValue("defaultValue");
    System.out.println("defaultValue = " + defaultValue);
    String[] defaultInts = cli.getOptionValues("defaultInt");
    System.out.println("defaultInts = " + Arrays.toString(defaultInts));
    String[] defaultStrings = cli.getOptionValues("defaultString");
    System.out.println("defaultStrings = " + Arrays.toString(defaultStrings));
  }

  @Override
  protected void cleanup() throws Exception {

  }

  @Override
  protected void process() throws Exception {

  }
}
