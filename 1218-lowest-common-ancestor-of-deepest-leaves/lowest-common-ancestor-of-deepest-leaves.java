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
    HashMap<Integer,Integer>mp= new HashMap<>();
    int maxval=0;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        depth(root,0);
        return lca(root);
    }
    public void depth(TreeNode root, int d){
        if(root==null) return;
        maxval=Math.max(maxval,d);
        mp.put(root.val,d);
        depth(root.left,d+1);
        depth(root.right,d+1);
    }
    public TreeNode lca(TreeNode root){
        if(root==null || mp.get(root.val)==maxval){
            return root;
        }
        TreeNode leftans=lca(root.left);
        TreeNode rightans=lca(root.right);
        if(leftans!=null & rightans!=null){
            return root;
        }
        if(leftans!=null) return leftans;
        return rightans;
    }
}