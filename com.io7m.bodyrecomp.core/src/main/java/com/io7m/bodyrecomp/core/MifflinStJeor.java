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

import systems.uom.unicode.CLDR;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Energy;
import java.util.Objects;

import static systems.uom.common.CGS.CENTIMETRE;

public final class MifflinStJeor
{
  private MifflinStJeor()
  {

  }

  public static Quantity<Energy> basalMetabolicRate(
    final MifflinStJeorInput input)
  {
    Objects.requireNonNull(input, "input");

    final double k =
      input.bodyWeight()
        .getValue()
        .doubleValue() * 10.0;

    final double j =
      input.height()
        .to(CENTIMETRE)
        .getValue()
        .doubleValue() * 6.25;

    final double m =
      input.age().getValue().doubleValue() * 5.0;

    final double n;
    switch (input.gender()) {
      case MALE:
        n = 5.0;
        break;
      case FEMALE:
        n = -161.0;
        break;
      default:
        throw new IllegalStateException();
    }

    final var raw = ((k + j) - m) + n;
    return Quantities.getQuantity(Double.valueOf(raw), CLDR.FOODCALORIE);
  }
}
