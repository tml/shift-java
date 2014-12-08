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

package com.shapesecurity.laserbat.js;

import com.shapesecurity.laserbat.functional.Unit;
import com.shapesecurity.laserbat.functional.data.Either;
import com.shapesecurity.laserbat.functional.data.List;
import com.shapesecurity.laserbat.functional.data.Maybe;
import com.shapesecurity.laserbat.functional.data.NonEmptyList;
import com.shapesecurity.laserbat.js.ast.Block;
import com.shapesecurity.laserbat.js.ast.CatchClause;
import com.shapesecurity.laserbat.js.ast.FunctionBody;
import com.shapesecurity.laserbat.js.ast.Identifier;
import com.shapesecurity.laserbat.js.ast.Node;
import com.shapesecurity.laserbat.js.ast.Script;
import com.shapesecurity.laserbat.js.ast.SwitchCase;
import com.shapesecurity.laserbat.js.ast.SwitchDefault;
import com.shapesecurity.laserbat.js.ast.VariableDeclaration;
import com.shapesecurity.laserbat.js.ast.VariableDeclarator;
import com.shapesecurity.laserbat.js.ast.directive.UnknownDirective;
import com.shapesecurity.laserbat.js.ast.directive.UseStrictDirective;
import com.shapesecurity.laserbat.js.ast.expression.ArrayExpression;
import com.shapesecurity.laserbat.js.ast.expression.AssignmentExpression;
import com.shapesecurity.laserbat.js.ast.expression.BinaryExpression;
import com.shapesecurity.laserbat.js.ast.expression.CallExpression;
import com.shapesecurity.laserbat.js.ast.expression.ComputedMemberExpression;
import com.shapesecurity.laserbat.js.ast.expression.ConditionalExpression;
import com.shapesecurity.laserbat.js.ast.expression.FunctionExpression;
import com.shapesecurity.laserbat.js.ast.expression.IdentifierExpression;
import com.shapesecurity.laserbat.js.ast.expression.LiteralBooleanExpression;
import com.shapesecurity.laserbat.js.ast.expression.LiteralNullExpression;
import com.shapesecurity.laserbat.js.ast.expression.LiteralNumericExpression;
import com.shapesecurity.laserbat.js.ast.expression.LiteralRegExpExpression;
import com.shapesecurity.laserbat.js.ast.expression.LiteralStringExpression;
import com.shapesecurity.laserbat.js.ast.expression.NewExpression;
import com.shapesecurity.laserbat.js.ast.expression.ObjectExpression;
import com.shapesecurity.laserbat.js.ast.expression.PostfixExpression;
import com.shapesecurity.laserbat.js.ast.expression.PrefixExpression;
import com.shapesecurity.laserbat.js.ast.expression.StaticMemberExpression;
import com.shapesecurity.laserbat.js.ast.expression.ThisExpression;
import com.shapesecurity.laserbat.js.ast.property.DataProperty;
import com.shapesecurity.laserbat.js.ast.property.Getter;
import com.shapesecurity.laserbat.js.ast.property.PropertyName;
import com.shapesecurity.laserbat.js.ast.property.Setter;
import com.shapesecurity.laserbat.js.ast.statement.BlockStatement;
import com.shapesecurity.laserbat.js.ast.statement.BreakStatement;
import com.shapesecurity.laserbat.js.ast.statement.ContinueStatement;
import com.shapesecurity.laserbat.js.ast.statement.DebuggerStatement;
import com.shapesecurity.laserbat.js.ast.statement.DoWhileStatement;
import com.shapesecurity.laserbat.js.ast.statement.EmptyStatement;
import com.shapesecurity.laserbat.js.ast.statement.ExpressionStatement;
import com.shapesecurity.laserbat.js.ast.statement.ForInStatement;
import com.shapesecurity.laserbat.js.ast.statement.ForStatement;
import com.shapesecurity.laserbat.js.ast.statement.FunctionDeclaration;
import com.shapesecurity.laserbat.js.ast.statement.IfStatement;
import com.shapesecurity.laserbat.js.ast.statement.LabeledStatement;
import com.shapesecurity.laserbat.js.ast.statement.ReturnStatement;
import com.shapesecurity.laserbat.js.ast.statement.SwitchStatement;
import com.shapesecurity.laserbat.js.ast.statement.SwitchStatementWithDefault;
import com.shapesecurity.laserbat.js.ast.statement.ThrowStatement;
import com.shapesecurity.laserbat.js.ast.statement.TryCatchStatement;
import com.shapesecurity.laserbat.js.ast.statement.TryFinallyStatement;
import com.shapesecurity.laserbat.js.ast.statement.VariableDeclarationStatement;
import com.shapesecurity.laserbat.js.ast.statement.WhileStatement;
import com.shapesecurity.laserbat.js.ast.statement.WithStatement;
import com.shapesecurity.laserbat.js.path.Branch;
import com.shapesecurity.laserbat.js.visitor.Reducer;

import javax.annotation.Nonnull;

public abstract class TestReducer implements Reducer<Unit> {

  protected abstract void accept(@Nonnull Node node);

  @Nonnull
  @Override
  public final Unit reduceScript(@Nonnull Script node, @Nonnull List<Branch> path, @Nonnull Unit body) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceIdentifier(@Nonnull Identifier node, @Nonnull List<Branch> path) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceIdentifierExpression(
      @Nonnull IdentifierExpression node,
      @Nonnull List<Branch> path,
      @Nonnull Unit name) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceThisExpression(@Nonnull ThisExpression node, @Nonnull List<Branch> path) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceLiteralBooleanExpression(@Nonnull LiteralBooleanExpression node, @Nonnull List<Branch> path) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceLiteralStringExpression(@Nonnull LiteralStringExpression node, @Nonnull List<Branch> path) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceLiteralRegexExpression(@Nonnull LiteralRegExpExpression node, @Nonnull List<Branch> path) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceLiteralNumericExpression(@Nonnull LiteralNumericExpression node, @Nonnull List<Branch> path) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceLiteralNullExpression(@Nonnull LiteralNullExpression node, @Nonnull List<Branch> path) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceFunctionExpression(
      @Nonnull FunctionExpression node,
      @Nonnull List<Branch> path,
      @Nonnull Maybe<Unit> id,
      @Nonnull List<Unit> params,
      @Nonnull Unit body) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceStaticMemberExpression(
      @Nonnull StaticMemberExpression node,
      @Nonnull List<Branch> path,
      @Nonnull Unit object,
      @Nonnull Unit property) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceComputedMemberExpression(
      @Nonnull ComputedMemberExpression node,
      @Nonnull List<Branch> path,
      @Nonnull Unit object,
      @Nonnull Unit expression) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceObjectExpression(
      @Nonnull ObjectExpression node,
      @Nonnull List<Branch> path,
      @Nonnull List<Unit> properties) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceBinaryExpression(
      @Nonnull BinaryExpression node,
      @Nonnull List<Branch> path,
      @Nonnull Unit left,
      @Nonnull Unit right) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceAssignmentExpression(
      @Nonnull AssignmentExpression node,
      @Nonnull List<Branch> path,
      @Nonnull Unit binding,
      @Nonnull Unit expression) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceArrayExpression(
      @Nonnull ArrayExpression node,
      @Nonnull List<Branch> path,
      @Nonnull List<Maybe<Unit>> elements) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceNewExpression(
      @Nonnull NewExpression node,
      @Nonnull List<Branch> path,
      @Nonnull Unit callee,
      @Nonnull List<Unit> arguments) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceCallExpression(
      @Nonnull CallExpression node,
      @Nonnull List<Branch> path,
      @Nonnull Unit callee,
      @Nonnull List<Unit> arguments) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reducePostfixExpression(
      @Nonnull PostfixExpression node,
      @Nonnull List<Branch> path,
      @Nonnull Unit operand) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reducePrefixExpression(
      @Nonnull PrefixExpression node,
      @Nonnull List<Branch> path,
      @Nonnull Unit operand) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceConditionalExpression(
      @Nonnull ConditionalExpression node,
      @Nonnull List<Branch> path,
      @Nonnull Unit test,
      @Nonnull Unit consequent,
      @Nonnull Unit alternate) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceFunctionDeclaration(
      @Nonnull FunctionDeclaration node,
      @Nonnull List<Branch> path,
      @Nonnull Unit id,
      @Nonnull List<Unit> params,
      @Nonnull Unit body) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceUseStrictDirective(@Nonnull UseStrictDirective node, @Nonnull List<Branch> path) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceUnknownDirective(@Nonnull UnknownDirective node, @Nonnull List<Branch> path) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceBlockStatement(
      @Nonnull BlockStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Unit block) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceBreakStatement(
      @Nonnull BreakStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Maybe<Unit> label) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceCatchClause(
      @Nonnull CatchClause node,
      @Nonnull List<Branch> path,
      @Nonnull Unit param,
      @Nonnull Unit body) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceContinueStatement(
      @Nonnull ContinueStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Maybe<Unit> label) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceDebuggerStatement(@Nonnull DebuggerStatement node, @Nonnull List<Branch> path) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceDoWhileStatement(
      @Nonnull DoWhileStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Unit body,
      @Nonnull Unit test) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceEmptyStatement(@Nonnull EmptyStatement node, @Nonnull List<Branch> path) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceExpressionStatement(
      @Nonnull ExpressionStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Unit expression) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceForInStatement(
      @Nonnull ForInStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Either<Unit, Unit> left,
      @Nonnull Unit right,
      @Nonnull Unit body) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceForStatement(
      @Nonnull ForStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Maybe<Either<Unit, Unit>> init,
      @Nonnull Maybe<Unit> test,
      @Nonnull Maybe<Unit> update,
      @Nonnull Unit body) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceIfStatement(
      @Nonnull IfStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Unit test,
      @Nonnull Unit consequent,
      @Nonnull Maybe<Unit> alternate) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceLabeledStatement(
      @Nonnull LabeledStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Unit label,
      @Nonnull Unit body) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceReturnStatement(
      @Nonnull ReturnStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Maybe<Unit> argument) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceSwitchCase(
      @Nonnull SwitchCase node,
      @Nonnull List<Branch> path,
      @Nonnull Unit test,
      @Nonnull List<Unit> consequent) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceSwitchDefault(
      @Nonnull SwitchDefault node,
      @Nonnull List<Branch> path,
      @Nonnull List<Unit> consequent) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceSwitchStatement(
      @Nonnull SwitchStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Unit discriminant,
      @Nonnull List<Unit> cases) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceSwitchStatementWithDefault(
      @Nonnull SwitchStatementWithDefault node,
      @Nonnull List<Branch> path,
      @Nonnull Unit discriminant,
      @Nonnull List<Unit> cases,
      @Nonnull Unit defaultCase,
      @Nonnull List<Unit> postDefaultCases) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceThrowStatement(
      @Nonnull ThrowStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Unit argument) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceTryCatchStatement(
      @Nonnull TryCatchStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Unit block,
      @Nonnull Unit catchClause) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceTryFinallyStatement(
      @Nonnull TryFinallyStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Unit block,
      @Nonnull Maybe<Unit> catchClause,
      @Nonnull Unit finalizer) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceVariableDeclarationStatement(
      @Nonnull VariableDeclarationStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Unit declaration) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceVariableDeclaration(
      @Nonnull VariableDeclaration node,
      @Nonnull List<Branch> path,
      @Nonnull NonEmptyList<Unit> declarators) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceWhileStatement(
      @Nonnull WhileStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Unit test,
      @Nonnull Unit body) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceWithStatement(
      @Nonnull WithStatement node,
      @Nonnull List<Branch> path,
      @Nonnull Unit object,
      @Nonnull Unit body) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceDataProperty(
      @Nonnull DataProperty node,
      @Nonnull List<Branch> path,
      @Nonnull Unit key,
      @Nonnull Unit value) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceGetter(
      @Nonnull Getter node,
      @Nonnull List<Branch> path,
      @Nonnull Unit key,
      @Nonnull Unit body) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceSetter(
      @Nonnull Setter node,
      @Nonnull List<Branch> path,
      @Nonnull Unit key,
      @Nonnull Unit parameter,
      @Nonnull Unit body) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reducePropertyName(@Nonnull PropertyName node, @Nonnull List<Branch> path) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceFunctionBody(
      @Nonnull FunctionBody node,
      @Nonnull List<Branch> path,
      @Nonnull List<Unit> directives,
      @Nonnull List<Unit> sourceElements) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceVariableDeclarator(
      @Nonnull VariableDeclarator node,
      @Nonnull List<Branch> path,
      @Nonnull Unit id,
      @Nonnull Maybe<Unit> init) {
    accept(node);
    return Unit.unit;
  }

  @Nonnull
  @Override
  public final Unit reduceBlock(@Nonnull Block node, @Nonnull List<Branch> path, @Nonnull List<Unit> statements) {
    accept(node);
    return Unit.unit;
  }
}
