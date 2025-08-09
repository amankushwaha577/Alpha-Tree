
import java.util.*;

public class Inorder {

    // 🌳 Node class representing each tree node
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // 🔁 Iterative Inorder Traversal: Left → Root → Right
    public static void iterativeInorder(Node root) {
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            // 📥 Reach the leftmost node of the current subtree
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // ⬅️ Backtrack: Pop from stack and visit the node
            current = stack.pop();
            System.out.print(current.data + " ");

            // ➡️ Move to the right subtree
            current = current.right;
        }
    }

    public static void main(String[] args) {
        // 🛠️ Build the following tree manually:
        //         1
        //       /   \
        //      2     3
        //     / \     \
        //    4   5     6

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        System.out.print("Iterative Inorder Traversal: ");
        iterativeInorder(root);  // ➡️ Output: 4 2 5 1 3 6
    }
}


/*
🧠 LOGIC: Iterative Inorder Traversal using Stack

📘 Inorder Traversal: Left → Root → Right

🔁 Idea:
- We simulate the recursion using an explicit Stack.
- Go as far left as possible, pushing nodes to the stack.
- Once you reach the end, backtrack (pop), visit, then go right.

📦 Algorithm Steps:
1. Initialize current = root
2. While current is not null or stack is not empty:
   a. Go to leftmost node:
      - While current is not null:
          - push current to stack
          - move current = current.left
   b. Pop from stack, print data
   c. Move to current.right

📌 Time Complexity: O(n) — each node is visited once
📌 Space Complexity: O(h) — max height of stack is height of tree

📋 Example Tree:
         🔵1
       /     \
     🔵2     🔵3
    /   \       \
  🔵4   🔵5     🔵6

🧪 Dry Run (stack operations):

Start:
current = 1, stack = []

Push 1 → current = 2
Push 2 → current = 4
Push 4 → current = null

Pop 4 → print(4) → current = null
Pop 2 → print(2) → current = 5
Push 5 → current = null
Pop 5 → print(5) → current = null
Pop 1 → print(1) → current = 3
Push 3 → current = null
Pop 3 → print(3) → current = 6
Push 6 → current = null
Pop 6 → print(6) → Done ✅

🖨️ Output: 4 2 5 1 3 6
*/
