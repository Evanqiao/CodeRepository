// Source : https://oj.leetcode.com/problems/climbing-stairs/
// Author : qiaoyihan
// Date   : 2015-04-29

/********************************************************************************** 
* 
* You are climbing a stair case. It takes n steps to reach to the top.
* 
* Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
* 
*               
**********************************************************************************/

int climbStairs(int n) {
    if(n <= 3)  return n;
    int firstS = 2;
    int secondS = 3;
    int result;
    for(int i = 3; i < n; i++) {
        result = firstS + secondS;
        firstS = secondS;
        secondS = result;
    }
    return result;
}
// Time Limit Exceeded
/*
int climbStairs(int n) {
    if(n <= 3)  return n;
    return climbStairs(n - 1) + climbStairs(n - 2);
}
*/
