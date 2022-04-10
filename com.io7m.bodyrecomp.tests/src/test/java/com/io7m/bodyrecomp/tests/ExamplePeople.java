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

package com.io7m.bodyrecomp.tests;

import com.io7m.bodyrecomp.core.BodyDefinition;
import com.io7m.bodyrecomp.core.CaloricAdjustment;
import com.io7m.bodyrecomp.core.NormalCoefficient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.units.indriya.quantity.Quantities;

import static com.io7m.bodyrecomp.core.ActivityCoefficient.MODERATELY_ACTIVE;
import static com.io7m.bodyrecomp.core.ActivityCoefficient.SEDENTARY;
import static com.io7m.bodyrecomp.core.BiologicalGender.FEMALE;
import static com.io7m.bodyrecomp.core.BiologicalGender.MALE;
import static systems.uom.common.Imperial.POUND;
import static tech.units.indriya.unit.Units.KILOGRAM;
import static tech.units.indriya.unit.Units.METRE;
import static tech.units.indriya.unit.Units.YEAR;

public final class ExamplePeople
{
  public static final BodyDefinition BILL =
    BodyDefinition.builder()
      .setGender(MALE)
      .setActivity(MODERATELY_ACTIVE)
      .setAge(Quantities.getQuantity(35, YEAR))
      .setBodyHeight(Quantities.getQuantity(1.72, METRE))
      .setBodyFatCoefficient(NormalCoefficient.of(0.3))
      .setBodyWeight(Quantities.getQuantity(Integer.valueOf(240), POUND).to(
        KILOGRAM))
      .build();
  public static final BodyDefinition JUNIOR =
    BodyDefinition.builder()
      .setGender(MALE)
      .setActivity(MODERATELY_ACTIVE)
      .setAge(Quantities.getQuantity(35, YEAR))
      .setBodyHeight(Quantities.getQuantity(1.72, METRE))
      .setBodyFatCoefficient(NormalCoefficient.of(0.1))
      .setBodyWeight(Quantities.getQuantity(Integer.valueOf(150), POUND).to(
        KILOGRAM))
      .build();
  public static final BodyDefinition HELGA =
    BodyDefinition.builder()
      .setGender(FEMALE)
      .setActivity(MODERATELY_ACTIVE)
      .setAge(Quantities.getQuantity(35, YEAR))
      .setBodyHeight(Quantities.getQuantity(1.72, METRE))
      .setBodyFatCoefficient(NormalCoefficient.of(0.4))
      .setBodyWeight(Quantities.getQuantity(Integer.valueOf(200), POUND).to(
        KILOGRAM))
      .build();
  public static final BodyDefinition SALLY =
    BodyDefinition.builder()
      .setGender(FEMALE)
      .setActivity(MODERATELY_ACTIVE)
      .setAge(Quantities.getQuantity(35, YEAR))
      .setBodyHeight(Quantities.getQuantity(1.72, METRE))
      .setBodyFatCoefficient(NormalCoefficient.of(0.25))
      .setBodyWeight(Quantities.getQuantity(Integer.valueOf(135), POUND).to(
        KILOGRAM))
      .build();
  public static final BodyDefinition FEMALE_LOWER_FAT =
    BodyDefinition.builder()
      .setGender(FEMALE)
      .setActivity(MODERATELY_ACTIVE)
      .setAge(Quantities.getQuantity(35, YEAR))
      .setBodyHeight(Quantities.getQuantity(1.72, METRE))
      .setBodyFatCoefficient(NormalCoefficient.of(0.08))
      .setBodyWeight(Quantities.getQuantity(Double.valueOf(80.0), KILOGRAM).to(
        KILOGRAM))
      .build();
  public static final BodyDefinition FEMALE_UPPER_FAT =
    BodyDefinition.builder()
      .setGender(FEMALE)
      .setActivity(MODERATELY_ACTIVE)
      .setAge(Quantities.getQuantity(35, YEAR))
      .setBodyHeight(Quantities.getQuantity(1.72, METRE))
      .setBodyFatCoefficient(NormalCoefficient.of(0.4))
      .setBodyWeight(Quantities.getQuantity(Double.valueOf(80.0), KILOGRAM).to(
        KILOGRAM))
      .build();
  public static final BodyDefinition MALE_LOWER_FAT =
    BodyDefinition.builder()
      .setGender(MALE)
      .setActivity(MODERATELY_ACTIVE)
      .setAge(Quantities.getQuantity(35, YEAR))
      .setBodyHeight(Quantities.getQuantity(1.72, METRE))
      .setBodyFatCoefficient(NormalCoefficient.of(0.05))
      .setBodyWeight(Quantities.getQuantity(Double.valueOf(80.0), KILOGRAM).to(
        KILOGRAM))
      .build();
  public static final BodyDefinition MALE_UPPER_FAT =
    BodyDefinition.builder()
      .setGender(MALE)
      .setActivity(MODERATELY_ACTIVE)
      .setAge(Quantities.getQuantity(35, YEAR))
      .setBodyHeight(Quantities.getQuantity(1.72, METRE))
      .setBodyFatCoefficient(NormalCoefficient.of(0.3))
      .setBodyWeight(Quantities.getQuantity(Double.valueOf(80.0), KILOGRAM).to(
        KILOGRAM))
      .build();
  public static final BodyDefinition ANDRE =
    BodyDefinition.builder()
      .setGender(MALE)
      .setActivity(SEDENTARY)
      .setCaloricAdjustment(CaloricAdjustment.SMALL_DEFICIT.coefficient())
      .setAge(Quantities.getQuantity(35, YEAR))
      .setBodyHeight(Quantities.getQuantity(1.72, METRE))
      .setBodyFatCoefficient(NormalCoefficient.of(0.2))
      .setBodyWeight(Quantities.getQuantity(Double.valueOf(81.3), KILOGRAM).to(
        KILOGRAM))
      .build();
  public static final BodyDefinition NB =
    BodyDefinition.builder()
      .setGender(FEMALE)
      .setActivity(SEDENTARY)
      .setCaloricAdjustment(CaloricAdjustment.SMALL_DEFICIT.coefficient())
      .setAge(Quantities.getQuantity(51, YEAR))
      .setBodyHeight(Quantities.getQuantity(1.65, METRE))
      .setBodyFatCoefficient(NormalCoefficient.of(0.29))
      .setBodyWeight(Quantities.getQuantity(Double.valueOf(81.64), KILOGRAM).to(
        KILOGRAM))
      .build();
  private static final Logger LOG = LoggerFactory.getLogger(ExamplePeople.class);

  private ExamplePeople()
  {

  }

  public static void showBody(
    final BodyDefinition body,
    final String name)
  {
    LOG.debug(
      "{}: weight    {} ({})",
      name,
      body.bodyWeight(),
      body.bodyWeight().to(
        POUND));
    LOG.debug(
      "{}: body fat  {} ({}%)",
      name,
      Double.valueOf(body.bodyFatCoefficient().value()),
      Double.valueOf(body.bodyFatPercentage()));
    LOG.debug(
      "{}: lean mass {} ({})",
      name,
      body.leanMassWeight(),
      body.leanMassWeight().to(POUND));
    LOG.debug(
      "{}: fat mass  {} ({})",
      name,
      body.fatWeight(),
      body.fatWeight().to(POUND));
  }
}
