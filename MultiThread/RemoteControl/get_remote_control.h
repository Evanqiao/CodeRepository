// get_remote_control.h
//

#ifndef _GET_REMOTE_CONTROL_H
#define _GET_REMOTE_CONTROL_H



// in an independent thread, 
// get a character ('N', 'E', 'W', 'S') denoting for a  direction
// 'Q' for quit

extern int  g_direction;	

void* thread_get_direction();

#endif
