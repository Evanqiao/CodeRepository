// computer.cpp
#include <iostream>
#include <cstring>
using namespace std;

class Computer
{
public:
	Computer(const char *s, float p) : fPrice_(p)
	{
		cout << "Create object" << endl;
		pBrand_ = new char[strlen(s) + 1];
		strcpy(pBrand_, s);
	}
	void p()
	{
		cout << "p"	<< endl;
	}
	void print()
	{
		cout << "Brand is " << pBrand_
		 	 << "	" << "Price is " << fPrice_
			 << endl;
	}
	~Computer()
	{
		delete []pBrand_;
		pBrand_ = NULL;
		cout << "clean" << endl;
	}
#if 0
	// 默认的拷贝构造函数,浅拷贝
	Computer(const Computer &c)
	{
		pBrand_ = c.pBrand_;
		fPrice_ = c.fPrice_;
	}
#endif
	Computer(const Computer &c) : fPrice_(c.fPrice_)
	{
		pBrand_ = new char[strlen(c.pBrand_) + 1];
		strcpy(pBrand_, c.pBrand_); // 深拷贝
	}
private:
	char *pBrand_;
	float fPrice_;
};
int main(void)
{
	Computer c1("dell", 3800);
	c1.print();
	Computer c2(c1);
	c2.print();
  	Computer c3;
	return 0;
}
