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
    public TreeNode pruneTree(TreeNode root) {
        if(root==null){
            return null;
        }
        if(!isOnepresent(root.left)){
            root.left=null;
        }
        if(!isOnepresent(root.right)){
            root.right=null;
        }
        pruneTree(root.left);
        pruneTree(root.right);
        if(root.right==null && root.left==null && root.val==0){
            return null;
        }
        return root;
    }
    public boolean isOnepresent(TreeNode root){
        if(root==null){
            return false;
        }
        if(root.val==1){
            return true;
        }
        return isOnepresent(root.left) || isOnepresent(root.right);
    }
}