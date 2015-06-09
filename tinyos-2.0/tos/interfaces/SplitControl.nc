/*
 * "Copyright (c) 2005 Washington University in St. Louis.
 * All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software and its
 * documentation for any purpose, without fee, and without written agreement is
 * hereby granted, provided that the above copyright notice, the following
 * two paragraphs and the author appear in all copies of this software.
 *
 * IN NO EVENT SHALL WASHINGTON UNIVERSITY IN ST. LOUIS BE LIABLE TO ANY PARTY 
 * FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES ARISING 
 * OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF WASHINGTON 
 * UNIVERSITY IN ST. LOUIS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * WASHINGTON UNIVERSITY IN ST. LOUIS SPECIFICALLY DISCLAIMS ANY WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE.  THE SOFTWARE PROVIDED HEREUNDER IS
 * ON AN "AS IS" BASIS, AND WASHINGTON UNIVERSITY IN ST. LOUIS HAS NO 
 * OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR
 * MODIFICATIONS."
 */
 
/*
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
 */

/*
 * - Revision -------------------------------------------------------------
 * $Revision: 1.5 $
 * $Date: 2007/04/15 21:11:38 $ 
 * ======================================================================== 
 */ 

/**
 * Please refer to TEP 115 for more information about this interface and its
 * intended use.<br><br>
 *
 * This is the split-phase counterpart to the StdContol interface.  It
 * should be used for switching between the on and off power states of
 * the component providing it.  For each <code>start()</code> or
 * <code>stop()</code> command, if the command returns SUCCESS, then a
 * corresponding  <code>startDone()</code> or <code>stopDone()</code> event
 * must be signalled.
 *
 * @author Joe Polastre
 * @author Kevin Klues (klueska@cs.wustl.edu)
 */

interface SplitControl
{
  /**
   * Start this component and all of its subcomponents.  Return
   * values of SUCCESS will always result in a <code>startDone()</code>
   * event being signalled.
   *
   * @return SUCCESS if the device is already in the process of 
   *         starting or the device was off and the device is now ready to turn 
   *         on.  After receiving this return value, you should expect a 
   *         <code>startDone</code> event in the near future.<br>
   *         EBUSY if the component is in the middle of powering down
   *               i.e. a <code>stop()</code> command has been called,
   *               and a <code>stopDone()</code> event is pending<br>
   *         EALREADY if the device is already on <br>
   *         FAIL Otherwise
   */
  command error_t start();

  /** 
   * Notify caller that the component has been started and is ready to
   * receive other commands.
   *
   * @param <b>error</b> -- SUCCESS if the component was successfully
   *                        turned on, FAIL otherwise
   */
  event void startDone(error_t error);

  /**
   * Start this component and all of its subcomponents.  Return
   * values of SUCCESS will always result in a <code>startDone()</code>
   * event being signalled.
   *
   * @return SUCCESS if the device is already in the process of 
   *         stopping or the device was on and the device is now ready to turn 
   *         off.  After receiving this return value, you should expect a 
   *         <code>stopDone</code> event in the near future.<br>
   *         EBUSY if the component is in the middle of powering up
   *               i.e. a <code>start()</code> command has been called,
   *               and a <code>startDone()</code> event is pending<br>
   *         EALREADY if the device is already off <br>
   *         FAIL Otherwise
   */
  command error_t stop();

  /**
   * Notify caller that the component has been stopped.
   *
  * @param <b>error</b> -- SUCCESS if the component was successfully
  *                        turned off, FAIL otherwise
   */
  event void stopDone(error_t error);
}
