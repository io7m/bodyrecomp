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
import java.util.List;

@ImmutablesStyleType
@Value.Immutable
public interface BodyMacrosType
{
  List<String> explanations();

  Quantity<Energy> calories();

  Quantity<Mass> proteinGrams();

  Quantity<Mass> fatGrams();

  Quantity<Mass> carbohydrateGrams();

  @Value.Derived
  @Value.Auxiliary
  default Quantity<Energy> proteinCalories()
  {
    return Quantities.getQuantity(
      Double.valueOf(this.proteinGrams().getValue().doubleValue() * 4.0),
      CLDR.FOODCALORIE
    );
  }

  @Value.Derived
  @Value.Auxiliary
  default Quantity<Energy> fatCalories()
  {
    return Quantities.getQuantity(
      Double.valueOf(this.fatGrams().getValue().doubleValue() * 9.0),
      CLDR.FOODCALORIE
    );
  }

  @Value.Derived
  @Value.Auxiliary
  default Quantity<Energy> carbohydrateCalories()
  {
    return Quantities.getQuantity(
      Double.valueOf(this.carbohydrateGrams().getValue().doubleValue() * 4.0),
      CLDR.FOODCALORIE
    );
  }

  @Value.Check
  default void checkPreconditions()
  {
    var cals = 0.0;
    cals += this.proteinCalories().getValue().doubleValue();
    cals += this.carbohydrateCalories().getValue().doubleValue();
    cals += this.fatCalories().getValue().doubleValue();
    cals = Math.ceil(cals);

    final var expected =
      Math.ceil(this.calories().getValue().doubleValue());

    Preconditions.checkPreconditionD(
      cals,
      cals == expected,
      x -> String.format(
        "Macronutrient calories must sum to %f", Double.valueOf(expected)
      )
    );
  }
}
