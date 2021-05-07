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

package com.io7m.bodyrecomp.cmdline.internal;

import com.io7m.bodyrecomp.strings.api.BRAbstractStrings;

import java.util.ResourceBundle;

/**
 * String resources.
 */

public final class BRMessages extends BRAbstractStrings
{
  private BRMessages(
    final ResourceBundle inResources)
  {
    super(inResources);
  }

  /**
   * @return The string resources
   */

  public static BRMessages create()
  {
    return new BRMessages(
      ofXMLResource(
        BRMessages.class,
        "/com/io7m/bodyrecomp/cmdline/internal/Messages.xml")
    );
  }

  @Override
  public String toString()
  {
    return String.format(
      "[BRMessages 0x%s]",
      Long.toUnsignedString(System.identityHashCode(this), 16)
    );
  }
}
