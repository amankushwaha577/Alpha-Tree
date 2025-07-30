import java.util.*;

public class Main {

    // 🌳 Node class for binary tree
    static class Node {
        int data;
        Node left, right;
        Node(int data) {
            this.data = data;
        }
    }

    // 🔍 Function to find root-to-node path and store in list
    public static boolean getPath(Node root, int target, List<Integer> path) {
        if (root == null) return false;

        // 🧺 Add current node to path
        path.add(root.data);

        // 🎯 If target found, return true
        if (root.data == target) return true;

        // 🔁 Recur for left or right subtree
        if (getPath(root.left, target, path) || getPath(root.right, target, path)) {
            return true;
        }

        // 🗑️ Backtrack if target not found in this path
        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        /*
              1
             / \
            2   3
           / \
          4   5
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        int target = 5; // 🎯 Target node to find path for
        List<Integer> path = new ArrayList<>();

        if (getPath(root, target, path)) {
            System.out.println("✅ Path from root to node " + target + ":");
            for (int val : path) {
                System.out.print(val + " ");
            }
        } else {
            System.out.println("❌ Node not found in the tree.");
        }
    }
}

/*
📌 GOAL:
Find path from root to given node in binary tree.

🛠️ ALGORITHM:
1. Traverse tree recursively.
2. At each node, add it to path.
3. If node is the target → return true.
4. Else recur for left and right.
5. If neither returns true → backtrack (remove node from path).

💡 Tip:
Use backtracking — only keep the correct path.

🕒 Time: O(N) → visit all nodes in worst case
🧠 Space: O(H) → path list + recursion stack (H = height)

 */