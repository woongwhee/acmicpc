use std::collections::HashSet;
use std::fs::canonicalize;
use std::hash::{Hash, Hasher};
#[derive(Eq, PartialEq,Hash,Clone)]
struct Sudoku{
    arr:[[usize;9];9],
    blank_vec:Vec<(usize,usize)>,
}

impl Sudoku{
    fn new(arr:[[usize;9];9])->Sudoku{
        Sudoku{arr,blank_vec:Vec::new()}
    }
    fn calculate(&mut self) ->(bool,Vec<(usize,usize)> ){
        for i in 0..9 {
            for j in 0..9 {
                if(self.arr[i][j]==0){
                    self.blank_vec.push((i,j));
                }
            }
        }
        let b=self.blank_vec.clone();
        self.blank_vec.reverse();
        (self.backtracking(),b)
    }
    fn backtracking(&mut self) ->bool{
        let s=self.blank_vec.pop();
        let(next_x,next_y)=if let Some(point)=s {point}else{return true};
        for num in 1..10 {
            if self.can_put_num(next_x,next_y,num){
                self.arr[next_x][next_y]= num;
                if self.backtracking(){
                    return true
                }
            }
        }
        self.blank_vec.push((next_x,next_y));
        self.arr[next_x][next_y]=0;
        return false;
    }
    fn is_solved(&self) ->bool{
        for i in 0..9{
            for j in 0..9 {
                if self.arr[i][j]==0 {return false;}
            }
        }
        true
    }
    fn can_put_num(&self,x:usize,y:usize,num:usize)->bool{
        let (x_,y_)=Self::get_block_prefix(x,y);
        for i in 0..9 {
            if self.arr[x][i]==num|| self.arr[i][y]==num||self.arr[x_+(i/3)][y_+(i%3)]==num{
                return false;
            }
        }
        true
    }
    fn get_block_prefix(x:usize,y:usize)->(usize,usize){
        (x-x%3,y-y%3)
    }


    fn check_clear_sudoku()->usize{
        let mut start_set=HashSet::new();
        start_set.insert(Sudoku::new([[0;9];9]));
        let step=0;
        Sudoku::check_step(step,start_set)
    }
    //sudoku의 규칙이 어겨지지 않는 모든 경우의 수를 확인한뒤 모든 경우의 수에 정답이 존재하는지 확인
    //현재 단계의 모든경우의 수에 대해 해결가능한 경우의수가 존재하는지 확인
    //만약 해결가능한 경우의 수가 없다면 그 step을 반환
    fn check_step(step:usize,before:HashSet<Sudoku>)->usize{
        let mut next=HashSet::new();
        for mut sudoku in before {
            let mut clone=sudoku.clone();
            let (is_solve,blank_vec)=clone.calculate();
            if !is_solve{
                println!("step {} can't clear all",step+1);
                return step
            }
            for blank in blank_vec {
                for num in 1..10{
                    let can_put_num=sudoku.can_put_num(blank.0, blank.1, num);
                    if can_put_num {
                        let mut next_sudoku=sudoku.clone();
                        next_sudoku.arr[blank.0][blank.1]=num;
                        next_sudoku.blank_vec.retain(|&(x, y)| (x, y) != blank);
                        next.insert(next_sudoku);
                    }
                }
            }
        }
        println!("step {} clear",step+1);
        if step==81{
            return step
        }
        Sudoku::check_step(step+1,next)
    }
}
//sudoku의 규칙이 어겨지지 않는 모든 경우의 수를 확인한뒤 모든 경우의 수에 정답이 존재하는지 확인
fn main() {
    Sudoku::check_clear_sudoku();
}