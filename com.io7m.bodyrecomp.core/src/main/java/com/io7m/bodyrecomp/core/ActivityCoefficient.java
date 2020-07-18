/*
 * Copyright © 2020 Mark Raynsford <code@io7m.com> http://io7m.com
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

/**
 * A non-exercise activity level.
 */

public enum ActivityCoefficient
{
  /**
   * Sedentary; desk job. Very little activity outside of lifting.
   */

  SEDENTARY(1.35),

  /**
   * Lightly active; Works a desk job, takes pet for a walk most days in
   * addition to lifting
   */

  LIGHTLY_ACTIVE(1.65),

  /**
   * Works as a full-time waitress, occasionally plays tennis in addition to
   * lifting.
   */

  MODERATELY_ACTIVE(1.9),

  /**
   * Works as a construction worker, regular hiking in addition to lifting
   */

  HIGHLY_ACTIVE(2.1);

  private final double coefficient;

  ActivityCoefficient(
    final double inCoefficient)
  {
    this.coefficient = inCoefficient;
  }

  public double coefficient()
  {
    return this.coefficient;
  }
}
