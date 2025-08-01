import java.util.Stack;

public class BSTIterator {
    static class Node {
        int val;
        Node left, right;
        Node(int x) { val = x; }
    }

    private Stack<Node> stack = new Stack<>();

    // 🔧 Constructor: Push all lefts of root
    public BSTIterator(Node root) {
        pushLeft(root);
    }

    // 👇 Helper to push all left nodes of a subtree
    private void pushLeft(Node node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    // ✅ Return true if there's a next smallest number
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    // 🔁 Return the next smallest number
    public int next() {
        Node curr = stack.pop();      // Current smallest
        if (curr.right != null) {
            pushLeft(curr.right);     // Push left path of right subtree
        }
        return curr.val;
    }

    // 📦 Usage Example
    public static void main(String[] args) {
        /*
                   7
                  / \
                 3   15
                     / \
                    9  20
         Inorder: [3, 7, 9, 15, 20]
        */
        Node root = new Node(7);
        root.left = new Node(3);
        root.right = new Node(15);
        root.right.left = new Node(9);
        root.right.right = new Node(20);

        BSTIterator it = new BSTIterator(root);
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
    }
}

/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🧠 BST Iterator — Concept & Time Complexity Notes
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

📌 GOAL:
Design a class to iterate through a BST in **sorted order** (inorder traversal):
    - next()     → returns the next smallest element
    - hasNext()  → returns true if elements remain

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔍 ALGORITHM (Iterative Inorder using Stack):
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

▶ Constructor:
    - Traverse from `root` to the **leftmost** node.
    - Push every node on the path into a stack.
    - Stack now contains the start of inorder traversal.

▶ next():
    - Pop top node from the stack → this is the next smallest.
    - If the node has a right child:
        ➤ Push all left children of that right subtree into stack.

▶ hasNext():
    - Return true if the stack is **not empty**.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
💡 WHY THIS WORKS:

- The **inorder traversal** of BST gives **sorted order**.
- The stack stores nodes we need to revisit.
- Only the path to leftmost node is stored at any time → saves space.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🕒 TIME COMPLEXITY:

- `hasNext()` → O(1) ✅
    Just checks if stack is empty.

- `next()` → O(1) *amortized* ✅
    Each node is pushed and popped **once**.

🔁 Total time over N calls to next() = O(N)
    → Each node visited once ⇒ O(1) amortized per `next()` call.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📦 SPACE COMPLEXITY:

- O(H) → where H is height of the BST
    - Stack holds at most one path from root to leaf.
    - Balanced BST: O(log N)
    - Skewed BST: O(N)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ Example:
         7
        / \
       3   15
           / \
          9  20

Inorder Traversal: 3 → 7 → 9 → 15 → 20

Stack Trace:
- Initially: push(7), push(3) → Stack: [3, 7]
- next() → pop 3 → Stack: [7]
- next() → pop 7 → push(15), push(9) → Stack: [9, 15]
- ... and so on.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 ITERATOR PATTERN:
- You don’t store all elements (not O(N) space).
- You only store what’s **needed to get next**.
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/



/*
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📚 BST Iterator — DRY RUN with STACK + TREE
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

▶️ TREE STRUCTURE:

               7
             /   \
           3      15
                /   \
              9      20

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔧 INITIALIZATION: BSTIterator(root)

1. pushLeftPath(7):
   → push(7)
   → push(3)
   Stack = [7, 3]  ← top is 3

📦 STACK:
[3, 7]

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 CALL next()

✅ POP 3 (no right child)
📦 STACK:
[7]
🖨️ OUTPUT: 3

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 CALL next()

✅ POP 7 → has right child 15
→ pushLeftPath(15):
    → push(15)
    → push(9)

📦 STACK:
[9, 15]
🖨️ OUTPUT: 7

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 CALL next()

✅ POP 9 (no right child)
📦 STACK:
[15]
🖨️ OUTPUT: 9

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 CALL next()

✅ POP 15 → has right child 20
→ pushLeftPath(20)
    → push(20)

📦 STACK:
[20]
🖨️ OUTPUT: 15

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔁 CALL next()

✅ POP 20 (no right)
📦 STACK:
[]
🖨️ OUTPUT: 20

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🛑 hasNext() → false → END

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ FINAL OUTPUT:
3
7
9
15
20

🧠 TIME COMPLEXITY:
• next() + hasNext() → O(1) amortized
• Total time for N nodes = O(N)

📦 SPACE COMPLEXITY:
• O(H) where H = height of BST (due to stack)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
*/