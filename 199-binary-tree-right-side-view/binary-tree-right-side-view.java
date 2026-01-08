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
    public List<Integer> rightSideView(TreeNode root){
        Queue<TreeNode>q= new LinkedList<>();
        ArrayList<Integer>ll= new ArrayList<>();
        if(root!=null){
            q.add(root);
        }else{
            return ll;
        }
        while(q.size()>0){
            int levelsize=q.size();
            for(int i=0;i<levelsize;i++){
                TreeNode temp=q.peek();
                if(i==levelsize-1){
                    ll.add(temp.val);
                }
                if(temp.left!=null) q.add(temp.left);
                if(temp.right!=null) q.add(temp.right);
                q.remove();
            }
        }
        return ll;
    }
}