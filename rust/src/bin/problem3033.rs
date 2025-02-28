use std::io::{BufRead, Read};

fn solve(n:usize,input_string:String)->usize{
    let mut hashes=vec![Vec::new();n];
    let chars:Vec<char>=input_string.chars().collect();
    for i in 0..n {
        hashes[i].push(chars[i] as usize);
        println!("{:?}",hashes[i])
    }


    0
}

fn main(){
    let stdin=std::io::stdin();
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
    let mut input_string=String::new();
    input!(input_string;String);
    solve(n,input_string);



}