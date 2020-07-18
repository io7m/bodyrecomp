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

import com.io7m.bodyrecomp.core.DietaryProteinEstimates;
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

public final class DietaryProteinEstimatesTest
{
  private static final Logger LOG =
    LoggerFactory.getLogger(DietaryProteinEstimatesTest.class);

  @Test
  public void exampleMaleUpper()
  {
    ExamplePeople.showBody(MALE_UPPER_FAT, "maleUpper");

    final var protein = DietaryProteinEstimates.estimateFor(MALE_UPPER_FAT);

    Assertions.assertEquals(
      1.2,
      protein.gramsPerPound().getValue().doubleValue()
    );
  }

  @Test
  public void exampleMaleLower()
  {
    ExamplePeople.showBody(MALE_LOWER_FAT, "maleLower");

    final var protein = DietaryProteinEstimates.estimateFor(MALE_LOWER_FAT);

    Assertions.assertEquals(
      1.6,
      protein.gramsPerPound().getValue().doubleValue()
    );
  }

  @Test
  public void exampleFemaleUpper()
  {
    ExamplePeople.showBody(FEMALE_UPPER_FAT, "femaleUpper");

    final var protein = DietaryProteinEstimates.estimateFor(FEMALE_UPPER_FAT);

    Assertions.assertEquals(
      1.2,
      protein.gramsPerPound().getValue().doubleValue()
    );
  }

  @Test
  public void exampleFemaleLower()
  {
    ExamplePeople.showBody(FEMALE_LOWER_FAT, "femaleLower");

    final var protein = DietaryProteinEstimates.estimateFor(FEMALE_LOWER_FAT);

    Assertions.assertEquals(
      1.6,
      protein.gramsPerPound().getValue().doubleValue()
    );
  }

  @Test
  public void exampleSally()
  {
    ExamplePeople.showBody(SALLY, "sally");

    final var protein = DietaryProteinEstimates.estimateFor(SALLY);

    Assertions.assertEquals(
      1.3875,
      protein.gramsPerPound().getValue().doubleValue()
    );
    Assertions.assertEquals(
      141.0,
      protein.proteinGrams().getValue().doubleValue()
    );
  }

  @Test
  public void exampleHelga()
  {
    ExamplePeople.showBody(HELGA, "helga");

    final var protein = DietaryProteinEstimates.estimateFor(HELGA);

    Assertions.assertEquals(
      144.0,
      protein.proteinGrams().getValue().doubleValue()
    );
    Assertions.assertEquals(
      1.2,
      protein.gramsPerPound().getValue().doubleValue()
    );
  }

  @Test
  public void exampleJunior()
  {
    ExamplePeople.showBody(JUNIOR, "junior");

    final var protein = DietaryProteinEstimates.estimateFor(JUNIOR);

    Assertions.assertEquals(
      1.52,
      protein.gramsPerPound().getValue().doubleValue()
    );
    Assertions.assertEquals(
      205.0,
      protein.proteinGrams().getValue().doubleValue()
    );
  }

  @Test
  public void exampleBill()
  {
    ExamplePeople.showBody(BILL, "bill");

    final var protein = DietaryProteinEstimates.estimateFor(BILL);

    Assertions.assertEquals(
      1.2,
      protein.gramsPerPound().getValue().doubleValue()
    );
    Assertions.assertEquals(
      202.0,
      protein.proteinGrams().getValue().doubleValue()
    );
  }

  @Test
  public void exampleAndre()
  {
    ExamplePeople.showBody(ANDRE, "andre");

    final var protein = DietaryProteinEstimates.estimateFor(ANDRE);

    Assertions.assertEquals(
      1.36,
      protein.gramsPerPound().getValue().doubleValue()
    );
    Assertions.assertEquals(
      195.0,
      protein.proteinGrams().getValue().doubleValue()
    );
  }
}
