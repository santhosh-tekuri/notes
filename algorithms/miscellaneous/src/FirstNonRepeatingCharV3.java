/**
 * @author Santhosh Kumar Tekuri
 */
public class FirstNonRepeatingCharV3 {
    static class Stream {
        boolean repeated[] = new boolean[256]; // repeated[ch] = true if ch is repeated
        Node head = null; // doubly linked list to find first non repeating character
        Node nodes[] = new Node[256]; // nodes[ch] = reference node in linked list for that character

        void consume(char ch) {
            if(!repeated[ch]) {
                if(nodes[ch]==null) { // first occurrence of ch
                    Node n = new Node(ch);
                    append(n);
                    nodes[ch] = n;
                } else {
                    remove(nodes[ch]);
                    nodes[ch] = null;
                    repeated[ch] = true;
                }
            }
        }

        void append(Node n) {
            if(head==null)
                head = n;
            else {
                join(head.prev, n);
                join(n, head);
            }
        }

        void remove(Node n) {
            if(head==n) {
                if(head.next==head) {
                    head = null;
                    return;
                }
                head = head.next;
            }
            join(n.prev, n.next);
        }

        void join(Node n1, Node n2) {
            n1.next = n2;
            n2.prev = n1;
        }

        Character firstNonRepeating(){
            return head==null ? null : head.data;
        }
    }

    static Character firstNonRepeating(String str) {
        Stream stream = new Stream();
        for(char ch: str.toCharArray())
            stream.consume(ch);
        return stream.firstNonRepeating();
    }

    public static void main(String[] args) {
        System.out.println(firstNonRepeating("GeeksforGeeks"));
        System.out.println(firstNonRepeating("GeeksQuiz"));
        System.out.println(firstNonRepeating("step on no pets"));

        // Output:
        // 5
        // 0
        // -1
    }

    static class Node {
        char data;
        Node prev;
        Node next;
        Node(char data) {
            this.data = data;
            this.prev = this;
            this.next = this;
        }
    }
}
