package net.acmicpc.programmers;

import java.util.Arrays;

public class lessons150367 {
    class Solution {
        private boolean validateTree(long number){
            if(number==0){return false;}
            if(number==1){return true;}
            int head=0;

            long bin=1;
            while(number/bin>0){
                head++;
                bin*=2;
            }
            //head 는 2^n-1 1,3  이여야 된다.

            double logValue = Math.log(head + 1) / Math.log(2);
            if (logValue != (int) logValue) {
                head++;
                logValue = Math.log(head + 1) / Math.log(2);
                if (logValue != (int) logValue) {
                    return false;
                }
            }
            int leaf=(int)logValue-1;
            BoxInt idx=new BoxInt(0);
            Result result=new Result();
            visitChild(0, idx, leaf, number,result);
            return result.isTree;
        }
        static class BoxInt{
            int i;
            BoxInt(int i){this.i=i;}
        }
        static class Result{
            boolean isTree;
            Result(){this.isTree=true;}
        }
        private boolean visitChild(int depth,BoxInt idx,int leaf,long number,Result resultPoint){
            if(!resultPoint.isTree){
                return false;
            }
            if(depth==leaf){
                return (number&(1L<<idx.i++))==0;
            }
            //오른쪽자식이 마지막으로 쓴 인덱스+1은 자신의 인덱스
            boolean childIsDummy=visitChild(depth+1,idx,leaf,number,resultPoint);
            if(!resultPoint.isTree){
                return false;
            }

            idx.i++;
            //자신이 더미인경우 두 자식도 모두 더미여야한다
            if((number&(1L<<idx.i))==0){
                if(!childIsDummy){
                    resultPoint.isTree=false;
                    return false;
                }
                childIsDummy=visitChild(depth+1,idx,leaf,number,resultPoint);
                if(!childIsDummy||!resultPoint.isTree){
                    resultPoint.isTree=false;
                }
                return false;
            }else{
                visitChild(depth+1,idx,leaf,number,resultPoint);
                return true;
            }

        }

        public int[] solution(long[] numbers) {
            int[] answer =new int[numbers.length];
            for(int i=0;i<numbers.length;i++){
                answer[i]=validateTree(numbers[i])?1:0;
            }
            return answer;
        }
    }
    public static void main(String[] args) {
        Solution sol=new lessons150367().new Solution();
        long[] numbers={0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 128, 129, 16512, 2147516555L};
        int[] result={0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0,1};
        int[] answer=sol.solution(numbers);

        for(int i=0;i<answer.length;i++){
            if(answer[i]!=result[i]){
                System.out.println(i+"failed");
//                return;
            }
        }
        System.out.println(Arrays.toString(answer));
    }
}
