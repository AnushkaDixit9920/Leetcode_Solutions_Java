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
    public int maxLevelSum(TreeNode root) {
        int anslevel=0;
        int maxsum=Integer.MIN_VALUE;
        Queue<TreeNode>q= new LinkedList<>();
        if(root!=null) q.add(root);
        int currlevel=0;
        while(q.size()>0){
            currlevel++;
            int levelsize=q.size();
            int currsum=0;
            for(int i=0;i<levelsize;i++){
                TreeNode temp=q.peek();
              
                if(temp.left!=null) q.add(temp.left);
                if(temp.right!=null) q.add(temp.right);
                currsum+=temp.val;
                q.remove();
            }
            if(currsum>maxsum){
                maxsum=currsum;
                anslevel=currlevel;
            }
        }
        return anslevel;
    }
}