use std::io::{self, BufRead};
struct Move{
    step:usize,
    x:usize,
    y:usize,
    num:usize,
}
fn solve(moves:Vec<Move>)->usize{




}




fn main() {
    let stdin = std::io::stdin();
    let mut reader = stdin.lock();
    let mut buffer = String::new();
    reader.read_line(&mut buffer).unwrap();
    let mut token = buffer.split_whitespace();
    macro_rules! input {
        ($t:ty) => {token.next().unwrap().parse::<$t>().unwrap()};
        ($($x:ident),*;$t:ty)=>{$(let mut $x:$t=token.next().unwrap().parse().unwrap();)*};
        (vec $x:ident;$t:ty;$n:expr)=>{
            let mut $x=Vec::<$t>::from_iter(token.by_ref().take($n).map(|x|x.parse().unwrap()));
        }
    }
    let mut moves:Vec<Move> = Vec::new();
    for i in 0..9 {
        let cur=Move{x:input!(usize),y:input!(usize),num:input!(usize),step:i};
        moves.push(cur);
    }
    let result=solve(moves);
    println!("{}", result);
}