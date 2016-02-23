#include "get_remote_control.h"

#include <pthread.h>
#include <stdio.h>
#include <termios.h>
#include <unistd.h>
#include <ctype.h>
#include <math.h>

// 此函数用于在终端输入字符时，不用按回车键，函数就可以得到输入的字符 
int getch( ) 
{
	struct termios oldt,
				   newt;
	int ch;
	tcgetattr( STDIN_FILENO, &oldt );
	newt = oldt;
	newt.c_lflag &= ~( ICANON | ECHO );
	tcsetattr( STDIN_FILENO, TCSANOW, &newt );

	ch = getchar();

	tcsetattr( STDIN_FILENO, TCSANOW, &oldt );
	return ch;
}


int  g_direction = 'O';


void* thread_get_direction()
{

	int ch;	

	printf("\n[thread_get_direction()] please type N,E,W,S,O for direction or Q for quit.\n");
	do
	{
		ch = getch();
		printf("%c", ch);  // Just for info. Comment this if not necessary. 

		switch (ch)
		{
			case 'N':		// North
			case 'E':		// East
			case 'W':		// West
			case 'S':		// South
			case 'O':		// Don't move !!!!!
			case 'Q':		// Quit!
			case 'X':		// Exit!	// Same as Quit currently.
				g_direction = ch;	break;

			case 'n':
			case 'e':
			case 'w':
			case 's':
			case 'o':
			case 'q':
			case 'x':
				g_direction = toupper(ch);	break;
			default:	break;
		}

		if (g_direction == 'X') 
			g_direction='Q';

		printf("%c",g_direction);

	}	while ( g_direction != 'Q');     // 'Quit' or 'Exit'

	printf("\n[thread_get_direction()] thread finished. \n");

	return NULL;
}
