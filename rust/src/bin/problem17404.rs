use std::cmp:: min;
use std::io::{BufReader, Read, stdin};

fn solve(values:Vec<(usize,usize,usize)>,n:usize)->usize{
    let inf:usize=10000001;
    let mut min_cost=inf;
    for first_color in 0..3 {
        let mut dp=vec![(inf,inf,inf);n];
        dp[0] = match first_color {
            0 => (values[0].0, inf, inf),
            1 => (inf, values[0].1, inf),
            2 => (inf, inf, values[0].2),
            _ => unreachable!(),
        };
        for i in 1..n{
            dp[i].0=values[i].0+min(dp[i-1].1,dp[i-1].2);
            dp[i].1=values[i].1+min(dp[i-1].0,dp[i-1].2);
            dp[i].2=values[i].2+min(dp[i-1].0,dp[i-1].1);
        }
        if first_color!=0{
            min_cost=min(min_cost,dp[n-1].0);
        }
        if first_color!=1{
            min_cost=min(min_cost,dp[n-1].1);
        }
        if first_color!=2{
            min_cost=min(min_cost,dp[n-1].2);
        }
    }
    min_cost
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
    let n=next();
    let mut values=Vec::new();
    for i in 0..n {
        values.push((next(),next(),next()));
    }
    let result= solve(values,n);
    println!("{result}");
}