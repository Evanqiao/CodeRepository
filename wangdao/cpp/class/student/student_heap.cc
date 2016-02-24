// student_heap.cc
#include <iostream>
#include <cstring>
#include <cstdlib>


class Student
{
public:
	Student()
	{
		std::cout << "Student::Student()" << std::endl;
	}
	void setId(int id)
	{
		iId_ = id;
	}
	void setName(const char *str)
	{
		strcpy(szName_, str);
	}
	void print()
	{
		std::cout << "id:  " << iId_ << std::endl;
		std::cout << "name:" << szName_ << std::endl;
	}
	void destory()
	{
		// this->~Student(); //error
		// 也不能直接调用析构函数，因为这样的话不会执行delete操作
		// 见输出结果1
		delete this; // 正确写法，见输出结果2
	}
	static void * operator new(std::size_t nSize)
	{
		std::cout << "operator new(int)" << std::endl;
		void *pRet = malloc(nSize);
		return pRet;
	}
	static void operator delete(void *pVoid)
	{
		std::cout << "operator delete" << std::endl;
		free(pVoid);
	}

private:
	int iId_;
	char szName_[128];
	~Student()
	{
		std::cout << "Student::~Student()" << std::endl;
	}
};

int main(void)
{
	std::cout << "输出结果2：" << std::endl;
	// Student s1; //error,不能生成栈对象，会提示析构函数是私有的
	Student *pstu = new Student;
	pstu->setId(100);
	pstu->setName("zhangsan");
	pstu->print();
	// delete pstu; //error,不能直接用delete，因为析构函数是私有的
	pstu->destory(); // 调用destory()函数来销毁对象
}
