// default.cpp
#include <iostream>
using namespace std;

// int add(int a, int b = 10, int c)  // error
int add(int a, int b, int c = 10)
{
	return a + b + c;
}
int main(void)
{
	cout << add(3,4) << endl;
}
