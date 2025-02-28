use std::io::{BufReader, Read, stdin};
fn solve(graph:Vec<Vec<usize>>,n:usize,value_vertex:(usize,usize)) -> usize{
    let inf=1_000_000_000;
    //fordwashar?
    let mut dist=vec![vec![inf;n];n];
    for i in 0..n{
        dist[i][i]=0;
        for &(j) in graph[i].iter(){
            dist[i][j]=0;
        }
    }
    dist[value_vertex.0][value_vertex.1]=1;
    dist[value_vertex.1][value_vertex.0]=1;
    for i in 0..n {
        for j in 0..n {
            let v1=dist[j][i];
            for k in 0..n {
                let v2=dist[j][k]+dist[k][i];
                dist[j][i]=v1.min(v2);
            }
        }
    }
    let mut result=0;
    for i in 0..n {
        for j in i+1..n{
            result=result+dist[i][j];
        }
    }
    result
}
fn main() {
    let stdin=stdin();
    let mut reader=BufReader::new(stdin);
    let mut buffer=String::new();
    reader.read_to_string(&mut buffer).unwrap();
    let mut token=buffer.split_whitespace();
    macro_rules! input {
        ($($x:ident),* ; $t:ty) => {
            $(let mut $x= token.next().unwrap().parse::<$t>().unwrap();)*
        };
    }
    input!(n,m,k;usize);
    let mut graph=vec![Vec::new();n];
    k=k-1;//zero index
    let mut value_vertex =(0, 0);
    for i in 0..m{
        input!(a,b;usize);
        if k==i{
            value_vertex =(a-1, b-1);
        }
        graph[a-1].push(b-1);
    }
    let result=solve(graph,n,value_vertex);
    println!("{}",result);
}
