// new.cpp
#include <iostream>
#include <cstdlib>
using namespace std;

int main(void)
{
#if 0
	int *pa = (int *)malloc(sizeof(int));
	*pa = 3;
	cout << "*pa = " << *pa << endl;
	int *pb = new int(4);
	cout << "*pb = " << *pb << endl;
	free(pa);
	delete pb;

	int *pc = new int[5];
	for(int i = 0; i < 5; i++) {
		pc[i] = i;
		cout << "pc[" << i << "] = " << pc[i] << endl;
	}
	delete []pc;
#endif 
	return 0;
}
