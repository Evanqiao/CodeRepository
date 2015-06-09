// Source : https://oj.leetcode.com/problems/compare-version-numbers/
// Author : qiaoyihan
// Date   : 2015-4-29

/********************************************************************************** 
 * 
 * Compare two version numbers version1 and version1.
 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 * 
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 * 
 * Here is an example of version numbers ordering:
 * 0.1 < 1.1 < 1.2 < 13.37
 * 
 * Credits:Special thanks to @ts for adding this problem and creating all test cases.
 *               
 **********************************************************************************/

int compareVersion(char* version1, char* version2) {
    int v1, v2;
    char *vs1, *vs2;
    char *v1_p = version1;
    char *v2_p = version2;
    v1 = atoi(version1);
    v2 = atoi(version2);
    if(!(v1 == v2)) return v1 > v2 ? 1 : -1;
    while(1) {
        vs1 = strchr(v1_p, '.');
        vs2 = strchr(v2_p, '.');
        if(vs1 == NULL && vs2 == NULL)  return 0;
        if(vs1 == NULL && vs2 != NULL) {
            char *vs2_r = vs2;
            while(vs2_r != NULL) {
                if(atoi(vs2_r + 1) != 0)    return -1; 
                vs2_r = strchr(vs2_r + 1, '.');
            }
            return 0;
        }
        if(vs1 != NULL && vs2 == NULL) {
            char *vs1_r = vs1;
            while(vs1_r != NULL) {
                if(atoi(vs1_r + 1) != 0)    return 1; 
                vs1_r = strchr(vs1_r + 1, '.');
            }
            return 0;
        }
        v1_p = vs1 + 1;
        v2_p = vs2 + 1;
        v1 = atoi(v1_p);
        v2 = atoi(v2_p);
        if(!(v1 == v2)) return v1 > v2 ? 1 : -1;
    } 
}
