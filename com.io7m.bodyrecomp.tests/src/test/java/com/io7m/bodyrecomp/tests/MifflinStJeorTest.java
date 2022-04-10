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

import com.io7m.bodyrecomp.core.MifflinStJeor;
import com.io7m.bodyrecomp.core.MifflinStJeorInput;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import static com.io7m.bodyrecomp.core.BiologicalGender.MALE;

public final class MifflinStJeorTest
{
  private static final Logger LOG =
    LoggerFactory.getLogger(MifflinStJeorTest.class);

  @Test
  public void example()
  {
    final var input =
      MifflinStJeorInput.builder()
        .setAge(Quantities.getQuantity(35, Units.YEAR))
        .setBodyWeight(Quantities.getQuantity(81.3, Units.KILOGRAM))
        .setHeight(Quantities.getQuantity(1.72, Units.METRE))
        .setGender(MALE)
        .build();

    final var bmr = MifflinStJeor.basalMetabolicRate(input);
    LOG.debug("BMR: {}", bmr);
  }
}
