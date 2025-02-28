use std::io::{BufRead, BufReader, Read, stdin};
use std::collections::BinaryHeap;
use std::cmp::Reverse;
fn solve(mut intervals: Vec<(usize, usize)>) ->usize{
    intervals.sort_by_key(|&(s,_)|s);
    let mut pq=BinaryHeap::new();
    for &(start,end) in intervals.iter(){
        if let Some(&Reverse(min_end))=pq.peek(){
            if min_end<=start{
                pq.pop();
            }
        }
        pq.push(Reverse(end));
    }
    pq.len()
}
fn main() {

    let stdin=stdin();
    let mut reader=stdin.lock();
    let mut buffer=String::new();
    reader.read_to_string(&mut buffer).unwrap();
    let mut token=buffer.split_whitespace();
    macro_rules! input {
        ($t:ty) => {token.next().unwrap().parse::<$t>().unwrap()};
        ($($x:ident),*;$t:ty)=>{$(let mut $x:$t=token.next().unwrap().parse().unwrap();)*};
        (vec $x:ident;$t:ty;$n:expr)=>{
            let mut $x=Vec::<$t>::from_iter(token.by_ref().take($n).map(|x|x.parse().unwrap()));
        }
    }
    input!(n;usize);
    let mut edges=Vec::with_capacity(n);
    for _ in 0..n {
        edges.push((input!(usize),input!(usize)));
    }
    let result=solve(edges);
    println!("{}",result);
}