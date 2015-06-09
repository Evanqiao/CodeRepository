#include <iostream> 

class Test
{
public:	
	Test();
	void print() const; 
private:
	static const int Num = 4;
	int a[Num];

};

int main()
{
	Test test;
	test.print();
	return 0;
}

Test::Test()
{
	a[0] = 1;
	a[1] = 2;
	a[2] = 3;
	a[3] = 4;
}
void Test::print() const
{
	using std::cout;
	using std::endl;
	using namespace std;
	vector<int> vec;
	
	vector<int>::iterator iter;
	
	for(int i = 0; i < 4; i++)	
		cout << a[i] << "	";
	cout << endl;
}
