use std::collections::{BTreeMap};
use std::io;
use std::io::BufRead;


fn solve(n:usize,m:usize,k:usize,tiles:Vec<Vec<char>>)->(usize,Vec<Vec<char>>){
    let mut key_vec=vec![vec![' ';k];k];
    let mut value_vec=vec![vec![0;k];k];
    for i in 0..k{
        for j in 0..k{
            let mut map =BTreeMap::new();
            for l in 0..n/k {
                for o in 0..m/k {
                   map.insert(tiles[l*k+i][o*k+j],map.get(&tiles[l*k+i][o*k+j]).unwrap_or(&0)+1);
                }
            }
            //get max value from map
            let mut max=0;
            let mut maxkey=' ';
            for (key,value) in map {
                if value > max {
                    max = value;
                    maxkey = key;
                }
            }
            key_vec[i][j]=maxkey;
            value_vec[i][j]=max;
        }
    }
    let mut result_vac=vec![vec![' ';m];n];
    for i in 0..n/k {
        for j in 0..m/k {
            for l in 0..k {
                for o in 0..k {
                    result_vac[i*k+l][j*k+o]=key_vec[l][o];
                }
            }

        }
    }

    let mut result_value=n*m;
    for i in 0..k {
        for j in 0..k {
            result_value-=value_vec[i][j];
        }
    }
    (result_value,result_vac)
}

fn main() {
    //입력
    let stdin = io::stdin();
    let mut reader = stdin.lock();
    let mut read_line=||{
        let mut buffer=String::new();
        reader.read_line(&mut buffer).unwrap();
        buffer
    };
    let nums: Vec<usize> = read_line().split_whitespace().map(|s| s.parse().unwrap()).collect();
    let n=nums[0];
    let m=nums[1];
    let k=nums[2];
    let tiles:Vec<Vec<char>>=(0..n).map(|_|{
        read_line().trim().chars().collect()
    }).collect();
    let (result,result_vec) = solve(n,m,k,tiles);
    println!("{result}");
    for i in 0..n {
        for j in 0..m {
            print!("{}",result_vec[i][j]);
        }
        println!();
    }
}