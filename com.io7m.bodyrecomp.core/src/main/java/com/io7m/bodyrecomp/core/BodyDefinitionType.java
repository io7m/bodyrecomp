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

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Time;
import java.util.Objects;

import static tech.units.indriya.unit.Units.KILOGRAM;
import static tech.units.indriya.unit.Units.METRE;
import static tech.units.indriya.unit.Units.YEAR;

@Value.Immutable
@ImmutablesStyleType
public interface BodyDefinitionType
{
  BiologicalGender gender();

  Quantity<Length> bodyHeight();

  Quantity<Mass> bodyWeight();

  Quantity<Time> age();

  NormalCoefficient bodyFatCoefficient();

  ActivityCoefficient activity();

  @Value.Default
  default GeneralCoefficient caloricAdjustment()
  {
    return CaloricAdjustment.MAINTENANCE.coefficient();
  }

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

  @Value.Derived
  @Value.Auxiliary
  default double bodyFatPercentage()
  {
    return this.bodyFatCoefficient().asPercent();
  }

  @Value.Derived
  @Value.Auxiliary
  default NormalCoefficient leanMassCoefficient()
  {
    return NormalCoefficients.inverse(this.bodyFatCoefficient());
  }

  @Value.Derived
  @Value.Auxiliary
  default double leanMassPercentage()
  {
    return this.leanMassCoefficient().asPercent();
  }

  @Value.Derived
  @Value.Auxiliary
  default Quantity<Mass> fatWeight()
  {
    return this.bodyWeight()
      .multiply(Double.valueOf(this.bodyFatCoefficient().value()));
  }

  @Value.Derived
  @Value.Auxiliary
  default Quantity<Mass> leanMassWeight()
  {
    return this.bodyWeight()
      .multiply(Double.valueOf(this.leanMassCoefficient().value()));
  }
}
