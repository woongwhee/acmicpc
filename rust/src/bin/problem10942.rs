use std::io::{BufReader, Read, stdin};

fn solve(sequence: Vec<usize>, test_case: Vec<(usize, usize)>) ->String{
    let dp=dynamic_programing(sequence);
    let mut result=String::new();
    test_case.iter().for_each(|(s,e)| result.push_str(if dp[*s-1][*e-1]{"1\n"}else{"0\n"}));
    result
}
fn dynamic_programing(sequence: Vec<usize>)->Vec<Vec<bool>> {
    let mut dp = vec![vec![false; sequence.len()]; sequence.len()];
    for lengths in 0..sequence.len() {
        for j in 0..sequence.len() - lengths {
            if sequence[j] == sequence[j + lengths] {
                if lengths < 3 {
                    dp[j][j + lengths] = true;
                } else if dp[j + 1][j + lengths - 1]{
                    dp[j][j + lengths] = true;
                }
            }
        }
    }
    dp
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
    let n = next();
    let sequence: Vec<usize> = (0..n).map(|_| next()).collect();
    let m = next();
    let test_case: Vec<(usize, usize)> = (0..m).map(|_| (next(), next())).collect();
    // let test_case: Vec<(usize, usize)> = vec![(1,3),(2,5),(3,3),(5,7)];
    // let sequence: Vec<usize> = vec![1, 2, 1, 3, 1, 2, 1];
    let result=solve(sequence,test_case);
    println!("{result}");


}
