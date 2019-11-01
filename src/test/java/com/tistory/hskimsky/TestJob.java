package com.tistory.hskimsky;

import com.tistory.hskimsky.commons.cli.AbstractJob;
import com.tistory.hskimsky.commons.cli.Constants;
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
    args = new String[]{"-r", "--requiredParameter", "REQUIRED_PARAMETER", "-rps", "param1,param2,param3"};
    int exitCode = new TestJob().run(args);
    System.exit(exitCode);
  }

  @Override
  protected void setOptions(String[] args) throws Exception {
    addOption(true, "r", "requiredOption", false, "required option.");
    addOption(true, "rp", "requiredParameter", true, "required parameter.");
    addOption(true, "rps", "requiredParameters", true, ',', "required parameters.");
    addOption(false, "oi", "optionalParameterInt", false, Integer.TYPE, "optional parameter int.");
    // addOption(false, "ol", "optionalParameterLong", true, Long.class, "optional parameter long.");// use instead of addOption(String, String, String, String) or addOption(String, String, Class<?>, String, char, String)
    addOption("d", "defaultValue", "DEFAULT_VALUE", "default value.");
    addOption("di", "defaultInt", Integer.TYPE, "1,2,3,4", ',', "default int 1,2,3,4.");
    addOption("ds", "defaultString", String.class, "s1|s2|s3", '|', "default string s1,s2,s3.");
    addOption("duv1", "duplicatedValue1", "duplicatedValue", "duplicated value 1.");
    addOption("duv2", "duplicatedValue2", "duplicatedValue", "duplicated value 2.");
  }

  @Override
  protected void setup(CommandLine cli) throws Exception {
    boolean requiredOption = cli.hasOption("r");
    System.out.println("requiredOption = " + requiredOption);
    String[] requiredParameters = cli.getOptionValues("requiredParameters");
    System.out.println("requiredParameters = " + Arrays.toString(requiredParameters));
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
    String duplicatedValue1 = cli.getOptionValue("duplicatedValue1");
    System.out.println("duplicatedValue1 = " + duplicatedValue1);
    String duplicatedValue2 = cli.getOptionValue("duplicatedValue2");
    System.out.println("duplicatedValue2 = " + duplicatedValue2);
  }

  @Override
  protected void cleanup() throws Exception {

  }

  @Override
  protected int process() throws Exception {
    return Constants.JOB_SUCCESS;
  }
}
