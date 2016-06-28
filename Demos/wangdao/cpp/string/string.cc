// string.cpp
#include <iostream>
#include <cstdlib>
using namespace std;
// using std::string;
int main(void)
{
#if 0
	string s1 = "Happy";
	string s2("new year");
	string s3 = s1 + s2;
	cout << s3 << endl;	
	char a = 'A';
	cout << s1 + s2 + a << endl;	

	int sz = s1.size();
	// 第一种遍历字符串的方式 -- 类似于数组
	for(int idx = 0; idx != sz; idx++) {
		cout << s1[idx] << endl;	
	}
	// 第二种遍历字符串的方式 -- 迭代器
	string::iterator it = s1.begin();
	for(; it != s1.end(); it++) {
		cout << *it << endl;	
	}
	// substr(size_type pos, size_type count)
	string substr = s3.substr(5, 3);
	cout << substr << endl; // substr = "new"
#endif
	string str;
	/*
	cout << "cin" << endl;
	while(cin >> str)  
		cout << str << endl;	
	*/
	cout << "getline" << endl;
	while(getline(cin, str))  
		cout << str << endl;	

	return 0;
}
