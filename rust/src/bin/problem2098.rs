use std::io::{BufReader, Read, stdin};
use std::collections::VecDeque;
use std::future::Future;

fn solve(graph:Vec<Vec<usize>>,n:usize)->usize{
    let INF=100_000_000usize;
    let full_mask=(1<<n)-1;
    let mut dp=vec![vec![INF; n]; full_mask + 1];
    //0에서 출발해 0으로 돌아오는 코드
    let mut queue=VecDeque::new();
    for i in 1..n {
        if(graph[0][i]!=0){
            dp[1<<i|1][i]=graph[0][i];
            queue.push_back(1<<i|1);
        }
    }
   while let Some(bit)=queue.pop_front(){
       for j in 0..n {
                if bit&(1<<j)!=0{
                    continue;
                }
                let next_bit=bit|(1<<j);
                let mut minvalue=INF;
                for k in 0..n{
                    if dp[bit][k]!=INF&&graph[k][j]>0 {
                        minvalue=minvalue.min(dp[bit][k]+graph[k][j]);
                    }
                }
                if dp[next_bit][j]>minvalue{
                    dp[next_bit][j]=minvalue;
                    queue.push_back(next_bit);
                }
            }
        }
    let mut result=INF;
     for i in 1..n{
         if(dp[full_mask][i]!=INF&&graph[i][0]>0){
             result=result.min(dp[full_mask][i]+graph[i][0]);
         }
     }
    result
}
fn main() {
    let stdin = stdin();
    let mut stdin = BufReader::new(stdin.lock());
    let mut buffer = String::new();
    stdin.read_to_string(&mut buffer).unwrap();
    let mut next = {
        let mut input = buffer.split_ascii_whitespace().flat_map(str::parse::<usize>);
        move || input.next().unwrap()
    };
    let n =next();
    let graph:Vec<Vec<usize>>=(0..n).map(|_|(0..n).map(|_| next()).collect::<Vec<usize>>()).collect();
    let result=solve(graph,n);
    println!("{result}");
}