    package net.acmicpc.bfs;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.LinkedList;
    import java.util.Queue;
    import java.util.StringTokenizer;

    public class problem16236 {


        final static int[]DX={1,0,-1,0};
        final static int[]DY={0,1,0,-1};
        static int[][]map;
        static int length;
        public static void main(String[] args) throws IOException {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in)) ;
            int N=Integer.parseInt(br.readLine());
            map=new int[N+1][N+1];
            Point babyShark=new Point(-1,-1);
            for (int i = 1; i <=N; i++) {
                StringTokenizer st=new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    map[i][j]=Integer.parseInt(st.nextToken());
                    if(map[i][j]==9){
                        map[i][j]=0;
                        babyShark=new Point(i,j);
                    }

                }
            }
            length=0;
            EatFish(babyShark,2,0);
            System.out.println(length);

        }
        static void EatFish(Point Shark,int sharkSize,int EXP){
            Point target=scanFish(Shark,sharkSize);
            if (target==null){
                return;
            }
            map[target.x][target.y]=0;
            EXP++;
            if(sharkSize==EXP){
                sharkSize++;
                EXP=0;
            }
            EatFish(target,sharkSize,EXP);
        }
        static void initCheck(int[][] check){
            for (int i = 1; i < check.length; i++) {
                for (int j = 1; j < check[i].length; j++) {
                    check[i][j]=-1;
                }
            }
        }
        static Point scanFish(Point babyShark,int sharkSize){
            //이동거리
            int[][] check=new int[map.length][map.length];
            initCheck(check);
            check[babyShark.x][babyShark.y]=0;
            //이동거리
            Queue<Point> queue= new LinkedList<>();
            queue.add(babyShark);
            Point nearFish=null;
            while (!queue.isEmpty()){
                Point p=queue.poll();
                int x=p.x;
                int y=p.y;
                for (int i=0;i<4;i++){
                    int nextX=x+DX[i];
                    int nextY=y+DY[i];
                    if(nextX<0||nextY<0||nextX>=map.length||nextY>=map.length){
                        continue;
                    }
                    if(check[nextX][nextY]!=-1||map[nextX][nextY]>sharkSize){
                        continue;
                    }
                    check[nextX][nextY]=check[x][y]+1;
                    if(map[nextX][nextY]!=0&&sharkSize>map[nextX][nextY]){
                        if(nearFish==null){
                            nearFish=new Point(nextX,nextY);;//처음 만난 물고기로 초기화
                        }else if(check[nextX][nextY]<check[nearFish.x][nearFish.y]){
                            nearFish=new Point(nextX,nextY);
                        } else if(check[nextX][nextY]   ==check[nearFish.x][nearFish.y]){//처음만난 물고기와 거리가 같으면?
                            int x2=nearFish.x;
                            int y2=nearFish.y;
                            if(nextX<x2){
                                nearFish=new Point(nextX,nextY);;
                            }else if(nextX==x2){
                                if(nextY<y2){
                                    nearFish=new Point(nextX,nextY);;
                                }
                            }
                        }
                    }
                    queue.add(new Point(nextX,nextY));
                    }
                }

            if(nearFish==null){
                return null;
            }else{
            length+=check[nearFish.x][nearFish.y];
            return nearFish;
            }
        }

        static class Point{
            int x;
            int y;
            Point(int x,int y){
                this.x=x;
                this.y=y;
            }

        }
    }
