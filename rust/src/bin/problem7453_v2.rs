use std::io::{Read, stdin};
//two point 알고리즘을 이용해 다시 풀어보기
fn solve(arrays:Vec<Vec<i32>>)->usize{
    let (mut v,mut w)=(vec![],vec![]);
    for i in 0..arrays.len(){
        for j in 0..arrays.len(){
            v.push(arrays[i][0]+arrays[j][1]);
            w.push(arrays[i][2]+arrays[j][3]);
        }
    }
    v.sort_unstable();
    w.sort_unstable();
    let (mut i,mut j)=(0,w.len() as i32-1);
    let mut zero_count=0;
    while i<v.len() as i32&&j>=0 {
        let (mut x,mut y)=(i as usize,j as usize);
        if v[x]+w[y]==0{
            let (mut count_v,mut count_w)=(1,1);
            while x+1<v.len()&&v[x]==v[x+1]{
                count_v+=1;
                x+=1;
            }
            while y>0&&w[y]==w[y-1]{
                count_w+=1;
                y-=1;
            }
            zero_count=zero_count+count_v*count_w;
        }else if v[x]+w[y]<0{
            i+=1;
        }else{
            j-=1;
        }
    }
    zero_count
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
    let mut arrays=vec![Vec::new();4];

    for _ in 0..n {
        for j in 0..4 {
            input!(m;i32);
            arrays[j].push(m);
        }
    }
    let result=solve(arrays);
    println!("{}",result);


}