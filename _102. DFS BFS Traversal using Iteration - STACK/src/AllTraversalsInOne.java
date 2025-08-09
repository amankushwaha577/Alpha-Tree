import java.util.*;

public class AllTraversalsInOne {

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    static class Pair {
        Node node;
        int state; // 1 = Pre, 2 = In, 3 = Post

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void allTraversals(Node root) {
        List<Integer> preorder = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));

        while (!stack.isEmpty()) {
            Pair top = stack.pop();

            if (top.state == 1) {
                // 🔁 Preorder: Root -> Left -> Right
                preorder.add(top.node.data);
                top.state++;
                stack.push(top); // Push again with updated state
                if (top.node.left != null) {
                    stack.push(new Pair(top.node.left, 1));
                }

            } else if (top.state == 2) {
                // 🔁 Inorder: Left -> Root -> Right
                inorder.add(top.node.data);
                top.state++;
                stack.push(top);
                if (top.node.right != null) {
                    stack.push(new Pair(top.node.right, 1));
                }

            } else {
                // ✅ Postorder: Left -> Right -> Root
                postorder.add(top.node.data);
            }
        }

        System.out.println("Preorder: " + preorder);
        System.out.println("Inorder: " + inorder);
        System.out.println("Postorder: " + postorder);
    }

    public static void main(String[] args) {
        /*
              1
            /   \
           2     3
          / \     \
         4   5     6
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        allTraversals(root);
    }
}

/*
📦 Algorithm Steps:
1. Create three empty lists: preorder, inorder, postorder.
2. Create a stack of Pair<Node, state>:
   - state = 1 → Preorder phase
   - state = 2 → Inorder phase
   - state = 3 → Postorder phase
3. Push (root, 1) into the stack.
4. While stack is not empty:
   a. Pop the top pair (node, state).
   b. If state == 1:
        - Add node.data to preorder list.
        - Increment state to 2 and push back into stack.
        - If node.left exists → push (node.left, 1) to stack.
   c. Else if state == 2:
        - Add node.data to inorder list.
        - Increment state to 3 and push back into stack.
        - If node.right exists → push (node.right, 1) to stack.
   d. Else (state == 3):
        - Add node.data to postorder list.
5. After loop ends, print all three traversal lists.

📌 Time Complexity: O(n) — each node is processed exactly 3 times (once for each phase).
📌 Space Complexity: O(n) — stack can hold up to n nodes in worst case (skewed tree).
*/


/*
🧠 LOGIC: Preorder, Inorder & Postorder in One Traversal (Single Stack)

🔢 We use a `Pair` of (Node, State) to track progress through each node:
    1 → Preorder time (process and go left)
    2 → Inorder time (process and go right)
    3 → Postorder time (final process after both subtrees)

📘 Traversal Order:
- Preorder: Process when state == 1
- Inorder: Process when state == 2
- Postorder: Process when state == 3

📦 Steps:
- Start with root node and state=1
- On state 1: process for preorder, push left
- On state 2: process for inorder, push right
- On state 3: process for postorder

📌 Time Complexity: O(n)
📌 Space Complexity: O(n)

🧪 DRY RUN:

For Tree:

         1
       /   \
     2       3
    / \        \
   4   5        6

Traversal Result:
Preorder:  [1, 2, 4, 5, 3, 6]
Inorder:   [4, 2, 5, 1, 3, 6]
Postorder: [4, 5, 2, 6, 3, 1]
*/
