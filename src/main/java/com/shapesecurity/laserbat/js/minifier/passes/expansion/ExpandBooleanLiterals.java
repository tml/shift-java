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

package com.shapesecurity.laserbat.js.minifier.passes.expansion;

import com.shapesecurity.laserbat.js.ast.Expression;
import com.shapesecurity.laserbat.js.ast.expression.LiteralBooleanExpression;
import com.shapesecurity.laserbat.js.ast.expression.LiteralNumericExpression;
import com.shapesecurity.laserbat.js.ast.expression.PrefixExpression;
import com.shapesecurity.laserbat.js.ast.operators.PrefixOperator;
import com.shapesecurity.laserbat.js.minifier.ExpansionRule;
import com.shapesecurity.laserbat.js.visitor.DirtyState;

import javax.annotation.Nonnull;

public class ExpandBooleanLiterals extends ExpansionRule {
  /* expand true to !0 and false to !1 */
  public static final ExpandBooleanLiterals INSTANCE = new ExpandBooleanLiterals();

  @Nonnull
  @Override
  public DirtyState<Expression> transform(@Nonnull LiteralBooleanExpression node) {
    return DirtyState.<Expression>dirty(new PrefixExpression(PrefixOperator.LogicalNot, new LiteralNumericExpression(
        node.value ? 0 : 1)));
  }
}
