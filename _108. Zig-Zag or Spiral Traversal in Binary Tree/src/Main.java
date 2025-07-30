import java.util.*;

public class Main {

    // 🌿 Node class for binary tree
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // 🚀 Zig-Zag / Spiral Level Order Traversal using 2 stacks
    public static void zigZagTraversal(Node root) {
        if (root == null) return;

        Stack<Node> currLevel = new Stack<>(); // 🔁 Current level
        Stack<Node> nextLevel = new Stack<>(); // 🔁 Next level
        boolean leftToRight = true; // ➡️ Direction flag

        currLevel.push(root);

        while (!currLevel.isEmpty()) {
            Node curr = currLevel.pop();
            System.out.print(curr.data + " ");

            // ➕ Push children depending on direction
            if (leftToRight) {
                if (curr.left != null) nextLevel.push(curr.left);
                if (curr.right != null) nextLevel.push(curr.right);
            } else {
                if (curr.right != null) nextLevel.push(curr.right);
                if (curr.left != null) nextLevel.push(curr.left);
            }

            // 🔄 When current level ends, swap stacks and toggle direction
            if (currLevel.isEmpty()) {
                leftToRight = !leftToRight;
                Stack<Node> temp = currLevel;
                currLevel = nextLevel;
                nextLevel = temp;
                System.out.println(); // ⏎ New line after each level
            }
        }
    }

    public static void main(String[] args) {
        // 🌲 Sample Tree
        /*
                  1
                /   \
              2       3
            /  \     /  \
           4    5   6    7

        Zig-Zag Output:
        1
        3 2
        4 5 6 7
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.println("Zig-Zag / Spiral Traversal:");
        zigZagTraversal(root);
    }
}

/*
🧠 LOGIC: Zig-Zag Level Order Traversal using 2 Stacks

🎯 Goal: Print nodes level-by-level, but alternate direction:
   Level 0: Left → Right
   Level 1: Right → Left
   Level 2: Left → Right
   ...

🔁 How it works:
- Use two stacks: one for the current level and one for the next level.
- Toggle direction each time a level ends.
- For Left → Right: push left child first, then right.
- For Right → Left: push right child first, then left.

🧪 Dry Run:

Tree:
         1
       /   \
     2       3
   /  \     /  \
  4    5   6    7

➡️ Level 0: currLevel = [1], leftToRight = true
Print 1 → push 2, 3 → nextLevel = [2, 3]

➡️ Level 1: currLevel = [3, 2], leftToRight = false
Print 3 → push 7, 6
Print 2 → push 5, 4 → nextLevel = [7, 6, 5, 4]

➡️ Level 2: currLevel = [4, 5, 6, 7], leftToRight = true
Print 4 5 6 7 → all leaf nodes, no push

✅ Output:
1
3 2
4 5 6 7

📌 Time Complexity: O(n) — visit every node once
📌 Space Complexity: O(n) — stack stores one level at a time
*/
