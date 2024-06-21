 use std::io::{BufRead, stdin};

 fn make_index(seq:&[usize])->Vec<Vec<usize>>{
    let mut index=vec![Vec::new();101];
    for i in 1..seq.len() {
        index[seq[i]].push(i);
    }
    for i in 1..101 {
        index[i].reverse();
    }
    index
}
 fn filter(seq1:&[usize],seq2:&[usize])->(Vec<usize>,Vec<usize>){
     let mut seq_a = Vec::new();
     let mut seq_b = Vec::new();
     seq_a.push(0);
     seq_b.push(0);
     for i in 0..seq1.len() {
         for j in 0..seq2.len() {
             if seq1[i] == seq2[j] {
                 seq_a.push(seq1[i]);
                 break;
             }
         }
     }
     for i in 0..seq2.len() {
         for j in 0..seq1.len() {
             if seq2[i] == seq1[j] {
                 seq_b.push(seq2[i]);
                 break;
             }
         }
     }
     (seq_a,seq_b)
 }
fn solve(seq1: Vec<usize>, seq2: Vec<usize>) -> Vec<usize> {
    let (seq_a,seq_b)=filter(&seq1,&seq2);
    let mut ans = Vec::new();
    let mut index_a = make_index(&seq_a);
    let mut index_b = make_index(&seq_b);
    let mut cur_a =101;
    let mut cur_b =101;
    for i in (1..100).rev() {
        while !index_a[i].is_empty() && index_a[i].last().unwrap() <= &(cur_a) {
            index_a[i].pop();
        }
        while !index_b[i].is_empty() && index_b[i].last().unwrap() <= &(cur_b) {
            index_b[i].pop();
        }
        while !index_a[i].is_empty() && !index_b[i].is_empty() {
            cur_a = index_a[i].pop().unwrap();
            cur_b = index_b[i].pop().unwrap();
            ans.push(i);
        }
    }
    ans
}
    fn main() {
        let stdin = stdin();
        let mut reader = stdin.lock();

        let mut read_line=||{
            let mut buffer=String::new();
            reader.read_line(&mut buffer).unwrap();
            buffer
        };
        let n:usize = read_line().trim().parse().unwrap();
        let seq1: Vec<usize> = read_line().trim().split_whitespace().map(|e| e.parse().unwrap()).collect();
        let m:usize = read_line().trim().parse().unwrap();
        let seq2: Vec<usize> = read_line().trim().split_whitespace().map(|e| e.parse().unwrap()).collect();;
        let result = solve(seq1, seq2);
        println!("{}", result.len());
        if !result.is_empty() {
            for num in result {
                print!("{} ", num);
            }
        }


    }