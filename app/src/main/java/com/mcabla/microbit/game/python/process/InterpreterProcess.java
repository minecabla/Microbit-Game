/*
 * Copyright (C) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

/*
 * Copyright (C) 2012, Anthony Prieur & Daniel Oppenheim. All rights reserved.
 *
 * Original from SL4A modified to allow to embed Interpreter and scripts into an APK
 */

package com.mcabla.microbit.game.python.process;

import android.content.Context;
import android.os.Environment;

import com.mcabla.microbit.game.App;
import com.googlecode.android_scripting.AndroidProxy;
import com.googlecode.android_scripting.interpreter.Interpreter;
import com.googlecode.android_scripting.interpreter.MyInterpreter;
import com.googlecode.android_scripting.interpreter.InterpreterConstants;
import com.googlecode.android_scripting.jsonrpc.RpcReceiverManagerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InterpreterProcess extends Process {

  private final AndroidProxy mProxy;
  private final Interpreter mInterpreter;
  private String mCommand;
  
  private String pyname;
  private File binary = null;
  private String niceName;
  private String interactiveCommand;
  private List<String> arguments;
  private Map<String, String> environmentVariables = null;
  
  /**
   * Creates a new {@link InterpreterProcess}.
   *
   * @ param launchScript
   *          the absolute path to a script that should be launched with the interpreter
   * @ param port
   *          the port that the AndroidProxy is listening on
   */
  public InterpreterProcess(MyInterpreter myInterpreter, AndroidProxy paramAndroidProxy) {
    mProxy = paramAndroidProxy;
    mInterpreter = myInterpreter.getInterpreter();

  	niceName = "Python 3.6.4";
    pyname = "python3";
    interactiveCommand = "";
    arguments = new ArrayList<String>();
    
    if(binary != null) {
        setBinary(binary);
    }
    setName(niceName);
    setCommand(interactiveCommand);
    addAllArguments(arguments);
    putAllEnvironmentVariables(System.getenv());
    putEnvironmentVariable("AP_HOST", getHost());
    putEnvironmentVariable("AP_PORT", Integer.toString(getPort()));
    if (paramAndroidProxy.getSecret() != null) {
      putEnvironmentVariable("AP_HANDSHAKE", getSecret());
    }
    
    if(environmentVariables != null) {
        putAllEnvironmentVariables(environmentVariables);
    }
  }

  protected void setCommand(String command) {
    mCommand = command;
  }

  public Interpreter getInterpreter() {
    return mInterpreter;
  }

  public String getHost() {
    return mProxy.getAddress().getHostName();
  }

  public int getPort() {
    return mProxy.getAddress().getPort();
  }

  public String getSecret() {
    return mProxy.getSecret();
  }

  @Override
  public void start(final Runnable shutdownHook,Context c) {
    start(shutdownHook, null,c);
  }
  
  public RpcReceiverManagerFactory getRpcReceiverManagerFactory()
  {
    return this.mProxy.getRpcReceiverManagerFactory();
  }
  
  public void start(Runnable paramRunnable, List<String> paramList, Context c)
  {
    if (!this.mCommand.equals(""))
      addArgument(this.mCommand);
    if (paramList != null)
      addAllArguments(paramList);
    super.start(paramRunnable, c);
  }
  
  @Override
  public void kill() {
    super.kill();
    mProxy.shutdown();
  }

}
