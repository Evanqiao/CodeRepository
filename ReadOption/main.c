#include "getopt.h"
#include <string.h>
#include <stdio.h>
 
int main(int argc, char **argv) 
{
	int result;
	double llh[3];
	while ((result = getopt(argc,argv,"e:u:g:l:o:s:b:")) != -1)
	{
		switch (result)
		{
			case 'e':
				printf("%s\n", optarg);
				break;
			case 'u':
				printf("%s\n", optarg);
				break;
			case 'g':
				printf("%s\n", optarg);
				break;
			case 'l':
				printf("%s\n", optarg);
				sscanf(optarg,"%lf,%lf,%lf",&llh[0],&llh[1],&llh[2]);
				for(int i = 0; i < 3; i++) {
					printf("%lf\n", llh[i]);	
				}
				break;
			case 'o':
				printf("%s\n", optarg);
				break;
			case 's':
				printf("%s\n", optarg);
				break;
			case 'b':
				printf("%s\n", optarg);
				break;
			case ':':
			case '?':
				printf("usage()\n");
				exit(1);
			default:
				break;
		}
	}

}

