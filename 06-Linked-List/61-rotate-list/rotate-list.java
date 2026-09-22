/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) {
            return head;
        }
        
        int len = 1;
        ListNode tail = head;
        while(tail.next != null) {
            tail = tail.next;
            len++;
        }
        // reduce unnessary rotation 
        k = k % len;
        if( k == 0) {
           return head;
        }
        tail.next = head;
        
        int step = len - k;
        ListNode tailhead = head;

        for(int i=1; i<step; i++) {
            tailhead = tailhead.next;
        }

        ListNode newhead = tailhead.next;
        tailhead.next = null;
        
        return newhead;
    }
}