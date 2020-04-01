import java.util.*;

public class Skyline {
    public static List<int[]> getSkyline(int[][] buildings) {
        return getSkyline(buildings, 0, buildings.length - 1);
    }

    static List<int[]> getSkyline(int[][] buildings, int lo, int hi) {
        if (lo > hi) {
            return new ArrayList<>();
        } else if (lo == hi) {
            int[] topLeft = new int[]{buildings[lo][0], buildings[lo][2]};
            int[] bottomRight = new int[]{buildings[lo][1], 0};
            List<int[]> skyline = new ArrayList<>();
            skyline.add(topLeft);
            skyline.add(bottomRight);
            return skyline;
        } else {
            int mid = (lo + hi) / 2;
            List<int[]> s1 = getSkyline(buildings, lo, mid);
            List<int[]> s2 = getSkyline(buildings, mid + 1, hi);
            return merge(s1, s2);
        }
    }

    static List<int[]> merge(List<int[]> s1, List<int[]> s2) {
        List<int[]> s = new ArrayList<>();
        int i = 0, j = 0;
        int h1 = 0, h2 = 0;
        while (i < s1.size() && j < s2.size()) {
            int[] p1 = s1.get(i);
            int[] p2 = s2.get(j);
            if (p1[0] < p2[0]) {
                i++;
                h1 = p1[1];
                addToSkyline(s, new int[]{p1[0], Math.max(h1, h2)});
            } else if (p2[0] < p1[0]) {
                j++;
                h2 = p2[1];
                addToSkyline(s, new int[]{p2[0], Math.max(h1, h2)});
            } else {
                i++;
                j++;
                h1 = p1[1];
                h2 = p2[1];
                addToSkyline(s, new int[]{p1[0], Math.max(h1, h2)});
            }
        }
        while (i < s1.size()) {
            addToSkyline(s, s1.get(i));
            i++;
        }
        while (j < s2.size()) {
            addToSkyline(s, s2.get(j));
            j++;
        }
        return s;
    }

    static void addToSkyline(List<int[]> s, int[] p) {
        if (!s.isEmpty()) {
            int[] last = s.get(s.size() - 1);
            if (p[1] == last[1]) {
                return;
            }
        }
        s.add(p);
    }

    public static void main(String[] args) {
        run(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}});
        run(new int[][]{{0, 2, 3}, {2, 5, 3}});
        run(new int[][]{{1, 5, 11}, {2, 7, 6}, {3, 9, 13}, {12, 16, 7}, {14, 25, 3}, {19, 22, 18}, {23, 29, 13}, {24, 28, 4}});
        run(new int[][]{{1, 3, 3}, {2, 4, 4}, {5, 8, 2}, {6, 7, 4}, {8, 9, 4}});
        // { {2,10} {3,15} {7,12} {12,0} {15,10} {20,8} {24,0} }
        // { {0,3} {5,0} }
        // { {1,11} {3,13} {9,0} {12,7} {16,3} {19,18} {22,3} {23,13} {29,0} }
        // { {1,3} {2,4} {4,0} {5,2} {6,4} {7,2} {8,4} {9,0} }
    }

    public static void run(int[][] buildings) {
        System.out.print("{ ");
        for (int[] p : getSkyline(buildings)) {
            System.out.print("{" + p[0] + "," + p[1] + "} ");
        }
        System.out.println("}");
    }
}
