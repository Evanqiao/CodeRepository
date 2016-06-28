// desk.cpp
#include <iostream>
#include <cstring>
using namespace std;

class Desk
{
public:
	Desk(float p) : fPrice_(p)
	{
		cout << "Create object" << endl;
	}
	void print()
	{
		cout << "price";
	}
	~Desk()
	{
		cout << "clean" << endl;
	}
private:
	float fPrice_;
	static float total_price_ = 0.0;
};
int main(void)
{
	Desk d1(3800);
	return 0;
}
