/*
 * Copyright 2014 Shape Security, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shapesecurity.shift.js.scope;

import com.shapesecurity.shift.functional.data.List;
import com.shapesecurity.shift.js.ast.Node;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

public class GlobalScope extends Scope {
  GlobalScope(
      @Nonnull List<Scope> children,
      @Nonnull List<Variable> variables,
      @Nonnull HashMap<String, ProjectionTree<Reference>> through,
      @Nonnull Node astNode) {
    super(children, variables, through, Type.Global, true, astNode);
    for (Map.Entry<String, ProjectionTree<Reference>> var : through.entrySet()) {
      this.variables.put(var.getKey(), new Variable(var.getKey(), var.getValue(), ProjectionTree.<Declaration>nil()));
    }
  }
}