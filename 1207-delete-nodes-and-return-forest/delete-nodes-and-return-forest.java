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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        HashSet<Integer>st= new HashSet<>();
        ArrayList<TreeNode>ll= new ArrayList<>();
        for(int x:to_delete){
            st.add(x);
        }
        deletehelper(st,ll,root);
        if(!st.contains(root.val)){
            ll.add(root);
        }
        return ll;

    }
    public TreeNode deletehelper(HashSet<Integer> st,List<TreeNode> ll,TreeNode root){
        if(root==null){
            return null;
        }
        root.left=deletehelper(st,ll,root.left);
        root.right=deletehelper(st,ll,root.right);
        if(st.contains(root.val)){
            if(root.left!=null){
                ll.add(root.left);
            }
            if(root.right!=null){
                ll.add(root.right);
            }
            return null;
        }
        return root;


    }
    
}