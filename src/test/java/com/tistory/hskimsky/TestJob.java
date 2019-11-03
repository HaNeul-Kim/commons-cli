package com.tistory.hskimsky;

import com.tistory.hskimsky.commons.cli.AbstractJob;
import com.tistory.hskimsky.commons.cli.CommandLine;
import com.tistory.hskimsky.commons.cli.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AbstractJob example
 *
 * @author Haneul, Kim
 */
public class TestJob extends AbstractJob {

  private static final long serialVersionUID = 6007570799467354348L;

  private static final Logger logger = LoggerFactory.getLogger(TestJob.class);

  public static void main(String[] args) throws Exception {
    args = new String[]{"--sourceVMName", "test", "--targetVMNames", "test1,test2"};
    // args = new String[]{"--sourceVMName", "test"};
    int exitCode = new TestJob().run(args);
    System.exit(exitCode);
  }

  @Override
  protected void setOptions() throws Exception {
    addOption(false, "sp", "sourcePath", "H:\\vm\\Linux", "원본 VM 경로");
    addOption(false, "sv", "sourceVMName", "template67", "원본 VM 이름");
    addOption(false, "tp", "targetPath", "H:\\vm\\Linux", "타겟 VM 경로");
    addOption(true, "tvs", "targetVMNames", "vm1,vm2,vm3", ",", "타겟 VM 이름들 (comma separated)");
    addOption(false, "e", "encoding", "UTF-8", "file encoding");
  }

  @Override
  protected void setup(CommandLine cli) throws Exception {
    String sourcePath = cli.getValue("sourcePath");
    logger.info("sourcePath = {}", sourcePath);
    String sourceVMName = cli.getValue("sourceVMName");
    logger.info("sourceVMName = {}", sourceVMName);
    String targetPath = cli.getValue("targetPath");
    logger.info("targetPath = {}", targetPath);
    String[] targetVMNames = cli.getValues("targetVMNames");
    logger.info("targetVMNames = {}", (Object) targetVMNames);
    String encoding = cli.getValue("encoding");
    logger.info("encoding = {}", encoding);
  }

  @Override
  protected void cleanup() throws Exception {

  }

  @Override
  protected int process() throws Exception {
    return Constants.JOB_SUCCESS;
  }
}
