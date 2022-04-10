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

import java.util.Objects;

/**
 * A specific caloric adjustment.
 */

public enum CaloricAdjustment
{
  /**
   * A large deficit, for severe weight reduction.
   */

  LARGE_DEFICIT(
    GeneralCoefficient.of(0.8)
  ),

  /**
   * A small deficit, for weight reduction.
   */

  SMALL_DEFICIT(
    GeneralCoefficient.of(0.9)
  ),

  /**
   * Maintenance level.
   */

  MAINTENANCE(
    GeneralCoefficient.of(1.0)
  ),

  /**
   * A small surplus, for weight increases.
   */

  SMALL_SURPLUS(
    GeneralCoefficient.of(1.1)
  ),

  /**
   * A large surplus, for severe weight increases.
   */

  LARGE_SURPLUS(
    GeneralCoefficient.of(1.2)
  );

  private final GeneralCoefficient coefficient;

  CaloricAdjustment(
    final GeneralCoefficient inCoefficient)
  {
    this.coefficient =
      Objects.requireNonNull(inCoefficient, "coefficient");
  }

  /**
   * @return The caloric adjustment coefficient
   */

  public GeneralCoefficient coefficient()
  {
    return this.coefficient;
  }
}
