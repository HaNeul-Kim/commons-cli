package com.tistory.hskimsky;

import com.tistory.hskimsky.commons.cli.AbstractJob;
import com.tistory.hskimsky.commons.cli.Constants;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import java.util.Arrays;

/**
 * AbstractJob example
 *
 * @author Haneul, Kim
 */
public class TestJob2 extends AbstractJob {

  private static final long serialVersionUID = 6007570799467354348L;

  public static void main(String[] args) throws Exception {
    // args = new String[]{"--sourceVMName", "test", "--targetVMNames", "test1,test2"};
    args = new String[]{"--sourceVMName", "test", "--targetVMNames", "test1", "test2", "--targetVMNamesD", "tvm1,tvm2"};
    int exitCode = new TestJob2().run(args);
    System.exit(exitCode);
  }

  @Override
  protected void setOptions(String[] args) throws Exception {
    addOption("sp", "sourcePath", "/Users/cloudine/Documents/Virtual Machines.localized", "원본 VM 경로");
    // addOption("sv", "sourceVMName", "centos_6.7_template", "원본 VM 이름");
    addOption("sv", "sourceVMName", "template67", "원본 VM 이름");
    // addOption("tp", "targetPath", "H:\\vm\\Linux", "타겟 VM 경로");
    addOption("tp", "targetPath", "/Users/cloudine/Documents/Virtual Machines.localized", "타겟 VM 경로");
    addOption(true, "tvs", "targetVMNames", true, String.class, ',', "타겟 VM 이름들 (comma separated)");
    addOption("tvsd", "targetVMNamesD", String.class, "vm1,vm2,vm3", ",", "타겟 VM 이름들 (comma separated)");
    addOption("e", "encoding", "UTF-8", "file encoding");
  }

  @Override
  protected void setup(CommandLine cli) throws Exception {
    Option[] options = cli.getOptions();
    for (Option option : options) {
      System.out.println("option = " + option);
    }
    String sourcePath = cli.getOptionValue("sourcePath");
    System.out.println("sourcePath = " + sourcePath);
    String sourceVMName = cli.getOptionValue("sourceVMName");
    System.out.println("sourceVMName = " + sourceVMName);
    String targetPath = cli.getOptionValue("targetPath");
    System.out.println("targetPath = " + targetPath);
    String[] targetVMNames = cli.getOptionValues("targetVMNames");
    System.out.println("targetVMNames = " + Arrays.toString(targetVMNames));
    String[] targetVMNamesD = cli.getOptionValues("targetVMNamesD");
    System.out.println("targetVMNamesD = " + Arrays.toString(targetVMNamesD));
    String encoding = cli.getOptionValue("encoding");
    System.out.println("encoding = " + encoding);
  }

  @Override
  protected void cleanup() throws Exception {

  }

  @Override
  protected int process() throws Exception {
    return Constants.JOB_SUCCESS;
  }
}
