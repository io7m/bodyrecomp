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

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Time;
import java.util.Objects;

import static tech.units.indriya.unit.Units.KILOGRAM;
import static tech.units.indriya.unit.Units.METRE;
import static tech.units.indriya.unit.Units.YEAR;

/**
 * The definition of a body.
 */

@Value.Immutable
@ImmutablesStyleType
public interface BodyDefinitionType
{
  /**
   * @return The body's biological gender
   */

  BiologicalGender gender();

  /**
   * @return The height of the body
   */

  Quantity<Length> bodyHeight();

  /**
   * @return The weight of the body
   */

  Quantity<Mass> bodyWeight();

  /**
   * @return The age of the body
   */

  Quantity<Time> age();

  /**
   * @return The body fat coefficient
   */

  NormalCoefficient bodyFatCoefficient();

  /**
   * @return The activity coefficient
   */

  ActivityCoefficient activity();

  /**
   * @return The caloric adjustment coefficient
   */

  @Value.Default
  default GeneralCoefficient caloricAdjustment()
  {
    return CaloricAdjustment.MAINTENANCE.coefficient();
  }

  /**
   * Check preconditions for the type.
   */

  @Value.Check
  default void checkPreconditions()
  {
    Preconditions.checkPrecondition(
      Objects.equals(this.bodyHeight().getUnit(), METRE),
      "Body height must be in meters"
    );
    Preconditions.checkPrecondition(
      Objects.equals(this.age().getUnit(), YEAR),
      "Age must be in years"
    );
    Preconditions.checkPrecondition(
      this.bodyWeight().getUnit().equals(KILOGRAM),
      "Body weight must be in kilograms"
    );
  }

  /**
   * @return The body fat percentage
   */

  @Value.Derived
  @Value.Auxiliary
  default double bodyFatPercentage()
  {
    return this.bodyFatCoefficient().asPercent();
  }

  /**
   * @return The lean mass coefficient (the inverse of the body fat coefficient)
   */

  @Value.Derived
  @Value.Auxiliary
  default NormalCoefficient leanMassCoefficient()
  {
    return NormalCoefficients.inverse(this.bodyFatCoefficient());
  }

  /**
   * @return The lean mass percentage
   */

  @Value.Derived
  @Value.Auxiliary
  default double leanMassPercentage()
  {
    return this.leanMassCoefficient().asPercent();
  }

  /**
   * @return The weight of the body fat
   */

  @Value.Derived
  @Value.Auxiliary
  default Quantity<Mass> fatWeight()
  {
    return this.bodyWeight()
      .multiply(Double.valueOf(this.bodyFatCoefficient().value()));
  }

  /**
   * @return The weight of the lean mass
   */

  @Value.Derived
  @Value.Auxiliary
  default Quantity<Mass> leanMassWeight()
  {
    return this.bodyWeight()
      .multiply(Double.valueOf(this.leanMassCoefficient().value()));
  }
}
