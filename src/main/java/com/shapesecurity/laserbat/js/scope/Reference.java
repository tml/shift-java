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

package com.shapesecurity.laserbat.js.scope;

import com.shapesecurity.laserbat.functional.data.List;
import com.shapesecurity.laserbat.js.ast.Identifier;
import com.shapesecurity.laserbat.js.path.Branch;

import javax.annotation.Nonnull;

public final class Reference {
  @Nonnull
  public final Identifier node;
  @Nonnull
  public final List<Branch> path;
  @Nonnull
  public final Accessibility accessibility;

  public Reference(@Nonnull Identifier node, @Nonnull List<Branch> path, @Nonnull Accessibility accessibility) {
    this.node = node;
    this.path = path;
    this.accessibility = accessibility;
  }

  @Nonnull
  public final Reference withReadability() {
    return new Reference(this.node, this.path, this.accessibility.withReadability());
  }

  @Nonnull
  public final Reference withWritability() {
    return new Reference(this.node, this.path, this.accessibility.withWritability());
  }
}
