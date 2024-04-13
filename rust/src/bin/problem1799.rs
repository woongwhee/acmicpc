use std::{
    io::stdin,
    io::BufReader,
};
use std::io::Read;

fn main() {
    let stdin = stdin();
    let mut stdin = BufReader::new(stdin.lock());
    let mut buffer = String::new();
    stdin.read_to_string(&mut buffer).unwrap();
    let mut next = {
        let mut input = buffer.split_ascii_whitespace().flat_map(str::parse::<usize>);
        move || input.next().unwrap()
    };
    let n = next();
    let mut board = vec![vec![0; n]; n];
    for i in 0..n {
        for j in 0..n {
            board[i][j] = next();
        }
    }
    let result = bishop::Chess::solve(board);
    println!("{result}")
}

pub mod bishop {
    pub struct Chess {
        board: [[bool; 10]; 10],
        left: [bool; 20],
        right: [bool; 20],
        size: usize,
        answer: [usize; 2],
    }

    impl Chess {
        fn new(b: Vec<Vec<usize>>, n: usize) -> Chess {
            let mut board = [[false; 10]; 10];
            for i in 0..n {
                for j in 0..n {
                    if b[i][j] == 1 {
                        board[i][j] = true;
                    }
                }
            }
            Chess {
                board,
                left: [false; 20],
                right: [false; 20],
                size: n,
                answer: [0; 2],
            }
        }
        pub fn solve(board: Vec<Vec<usize>>) -> usize {
            let n = board.len();
            let mut chess = Self::new(board, n);
            chess.back_tracking(0, 0, 0, 0);
            chess.back_tracking(0, 1, 1, 0);
            chess.answer[0] + chess.answer[1]
        }

        fn back_tracking(&mut self, row: usize, colum: usize, color: usize, value: usize) {
            let (row, colum) = if colum < self.size { (row, colum) } else if colum % 2 == 0 { (row + 1, 1) } else { (row + 1, 0) };
            if row >= self.size{
                self.answer[color] = self.answer[color].max(value);
                return;
            }
            let left_group = self.size +  colum- row - 1;
            let right_group = colum + row;
            if self.board[row][colum]
                && !self.left[left_group]
                && !self.right[right_group] {
                self.left[left_group] = true;
                self.right[right_group] = true;
                self.back_tracking(row, colum+2, color, value + 1);
                self.left[left_group] = false;
                self.right[right_group] = false;
            }
            self.back_tracking(row, colum+2, color, value );

        }
    }
}