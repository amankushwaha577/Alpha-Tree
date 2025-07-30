public class Main {

    // 🌳 Node structure
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    // 🔍 Function to find LCA
    public static Node findLCA(Node root, int n1, int n2) {
        // 🛑 Base Case: if root is null or matches any of the nodes
        if (root == null || root.data == n1 || root.data == n2) {
            return root;
        }

        // 🔁 Recursively search left and right subtrees
        Node leftLCA = findLCA(root.left, n1, n2);
        Node rightLCA = findLCA(root.right, n1, n2);

        // 🧠 Case 1: If both left and right return non-null → current node is LCA
        if (leftLCA != null && rightLCA != null) {
            return root;
        }

        // 📤 Case 2: Return non-null child (either left or right)
        return (leftLCA != null) ? leftLCA : rightLCA;
    }

    public static void main(String[] args) {
        /*
                  1
                /   \
               2     3
              / \   / \
             4   5 6   7
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        int n1 = 4, n2 = 5;

        Node lca = findLCA(root, n1, n2);
        if (lca != null) {
            System.out.println("LCA of " + n1 + " and " + n2 + " is: " + lca.data);
        } else {
            System.out.println("Nodes not found in tree");
        }
    }
}

/*
🧠 LOGIC: Lowest Common Ancestor (LCA)

📘 LCA is the lowest (deepest) node that has both nodes as descendants.

🔁 Idea:
- Traverse tree recursively
- If current node matches n1 or n2 → return current node
- Search left and right subtrees
- If both return non-null → current node is LCA
- If one side is null → return the non-null side

📌 Time Complexity: O(N)
📌 Space Complexity: O(H) [H = height of tree, due to recursion stack]

🧪 DRY RUN:

Tree:
          1
        /   \
       2     3
      / \   / \
     4   5 6   7

Find LCA of 4 and 5:

1. Start at root (1):
   → go left to 2

2. At node 2:
   → left = findLCA(4), returns 4
   → right = findLCA(5), returns 5
   → both sides non-null ⇒ LCA is 2 ✅

Final Answer: 2
*/


/*
🧠 DRY RUN: LCA of 4 and 5 in binary tree

Tree structure:
          1
        /   \
       2     3
      / \   / \
     4   5 6   7

Call: findLCA(1, 4, 5)
→ Not null, not equal to 4 or 5 → go deeper

  Call: findLCA(2, 4, 5)
  → Not null, not equal to 4 or 5 → go deeper

    Call: findLCA(4, 4, 5)
    → root == 4 → return Node(4)

    Call: findLCA(5, 4, 5)
    → root == 5 → return Node(5)

  Now at Node(2): leftLCA = Node(4), rightLCA = Node(5)
  → both sides returned non-null → return Node(2) ✅

  Call: findLCA(3, 4, 5)
  → check left (6) and right (7)
      → findLCA(6, 4, 5) → returns null
      → findLCA(7, 4, 5) → returns null
  → leftLCA = null, rightLCA = null → return null

At Node(1): leftLCA = Node(2), rightLCA = null
→ Return leftLCA = Node(2)

🎯 Final LCA = Node(2)
Prints: LCA of 4 and 5 is: 2

🕒 Time Complexity = O(N)
🧠 Space Complexity = O(H) where H is height of the tree

Why O(N)?
→ Every node may be visited once in worst-case to locate both n1 and n2.

*/
