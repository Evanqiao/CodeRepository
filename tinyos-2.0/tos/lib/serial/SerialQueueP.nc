// $Id: SerialQueueP.nc,v 1.4 2006/12/12 18:23:32 vlahan Exp $
/*
 * "Copyright (c) 2005 Stanford University. All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software and
 * its documentation for any purpose, without fee, and without written
 * agreement is hereby granted, provided that the above copyright
 * notice, the following two paragraphs and the author appear in all
 * copies of this software.
 * 
 * IN NO EVENT SHALL STANFORD UNIVERSITY BE LIABLE TO ANY PARTY FOR
 * DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
 * ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN
 * IF STANFORD UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 * 
 * STANFORD UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.  THE SOFTWARE
 * PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND STANFORD UNIVERSITY
 * HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
 * ENHANCEMENTS, OR MODIFICATIONS."
 */

/**
 * The fair-share AM send queue.
 *
 * @author Philip Levis
 * @date   Jan 16 2006
 */ 

#include "Serial.h"

configuration SerialQueueP {
  provides interface Send[uint8_t client];
}

implementation {
  enum {
    NUM_CLIENTS = uniqueCount(UQ_SERIALQUEUE_SEND)
  };
  
  components new AMQueueImplP(NUM_CLIENTS), SerialActiveMessageC;

  Send = AMQueueImplP;
  AMQueueImplP.AMSend -> SerialActiveMessageC;
  AMQueueImplP.AMPacket -> SerialActiveMessageC;
  AMQueueImplP.Packet -> SerialActiveMessageC;
  
}

