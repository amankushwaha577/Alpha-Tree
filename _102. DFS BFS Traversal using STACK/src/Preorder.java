import java.util.*;

public class Preorder {

    // 🌳 Node class representing a binary tree node
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // 🔁 Iterative Preorder Traversal (Root → Left → Right)
    public static void iterativePreorder(Node root) {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(root); // ✅ Step 1: Push root

        while (!stack.isEmpty()) {
            Node current = stack.pop();         // 🧱 Step 2: Pop and visit top node
            System.out.print(current.data + " "); // 🖨️ Visit node

            // ➕ Step 3: Push right first so left is on top of stack
            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    public static void main(String[] args) {
        // 🛠️ Manually build this tree:
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

        System.out.print("Iterative Preorder Traversal: ");
        iterativePreorder(root);  // ➡️ Output: 1 2 4 5 3 6
    }
}


/*
🔍 DRY RUN — Iterative Preorder Traversal using Stack (LIFO):

Tree:
         🔵1
       /     \
     🔵2     🔵3
    /   \       \
  🔵4   🔵5     🔵6

Initial stack: [1]
Output: (empty)

1️⃣ Pop 1 → print 1 → push 3, push 2
Stack: [3, 2]
Output: 1

2️⃣ Pop 2 → print 2 → push 5, push 4
Stack: [3, 5, 4]
Output: 1 2

3️⃣ Pop 4 → print 4 → no children
Stack: [3, 5]
Output: 1 2 4

4️⃣ Pop 5 → print 5 → no children
Stack: [3]
Output: 1 2 4 5

5️⃣ Pop 3 → print 3 → push 6
Stack: [6]
Output: 1 2 4 5 3

6️⃣ Pop 6 → print 6 → no children
Stack: []
Output: 1 2 4 5 3 6

✅ Final Output:
Iterative Preorder Traversal: 1 2 4 5 3 6
*/



/*
🧠 LOGIC: Iterative Preorder Traversal using Stack

👉 Traversal Order: Root → Left → Right

🪄 How it works:
- Preorder means visiting root node first, then going left, then right.
- In recursion, this happens naturally through the call stack.
- Iteratively, we simulate this with our **own stack**.

🔁 Algorithm Steps:

1. Push the root node to the stack.
2. While stack is not empty:
   a. Pop the top node.
   b. Print its data (Root).
   c. Push its **right child first** (so it's visited after left).
   d. Push its **left child** next (so it's visited next).
3. Repeat until stack is empty.

📦 Why right child first?
- Stack is **Last In First Out (LIFO)**.
- So by pushing right before left, left gets processed first, matching preorder order.

📌 Time Complexity: O(n)
- Each node is visited once.

📌 Space Complexity: O(h)
- h = height of tree (stack holds at most h nodes in skewed tree)

📋 Example:
         🔵1
       /     \
     🔵2     🔵3
    /   \       \
  🔵4   🔵5     🔵6

➤ Output: 1 2 4 5 3 6
*/
