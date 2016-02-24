// sizeof.cc
#include <iostream>
using namespace std;

class Cell
{
private:
// ----------------32bit 64bit--------
	int a;        // 4    4
	char b;       // 1    1
	float c;      // 4    4
	double d;     // 8    8
	short e[5];   // 10   10
	char &f;      // 4    8
	double &g;    // 4    8
	static int h; 
};
int main(void)
{
	cout << "sizeof(Cell):" << sizeof(Cell) << endl;
	return 0;
}
