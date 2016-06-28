// single.cc
#include <iostream>
#include <cstdio>
using namespace std;
// Singleton类的对象在内存中只有一个实例
// 1. 生成的这一对象不能是栈对象，则它只能是一个堆对象
// 2. 不能通过new表达式来创建对象
class Singleton
{
public:
	static Singleton * create_instance()
	{
		if(pInstance_ == NULL)	
			pInstance_ = new Singleton();
		return pInstance_;
	}
	~Singleton()
	{
		// delete pInstance_; // error	
	}
	static void destory()
	{
		delete pInstance_;	
	}
private:
	Singleton() {};
	static Singleton *pInstance_;
};
Singleton* Singleton::pInstance_ = NULL;
int main(void)
{
	Singleton *p1 = Singleton::create_instance();
	Singleton *p2 = Singleton::create_instance();
	printf("p1 = %x\n", p1);
	printf("p2 = %x\n", p2);
	return 0;
}
