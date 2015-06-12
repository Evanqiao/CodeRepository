#include <iostream>
#include <fstream>
#include <bitset>
#include <string>
using namespace std;


// 初始置换表
int IP[] = {58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9,  1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
           };

// 结尾置换表
int IP_1[] = {40, 8, 48, 16, 56, 24, 64, 32,
              39, 7, 47, 15, 55, 23, 63, 31,
              38, 6, 46, 14, 54, 22, 62, 30,
              37, 5, 45, 13, 53, 21, 61, 29,
              36, 4, 44, 12, 52, 20, 60, 28,
              35, 3, 43, 11, 51, 19, 59, 27,
              34, 2, 42, 10, 50, 18, 58, 26,
              33, 1, 41,  9, 49, 17, 57, 25
             };

/*------------------下面是生成密钥所用表-----------------*/

// 密钥置换表，将64位密钥变成56位
int PC_1[] = {57, 49, 41, 33, 25, 17, 9,
              1, 58, 50, 42, 34, 26, 18,
              10,  2, 59, 51, 43, 35, 27,
              19, 11,  3, 60, 52, 44, 36,
              63, 55, 47, 39, 31, 23, 15,
              7, 62, 54, 46, 38, 30, 22,
              14,  6, 61, 53, 45, 37, 29,
              21, 13,  5, 28, 20, 12,  4
             };

// 压缩置换，将56位密钥压缩成48位子密钥
int PC_2[] = {14, 17, 11, 24,  1,  5,
              3, 28, 15,  6, 21, 10,
              23, 19, 12,  4, 26,  8,
              16,  7, 27, 20, 13,  2,
              41, 52, 31, 37, 47, 55,
              30, 40, 51, 45, 33, 48,
              44, 49, 39, 56, 34, 53,
              46, 42, 50, 36, 29, 32
             };

// 每轮左移的位数
int shiftBitCounts[] = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

/*------------------下面是密码函数 f 所用表-----------------*/

// 扩展置换表，将 32位 扩展至 48位
int E[] = {32,  1,  2,  3,  4,  5,
           4,  5,  6,  7,  8,  9,
           8,  9, 10, 11, 12, 13,
           12, 13, 14, 15, 16, 17,
           16, 17, 18, 19, 20, 21,
           20, 21, 22, 23, 24, 25,
           24, 25, 26, 27, 28, 29,
           28, 29, 30, 31, 32,  1
          };

// S盒，每个S盒是4x16的置换表，6位 -> 4位
int S_BOX[8][4][16] = {
	{
		{14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
		{0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
		{4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
		{15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}
	},
	{
		{15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
		{3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
		{0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
		{13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}
	},
	{
		{10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
		{13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
		{13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
		{1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}
	},
	{
		{7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
		{13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
		{10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
		{3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}
	},
	{
		{2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
		{14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
		{4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
		{11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}
	},
	{
		{12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
		{10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
		{9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
		{4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}
	},
	{
		{4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
		{13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
		{1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
		{6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}
	},
	{
		{13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
		{1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
		{7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
		{2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}
	}
};

// P置换，32位 -> 32位
int P[] = {16,  7, 20, 21,
           29, 12, 28, 17,
           1, 15, 23, 26,
           5, 18, 31, 10,
           2,  8, 24, 14,
           32, 27,  3,  9,
           19, 13, 30,  6,
           22, 11,  4, 25
          };

typedef enum {
    Encryption = 1,
    Decipherment = 2
}EDFlag;
// 实现 F 函数
bitset<32> f_function(const bitset<32> &R, const bitset<48> K) {
	bitset<48> expandedR;
	// 1.扩展置换
	for(int i = 0; i < 48; i++) {
                expandedR[i] = R[E[i] - 1];
	}
	// 2.expandedR 与 K 执行XOR运算
	expandedR = expandedR ^ K;
	// 3. S盒置换
	bitset<32> output;
	for(int i = 0, j = 0; i < 48; i += 6, j += 4) {
		int row = expandedR[i] * 2 + expandedR[i + 5];
		int column = expandedR[i + 1] * 8 + expandedR[i + 2] * 4 + expandedR[i + 3] * 2 + expandedR[i + 4];

		bitset<4> bitTemp(S_BOX[i / 6][row - 1][column - 1]);
		output[j] = bitTemp[0];
		output[j + 1] = bitTemp[1];
		output[j + 2] = bitTemp[2];
		output[j + 3] = bitTemp[3];
	}
	// 4. P置换
	bitset<32> tmp = output;
	for(int i = 0; i < 32; i++) {
		output[i] = tmp[P[i] - 1];
	}
	return output;
}
// 用内联函数实现循环左移
void inline leftShift(bitset<28> &bits, int count) {
	bits = (bits << count) | (bits >> (28 - count));
}
// 生成子密钥
bitset<48> getSubKey(bitset<28> &C, bitset<28> &D, int shiftCounts) {
	leftShift(C, shiftCounts);
	leftShift(D, shiftCounts);
	bitset<56> con;
	for(int i = 0; i < 56; i++) {
		if(i < 27)
			con[i] = C[i];
		else
			con[i] = D[i - 28];
	}
	bitset<48> res;
	for(int i = 0; i < 48; i++) {
		res[i] = con[PC_2[i] - 1];
	}
	return res;
}

// DES加密或解密
bitset<64> encrypt(const bitset<64> &text, const bitset<64> key, EDFlag edFlag) {
	// 1. 初始置换IP
	bitset<64> initReplace;
	for(int i = 0; i < 64; i++) {
		initReplace[i] = text[IP[i] - 1];
	}
	// 2. 16轮
	bitset<32> currentL;
	bitset<32> currentR;
	bitset<32> nextL;
	bitset<28> currentC;
	bitset<28> currentD;
	// 2.1 获得L和R
	for(int i = 0; i < 64; i++) {
		if(i < 32)
			currentL[i] = initReplace[i];
		else
			currentR[i - 32] = initReplace[i];
	}
	// 2.2 获得C和D
	bitset<56> initKeyReplace;
	for(int i = 0; i < 56; i++) {
		initKeyReplace[i] = key[PC_1[i] - 1];
		if(i < 32)
			currentC[i] = initKeyReplace[i];
		else
			currentD[i - 32] = initKeyReplace[i];
	}
	// 2.3 16 round
	for(int round = 0; round < 16; round++) {
		nextL = currentR;
                int key_round = (edFlag == Encryption) ? round : (15 - round);
                currentR = f_function(currentR, getSubKey(currentC, currentD, shiftBitCounts[key_round]));

		currentR = currentR ^ currentL;
		currentL = nextL;
	}
	// 3. 32位互换
	bitset<64> exchange;
	for(int i = 0; i < 64; i++) {
		if(i < 32)
			exchange[i] = currentR[i];
		else
			exchange[i] = currentL[i - 32];
	}
	// 4. 结尾IP_1置换
	bitset<64> result;
	for(int i = 0; i < 64; i++) {
		result[i] = exchange[IP_1[i] - 1];
	}
	return result;
}
// 64位的字符串转换成bitset
bitset<64> charToBitset(const char *s) {
	bitset<64> res;
	for(int i = 0; i < 8; i++)
		for(int j = 0; j < 8; j++)
			res[i * 8 + j] = ((s[i] >> j) & 0x01);
	return res;
}

int main(int argc, char *argv[]) {
	string s_text = "romantic";
	string s_key = "12345678";
	bitset<64> text = charToBitset(s_text.c_str());
	bitset<64> key = charToBitset(s_key.c_str());

        bitset<64> ciphertext = encrypt(text, key, Encryption);

	fstream file1;
	file1.open("D://ciphertext.txt", ios::binary | ios::out);
	file1.write((char*)&ciphertext, sizeof(ciphertext));
	file1.close();

        /**
        bitset<64> temp;
        file1.open("D://ciphertext.txt", ios::binary | ios::out);
        file1.read((char*)&temp, sizeof(temp));
        file1.close();

        bitset<64> plaintext = encrypt(text, key, Decipherment);
        file1.open("D://plaintext.txt", ios::binary | ios::out);
        file1.write((char*)&plaintext, sizeof(plaintext));
        file1.close();
        **/
        return 0;
}










