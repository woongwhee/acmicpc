use std::collections::HashMap;
use std::io::{Read, stdin};

fn solve(arrays:Vec<Vec<i32>>)->usize{
    let mut zero_count=0;
    let mut forward_sum:HashMap<i32,usize> =HashMap::new();
    for i in 0..arrays.len(){
        for j in 0..arrays.len(){
            let sum=arrays[i][0]+arrays[j][1];
            forward_sum.insert(sum,forward_sum.get(&sum).unwrap_or(&0)+1);
        }
    }
    for i in 0..arrays.len(){
        for j in 0..arrays.len(){
            let sum=arrays[i][2]+arrays[j][3];
            if forward_sum.contains_key(&-sum){
                zero_count=zero_count+forward_sum.get(&-sum).unwrap();
            }
        }
    }
    zero_count
}
fn main(){
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
    let mut arrays=Vec::new();
    for _ in 0..n {
        input!(vec a;i32;4);
        arrays.push(a);
    }
    let result=solve(arrays);
    println!("{}",result);
    
}