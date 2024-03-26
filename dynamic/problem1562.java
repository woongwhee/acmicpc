package net.acmicpc.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class problem1562 {
    private static final int MOD = 1000000000;
    private static final int FULL_MASK = (1 << 10) - 1; // 모든 숫자가 포함된 상태

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();

        HashMap<Integer, Long> dp = new HashMap<>();
        for (int i = 1; i < 10; i++) {
            int key = (1 << 10) | (i << 5) | (1 << i); // 자리수(상위 5비트) + 현재 숫자(다음 5비트) + 포함된 숫자들의 상태(하위 10비트)
            dp.put(key, 1L);
        }

        for (int i = 2; i <= N; i++) {
            HashMap<Integer, Long> nextDp = new HashMap<>();
            Integer finalI = i;
            dp.forEach((key, count) -> {

                int position = (key >> 10) & 31; // 현재 자리수
                int num = (key >> 5) & 31; // 현재 숫자
                int mask = key & FULL_MASK; // 포함된 숫자들의 상태

                if (position != finalI - 1) return; // 현재 자리수가 i-1이 아니면 넘어감

                // 다음 숫자로 이동
                for (int nextNum = 0; nextNum < 10; nextNum++) {
                    if (Math.abs(num - nextNum) == 1) {
                        int nextKey = ((finalI << 10) | (nextNum << 5) | (mask | (1 << nextNum)));
                        nextDp.put(nextKey, (nextDp.getOrDefault(nextKey, 0L) + count) % MOD);
                    }
                }
            });
            dp = nextDp;
        }

        // 결과 계산
        long result = dp.entrySet().stream()
                .filter(entry -> (entry.getKey() & FULL_MASK) == FULL_MASK) // 모든 숫자를 포함하는 경우만 선택
                .mapToLong(HashMap.Entry::getValue)
                .sum() % MOD;

        System.out.println(result);
    }
}