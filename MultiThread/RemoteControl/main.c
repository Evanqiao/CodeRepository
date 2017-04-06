#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <pthread.h>
#include "get_remote_control.h"

int main(int argc, char **argv) 
{
	pthread_t tid;
	int rc =  pthread_create(&tid, NULL, thread_get_direction, NULL);
	if ( rc != 0 ) {
		printf("can't create thread: %s\n", strerror(rc));
	}
	while(1) {
		switch(g_direction)	{	
			case 'Q':
				printf("exit!\n");
				exit(0);
				break;
			default:
				printf("\nmain: %c\n", g_direction);
				break;
		}
		sleep(1);
	}
	return 0;
}
