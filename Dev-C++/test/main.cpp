#include <iostream>
#include <new>
using  std::cout;

const int BUF = 512;
const int N = 5;
char buffer[BUF];
namespace Evan
{
	int adc = 0;
};
namespace Evan2
{
	int adc = 0;
};
using namespace Evan;
using namespace Evan2;
int main(int argc, char** argv) {

	adc = 1;
	using std::endl;
	cout << adc << std::endl << Evan::adc << endl;
	
	return 0;
}
