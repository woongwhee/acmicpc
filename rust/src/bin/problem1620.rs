use std::io::{BufReader, Read, stdin};

fn main(){
    let stdin=stdin();
    let mut reader=BufReader::new(stdin.lock());
    let mut buffer=String::new();
    reader.read_to_string(&mut buffer).unwrap();
    let mut token=buffer.split_whitespace();
    macro_rules! input{
        ($($x:ident),*;$t:ty)=>{
            $(let mut $x=token.next().unwrap().parse::<$t>().unwrap();)*
        };
        (vec $x:ident,$t:ty,$n:expr)=>{
            let mut $x=Vec::<$t>::from_iter(token.by_ref().take($n).map(|x|x.parse().unwrap()));
        };
    }
    input!(n,m;usize);
    input!(vec dogam,String,n);





}