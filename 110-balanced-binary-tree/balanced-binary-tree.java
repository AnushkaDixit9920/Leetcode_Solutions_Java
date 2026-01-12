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
    public boolean isBalanced(TreeNode root) {
        Queue<TreeNode>q= new LinkedList<>();
        if(root!=null) q.add(root);
        while(q.size()>0){
            TreeNode temp=q.peek();
            if(Math.abs(height(temp.left)-height(temp.right))>1){
                return false;
            }else{
                if(temp.left!=null)q.add(temp.left);
                if(temp.right!=null)q.add(temp.right);
            }
            q.remove();
        }
        return true;
    }
    public int height(TreeNode root){
        if(root==null){
            return -1;
        }
        return 1+Math.max(height(root.left),height(root.right));
    }
}
