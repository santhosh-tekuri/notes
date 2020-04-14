public class ThemePark3 {
    public static int findCycleHead(int newHead[]) {
        boolean visited[] = new boolean[newHead.length];
        int head = 0;
        while(!visited[head]) {
            visited[head] = true;
            head = newHead[head];
        }
        return head;
    }

    public static long findMoney(long R, long k, long g[]) {
        // pre-processing
        long people[] = new long[g.length];
        int newHead[] = new int[g.length];
        for(int head=0; head<g.length; head++){
            long space = head==0 ? k : k-(people[head-1]-g[head-1]);
            int hNext = head==0 ? 0 : newHead[head-1];
            do {
                if(space-g[hNext]>=0) {
                    space -= g[hNext];
                    hNext = (hNext+1) % g.length;
                } else
                    break;
            } while(hNext!=head);
            people[head] = k-space;
            newHead[head] = hNext;
        }

        // main-computation ---
        int cycleHead = findCycleHead(newHead);

        long r = 0;
        long money = 0;
        int head = 0;

        // ride until cycle reaches
        while(r<R && head!=cycleHead) {
            money += people[head];
            head = newHead[head];
            r++;
        }

        // ride one cycle
        int ridesInCycle = 0;
        int moneyInCycle = 0;
        while(r<R) {
            money += people[head];
            moneyInCycle += people[head];
            head = newHead[head];
            r++;
            ridesInCycle++;
            if(head==cycleHead)
                break;
        }
        if(r==R)
            return money;
        assert head==cycleHead;

        // finish cycles in remaining rounds
        long cycles = (R-r)/ridesInCycle;
        r += cycles*ridesInCycle;
        money += cycles*moneyInCycle;

        // finish remaining rounds, if any
        while(r<R) {
            money += people[head];
            head = newHead[head];
            r++;
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