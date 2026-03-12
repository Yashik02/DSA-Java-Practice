import java.util.*;
public class BinaryTreePractice {

    public static class Node {
        
        int val;
        Node left;  
        Node right;

        Node (int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        Node (int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    public static class BinaryTree {
        static int idx = -1;
        public static Node buildTree(int[] nodes) {
            idx++;
            if(nodes[idx] == -1) {
                return null;
            }
            
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }
    }

    

    public static void preorder (Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder (Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void postorder (Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val + " ");
    }

    public static void levelorder (Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while(!q.isEmpty()) {
            Node curr = q.remove();
            if (curr == null) {
                System.out.println();
                if(q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                System.out.print(curr.val);
                if(curr.left != null) {
                    q.add(curr.left);
                }
                if(curr.right != null) {
                    q.add(curr.right);
                }
            }
        }
    }

    
    
    public static int maxHeight (Node root) {
        if(root == null) {
            return 0;
        }
        int leftHeight = maxHeight(root.left);
        int rightHeight = maxHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static int maxDiameter(Node root) {
        if(root == null) {
            return 0;
        }
        int dia1 = maxDiameter(root.left);
        int dia2 = maxDiameter(root.right);
        int height1 = maxHeight(root.left);
        int height2 = maxHeight(root.right);
        int dia3 = height1 + height2 + 1;

        return Math.max(dia1, Math.max(dia2, dia3));
    }

    public static int diameter(Node root) {
       return maxDiameter1(root)[1];
    }

    public static int[] maxDiameter1(Node root) {
        int[] ans = new int[2];
        if(root == null) {
            return ans;
        }

        //height, diameter
        int[] left = maxDiameter1(root.left);
        int[] right = maxDiameter1(root.right);

        int height = Math.max(left[0], right[0]) + 1;
        ans[0] = height;
        
        int diameter = left[0] + right[0] + 1;
        int maxDiameter = Math.max(diameter, Math.max(left[1], right[1]));
        ans[1] = maxDiameter;
        return ans;
    }

    

    public static int sum (Node root) {
        if(root == null) {
            return 0;
        }
        int leftSum = sum(root.left);
        int rightSum = sum(root.right);
        
        return leftSum + rightSum + root.val;
    }

    public static int totalNode (Node root) {
        if(root == null) {
            return 0;
        }
        int leftTotal = totalNode(root.left);
        int rightTotal = totalNode(root.right);
        
        return leftTotal + rightTotal + 1;
    }

    public static Node lca (Node root, int n1, int n2) {
        if(root == null || root.val == n1 || root.val == n2) {
            return root;
        }

        Node left = lca(root.left, n1, n2);
        Node right = lca(root.right, n1, n2);

        if(left == null) {
            return right;
        }
        if(right == null) {
            return left;
        }
        return root;
    }

    public static int dist (Node root, int key) {
        if(root == null) return -1;
        if(root.val == key) return 0;

        int left = dist(root.left, key), right = dist(root.right, key);
        if( left == -1 && right == -1) {
            return -1;
        } else if (left == -1) {
            return right + 1;
        } else {
            return left + 1;
        }
    }

    //for a Key value, it's kth ancestor will be?
    public static int kthAncestor (Node root, int key, int k) {
        int[] anskth = {-1};
        helper(root, key, k, anskth);
        return anskth[0];
    }
    public static int helper(Node root, int key, int k, int[] anskth) {
        if(root == null) {
            return -1;
        }
        if(root.val == key) {
            return 0;
        }

        int left = helper(root.left, key, k, anskth);
        int right = helper(root.right, key, k, anskth);

        if(left == -1 && right == -1) {
            return -1;
        }
        int max = Math.max(left, right);

        if(max+1 == k) {
            anskth[0] = root.val;
        }
        return max+1;
    }

    public static int distanceBetween (Node root, int n1, int n2) {
        Node lca = lca(root, n1, n2);
        int leftDist = dist(lca, n1);
        int rightDist = dist(lca, n2);
        if(leftDist != -1 && rightDist != -1) {
            return leftDist + rightDist;
        } else {
            return -1;
        }
    }

    public static int sumTree(Node root) {
        if(root == null) return 0;

        int sum = sumTree(root.left) + sumTree(root.right);
        int temp = sum + root.val;
        root.val = sum;
        return temp;

        //adding the sum of left and right subtree to the current node
    }


    public static boolean helper(Node root, int rootVal) {
        if(root == null) return true;
        if(root.val != rootVal) return false;
        return helper(root.left, rootVal) && helper(root.right, rootVal);
    }
    public static boolean isUnivalTree(Node root) {
        return helper(root, root.val);
    }

    

    public static void invert(Node root) {
        if(root == null) return;
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;
        invert(root.left);
        invert(root.right);
    }


    public static void main (String[] args) {

        // int[] nodes = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        // Node root = BinaryTree.buildTree(nodes);

        
    
    }
}