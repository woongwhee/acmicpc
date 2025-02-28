package net.acmicpc.binary;

public class leasons49995 {
    class Solution {
        private int binarySearch(int first,int[] sum,int i,int l){
            int left=i+l;
            int right=sum.length-1;
            int maxResult=0;
            while(left<=right){
                int mid=(left+right)/2;
                int second=sum[mid]-sum[i+l-1];
                if(second==first){
                    maxResult = first;
                    break;
                }else if(second>first){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }
            return maxResult;
        }
        public int solution(int[] cookies) {
            int n=cookies.length;
            int[] sum=new int[n+1];
            int result=0;
            for(int i=1;i<=n;i++){
                sum[i]=sum[i-1]+cookies[i-1];
            }

            for(int l=1;l<n;l++){
                for(int i=1;i<=n-l;i++){
                    int first=sum[i+l-1]-sum[i-1];
                    if(result>=first){
                        continue;
                    }
                    result=Math.max(result,binarySearch(first,sum,i,l));
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        leasons49995 main = new leasons49995();
        leasons49995.Solution solution = main.new Solution();
        int[][] testcase ={
                {1, 1, 2, 3}
                ,{1, 1, 1,10, 5, 5}
                ,{1, 2, 4, 5}
        };
        int[] result={3,10,0};
        for (int i = 0; i < result.length; i++) {
            int resultVal = solution.solution(testcase[i]);
            System.out.println(resultVal);
            System.out.println(resultVal == result[i]);
        }

    }
}
