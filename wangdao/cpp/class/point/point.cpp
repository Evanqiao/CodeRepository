// point.cpp
#include <iostream>
#include <cstdlib>
using namespace std;

class Point 
{
public:
  	Point(int x = 1, int y = 2) : ix_(x), iy_(y)
  	{}
	void print()
	{
		cout << "ix_ = " << ix_
		 	 << ", iy_ = " << iy_
			 << endl;
	}
private:
	int ix_;
	int iy_;
};
int main(void)
{
	Point p1(3, 4);
	p1.print();
	Point p2;
	p2.print();
	p2 = p1;
	p2.print();
	return 0;
}
