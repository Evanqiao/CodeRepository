// overload.cpp
#include <iostream>
using namespace std;
#ifdef __cplusplus
extern "C"
{
#endif
	int add(int a, int b)
	{
		return a + b;
	}
#ifdef __cplusplus
}
#endif
int add(int a, int b, int c)
{
	return a + b + c;
}
double add(int a, double b)
{
	return a + b;
}
int main(void)
{
	return 0;
}
