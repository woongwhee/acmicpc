use std::cmp::Reverse;
use std::collections::BinaryHeap;
use std::io::{BufReader, Read, stdin};



    fn solve(querys:Vec<(usize,usize)>,n:usize)->Vec<usize>{
        let mut result =Vec::new();
        let mut ref_count: Vec<usize>=vec![0;n];
        let mut rear_list: Vec<Vec<usize>>=(0..n).map(|_|Vec::new()).collect();
        for (front,rear) in querys{
            ref_count[rear]+=1;
            rear_list[front].push(rear);
        }
        let mut heap:BinaryHeap<Reverse<usize>>=BinaryHeap::new();
        for i in 0..n {
            if ref_count[i]==0{
                heap.push(Reverse(i));
            }
        }
        while let Some(Reverse(cur))=heap.pop(){
            result.push(cur+1);
            for rear in &rear_list[cur]{
                ref_count[*rear]-=1;
                if ref_count[*rear]==0{
                    heap.push(Reverse(*rear));
                }
            }
        }
        result
    }



fn main(){
    let stdin = stdin();
    let mut buffer=String::new();
    let mut stdin=BufReader::new(stdin.lock());
    stdin.read_to_string(&mut buffer).unwrap();
    let mut next={
        let mut input=buffer.split_ascii_whitespace().flat_map(str::parse::<usize>);
        move | | input.next().unwrap()
    };
    let (n,m)=(next(),next());
    let mut querys:Vec<(usize,usize)>=Vec::new();
    for _ in 0..m{
        querys.push((next()-1,next()-1));
    }
    let result = solve(querys, n);
    let result_str=result.iter().map(|&num|num.to_string()).collect::<Vec<String>>().join(" ");
    println!("{result_str}");
}