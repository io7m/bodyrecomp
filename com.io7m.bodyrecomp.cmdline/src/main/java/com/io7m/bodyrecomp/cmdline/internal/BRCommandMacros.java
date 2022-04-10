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

package com.io7m.bodyrecomp.cmdline.internal;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.io7m.bodyrecomp.core.ActivityCoefficient;
import com.io7m.bodyrecomp.core.BiologicalGender;
import com.io7m.bodyrecomp.core.BodyDefinition;
import com.io7m.bodyrecomp.core.BodyMacroEstimates;
import com.io7m.bodyrecomp.core.CaloricAdjustment;
import com.io7m.bodyrecomp.core.NormalCoefficient;
import com.io7m.claypot.core.CLPAbstractCommand;
import com.io7m.claypot.core.CLPCommandContextType;
import tech.units.indriya.quantity.Quantities;

import static com.io7m.claypot.core.CLPCommandType.Status.SUCCESS;
import static systems.uom.common.historic.CGS.CENTIMETRE;
import static tech.units.indriya.unit.Units.KILOGRAM;
import static tech.units.indriya.unit.Units.METRE;
import static tech.units.indriya.unit.Units.YEAR;

/**
 * The "macros" command.
 */

@Parameters(commandDescription = "Calculate macronutrients.")
public final class BRCommandMacros extends CLPAbstractCommand
{
  @Parameter(
    names = "--activity-level",
    required = true,
    description = "Your non-exercise activity level."
  )
  private ActivityCoefficient activity;

  @Parameter(
    names = "--height-cm",
    required = true,
    description = "Your height in centimeters."
  )
  private int heightCm;

  @Parameter(
    names = "--weight-kg",
    required = true,
    description = "Your weight in kilograms."
  )
  private int weightKg;

  @Parameter(
    names = "--age",
    required = true,
    description = "Your age in years."
  )
  private int ageYears;

  @Parameter(
    names = "--caloric-adjustment",
    required = true,
    description = "Your intended caloric adjustment."
  )
  private CaloricAdjustment caloricAdjustment;

  @Parameter(
    names = "--gender",
    required = true,
    description = "Your biological gender."
  )
  private BiologicalGender gender;

  @Parameter(
    names = "--body-fat-percent",
    required = true,
    description = "Your body fat percentage."
  )
  private int bodyFatPercentage;

  /**
   * Construct a command.
   *
   * @param inContext The command context
   */

  public BRCommandMacros(
    final CLPCommandContextType inContext)
  {
    super(inContext);
  }

  @Override
  public String extendedHelp()
  {
    return BRMessages.create().format("helpCmdMacros");
  }

  @Override
  protected Status executeActual()
  {
    final var messages = BRMessages.create();

    if (this.bodyFatPercentage < 0 || this.bodyFatPercentage > 100) {
      throw new IllegalArgumentException(messages.format("errorInvalidRange"));
    }

    final var heightAsCm =
      Quantities.getQuantity(Integer.valueOf(this.heightCm), CENTIMETRE);
    final var heightMeter =
      heightAsCm.to(METRE);
    final var ageQuantity =
      Quantities.getQuantity(Integer.valueOf(this.ageYears), YEAR);
    final var weightQuantity =
      Quantities.getQuantity(Integer.valueOf(this.weightKg), KILOGRAM);
    final var bodyFatCoefficient =
      NormalCoefficient.of(this.bodyFatPercentage / 100.0);

    final BodyDefinition person =
      BodyDefinition.builder()
        .setActivity(this.activity)
        .setAge(ageQuantity)
        .setBodyHeight(heightMeter)
        .setBodyWeight(weightQuantity)
        .setCaloricAdjustment(this.caloricAdjustment.coefficient())
        .setGender(this.gender)
        .setBodyFatCoefficient(bodyFatCoefficient)
        .build();

    final var macros = BodyMacroEstimates.estimateFor(person);
    for (final var explanation : macros.explanations()) {
      System.out.println(explanation);
    }
    return SUCCESS;
  }

  @Override
  public String name()
  {
    return "macros";
  }
}
