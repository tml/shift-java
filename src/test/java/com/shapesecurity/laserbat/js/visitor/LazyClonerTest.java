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

package com.shapesecurity.laserbat.js.visitor;

import static org.junit.Assert.assertTrue;

import com.shapesecurity.laserbat.functional.data.List;
import com.shapesecurity.laserbat.js.TestBase;
import com.shapesecurity.laserbat.js.ast.Expression;
import com.shapesecurity.laserbat.js.ast.Identifier;
import com.shapesecurity.laserbat.js.ast.Script;
import com.shapesecurity.laserbat.js.ast.expression.LiteralStringExpression;
import com.shapesecurity.laserbat.js.parser.JsError;
import com.shapesecurity.laserbat.js.parser.Parser;
import com.shapesecurity.laserbat.js.path.Branch;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;

public class LazyClonerTest extends TestBase {
  @Test
  public void testLibraries() throws IOException, JsError {
    List<String> jsFiles = List.nil();
    setFatal(false); // Collect the failures in an ErrorCollector

    // Get a list of the js files within the resources directory to process
    File[] files = new File(getPath("libraries").toString()).listFiles();
    if (files == null) {
      System.out.println("Error retrieving list of javascript libraries.");
      return;
    }
    for (File file : files) {
      if (file.isFile() && file.getName().endsWith(".js")) {
        jsFiles = List.cons(file.getName(), jsFiles);
      }
    }

    // Test the hell out of it... ": )
    long start = System.nanoTime();
    System.out.println("Testing " + jsFiles.length() + " javascript libraries.");
    int i = 0;
    for (String jsLib : jsFiles) {
      System.out.print(".");
      if (i++ == 80) {
        i = 0;
        System.out.println();
      }
      testLibrary(jsLib);
    }
    System.out.println("");
    double elapsed = ((System.nanoTime() - start) * NANOS_TO_SECONDS);
    System.out.printf("Library testing time: %.1fsec\n", elapsed);
    setFatal(true); // Revert back to the default behavior
  }

  private void testLibrary(String name) throws IOException, JsError {
    String file = readLibrary(name);
    Script script = Parser.parse(file);
    assertTrue(script == script.reduce(new LazyCloner()).node);

    LazyCloner a = new LazyCloner() {
      @Nonnull
      @Override
      public DirtyState<Identifier> reduceIdentifier(
          @Nonnull Identifier node, @Nonnull List<Branch> path) {
        return DirtyState.dirty(new Identifier("a"));
      }
    };
    LazyCloner b = new LazyCloner() {
      @Nonnull
      @Override
      public DirtyState<Expression> reduceLiteralStringExpression(
          @Nonnull LiteralStringExpression node, @Nonnull List<Branch> path) {
        return DirtyState.dirty(new LiteralStringExpression("a"));
      }
    };
    assertEquals(script.reduce(a).node.reduce(b).node, script.reduce(b).node.reduce(a).node);
  }
}
