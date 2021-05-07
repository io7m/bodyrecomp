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
import systems.uom.unicode.CLDR;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Energy;
import javax.measure.quantity.Mass;
import java.util.Objects;

import static tech.units.indriya.unit.Units.GRAM;

/**
 * An estimate of the required dietary protein.
 */

@ImmutablesStyleType
@Value.Immutable
public interface DietaryProteinEstimateType
{
  /**
   * @return The number of grams of protein
   */

  Quantity<Mass> proteinGrams();

  /**
   * @return The number of grams of protein per kilogram of body weight
   */

  Quantity<Mass> gramsPerKilogram();

  /**
   * @return The number of grams of protein per pound of body weight
   */

  Quantity<Mass> gramsPerPound();

  /**
   * @return The number of calories for the protein.
   */

  @Value.Auxiliary
  @Value.Derived
  default Quantity<Energy> proteinCalories()
  {
    return Quantities.getQuantity(
      Double.valueOf(this.proteinGrams().getValue().doubleValue() * 4.0),
      CLDR.FOODCALORIE
    );
  }

  /**
   * Check preconditions for the type.
   */

  @Value.Check
  default void checkPreconditions()
  {
    Preconditions.checkPrecondition(
      Objects.equals(this.proteinGrams().getUnit(), GRAM),
      "Protein must be in grams"
    );
    Preconditions.checkPrecondition(
      Objects.equals(this.gramsPerKilogram().getUnit(), GRAM),
      "Protein g/kg must be in grams"
    );
    Preconditions.checkPrecondition(
      Objects.equals(this.gramsPerPound().getUnit(), GRAM),
      "Protein g/lb must be in grams"
    );
  }
}
