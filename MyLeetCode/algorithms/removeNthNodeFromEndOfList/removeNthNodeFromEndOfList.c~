// Source : https://oj.leetcode.com/problems/remove-nth-node-from-end-of-list/
// Author : qiaoyihan
// Date   : 2015-04-29

/********************************************************************************** 
* 
* Given a linked list, remove the nth node from the end of list and return its head.
* 
* For example,
* 
*    Given linked list: 1->2->3->4->5, and n = 2.
* 
*    After removing the second node from the end, the linked list becomes 1->2->3->5.
* 
* Note:
* Given n will always be valid.
* Try to do this in one pass.
* 
*               
**********************************************************************************/

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
// 注意只有一个节点的情况
// 本题目中的链表是不带头结点的链表
struct ListNode* removeNthFromEnd(struct ListNode* head, int n) {
    if(head == NULL || n <= 0)
        return NULL;
    struct ListNode qhead, *p1, *p2, *q;
    qhead.next = head;
    head = &qhead;
    p1 = head;
    p2 = head;
    int i;
    for(i = 0; i < n; i++) {
        if(p1 == NULL)  return NULL;
        p1 = p1->next; 
    }
    while(p1->next != NULL) {
        p1 = p1->next; 
        p2 = p2->next;
    }
    q = p2->next;
    p2->next = p2->next->next;
    free(q);
    return head->next;
}
