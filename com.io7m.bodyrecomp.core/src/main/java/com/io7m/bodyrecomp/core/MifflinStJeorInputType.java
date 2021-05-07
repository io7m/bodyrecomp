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

import com.io7m.immutables.styles.ImmutablesStyleType;
import com.io7m.jaffirm.core.Preconditions;
import org.immutables.value.Value;
import si.uom.SI;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Time;
import java.util.Objects;

/**
 * Input to the Mifflin St. Jeor BMR estimation.
 *
 * @see "https://en.wikipedia.org/wiki/Basal_metabolic_rate#BMR_estimation_formulas"
 */

@ImmutablesStyleType
@Value.Immutable
public interface MifflinStJeorInputType
{
  /**
   * @return The height of the body
   */

  Quantity<Length> height();

  /**
   * @return The weight of the body
   */

  Quantity<Mass> bodyWeight();

  /**
   * @return The age of the body
   */

  Quantity<Time> age();

  /**
   * @return The biological gender of the body
   */

  BiologicalGender gender();

  /**
   * Check preconditions for the type.
   */

  @Value.Check
  default void checkPreconditions()
  {
    Preconditions.checkPrecondition(
      Objects.equals(this.height().getUnit(), SI.METRE),
      "Body height must be in meters"
    );
    Preconditions.checkPrecondition(
      Objects.equals(this.age().getUnit(), SI.YEAR),
      "Age must be in years"
    );
    Preconditions.checkPrecondition(
      Objects.equals(this.bodyWeight().getUnit(), SI.KILOGRAM),
      "Body weight must be in kilograms"
    );
  }
}
