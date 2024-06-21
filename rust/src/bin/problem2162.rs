use std::io::{BufReader, self, BufRead};
use std::thread::current;


struct Line(Point, Point);

struct Point(i32, i32);

impl Line {
    fn new(x1: i32, y1: i32, x2: i32, y2: i32) -> Line {
        Line(Point(x1, y1), Point(x2, y2))
    }
}

//A(x1,y1),B(x2,y2),C(x3,y3) CCW(A,B,C)=ACxBC=(x2-x1)*(y3-y1)-(y2-y1)*(x3-x1)
fn ccw(a: &Point, b: &Point, c: &Point) -> i32 {
    ((b.0 - a.0) * (c.1 - a.1)) - ((b.1 - a.1) * (c.0 - a.0))
}

fn segments_intersect(l1: &Line, l2: &Line) -> bool {
    let ccw = vec![ccw(&l1.0, &l1.1, &l2.0), ccw(&l1.0, &l1.1, &l2.1), ccw(&l2.0, &l2.1, &l1.0), ccw(&l2.0, &l2.1, &l1.1)];
    if ccw[0] * ccw[1] <= 0 && ccw[2] * ccw[3] <= 0 {
        true
    } else if ccw.iter().any(|&c|c==0){
        true
    } else {
        false
    }
}

fn bfs(current: usize, groups: &mut Vec<usize>, group_num: usize, graph: &Vec<Vec<usize>>) -> usize {
    let mut size = 1;
    groups[current] = group_num;
    for i in graph[current].iter() {
        if groups[*i] == 0 {
            size += bfs(*i, groups, group_num, graph);
        }
    }

    size
}

fn solve(lines: Vec<Line>) -> (usize, usize) {
    let mut graph: Vec<Vec<usize>> = (0..lines.len()).map(|_| Vec::new()).collect();
    for i in 0..lines.len() {
        for j in i+1..lines.len() {
            if segments_intersect(&lines[i], &lines[j]) {
                graph[i].push(j);
                graph[j].push(i);
            }
        }
    }

    let mut groups: Vec<usize> = vec![0; lines.len()];
    let mut group_num = 0;
    let mut max_size = 0;
    for i in 0..lines.len() {
        if groups[i] == 0 {
            group_num += 1;
            max_size = bfs(i, &mut groups, group_num, &graph).max(max_size);
        }
    }
    (group_num, max_size)
}

fn main() {
    let stdin = io::stdin();
    let reader = stdin.lock();
    let mut lines = reader.lines();
    let n = lines.next().unwrap().unwrap().trim().parse::<usize>().unwrap();
    let mut points: Vec<Line> = Vec::new();
    for _ in 0..n {
        if let Some(Ok(line)) = lines.next() {
            let nums: Vec<i32> = line.split_whitespace().map(|s| s.parse().unwrap()).collect();
            if nums.len() == 4 {
                let line = Line::new(nums[0], nums[1], nums[2], nums[3]);
                points.push(line);
            }
        }
    }
    let (group_length, max_size) = solve(points);
    println!("{}\n{}", group_length, max_size);
}