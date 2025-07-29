public class IdenticalTreesCheck {

    // 🌿 Node class representing each element of the binary tree
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // 🔍 Function to check if two trees are identical
    public static boolean isIdentical(Node root1, Node root2) {
        // ✅ Case 1: both nodes are null — identical till now
        if (root1 == null && root2 == null) {
            return true;
        }

        // ❌ Case 2: One node is null, but not the other — structure mismatch
        if (root1 == null || root2 == null) {
            return false;
        }

        // ❌ Case 3: Node data mismatch
        if (root1.data != root2.data) {
            return false;
        }

        // 🔁 Recur on left and right subtrees
        // code execution came here means -> root1.data == root2.data now check more
        return isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right);
    }

    public static void main(String[] args) {
        // 🌲 Tree 1
    /*
            Tree 1:
                 1
               /   \
              2     3
             / \
            4   5
    */
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(5);

        // 🌳 Tree 2
    /*
            Tree 2:
                 1
               /   \
              2     3
             / \
            4   5
    */
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        root2.left.left = new Node(4);
        root2.left.right = new Node(5);

        // 🧪 Check if identical
        if (isIdentical(root1, root2)) {
            System.out.println("The two trees are identical ✅");
        } else {
            System.out.println("The two trees are NOT identical ❌");
        }
    }

}

/*
🧠 LOGIC:
We recursively check if both trees are:
✔️ Null together (means identical so far),
❌ One is null but not the other (not identical),
❌ Data doesn't match (not identical),
🔁 Otherwise, recurse on left and right subtree and return their result.

-----------------------------------------------------
🧪 DRY RUN (Visual):

Tree 1:              Tree 2:

       1                   1
     /   \               /   \
    2     3             2     3
   / \                 / \
  4   5               4   5

Step-by-step:
- Compare root1(1) & root2(1) ✅
- Compare root1.left(2) & root2.left(2) ✅
- Compare root1.left.left(4) & root2.left.left(4) ✅
- Compare null & null ✅
- Compare null & null ✅
- Compare root1.left.right(5) & root2.left.right(5) ✅
- Compare null & null ✅
- Compare null & null ✅
- Compare root1.right(3) & root2.right(3) ✅
- Compare null & null ✅
- Compare null & null ✅
➡️ All matched → IDENTICAL ✅

-----------------------------------------------------
⏱️ Time Complexity: O(n)
(n = number of nodes in the smaller tree; each node is visited once)

🧠 Space Complexity: O(h)
(h = height of the tree → stack space due to recursion)
Best: O(log n) for balanced tree, Worst: O(n) for skewed tree
*/
