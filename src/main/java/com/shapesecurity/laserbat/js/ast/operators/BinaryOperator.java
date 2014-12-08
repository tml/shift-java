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

package com.shapesecurity.laserbat.js.ast.operators;

import javax.annotation.Nonnull;

public interface BinaryOperator extends Operator {
  BinaryOperator SEQUENCE = new BinaryOperator() {
    @Nonnull
    @Override
    public Precedence getPrecedence() {
      return Precedence.SEQUENCE;
    }

    @Nonnull
    @Override
    public String getName() {
      return ",";
    }
  };
  BinaryOperator LOGICAL_OR = new BinaryOperator() {
    @Nonnull
    @Override
    public Precedence getPrecedence() {
      return Precedence.LOGICAL_OR;
    }

    @Nonnull
    @Override
    public String getName() {
      return "||";
    }
  };
  BinaryOperator LOGICAL_AND = new BinaryOperator() {
    @Nonnull
    @Override
    public Precedence getPrecedence() {
      return Precedence.LOGICAL_AND;
    }

    @Nonnull
    @Override
    public String getName() {
      return "&&";
    }
  };
  BinaryOperator BITWISE_OR = new BinaryOperator() {
    @Nonnull
    @Override
    public Precedence getPrecedence() {
      return Precedence.BITWISE_OR;
    }

    @Nonnull
    @Override
    public String getName() {
      return "|";
    }
  };
  BinaryOperator BITWISE_XOR = new BinaryOperator() {
    @Nonnull
    @Override
    public Precedence getPrecedence() {
      return Precedence.BITWISE_XOR;
    }

    @Nonnull
    @Override
    public String getName() {
      return "^";
    }
  };
  BinaryOperator BITWISE_AND = new BinaryOperator() {
    @Nonnull
    @Override
    public Precedence getPrecedence() {
      return Precedence.BITWISE_AND;
    }

    @Nonnull
    @Override
    public String getName() {
      return "&";
    }
  };

  @Nonnull
  Precedence getPrecedence();
}
