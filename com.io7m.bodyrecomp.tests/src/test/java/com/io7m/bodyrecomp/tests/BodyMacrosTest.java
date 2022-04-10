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

import com.io7m.bodyrecomp.core.BodyMacroEstimates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.io7m.bodyrecomp.tests.ExamplePeople.ANDRE;
import static com.io7m.bodyrecomp.tests.ExamplePeople.BILL;
import static com.io7m.bodyrecomp.tests.ExamplePeople.NB;
import static com.io7m.bodyrecomp.tests.ExamplePeople.SALLY;

public final class BodyMacrosTest
{
  private static final Logger LOG =
    LoggerFactory.getLogger(BodyMacrosTest.class);

  @Test
  public void exampleBill()
  {
    final var macros = BodyMacroEstimates.estimateFor(BILL);
    LOG.debug("macros: {}", macros);
  }

  @Test
  public void exampleSally()
  {
    final var macros = BodyMacroEstimates.estimateFor(SALLY);
    LOG.debug("macros: {}", macros);
  }

  @Test
  public void exampleAndre()
  {
    final var macros = BodyMacroEstimates.estimateFor(ANDRE);
    LOG.debug("macros: {}", macros);
    LOG.debug("calories {}", macros.calories());
    LOG.debug("fat grams {}", macros.fatGrams());
    LOG.debug("protein grams {}", macros.proteinGrams());
    LOG.debug("carbohydrate grams {}", macros.carbohydrateGrams());

    Assertions.assertEquals(
      2088.0,
      Math.ceil(macros.calories().getValue().doubleValue()));
    Assertions.assertEquals(
      195.0,
      Math.ceil(macros.proteinGrams().getValue().doubleValue()));
    Assertions.assertEquals(
      73.0,
      Math.ceil(macros.fatGrams().getValue().doubleValue()));
    Assertions.assertEquals(
      164.0,
      Math.ceil(macros.carbohydrateGrams().getValue().doubleValue()));
  }

  @Test
  public void exampleNB()
  {
    final var macros = BodyMacroEstimates.estimateFor(NB);
    LOG.debug("calories {}", macros.calories());
    LOG.debug("fat grams {}", macros.fatGrams());
    LOG.debug("protein grams {}", macros.proteinGrams());
    LOG.debug("carbohydrate grams {}", macros.carbohydrateGrams());
  }
}
