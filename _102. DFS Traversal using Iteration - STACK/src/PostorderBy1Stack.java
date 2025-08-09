
import java.util.*;

public class PostorderBy1Stack {

    // 🌳 Node class
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // 🔁 Iterative Postorder using 1 Stack
    public static void postorderOneStack(Node root) {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        Node lastVisited = null;

        while (current != null || !stack.isEmpty()) {
            // 🌿 Go left as much as possible
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                Node peekNode = stack.peek();

                // 🪜 If right child exists and not yet visited, go right
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    current = peekNode.right;
                } else {
                    // ✅ Both left & right done, process node
                    System.out.print(peekNode.data + " ");
                    lastVisited = stack.pop();
                }
            }
        }
    }

    public static void main(String[] args) {
        // Sample Tree:
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

        System.out.print("Iterative Postorder Traversal (1 stack): ");
        postorderOneStack(root); // ➡️ Expected: 4 5 2 6 3 1
    }
}


/*
🧠 LOGIC: Iterative Postorder Traversal using 1 Stack

📘 Postorder = Left → Right → Root

🔁 Idea:
- Simulate recursion manually using just 1 stack and a pointer to last visited node
- Keep going left as deep as possible (like recursion)
- When at dead end, peek top of stack:
    • If right child exists and is not visited, move to right
    • Else: pop and print (both children are done)

📦 Steps:
1. Initialize `stack`, `current = root`, `lastVisited = null`
2. While current is not null or stack is not empty:
   - Push current to stack and go left
   - If left is null, peek stack:
     - If right exists and not visited → move to right
     - Else → process node, pop and update lastVisited

📌 Time Complexity: O(n)
📌 Space Complexity: O(h) — height of tree

🧪 Dry Run:

Tree:
         1
       /   \
     2       3
    / \        \
   4   5        6

Stack Trace:

Push: 1 → 2 → 4
Top=4, no right → pop 4 ✅

Top=2, right=5 → go right → push 5
Top=5, no right → pop 5 ✅

Top=2, both done → pop 2 ✅

Top=1, right=3 → go right → push 3 → push 6
Top=6, no right → pop 6 ✅
Top=3, done → pop 3 ✅
Top=1, done → pop 1 ✅

✅ Output: 4 5 2 6 3 1
*/