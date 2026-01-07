/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode>q= new LinkedList<>();
        q.add(root);
        ArrayList<Integer>ll= new ArrayList<>();
        while(q.size()>0){
            int levelsize=q.size();
            int curr=Integer.MIN_VALUE;
            for(int i=0;i<levelsize;i++){
                TreeNode temp=q.peek();
                if(temp==null) return ll;
                if(temp.left!=null) q.add(temp.left);
                if(temp.right!=null) q.add(temp.right);
                curr=Math.max(curr,temp.val);
                q.remove();
            }
            ll.add(curr);
        }
        return ll;
    }
}