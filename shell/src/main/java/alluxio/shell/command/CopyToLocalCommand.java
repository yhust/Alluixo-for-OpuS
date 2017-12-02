/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.shell.command;

import alluxio.client.file.FileSystem;
import alluxio.exception.AlluxioException;

import com.google.common.base.Throwables;
import org.apache.commons.cli.*;

import javax.annotation.concurrent.ThreadSafe;

import java.io.File;
import java.io.IOException;

/**
 * Copies a file or a directory from the Alluxio filesystem to the local filesystem.
 */
@ThreadSafe
public final class CopyToLocalCommand extends AbstractShellCommand {

  private CpCommand mCpCommand;

  /**
   * @param fs the filesystem of Alluxio
   */
  public CopyToLocalCommand(FileSystem fs) {
    super(fs);
    mCpCommand = new CpCommand(fs);
  }

  @Override
  public String getCommandName() {
    return "copyToLocal";
  }

  @Override
  protected int getNumOfArgs() {
    return 2;
  }

  @Override
  public int run(CommandLine cl) throws AlluxioException, IOException, InterruptedException{
    String[] args = cl.getArgs();
    String dst = args[1];
    int userId = 0;
    if (args.length >= 3) {
      userId = Integer.parseInt(args[2]);
    }
    System.out.println("From User " + userId);
    cl.getArgList().set(1, "file://" + new File(dst).getAbsolutePath());
    mCpCommand.run(cl);
    return 0;
  }
  @Override
  public String getUsage() {
    return "copyToLocal <src> <localDst>";
  }

  @Override
  public String getDescription() {
    return "Copies a file or a directory from the Alluxio filesystem to the local filesystem.";
  }

  @Override
  public CommandLine parseAndValidateArgs(String... args) {
    Options opts = getOptions();
    CommandLineParser parser = new DefaultParser();
    CommandLine cmd;

    try {
      cmd = parser.parse(opts, args);
    } catch (ParseException e) {
      System.err.println(String.format("%s: %s", getCommandName(), e.getMessage()));
      return null;
    }
    return cmd;
  }
}
