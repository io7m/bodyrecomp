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

import com.io7m.jaffirm.core.Invariants;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.util.Objects;

/**
 * Functions to estimate dietary protein.
 */

public final class DietaryProteinEstimates
{
  private DietaryProteinEstimates()
  {

  }

  /**
   * Estimate dietary protein for the given body.
   *
   * @param body The body
   *
   * @return The dietary protein estimate
   */

  public static DietaryProteinEstimate estimateFor(
    final BodyDefinition body)
  {
    Objects.requireNonNull(body, "body");

    final Quantity<Mass> gramsPerPound =
      proteinGramsPerPound(body);

    Invariants.checkInvariant(
      Objects.equals(gramsPerPound.getUnit(), Units.GRAM),
      "Value must be in grams"
    );

    final Quantity<Mass> gramsPerKilo =
      gramsPerPound.multiply(Double.valueOf(2.2));

    Invariants.checkInvariant(
      Objects.equals(gramsPerKilo.getUnit(), Units.GRAM),
      "Value must be in grams"
    );

    final double leanMassKilos =
      body.leanMassWeight()
        .to(Units.KILOGRAM)
        .getValue()
        .doubleValue();

    final double totalRaw =
      leanMassKilos * gramsPerKilo.getValue().doubleValue();

    final var totalGrams =
      Quantities.getQuantity(
        Double.valueOf(Math.ceil(totalRaw)),
        Units.GRAM
      );

    return DietaryProteinEstimate.builder()
      .setGramsPerKilogram(gramsPerKilo)
      .setGramsPerPound(gramsPerPound)
      .setProteinGrams(totalGrams)
      .build();
  }

  private static Quantity<Mass> proteinGramsPerPound(
    final BodyDefinition body)
  {
    final var gramsPerPoundLow = 1.6;
    final var gramsPerPoundHigh = 1.2;

    final double gramsPerPound;
    switch (body.gender()) {
      case MALE: {
        gramsPerPound =
          NormalCoefficients.mapIntoRange(
            5.0,
            30.0,
            gramsPerPoundLow,
            gramsPerPoundHigh,
            body.bodyFatPercentage()
          );
        break;
      }
      case FEMALE: {
        gramsPerPound =
          NormalCoefficients.mapIntoRange(
            8.0,
            40.0,
            gramsPerPoundLow,
            gramsPerPoundHigh,
            body.bodyFatPercentage()
          );
        break;
      }
      default:
        throw new IllegalStateException();
    }

    return Quantities.getQuantity(Double.valueOf(gramsPerPound), Units.GRAM);
  }
}
