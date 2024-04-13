use std::io::{BufRead, BufReader, Read, stdin};
use std::num::ParseIntError;
use crate::segment_tree::TreeOperation;

fn main() {
    let stdin=stdin();
    let mut stdin=BufReader::new(stdin.lock());
    let mut buffer=String::new();
    stdin.read_line(&mut buffer).unwrap();
    let mut next={
        let mut input=buffer.split_ascii_whitespace().flat_map(str::parse::<usize>);
        move ||input.next().unwrap()
    };
    let (N,M)=(next(),next()+next());
    let mut next=
        || {stdin.read_line(&mut buffer).unwrap();
        buffer.trim().parse::<i64>().unwrap()
    };
    let sequence=(0..N).map(|_| next()).collect();
    stdin.read_to_string(&mut buffer).unwrap();
    let mut input=buffer.lines();
    let mut opers=Vec::new();
    for s in input{
        let mut input_lines =s.split_ascii_whitespace();
        let oper_type= input_lines.next().unwrap().parse::<usize>().unwrap();
        let v1= input_lines.next().unwrap().parse::<usize>().unwrap();
        let o=if oper_type==1 {
            TreeOperation::Print(v1, input_lines.next().unwrap().parse::<usize>().unwrap())
        }else{
            TreeOperation::Change(v1, input_lines.next().unwrap().parse::<i64>().unwrap())
        };
        opers.push(o);
    }
    let result=segment_tree::SegmentTree::solve(sequence,opers);
    let mut result_str=String::new();
    let mut push_value_and_line=|i:&i64|{
        result_str.push_str(&(i.to_string()));
        result_str.push_str("\n");
    };
    result.iter().for_each(|i|push_value_and_line(i));
    println!("{result_str}");
}
mod segment_tree{
    pub struct SegmentTree{
        sum_segment:Vec<i128>,
        size: usize,
        height:u32,
        len:usize
    }
    pub enum TreeOperation {
        Change(usize,i64),
        Print(usize,usize),
    }
    impl TreeOperation {
        fn new_change(t:usize,u1:usize,i1:i64)-> TreeOperation {
            TreeOperation::Change(u1, i1)
        }
        fn new_print(t:usize,u1:usize,u2:usize)-> TreeOperation {
            TreeOperation::Print(u1, u2)
        }
    }
    impl SegmentTree{
        fn new(sequence:Vec<i64>) ->SegmentTree{
            let n = sequence.len();
            let height = (n as f64).log2().ceil() as u32;
            let size = 2 * 2_usize.pow(height) - 1; // 전체 트리 크기 계산
            let mut sum_segment=vec![0;size];
            for (i, &val) in sequence.iter().enumerate() {
                sum_segment[n-1+i] = val as i128;
            }
            for i in (0..n-1).rev() {
                sum_segment[i] =sum_segment[i*2+1]+sum_segment[i*2+2];
            }
            SegmentTree{
                sum_segment,size,height,len:n
            }
        }

        pub fn solve(sequence:Vec<i64>, oper_list:Vec<TreeOperation>) ->Vec<i64>{
            let mut tree=SegmentTree::new(sequence);
            let mut result=Vec::new();
            for oper in oper_list{
                match oper {
                    TreeOperation::Change(index, value)=>{
                        tree.change_value(index,value)
                    }
                    TreeOperation::Print(start, end)=>{
                        result.push(tree.get_sum(start,end));
                    }
                }
            }
            result
        }
        fn get_sum(&self,start:usize,end:usize)->i64{
            self.query(0,0,self.len-1,start,end)as i64
        }
        /*
            node: 현재노드
            left: 현재노드가 담당하고있는 구간의 왼쪽 끝,(수열기준)
            right: 현재노드가 담당하고있는 구간합의 오른쪽 끝,(수열기준)
            start,end: 원하는 구간합의 시작과 끝
         */
        fn query(&self, node: usize, left: usize, right: usize, start: usize, end: usize) -> i128 {
            if end < left || right < start { // 쿼리 범위 밖
                return 0;
            }
            if start <= left && right <= end { // 쿼리 범위 안
                return self.sum_segment[node];
            }
            // 부분적으로 겹치는 경우
            let mid = (left + right) / 2;
            let p1 = self.query(node*2+1, left, mid, start, end); // 왼쪽 자식
            let p2 = self.query(node*2+2, mid+1, right, start, end); // 오른쪽 자식
            p1 + p2 // 결과 합치기
        }
        fn change_value(&mut self,index:usize,value:i64){
            let mut idx=self.len-1+index;
            self.sum_segment[self.len-1+index]=value as i128;
            while idx>0{
                idx=(idx-1)/2;
                self.sum_segment[idx] = self.sum_segment[2 * idx + 1] + self.sum_segment[2 * idx + 2];
            }
        }

    }

}