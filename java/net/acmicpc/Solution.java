package net.acmicpc;


import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    private int[] cards;
    private int totalCard;
    private int maxRound;
    private int target;

    public int solution(int coin, int[] cards) {
        this.cards = cards;
        this.totalCard = cards.length;
        this.target = cards.length + 1;
        this.maxRound = totalCard / 3;
        Set<Integer> startHands = new HashSet<>();
        for (int i = 0; i < totalCard / 3; i++) {
            startHands.add(cards[i]);
        }
        int pair = findCombination(startHands);
        return findMaxRound(startHands, pair, coin);
    }

    public int findMaxRound(Set<Integer> hands, int pair, int coin) {
        //뽑을 카드가 없는경우
        int round = 0;
        Set<Integer> canDrow = new HashSet<>();
        for (int i = totalCard / 3; i < totalCard; i += 2) {
            if (i + 2 > totalCard) {
                round++;
            }
            for (int j = 0; j < 2; j++) {
                Integer cur = cards[i + j];
                if (hands.contains(target - cur) && coin > 0) {
                    pair++;
                    hands.remove(target - cur);
                    coin--;
                } else {
                    canDrow.add(cur);
                }
            }
            round++;
            if (pair < 1) {
                if (coin > 1 && findCombinationOne(canDrow)) {
                    coin -= 2;
                } else {
                    break;
                }
            } else {
                pair--;
            }
        }

        return round;
    }

    public boolean findCombinationOne(Set<Integer> hands) {
        Integer find = null;
        for (Integer cur : hands) {
            if (hands.contains(target - cur)) {
                find = cur;
                break;
            }
        }
        if (find == null) return false;
        hands.remove(find);
        hands.remove(target - find);
        return true;
    }

    public int findCombination(Set<Integer> hands) {
        Set<Integer> toRemove = new HashSet<>();
        for (Integer cur : hands) {
            if (toRemove.contains(cur)) continue;
            if (hands.contains(target - cur)) {
                toRemove.add(cur);
                toRemove.add(target - cur);
                break;
            }
        }
        hands.removeAll(toRemove);
        return toRemove.size() / 2;
    }
}