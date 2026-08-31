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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int n=length(head);
        ListNode prev=head;
        ListNode temp=head.next;
        ArrayList<Integer>mp= new ArrayList<>();
        for(int i=1;i<n-1;i++){
            if(temp.val>prev.val && temp.val> temp.next.val){
                mp.add(i);
            }else if(temp.val<prev.val && temp.val<temp.next.val){
                mp.add(i);
            }
            prev=temp;
            temp=temp.next;
        }
        Collections.sort(mp);
        int min=Integer.MAX_VALUE;
        for(int j=1;j<mp.size();j++){
            min=Math.min(min,Math.abs(mp.get(j)-mp.get(j-1)));
        }
        int[]ans= new int[2];
        if(mp.size()<2){
            ans[0]=-1;
            ans[1]=-1;
        }else{
            ans[0]=min;
            ans[1]=mp.get(mp.size()-1)-mp.get(0);
        }
        return ans;
    }
    public int length(ListNode head){
        int count=0;
        ListNode temp= head;
        while(temp!=null){
            count++;
            temp=temp.next;
        }
        return count;
    }
}