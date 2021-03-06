public class ThemePark1 {
    public static Tuple doRide(long k, long g[], int head) {
        long space = k;
        int newHead = head;
        do {
            if (space-g[newHead]>=0) {
                space -= g[newHead];
                newHead = (newHead+1) % g.length;
            } else
                break;
        } while (newHead!=head);
        return new Tuple(k-space, newHead);
    }

    public static long findMoney(long R, long k, long g[]) {
        // pre-processing
        long people[] = new long[g.length];
        int newHead[] = new int[g.length];
        for(int head=0; head<g.length; head++){
            Tuple t = doRide(k, g, head);
            people[head] = t.people;
            newHead[head] = t.newHead;
        }

        // main-computation
        long money = 0;
        int head = 0;
        for(long r=0; r<R; r++) {
            money += people[head];
            head = newHead[head];
        }
        return money;
    }

    public static void main(String[] args) {
        System.out.println(findMoney(4, 6, new long[]{1, 4, 2, 1}));
        System.out.println(findMoney(102, 6, new long[]{1, 4, 2, 1}));
        System.out.println(findMoney(100, 10, new long[]{1}));
        System.out.println(findMoney(5, 5, new long[]{2, 4, 2, 3, 4, 2, 1, 2, 1, 3}));
        // Output:
        // 21
        // 543
        // 100
        // 20
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