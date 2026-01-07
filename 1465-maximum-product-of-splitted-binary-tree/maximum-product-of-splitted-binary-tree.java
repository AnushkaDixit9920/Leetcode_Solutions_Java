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
    long totalsum=0;
    long maxproduct=0;
    int mod=1_000_000_007;
    public int maxProduct(TreeNode root) {
        totalsum=treesum(root);
        subtreesum(root);
        return (int)(maxproduct%mod);
    }
    public long treesum(TreeNode root){
        if(root==null) return 0;
        return root.val+treesum(root.left)+treesum(root.right);
    }
    public long subtreesum(TreeNode root){
        if(root==null) return 0;;
        long left=subtreesum(root.left);
        long right=subtreesum(root.right);
        long subsum=root.val+left+right;
        long product=subsum*(totalsum-subsum);
        maxproduct=Math.max(product,maxproduct);
        return subsum; 
    }
}