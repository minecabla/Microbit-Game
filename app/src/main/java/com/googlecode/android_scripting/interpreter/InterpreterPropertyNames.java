/*
 * Copyright (C) 2016 Google Inc.
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

package com.googlecode.android_scripting.interpreter;

/**
 * A collection of {@link String} keys for querying an InterpreterProvider.
 *
 * @author Alexey Reznichenko (alexey.reznichenko@gmail.com)
 */
public interface InterpreterPropertyNames {

  /**
   * Unique name of the interpreter.
   */
  String NAME = "name";

  /**
   * Display name of the interpreter.
   */
  String NICE_NAME = "niceName";

  /**
   * Supported script file extension.
   */
  String EXTENSION = "extension";

  /**
   * Absolute path of the interpreter executable.
   */
  String BINARY = "binary";

  /**
   * Final argument to interpreter binary when running the interpreter interactively.
   */
  String INTERACTIVE_COMMAND = "interactiveCommand";

  /**
   * Final argument to interpreter binary when running a script.
   */
  String SCRIPT_COMMAND = "scriptCommand";

  /**
   * Interpreter interactive mode flag.
   */
  String HAS_INTERACTIVE_MODE = "hasInteractiveMode";

}
