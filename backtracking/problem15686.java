package net.acmicpc.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class problem15686 {
    static int min = 1000007;
    static List<Potision> chicken;
    static List<Potision> house;
    static int[][] range;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        chicken = new ArrayList<>(2 * n);
        house = new ArrayList<>(13);
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int cur = Integer.parseInt(split[j]);
                if (cur == 1) {
                    house.add(new Potision(i, j));
                } else if (cur == 2) {
                    chicken.add(new Potision(i, j));
                }
            }
        }
        checkRange();
        boolean[] check = new boolean[chicken.size()];
        backtracking(check, m, 0);
        System.out.println(min);
    }

    public static void checkRange() {
        range = new int[house.size()][chicken.size()];
        int i = 0;
        for (Potision h : house) {
            int j = 0;
            for (Potision c : chicken) {
                range[i][j++] = h.getRange(c);
            }
            i++;
        }
    }

    public static void backtracking(boolean[] check, int toSelect, int index) {
        if (toSelect == 0) {
            int totalDistance = 0;
            for (int i = 0; i < house.size(); i++) {
                int minDistance = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (check[j]) {
                        minDistance = Math.min(range[i][j], minDistance);
                    }
                }
                totalDistance += minDistance;
            }

            min = Math.min(totalDistance, min);
            return;
        }
        if (index == check.length) {
            return;
        }
        check[index] = true;
        backtracking(check, toSelect - 1, index + 1);
        check[index] = false;
        backtracking(check, toSelect, index + 1);

    }

    static class Potision {
        int x, y;

        public Potision(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getRange(Potision p) {
            return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
        }
    }
}
