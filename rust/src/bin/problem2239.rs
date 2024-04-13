use std::fmt::Error;
use std::io;
use std::io::{BufReader, BufWriter, Read, Write};
use std::mem::forget;
struct Sudoku{
    arr:[[usize;9];9],
    blank_vec:Vec<(usize,usize)>,
}

impl Sudoku{

    fn new(arr:[[usize;9];9])->Sudoku{


        Sudoku{arr,blank_vec:Vec::new()}
    }

    fn calculate(&mut self) ->[[usize;9];9] {
        for i in 0..9 {
            for j in 0..9 {
                if(self.arr[i][j]==0){
                    self.blank_vec.push((i,j));
                }
            }
        }
        self.blank_vec.reverse();
        self.backtracking();
        self.arr.clone()
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
}
fn solve(mut arr:[[usize;9];9]) ->String{
    let mut sudoku=Sudoku::new(arr);
    let result_arr=sudoku.calculate();
    let mut result=String::new();
    for i in 0.. 9{
        for j in 0..9 {
            result.push(char::from_digit(result_arr[i][j] as u32, 10).expect("not digit"));
        }
        result.push_str("\n");
    }
    result

}




fn parse(inputs:&str)->[[usize;9];9]{
    let mut arr=[[0;9];9];
    for (i,line) in inputs.trim().lines().enumerate(){
        for (j,c) in line.chars().enumerate(){
            arr[i][j]=c.to_digit(10).expect("non digit") as usize;
        }
    }
    arr
}
fn main() {
    let stdin=io::stdin();
    // let mut stdin=BufReader::new(stdin.lock());
    let mut buffer=String::new();
    // let _=stdin.read_to_string(&mut buffer);
    for _ in 0..9 {
        let mut line = String::new();
        stdin.read_line(&mut line).expect("Failed to read line");
        buffer.push_str(&line);
    }

    let test_case = parse(&buffer);
    let bind = solve(test_case);
    let result=bind.trim();
    let stdout = io::stdout();
    let mut stdout = BufWriter::new(stdout.lock());
    let _=writeln!(stdout,"{result}");
}
