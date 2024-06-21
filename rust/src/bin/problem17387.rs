use std::io::{self, BufRead};

struct Line(Point, Point);

struct Point(i32, i32);

impl Line {
    fn new(x1: i32, y1: i32, x2: i32, y2: i32) -> Line {
        Line(Point(x1, y1), Point(x2, y2))
    }
}

//A(x1,y1),B(x2,y2),C(x3,y3) CCW(A,B,C)=ACxBC=(x2-x1)*(y3-y1)-(y2-y1)*(x3-x1)
fn ccw(a: &Point, b: &Point, c: &Point) -> (i32, bool) {
    let ccw = ((b.0 - a.0) * (c.1 - a.1)) - ((b.1 - a.1) * (c.0 - a.0));
    if ccw == 0 {
        (ccw, on_segment(a, b, c))
    } else {
        (ccw, false)
    }
}

fn segments_intersect(l1: &Line, l2: &Line) -> bool {
    let ccw = vec![ccw(&l1.0, &l1.1, &l2.0), ccw(&l1.0, &l1.1, &l2.1), ccw(&l2.0, &l2.1, &l1.0), ccw(&l2.0, &l2.1, &l1.1)];
    let is_intersect = (ccw[0].0 * ccw[1].0 <= 0 && ccw[2].0 * ccw[3].0 <= 0)
        && !(ccw.iter().any(|&c| c.0 == 0));
    is_intersect || ccw.iter().any(|&c| c.1)
}

fn on_segment(p1: &Point, p2: &Point, p: &Point) -> bool {
    p1.0.min(p2.0) <= p.0 && p.0 <= p1.0.max(p2.0) && p1.1.min(p2.1) <= p.1 && p.1 <= p1.1.max(p2.1)
}

fn main() {
    let stdin = io::stdin();
    let reader = stdin.lock();
    let mut lines = reader.lines();

    let nums: Vec<i32> = lines.next().unwrap().unwrap().split_whitespace().map(|s| s.parse().unwrap()).collect();
    let line1 = Line::new(nums[0], nums[1], nums[2], nums[3]);
    let nums: Vec<i32> = lines.next().unwrap().unwrap().split_whitespace().map(|s| s.parse().unwrap()).collect();
    let line2 = Line::new(nums[0], nums[1], nums[2], nums[3]);
    let result = if segments_intersect(&line1, &line2) { 1 } else { 0 };
    println!("{result}");
}