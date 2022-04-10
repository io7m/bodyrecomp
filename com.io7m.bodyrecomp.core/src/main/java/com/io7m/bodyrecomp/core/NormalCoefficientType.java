/*
 * Copyright © 2020 Mark Raynsford <code@io7m.com> https://www.io7m.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.bodyrecomp.core;

import com.io7m.immutables.styles.ImmutablesStyleType;
import com.io7m.jaffirm.core.Preconditions;
import org.immutables.value.Value;

/**
 * A normal coefficient in the range {@code [0, 1]}.
 */

@ImmutablesStyleType
@Value.Immutable
public interface NormalCoefficientType
{
  /**
   * @return The coefficient value
   */

  @Value.Parameter
  double value();

  /**
   * @return The coefficient value as a percentage
   */

  @Value.Auxiliary
  @Value.Derived
  default double asPercent()
  {
    return this.value() * 100.0;
  }

  /**
   * Check preconditions for the type.
   */

  @Value.Check
  default void checkPreconditions()
  {
    final double value = this.value();
    Preconditions.checkPreconditionD(
      value,
      value >= 0.0,
      x -> "Value must be in the range [0, 1]"
    );
    Preconditions.checkPreconditionD(
      value,
      value <= 1.0,
      x -> "Value must be in the range [0, 1]"
    );
  }
}
