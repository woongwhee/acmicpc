package net.acmicpc.programmers;

public class problem160585 {
    private static class Solution {
        public int solution(String[] board) {
            int answer = 1;
            int[][] intBoard = new int[3][3];
            int xCount = 0;
            int oCount = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    switch (board[i].charAt(j)) {
                        case 'O':
                            intBoard[i][j] = 1;
                            oCount++;
                            break;
                        case 'X':
                            intBoard[i][j] = -1;
                            xCount++;
                            break;
                        default:
                            intBoard[i][j] = 0;
                            break;
                    }
                }
            }
            if (xCount !=oCount&&xCount !=oCount-1) {//x가 하나 더 많은 경우
                return 0;
            }
            boolean hasXLine = false;
            boolean hasOLine = false;
            for (int i = 0; i < 3; i++) {//가로
                if (intBoard[i][0] == intBoard[i][1] && intBoard[i][1] == intBoard[i][2]) {
                    if (intBoard[i][0] == 1) {
                        hasOLine = true;
                    } else if (intBoard[i][0] == -1) {
                        hasXLine = true;
                    }
                }
            }
            for (int i = 0; i < 3; i++) {//세로
                if (intBoard[0][i] == intBoard[1][i] && intBoard[0][i] == intBoard[2][i]) {
                    if (intBoard[0][i] == 1) {
                        hasOLine = true;
                    } else if (intBoard[0][i] == -1) {
                        hasXLine = true;
                    }
                }
            }
            if (intBoard[0][0] == intBoard[1][1] && intBoard[1][1] == intBoard[2][2]) {//대각선
                if (intBoard[0][0] == 1) {
                    hasOLine = true;
                } else if (intBoard[0][0] == -1) {
                    hasXLine = true;
                }
            }
            if (intBoard[0][2] == intBoard[1][1] && intBoard[1][1] == intBoard[2][0]) {//대각선
                if (intBoard[0][2] == 1) {
                    hasOLine = true;
                } else if (intBoard[0][2] == -1) {
                    hasXLine = true;
                }
            }
            if (hasXLine) {
                if (xCount != oCount) {
                    answer = 0;
                }
                if (hasOLine) {
                    answer = 0;
                }
            } else if (hasOLine) {
                if (oCount != xCount + 1) {
                    answer = 0;
                }
            }
            return answer;
        }

    }

    static String[]borad= {"OXO","XOX","OXO"};
    public static void main(String[] args) {
        Solution s=new Solution();
        int solution = s.solution(borad);
        System.out.println(solution);
    }
}
