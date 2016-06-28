// cast.cpp
#include <iostream>
using namespace std;

int main(void)
{
	int ival = 10;
	double dval = static_cast<double>(ival);
	cout << "dval = " << dval << endl;
	return 0;
}
