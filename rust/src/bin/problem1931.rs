use std::io::{BufReader, Read, stdin};

fn main() {
    let stdin = stdin();
    let mut reader = BufReader::new(stdin);
    let mut buffer = String::new();
    reader.read_to_string(&mut buffer).unwrap();
    let mut token = buffer.split_whitespace();
    macro_rules! input {
        ($($x:ident),* ; $t:ty)=>{
            $(let $x:$t= token.next().unwrap().parse().unwrap();)*
        }
    }
    input!(n;usize);
    let mut meetings = Vec::with_capacity(n);
    for _ in 0..n {
        input!(start,end;usize);
        meetings.push((start, end));
    }
    meetings.sort_by(|a, b| {
        if a.1 != b.1 { a.1.cmp(&b.1) }
        else {a.0.cmp(&b.0)}
    });
    let mut last_end = 0;
    let mut result = 0;
    for (start, end) in meetings {
        if start >= last_end {
            last_end = end;
            result += 1;
        }
    }
    println!("{}", result);
}