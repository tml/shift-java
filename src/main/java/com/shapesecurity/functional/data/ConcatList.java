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

package com.shapesecurity.functional.data;

import com.shapesecurity.functional.F;
import com.shapesecurity.functional.F2;

import org.jetbrains.annotations.NotNull;

public abstract class ConcatList<T> {
  private static final Empty<Object> EMPTY = new Empty<>();
  private static BinaryTreeMonoid<Object> MONOID = new BinaryTreeMonoid<>();
  public final int length;

  protected ConcatList(int length) {
    this.length = length;
  }

  @SuppressWarnings("unchecked")
  @NotNull
  public static <T> ConcatList<T> empty() {
    return (ConcatList<T>) EMPTY;
  }

  @NotNull
  public static <T> ConcatList<T> single(@NotNull T scope) {
    return new Leaf<>(scope);
  }

  @SuppressWarnings("unchecked")
  public static <T> Monoid<ConcatList<T>> monoid() {
    return (BinaryTreeMonoid<T>) MONOID;
  }

  @NotNull
  public final List<T> toList() {
    return this.toList(List.<T>nil());
  }

  protected abstract List<T> toList(@NotNull List<T> acc);

  @NotNull
  public abstract <B> B foldLeft(@NotNull F2<B, ? super T, B> f, @NotNull B init);

  @NotNull
  public abstract <B> B foldRight(@NotNull F2<? super T, B, B> f, @NotNull B init);

  public abstract boolean isEmpty();

  @NotNull
  public abstract ConcatList<T> append(@NotNull ConcatList<? extends T> defaultClause);

  public abstract boolean exists(@NotNull F<T, Boolean> f);

  @NotNull
  public abstract Maybe<T> find(@NotNull F<T, Boolean> f);

  @NotNull
  public abstract ConcatList<T> reverse();

  @NotNull
  public abstract Maybe<T> index(int index);

  @NotNull
  public abstract Maybe<ConcatList<T>> update(int index, @NotNull T element);

  public final static class Empty<T> extends ConcatList<T> {
    private Empty() {
      super(0);
    }

    @NotNull
    @Override
    protected List<T> toList(@NotNull List<T> acc) {
      return acc;
    }

    @NotNull
    @Override
    public <B> B foldLeft(@NotNull F2<B, ? super T, B> f, @NotNull B init) {
      return init;
    }

    @NotNull
    @Override
    public <B> B foldRight(@NotNull F2<? super T, B, B> f, @NotNull B init) {
      return init;
    }

    @Override
    public boolean isEmpty() {
      return true;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public ConcatList<T> append(@NotNull ConcatList<? extends T> defaultClause) {
      return (ConcatList<T>) defaultClause;
    }

    @Override
    public boolean exists(@NotNull F<T, Boolean> f) {
      return false;
    }

    @NotNull
    @Override
    public Maybe<T> find(@NotNull F<T, Boolean> f) {
      return Maybe.nothing();
    }

    @NotNull
    @Override
    public ConcatList<T> reverse() {
      return this;
    }

    @NotNull
    @Override
    public Maybe<T> index(int index) {
      return Maybe.nothing();
    }

    @NotNull
    @Override
    public Maybe<ConcatList<T>> update(int index, @NotNull T element) {
      return Maybe.nothing();
    }
  }

  public final static class Leaf<T> extends ConcatList<T> {
    @NotNull
    public final T data;

    private Leaf(@NotNull T data) {
      super(1);
      this.data = data;
    }

    @NotNull
    @Override
    protected List<T> toList(@NotNull List<T> acc) {
      return acc.cons(this.data);
    }

    @NotNull
    @Override
    public <B> B foldLeft(@NotNull F2<B, ? super T, B> f, @NotNull B init) {
      return f.apply(init, this.data);
    }

    @NotNull
    @Override
    public <B> B foldRight(@NotNull F2<? super T, B, B> f, @NotNull B init) {
      return f.apply(this.data, init);
    }

    @Override
    public boolean isEmpty() {
      return false;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public ConcatList<T> append(@NotNull ConcatList<? extends T> defaultClause) {
      return new Fork<>(this, (ConcatList<T>) defaultClause);
    }

    @Override
    public boolean exists(@NotNull F<T, Boolean> f) {
      return f.apply(this.data);
    }

    @NotNull
    @Override
    public Maybe<T> find(@NotNull F<T, Boolean> f) {
      if (f.apply(this.data)) {
        return Maybe.just(this.data);
      }
      return Maybe.nothing();
    }

    @NotNull
    @Override
    public ConcatList<T> reverse() {
      return this;
    }

    @NotNull
    @Override
    public Maybe<T> index(int index) {
      return Maybe.iff(index == 0, this.data);
    }

    @NotNull
    @Override
    public Maybe<ConcatList<T>> update(int index, @NotNull T element) {
      return index == 0 ? Maybe.just(single(element)) : Maybe.<ConcatList<T>>nothing();
    }
  }

  public final static class Fork<T> extends ConcatList<T> {
    @NotNull
    public final ConcatList<T> left, right;

    private Fork(@NotNull ConcatList<T> left, @NotNull ConcatList<T> right) {
      super(left.length + right.length);
      this.left = left;
      this.right = right;
    }

    @NotNull
    @Override
    protected List<T> toList(@NotNull List<T> acc) {
      return this.left.toList(this.right.toList(acc));
    }

    @NotNull
    @Override
    public <B> B foldLeft(@NotNull F2<B, ? super T, B> f, @NotNull B init) {
      return this.right.foldLeft(f, this.left.foldLeft(f, init));
    }

    @NotNull
    @Override
    public <B> B foldRight(@NotNull F2<? super T, B, B> f, @NotNull B init) {
      return this.left.foldRight(f, this.right.foldRight(f, init));
    }

    @Override
    public boolean isEmpty() {
      return this.left.isEmpty() || this.right.isEmpty();
    }

    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public ConcatList<T> append(@NotNull ConcatList<? extends T> defaultClause) {
      return new Fork<>(this, (ConcatList<T>) defaultClause);
    }

    @Override
    public boolean exists(@NotNull F<T, Boolean> f) {
      return this.left.exists(f) || this.right.exists(f);
    }

    @NotNull
    @Override
    public Maybe<T> find(@NotNull F<T, Boolean> f) {
      Maybe<T> foundLeft = this.left.find(f);
      if (foundLeft.isNothing()) {
        return this.right.find(f);
      }
      return foundLeft;
    }

    @NotNull
    @Override
    public Fork<T> reverse() {
      return new Fork<>(this.right.reverse(), this.left.reverse());
    }

    @NotNull
    @Override
    public Maybe<T> index(int index) {
      if (index >= this.length) {
        return Maybe.nothing();
      }
      return index < this.left.length ? this.left.index(index) : this.right.index(index - this.left.length);
    }

    @NotNull
    @Override
    public Maybe<ConcatList<T>> update(int index, @NotNull T element) {
      if (index >= this.length) { return Maybe.nothing(); }
      ConcatList<T> left = this.left;
      ConcatList<T> right = this.right;

      if (index < this.left.length) {
        left = left.update(index, element).just();
      } else {
        right = right.update(index - this.left.length, element).just();
      }
      return Maybe.just(left.append(right));
    }
  }

  private static class BinaryTreeMonoid<T> implements Monoid<ConcatList<T>> {
    @NotNull
    @Override
    public ConcatList<T> identity() {
      return new Empty<>();
    }

    @NotNull
    @Override
    public ConcatList<T> append(ConcatList<T> a, ConcatList<T> b) {
      return a.append(b);
    }
  }
}
