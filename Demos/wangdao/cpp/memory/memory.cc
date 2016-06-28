// memory.cpp
#include <iostream>
using namespace std;

int ival = 10;// 位于全局区
const int ival2 = 20;// 位于文字常量区
double dval = 30.0;// 位于全局区

int main(void)
{
	int  ival3 = 40;
	const int ival4 = 50;// 位于栈区，因为和上面的
						 // ival2的生命周期不一样
	double dval2 = 60.0;
	char *pstr = "123456";// pstr位于栈区，字符串
						  // "123456"位于文字常量区
	return 0;
}
