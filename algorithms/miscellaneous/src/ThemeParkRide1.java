public class ThemeParkRide1 {
    public static Tuple doRide(long k, long g[], int head) {
        long people = 0;
        int newHead = head;
        do {
            if (people + g[newHead] <= k) {
                people += g[newHead];
                newHead = (newHead + 1) % g.length;
            } else
                break;
        } while (newHead != head);
        return new Tuple(people, newHead);
    }

    public static void main(String[] args) {
        System.out.println(doRide(6, new long[]{1, 4, 2, 1}, 0));
        System.out.println(doRide(6, new long[]{1, 4, 2, 1}, 1));
        System.out.println(doRide(6, new long[]{1, 4, 2, 1}, 2));
        System.out.println(doRide(6, new long[]{1, 4, 2, 1}, 3));
        // Output:
        // People:5 newHead:2
        // People:6 newHead:3
        // People:4 newHead:1
        // People:6 newHead:2
    }

    static class Tuple {
        long people;
        int newHead;

        public Tuple(long people, int newHead) {
            this.people = people;
            this.newHead = newHead;
        }

        @Override
        public String toString() {
            return "People:" + people + " newHead:" + newHead;
        }
    }
}