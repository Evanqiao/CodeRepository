// computer.cc
#include <iostream>
using namespace std;

class Computer
{
public:
	Computer(const string &name, float p):
			name_(name), fPrice_(p)
	{
		total_price_ += fPrice_;
	}
	~Computer()
	{
		total_price_ -= fPrice_;	
	}
	static void print_total();
	static void print(Computer &);
private:
	string name_;
	float fPrice_;
	static float total_price_;
};
void Computer::print_total() 
// 不包含this指针，不能调用非静态数据成员
// static成员函数由整个类共享，可以直接通过类调用
{
	//cout << "price: " << fPrice_ << endl; // error
	cout << "total price: " << total_price_ << endl;
}
void Computer::print(Computer &c)
{
	cout << "name: " << c.name_ << endl;
	cout << "price: " << c.fPrice_ << endl;
}

float Computer::total_price_ = 0.0;

int main()
{
	Computer c1("IBM", 3500);
	c1.print_total();
	Computer::print(c1);
}
