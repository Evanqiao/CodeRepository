include<iostream>
using namespace std;

int num = 0;
namespace A
{
	int num = 1;
}
namespace B
{
	int num = 2;
	void func()
	{
		cout << "B::num = " << num << endl;
		cout << "B::num = " << B::num << endl;
		cout << "A::num = " << A::num << endl;
		cout << "extern num = " << ::num << endl;
	}
}
int main(void)
{
	B::func();
	return 0;
}
