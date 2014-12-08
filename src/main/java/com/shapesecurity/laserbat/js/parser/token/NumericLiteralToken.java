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

package com.shapesecurity.laserbat.js.parser.token;

import com.shapesecurity.laserbat.js.parser.SourceRange;
import com.shapesecurity.laserbat.js.parser.Token;
import com.shapesecurity.laserbat.js.parser.TokenType;
import com.shapesecurity.laserbat.js.utils.D2A;

import javax.annotation.Nonnull;

public final class NumericLiteralToken extends Token {
  public final double value;

  public NumericLiteralToken(@Nonnull SourceRange slice, double value) {
    this(slice, value, false);
  }

  public NumericLiteralToken(@Nonnull SourceRange slice, double value, boolean octal) {
    super(TokenType.NUMBER, slice, octal);
    this.value = value;
  }

  @Nonnull
  @Override
  public String getValueString() {
    return D2A.d2a(this.value);
  }
}
