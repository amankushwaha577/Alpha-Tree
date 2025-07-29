import java.util.*;

public class PostorderBy2Stacks {

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

    // 🔁 Iterative Postorder Traversal: Left → Right → Root using 2 stacks
    public static void iterativePostorder(Node root) {
        if (root == null) return;

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        s1.push(root); // Start with root

        while (!s1.isEmpty()) {
            Node curr = s1.pop();
            s2.push(curr); // ➕ Root added to second stack (to reverse order)

            // 👈 First push left, then right into s1
            if (curr.left != null) {
                s1.push(curr.left);
            }
            if (curr.right != null) {
                s1.push(curr.right);
            }
        }

        // 📤 Pop from second stack to get postorder
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().data + " ");
        }
    }

    public static void main(String[] args) {
        // 🛠️ Build the following tree:
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

        System.out.print("Iterative Postorder Traversal: ");
        iterativePostorder(root);  // ➡️ Output: 4 5 2 6 3 1
    }
}


/*
🧠 LOGIC: Iterative Postorder Traversal using 2 Stacks

📘 Postorder = Left → Right → Root

🔁 Idea:
1. Use two stacks: s1 (processing) and s2 (result)
2. Traverse tree as if doing reverse preorder: Root → Right → Left
3. Instead of printing immediately, push to s2 to reverse later
4. Finally pop from s2 to print in correct postorder

📦 Steps:
- Push root to s1
- While s1 is not empty:
  - Pop curr from s1, push to s2
  - Push curr.left to s1 (if exists)
  - Push curr.right to s1 (if exists)
- Now pop all from s2 (gives Left → Right → Root order)

📌 Time Complexity: O(n)
📌 Space Complexity: O(n)

🧪 Dry Run:

Tree:
         1
       /   \
     2       3
    / \        \
   4   5        6

s1 → processing
s2 → reversed postorder

Initial: s1 = [1], s2 = []

Step 1: pop 1 → push to s2 → push 2 & 3 to s1
s1 = [2, 3], s2 = [1]

Step 2: pop 3 → push to s2 → push 6 to s1
s1 = [2, 6], s2 = [1, 3]

Step 3: pop 6 → push to s2
s1 = [2], s2 = [1, 3, 6]

Step 4: pop 2 → push to s2 → push 4 & 5 to s1
s1 = [4, 5], s2 = [1, 3, 6, 2]

Step 5: pop 5 → push to s2
s1 = [4], s2 = [1, 3, 6, 2, 5]

Step 6: pop 4 → push to s2
s1 = [], s2 = [1, 3, 6, 2, 5, 4]

Now pop from s2: 4 5 2 6 3 1 ✅
*/
