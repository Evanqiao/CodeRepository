/**********************************************************************\
Modified by qiaoyihan for test RC2 algorithm.
date:2015-10-18
\**********************************************************************/
#include <cstring>
#include <assert.h>
#include <iostream>
#include <fstream>
using namespace std;

/**********************************************************************\
* Expand a variable-length user key (between 1 and 128 bytes) to a     *
* 64-short working rc2 key, of at most "bits" effective key bits.      *
* The effective key bits parameter looks like an export control hack.  *
* For normal use, it should always be set to 1024.  For convenience,   *
* zero is accepted as an alias for 1024.                               *
\**********************************************************************/

void rc2_keyschedule( unsigned short xkey[64],
                      const unsigned char *key,
                      unsigned len,
                      unsigned bits )
        {
        unsigned char x;
        unsigned i;
        /* 256-entry permutation table, probably derived somehow from pi */
        static const unsigned char permute[256] = {
            217,120,249,196, 25,221,181,237, 40,233,253,121, 74,160,216,157,
            198,126, 55,131, 43,118, 83,142, 98, 76,100,136, 68,139,251,162,
             23,154, 89,245,135,179, 79, 19, 97, 69,109,141,  9,129,125, 50,
            189,143, 64,235,134,183,123, 11,240,149, 33, 34, 92,107, 78,130,
             84,214,101,147,206, 96,178, 28,115, 86,192, 20,167,140,241,220,
             18,117,202, 31, 59,190,228,209, 66, 61,212, 48,163, 60,182, 38,
            111,191, 14,218, 70,105,  7, 87, 39,242, 29,155,188,148, 67,  3,
            248, 17,199,246,144,239, 62,231,  6,195,213, 47,200,102, 30,215,
              8,232,234,222,128, 82,238,247,132,170,114,172, 53, 77,106, 42,
            150, 26,210,113, 90, 21, 73,116, 75,159,208, 94,  4, 24,164,236,
            194,224, 65,110, 15, 81,203,204, 36,145,175, 80,161,244,112, 57,
            153,124, 58,133, 35,184,180,122,252,  2, 54, 91, 37, 85,151, 49,
             45, 93,250,152,227,138,146,174,  5,223, 41, 16,103,108,186,201,
            211,  0,230,207,225,158,168, 44, 99, 22,  1, 63, 88,226,137,169,
             13, 56, 52, 27,171, 51,255,176,187, 72, 12, 95,185,177,205, 46,
            197,243,219, 71,229,165,156,119, 10,166, 32,104,254,127,193,173
        };

        assert(len > 0 && len <= 128);
        assert(bits <= 1024);
        if (!bits)
                bits = 1024;

        memcpy(xkey, key, len);

        /* Phase 1: Expand input key to 128 bytes */
        if (len < 128) {
                i = 0;
                x = ((unsigned char *)xkey)[len-1];
                do {
                        x = permute[(x + ((unsigned char *)xkey)[i++]) & 255];
                        ((unsigned char *)xkey)[len++] = x;
                } while (len < 128);
        }

        /* Phase 2 - reduce effective key size to "bits" */
        len = (bits+7) >> 3;
        i = 128-len;
        x = permute[((unsigned char *)xkey)[i] & (255 >> (7 & -bits))];
        ((unsigned char *)xkey)[i] = x;

        while (i--) {
                x = permute[ x ^ ((unsigned char *)xkey)[i+len] ];
                ((unsigned char *)xkey)[i] = x;
        }

        /* Phase 3 - copy to xkey in little-endian order */
        i = 63;
        do {
                xkey[i] =  ((unsigned char *)xkey)[2*i] +
                          (((unsigned char *)xkey)[2*i+1] << 8);
        } while (i--);
        }

/**********************************************************************\
* Encrypt an 8-byte block of plaintext using the given key.            *
\**********************************************************************/

void rc2_encrypt( const unsigned short xkey[64],
                  const unsigned char *plain,
                  unsigned char *cipher )
        {
        unsigned x76, x54, x32, x10, i;

        x76 = (plain[7] << 8) + plain[6];
        x54 = (plain[5] << 8) + plain[4];
        x32 = (plain[3] << 8) + plain[2];
        x10 = (plain[1] << 8) + plain[0];

        for (i = 0; i < 16; i++) {
                x10 += (x32 & ~x76) + (x54 & x76) + xkey[4*i+0];
                x10 = (x10 << 1) + (x10 >> 15 & 1);
                
                x32 += (x54 & ~x10) + (x76 & x10) + xkey[4*i+1];
                x32 = (x32 << 2) + (x32 >> 14 & 3);

                x54 += (x76 & ~x32) + (x10 & x32) + xkey[4*i+2];
                x54 = (x54 << 3) + (x54 >> 13 & 7);

                x76 += (x10 & ~x54) + (x32 & x54) + xkey[4*i+3];
                x76 = (x76 << 5) + (x76 >> 11 & 31);

                if (i == 4 || i == 10) {
                        x10 += xkey[x76 & 63];
                        x32 += xkey[x10 & 63];
                        x54 += xkey[x32 & 63];
                        x76 += xkey[x54 & 63];
                }
        }

        cipher[0] = (unsigned char)x10;
        cipher[1] = (unsigned char)(x10 >> 8);
        cipher[2] = (unsigned char)x32;
        cipher[3] = (unsigned char)(x32 >> 8);
        cipher[4] = (unsigned char)x54;
        cipher[5] = (unsigned char)(x54 >> 8);
        cipher[6] = (unsigned char)x76;
        cipher[7] = (unsigned char)(x76 >> 8);
        }

/**********************************************************************\
* Decrypt an 8-byte block of ciphertext using the given key.           *
\**********************************************************************/

void rc2_decrypt( const unsigned short xkey[64],
                  unsigned char *plain,
                  const unsigned char *cipher )
        {
        unsigned x76, x54, x32, x10, i;

        x76 = (cipher[7] << 8) + cipher[6];
        x54 = (cipher[5] << 8) + cipher[4];
        x32 = (cipher[3] << 8) + cipher[2];
        x10 = (cipher[1] << 8) + cipher[0];

        i = 15;
        do {
                x76 &= 65535;
                x76 = (x76 << 11) + (x76 >> 5);
                x76 -= (x10 & ~x54) + (x32 & x54) + xkey[4*i+3];

                x54 &= 65535;
                x54 = (x54 << 13) + (x54 >> 3);
                x54 -= (x76 & ~x32) + (x10 & x32) + xkey[4*i+2];
                
                x32 &= 65535;
                x32 = (x32 << 14) + (x32 >> 2);
                x32 -= (x54 & ~x10) + (x76 & x10) + xkey[4*i+1];

                x10 &= 65535;
                x10 = (x10 << 15) + (x10 >> 1);
                x10 -= (x32 & ~x76) + (x54 & x76) + xkey[4*i+0];

                if (i == 5 || i == 11) {
                        x76 -= xkey[x54 & 63];
                        x54 -= xkey[x32 & 63];
                        x32 -= xkey[x10 & 63];
                        x10 -= xkey[x76 & 63];
                }
        } while (i--);

        plain[0] = (unsigned char)x10;
        plain[1] = (unsigned char)(x10 >> 8);
        plain[2] = (unsigned char)x32;
        plain[3] = (unsigned char)(x32 >> 8);
        plain[4] = (unsigned char)x54;
        plain[5] = (unsigned char)(x54 >> 8);
        plain[6] = (unsigned char)x76;
        plain[7] = (unsigned char)(x76 >> 8);
        }


int main()
{
	unsigned short xkey[64];
	unsigned char key[] = "qiaoyhan";
	unsigned char plainBuffer[8];
	unsigned char cipherBuffer[8];
	unsigned char outBuffer[8];
	
	rc2_keyschedule( xkey, key, 8, 64);
	FILE *fp_plain, *fp_cipher, *fp_out;
#define __PIC__	 1
#if __PIC__
	fp_plain = fopen("plain_pic.jpg", "rb");
	fp_cipher = fopen("cipher_pic.jpg", "wb");
	fp_out = fopen("out_pic.jpg", "wb");
	if(fp_plain != NULL && fp_cipher != NULL && fp_out != NULL) {
		while(fread(plainBuffer, 1, 1, fp_plain) != 0 )
		{
			rc2_encrypt( xkey, plainBuffer, cipherBuffer); 
			fwrite(cipherBuffer, 1, 1, fp_cipher);
			rc2_decrypt( xkey, outBuffer, cipherBuffer);
			fwrite(outBuffer, 1, 1, fp_out);	
		}
		 
	}
#else
	fp_plain = fopen("plaintext.txt", "r");
	fp_cipher = fopen("ciphertext.txt", "w");
	fp_out = fopen("outtext.txt", "w");
	if(fp_plain != NULL && fp_cipher != NULL && fp_out != NULL) {
		while(fread(plainBuffer, 1, 1, fp_plain) != 0 )
		{
			rc2_encrypt( xkey, plainBuffer, cipherBuffer); 
			fwrite(cipherBuffer, 1, 1, fp_cipher);
			rc2_decrypt( xkey, outBuffer, cipherBuffer);
			fwrite(outBuffer, 1, 1, fp_out);	
		}
		 
	}
#endif
	return 0;
	
	
	/*
	unsigned char key[2][8] = {
		{0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08},
		{0x11, 0x12, 0x13, 0x14, 0x25, 0x26, 0x27, 0x28}
	};
	unsigned char plaintext[2][8] = {
		{0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff},
		{0x66, 0x66, 0x66, 0x66, 0x66, 0x66, 0x66, 0x66}
	};
	unsigned char ciphertext[2][8];
	unsigned char outtext[2][8];
	rc2_keyschedule( xkey, key[0], 8, 64); 
	rc2_encrypt( xkey, plaintext[0], ciphertext[0]); 
	rc2_decrypt( xkey, outtext[0], ciphertext[0]); 
	
	rc2_keyschedule( xkey, key[1], 8, 64); 
	rc2_encrypt( xkey, plaintext[1], ciphertext[1]); 
	rc2_decrypt( xkey, outtext[1], ciphertext[1]);
	int i, j;
	for(j = 0; j < 2; j++) {
	
		printf("Example %d:\n", j + 1);
		printf("KEY:        ");
		for(i = 0; i < 8; i++) {
			if(key[j][i] <= 0x0f)
				printf("0x0%x ", key[j][i]);	
			else
				printf("0x%x ", key[j][i]);	
	
		}
		printf("\n");
		printf("PLAINTEXT:  ");
		for(i = 0; i < 8; i++) {
			if(plaintext[j][i] <= 0x0f)
				printf("0x0%x ", plaintext[j][i]);	
			else
				printf("0x%x ", plaintext[j][i]);	
	
		}
		printf("\n");
		printf("CIPHERTEXT: ");
		for(i = 0; i < 8; i++) {
			if(ciphertext[j][i] <= 0x0f)
				printf("0x0%x ", ciphertext[j][i]);	
			else
				printf("0x%x ", ciphertext[j][i]);	
	
		}
		printf("\n");
		
		printf("OUTTEXT:    ");
		for(i = 0; i < 8; i++) {
			if(outtext[j][i] <= 0x0f)
				printf("0x0%x ", outtext[j][i]);	
			else
				printf("0x%x ", outtext[j][i]);	
	
		}
		printf("\n\n");
	}
	*/

}
