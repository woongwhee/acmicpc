use std::cmp::{max, Reverse};
use std::collections::BinaryHeap;
use std::io::{BufReader, Read, stdin};

#[derive(Copy, Clone)]
struct Edge {
    to: usize,
    time: u64,
    cost: usize,
}

impl Edge {
    fn new(to: usize, time: u64, cost: usize) -> Edge {
        Edge { to, time , cost }
    }
}
fn dijkstra(graph: &Vec<Vec<Edge>>, n: usize, limit_time: u64, cost_limit: usize) -> bool {
    const INF: u64 =1000000000000;
    let (start, end,) = (0, n - 1);
    let mut dist = vec![INF; n];
    let mut heap = BinaryHeap::new();
    dist[start] = 0;
    heap.push((Reverse(0), 0));
    while let Some((Reverse(time), vert)) = heap.pop() {
        if time > dist[vert] {
            continue;
        }
        if vert == end {
            return true;
        }
        for e in &graph[vert] {
            let add=if e.cost>cost_limit {e.cost-cost_limit}else{0};
            let next_time = time + e.time+ add as u64;
            if next_time > limit_time {
                continue;
            }
            if dist[e.to] > next_time {
                heap.push((Reverse(next_time), e.to));
                dist[e.to] = next_time;
            }
        }
    }
    false
}

fn solve(graph: Vec<Vec<Edge>>, n: usize, t: u64, max_cost: usize) -> isize {
    let mut left = 0;
    let mut right = max_cost;
    let mut answer = -1;
    while left <= right {
        let mid = (left + right) / 2;
        if dijkstra(&graph, n, t, mid) {
            answer = mid as isize;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    answer
}

fn parse_input(buffer: String) -> (Vec<Vec<Edge>>, usize, usize, usize) {
    let mut token = buffer.split_whitespace();
    macro_rules! input {
            ($t:ty) => {token.next().unwrap().parse::<$t>().unwrap()};
            ($( $ x: ident), *;$ t: ty)=>{$ ( let $ x = token.next().unwrap().parse::<$t>().unwrap(); ) *};
            (vec $ x: ident, $ t: ty, $ n: expr) =>{
            let mut $ x = Vec::< $ t>::from_iter(token.by_ref().take( $ n).map( | x| x.parse().unwrap()));}
    }
    input!(n, m, t;usize);
    let mut graph = vec![Vec::new(); n];
    let mut max_cost = 0;
    for _ in 0..m {
        input!(a, b,  time,cost;usize);
        graph[a - 1].push(Edge::new(b - 1, time as u64, cost));
        graph[b - 1].push(Edge::new(a - 1, time as u64, cost));
        if max_cost < cost {
            max_cost = cost;
        }
    }
    (graph, n, t, max_cost)
}
fn main() {
    let stdin = stdin();
    let mut reader = BufReader::new(stdin.lock());
    let mut buffer = String::new();
    reader.read_to_string(&mut buffer).unwrap();
    let (graph, n, t, max_cost) = parse_input(buffer);
    let result = solve(graph, n, t as u64, max_cost);
    println!("{}", result);
}