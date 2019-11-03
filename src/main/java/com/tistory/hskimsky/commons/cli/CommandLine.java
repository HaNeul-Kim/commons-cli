package com.tistory.hskimsky.commons.cli;

import java.io.Serializable;

/**
 * command line
 *
 * @author Haneul, Kim
 */
public interface CommandLine extends Serializable {

  boolean hasOption(Option option);

  boolean hasOption(String name);

  /**
   * command line interface 로부터 입력받는 옵션인지 체크
   *
   * @param name option 명
   * @return fromCli
   */
  boolean fromCliOption(String name);

  String getValue(Option option);

  String getValue(String name);

  String[] getValues(Option option);

  String[] getValues(String name);
}
