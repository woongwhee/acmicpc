 use std::io::{self, Read};

fn main() {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();
    let lines: Vec<&str> = input.trim().split('\n').collect();
    let t: usize = lines[0].parse().unwrap();
    let mut results = Vec::new();

    for i in 1..=t {
        let n: i32 = lines[i].parse().unwrap();
        let list = find(n);
        results.push(list);
    }

    for list in results {
        for n in list {
            print!("{} ", n);
        }
        println!();
    }
}

fn find(n: i32) -> Vec<i32> {
    let mut result = Vec::new();
    let mut value = 1;
    for i in 0..2*n-1 {
        let (mut row, mut col) = if i < n { (i, 0) } else { (n-1, i-n+1) };
        while row >= 0 && col < n {
            if value == n*row + col + 1 {
                result.push(value);
            }
            value += 1;
            row -= 1;
            col += 1;
        }
    }
    result
}
