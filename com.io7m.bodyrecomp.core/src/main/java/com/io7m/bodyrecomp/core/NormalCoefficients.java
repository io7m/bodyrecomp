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
 * Functions over normal coefficients.
 */

public final class NormalCoefficients
{
  private NormalCoefficients()
  {

  }

  /**
   * Determine the inverse of the normal coefficient.
   *
   * @param proportion The coefficient
   *
   * @return The inverse
   */

  public static NormalCoefficient inverse(
    final NormalCoefficient proportion)
  {
    Objects.requireNonNull(proportion, "proportion");
    return NormalCoefficient.of(1.0 - proportion.value());
  }

  static double mapIntoRange(
    final double inputLow,
    final double inputHigh,
    final double outputLow,
    final double outputUpper,
    final double x)
  {
    final double rangeUpper =
      outputUpper - outputLow;
    final double rangeLower =
      inputHigh - inputLow;
    final double result =
      outputLow + (rangeUpper / rangeLower) * (x - inputLow);

    return result;
  }
}
