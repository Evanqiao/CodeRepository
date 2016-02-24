#include<iostream>
using namespace std;

int a[3] = {1, 2, 3};
int & index(int idx)
{
	return a[idx];
}
int main(void)
{
	cout << "a[1] = " << index(1) << endl;
	index(1) = 100;
	cout << "a[1] = " << index(1) << endl;
	return 0;
}
