// $Id: InternalFlashC.nc,v 1.1 2007/06/02 00:09:15 razvanm Exp $

/*									tab:2
 *
 *
 * "Copyright (c) 2000-2005 The Regents of the University  of California.  
 * All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software and its
 * documentation for any purpose, without fee, and without written agreement is
 * hereby granted, provided that the above copyright notice, the following
 * two paragraphs and the author appear in all copies of this software.
 * 
 * IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR
 * DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES ARISING OUT
 * OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF THE UNIVERSITY OF
 * CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE.  THE SOFTWARE PROVIDED HEREUNDER IS
 * ON AN "AS IS" BASIS, AND THE UNIVERSITY OF CALIFORNIA HAS NO OBLIGATION TO
 * PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS."
 *
 */

/**
 * @author Jonathan Hui <jwhui@cs.berkeley.edu>
 */

#include "InternalFlash.h"

module InternalFlashC {
  provides interface InternalFlash;
}

implementation {

  command error_t InternalFlash.write(void* addr, void* buf, uint16_t size) {

    uint8_t *addrPtr = (uint8_t*)addr;
    uint8_t *bufPtr = (uint8_t*)buf;

    for ( ; size; size-- )
      eeprom_write_byte(addrPtr++, *bufPtr++);

    while(!eeprom_is_ready());

    return SUCCESS;

  }

  command error_t InternalFlash.read(void* addr, void* buf, uint16_t size) {

    uint8_t *addrPtr = (uint8_t*)addr;
    uint8_t *bufPtr = (uint8_t*)buf;

    for ( ; size; size-- )
      *bufPtr++ = eeprom_read_byte(addrPtr++);

    return SUCCESS;

  }

}
