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

package com.io7m.bodyrecomp.tests;

import com.io7m.bodyrecomp.core.BodyDefinition;
import com.io7m.bodyrecomp.core.DietaryFatEstimates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.io7m.bodyrecomp.tests.ExamplePeople.ANDRE;
import static com.io7m.bodyrecomp.tests.ExamplePeople.BILL;
import static com.io7m.bodyrecomp.tests.ExamplePeople.FEMALE_LOWER_FAT;
import static com.io7m.bodyrecomp.tests.ExamplePeople.FEMALE_UPPER_FAT;
import static com.io7m.bodyrecomp.tests.ExamplePeople.HELGA;
import static com.io7m.bodyrecomp.tests.ExamplePeople.JUNIOR;
import static com.io7m.bodyrecomp.tests.ExamplePeople.MALE_LOWER_FAT;
import static com.io7m.bodyrecomp.tests.ExamplePeople.MALE_UPPER_FAT;
import static com.io7m.bodyrecomp.tests.ExamplePeople.SALLY;
import static systems.uom.common.Imperial.POUND;

public final class DietaryFatEstimatesTest
{
  private static final Logger LOG =
    LoggerFactory.getLogger(DietaryFatEstimatesTest.class);

  private static void showBody(
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

  @Test
  public void exampleMaleUpper()
  {
    showBody(MALE_UPPER_FAT, "maleUpper");

    final var fat = DietaryFatEstimates.estimateFor(MALE_UPPER_FAT);

    Assertions.assertEquals(
      35.0,
      fat.fatCoefficient().asPercent()
    );
  }

  @Test
  public void exampleMaleLower()
  {
    showBody(MALE_LOWER_FAT, "maleLower");

    final var fat = DietaryFatEstimates.estimateFor(MALE_LOWER_FAT);

    Assertions.assertEquals(
      20.0,
      fat.fatCoefficient().asPercent()
    );
  }

  @Test
  public void exampleFemaleUpper()
  {
    showBody(FEMALE_UPPER_FAT, "femaleUpper");

    final var fat = DietaryFatEstimates.estimateFor(FEMALE_UPPER_FAT);

    Assertions.assertEquals(
      35.0,
      fat.fatCoefficient().asPercent()
    );
  }

  @Test
  public void exampleFemaleLower()
  {
    showBody(FEMALE_LOWER_FAT, "femaleLower");

    final var fat = DietaryFatEstimates.estimateFor(FEMALE_LOWER_FAT);

    Assertions.assertEquals(
      20.0,
      fat.fatCoefficient().asPercent()
    );
  }

  @Test
  public void exampleSally()
  {
    showBody(SALLY, "sally");

    final var fat = DietaryFatEstimates.estimateFor(SALLY);

    Assertions.assertEquals(
      27.500000000000004,
      fat.fatCoefficient().asPercent()
    );
  }

  @Test
  public void exampleHelga()
  {
    showBody(HELGA, "helga");

    final var fat = DietaryFatEstimates.estimateFor(HELGA);

    Assertions.assertEquals(
      35.0,
      fat.fatCoefficient().asPercent()
    );
  }

  @Test
  public void exampleJunior()
  {
    showBody(JUNIOR, "junior");

    final var fat = DietaryFatEstimates.estimateFor(JUNIOR);

    Assertions.assertEquals(
      23.75,
      fat.fatCoefficient().asPercent()
    );
  }

  @Test
  public void exampleBill()
  {
    showBody(BILL, "bill");

    final var fat = DietaryFatEstimates.estimateFor(BILL);

    Assertions.assertEquals(
      35.0,
      fat.fatCoefficient().asPercent()
    );
  }

  @Test
  public void exampleAndre()
  {
    showBody(ANDRE, "andre");

    final var fat = DietaryFatEstimates.estimateFor(ANDRE);

    Assertions.assertEquals(
      31.25,
      fat.fatCoefficient().asPercent()
    );
  }
}
