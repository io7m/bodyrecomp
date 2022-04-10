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

import com.io7m.bodyrecomp.core.internal.BRCoreMessages;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Energy;
import javax.measure.quantity.Mass;
import java.util.ArrayList;
import java.util.Objects;

import static tech.units.indriya.unit.Units.GRAM;

/**
 * Functions to estimate macronutrients for a given body.
 */

public final class BodyMacroEstimates
{
  private BodyMacroEstimates()
  {

  }

  /**
   * Estimate the macronutrients required for the given body.
   *
   * @param body The body
   *
   * @return The set of macronutrients
   */

  public static BodyMacros estimateFor(
    final BodyDefinition body)
  {
    Objects.requireNonNull(body, "body");

    final var messages =
      BRCoreMessages.create();
    final var explanations =
      new ArrayList<String>();

    final var bmrInput =
      MifflinStJeorInput.builder()
        .setAge(body.age())
        .setBodyWeight(body.bodyWeight())
        .setGender(body.gender())
        .setHeight(body.bodyHeight())
        .build();

    final Quantity<Energy> bmr =
      MifflinStJeor.basalMetabolicRate(bmrInput);

    explainMifflinStJeor(messages, explanations, bmrInput, bmr);

    final Quantity<Energy> maintenanceCalories =
      MaintenanceCalories.maintenanceEstimate(bmr, body.activity());

    explainMaintenanceCalories(
      messages,
      explanations,
      body.activity(),
      maintenanceCalories
    );

    final Quantity<Energy> recompCalories =
      maintenanceCalories.multiply(
        Double.valueOf(body.caloricAdjustment().value())
      );

    explainRecompCalories(
      messages,
      explanations,
      recompCalories,
      body.caloricAdjustment()
    );

    final DietaryProteinEstimate protein =
      DietaryProteinEstimates.estimateFor(body);
    final Quantity<Energy> proteinCalories =
      protein.proteinCalories();

    final DietaryFatEstimate fat =
      DietaryFatEstimates.estimateFor(body);
    final Quantity<Energy> fatCalories =
      recompCalories.multiply(Double.valueOf(fat.fatCoefficient().value()));
    final Quantity<Mass> fatGrams =
      Quantities.getQuantity(
        Double.valueOf(fatCalories.getValue().doubleValue() / 9.0),
        GRAM
      );

    final Quantity<Energy> carbohydrateCalories =
      recompCalories.subtract(proteinCalories.add(fatCalories));
    final Quantity<Mass> carbohydrateGrams =
      Quantities.getQuantity(
        Double.valueOf(carbohydrateCalories.getValue().doubleValue() / 4.0),
        GRAM
      );

    explainMacros(
      messages,
      explanations,
      recompCalories,
      protein,
      fatCalories,
      fatGrams,
      carbohydrateCalories,
      carbohydrateGrams
    );

    return BodyMacros.builder()
      .setExplanations(explanations)
      .setCalories(recompCalories)
      .setProteinGrams(protein.proteinGrams())
      .setCarbohydrateGrams(carbohydrateGrams)
      .setFatGrams(fatGrams)
      .build();
  }

  private static void explainMacros(
    final BRCoreMessages messages,
    final ArrayList<String> explanations,
    final Quantity<Energy> recompCalories,
    final DietaryProteinEstimate protein,
    final Quantity<Energy> fatCalories,
    final Quantity<Mass> fatGrams,
    final Quantity<Energy> carbohydrateCalories,
    final Quantity<Mass> carbohydrateGrams)
  {
    explanations.add(
      messages.format(
        "explainMacros",
        Integer.valueOf(recompCalories.getValue().intValue()),
        protein.gramsPerKilogram(),
        Integer.valueOf(protein.proteinCalories().getValue().intValue()),
        Integer.valueOf(protein.proteinGrams().getValue().intValue()),
        Integer.valueOf(fatCalories.getValue().intValue()),
        Integer.valueOf(fatGrams.getValue().intValue()),
        Integer.valueOf(carbohydrateCalories.getValue().intValue()),
        Integer.valueOf(carbohydrateGrams.getValue().intValue())
      )
    );

    explanations.add(
      messages.format(
        "explainSummary",
        Integer.valueOf(recompCalories.getValue().intValue()),
        Integer.valueOf(protein.proteinGrams().getValue().intValue()),
        Integer.valueOf(fatGrams.getValue().intValue()),
        Integer.valueOf(carbohydrateGrams.getValue().intValue())
      )
    );
  }

  private static void explainRecompCalories(
    final BRCoreMessages messages,
    final ArrayList<String> explanations,
    final Quantity<Energy> recompCalories,
    final GeneralCoefficient caloricAdjustment)
  {
    explanations.add(
      messages.format(
        "explainRecompCalories",
        caloricAdjustmentName(messages, caloricAdjustment),
        Integer.valueOf(recompCalories.getValue().intValue())
      )
    );
  }

  private static String caloricAdjustmentName(
    final BRCoreMessages messages,
    final GeneralCoefficient caloricAdjustment)
  {
    final var values = CaloricAdjustment.values();
    for (final var value : values) {
      if (value.coefficient().equals(caloricAdjustment)) {
        return value.toString();
      }
    }

    if (caloricAdjustment.asPercent() > 100.0) {
      return messages.format(
        "surplus",
        Double.valueOf(caloricAdjustment.asPercent())
      );
    }

    if (caloricAdjustment.asPercent() < 100.0) {
      return messages.format(
        "deficit",
        Double.valueOf(caloricAdjustment.asPercent())
      );
    }

    return messages.format(
      "maintain",
      Double.valueOf(caloricAdjustment.asPercent())
    );
  }

  private static void explainMaintenanceCalories(
    final BRCoreMessages messages,
    final ArrayList<String> explanations,
    final ActivityCoefficient activity,
    final Quantity<Energy> maintenanceCalories)
  {
    explanations.add(
      messages.format(
        "explainMaintenance",
        activity,
        Double.valueOf(activity.coefficient()),
        Integer.valueOf(maintenanceCalories.getValue().intValue())
      )
    );
  }

  private static void explainMifflinStJeor(
    final BRCoreMessages messages,
    final ArrayList<String> explanations,
    final MifflinStJeorInput bmrInput,
    final Quantity<Energy> bmr)
  {
    explanations.add(
      messages.format(
        "explainBMR",
        Integer.valueOf(bmrInput.age().getValue().intValue()),
        Double.valueOf(bmrInput.height().getValue().doubleValue()),
        Integer.valueOf(bmrInput.bodyWeight().getValue().intValue()),
        bmrInput.gender(),
        Integer.valueOf(bmr.getValue().intValue())
      )
    );
  }
}
