#if !defined(__CYGWIN__)
#if defined(__MSP430__)
#include <sys/inttypes.h>
#else
#include <inttypes.h>
#endif
#else //cygwin
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#endif

#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <stddef.h>
#include <ctype.h>


typedef uint8_t bool;
enum { FALSE = 0, TRUE = 1 };

typedef nx_int8_t nx_bool;
uint16_t TOS_NODE_ID = 1;

/* This macro is used to mark pointers that represent ownership
   transfer in interfaces. See TEP 3 for more discussion. */
#define PASS

struct @atmostonce { };
struct @atleastonce { };
struct @exactlyonce { };

/* This platform_bootstrap macro exists in accordance with TEP
   107. A platform may override this through a platform.h file. */
#include <platform.h>
#ifndef platform_bootstrap
#define platform_bootstrap() {}
#endif

#ifndef TOSSIM
#define dbg(s, ...) 
#define dbgerror(s, ...) 
#define dbg_clear(s, ...) 
#define dbgerror_clear(s, ...) 
#endif

