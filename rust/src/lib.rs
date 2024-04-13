pub mod prim{
    use std::cmp::{Ordering, Reverse};
    use std::collections::BinaryHeap;
    #[derive(Clone,Eq,PartialEq)]
    struct Edge{
        to:usize,
        value:usize
    }


    impl PartialOrd for Edge {
        fn partial_cmp(&self, other: &Self) -> Option<Ordering>{
            Some(self.value.cmp(&other.value))
        }
    }

    impl Ord for Edge{
        fn cmp(&self, other: &Self) -> Ordering {
            self.value.cmp(&other.value)
        }
    }
    pub fn solve(n:usize,edges:Vec<(usize,usize,usize)>) ->usize{
        //그래프 초기화
        let graph = make_graph(n, edges);
        //프림알고리즘
        let mut heap = BinaryHeap::new();
        let mut visited=vec![false;n+1];
        let mut total_value=0;
        let mut max_value=0;
        heap.push(Reverse(Edge{to:1,value:0}));
        loop {
            if let Some(Reverse(cur))=heap.pop(){
                let (to,value)=(cur.to,cur.value);
                if visited[to]{continue;}
                visited[to]=true;
                total_value+=value;
                max_value=max_value.max(value);
                for e in &graph[to]{
                    if !visited[e.to]{
                        heap.push(Reverse(e.clone()));
                    }
                }
            }else{
                break;
            }
        }
        total_value-max_value
    }

    fn make_graph(n: usize, edges: Vec<(usize, usize, usize)>)->Vec<Vec<Edge>> {
        let mut graph:Vec<Vec<Edge>> = (0..=n).map(|_| Vec::new()).collect();
        for (a, b, value) in edges {
            graph[a].push(Edge { to: b, value });
            graph[b].push(Edge { to: a, value });
        }
        graph
    }
}

pub mod kruskal{
    use std::cmp::Ordering;

    struct UnionFind{
        parent:Vec<usize>,
        size:Vec<usize>,
    }
    impl UnionFind{
        fn new(n:usize)->Self{
            Self{
                parent:(0..=n).collect(),
                size:vec![1;n+1]
            }
        }
        fn union(&mut self,x:usize,y:usize){
            let root_x=self.find(x);
            let root_y=self.find(y);
            match self.size[root_x].cmp(&self.size[root_y]){
                Ordering::Less => {
                    self.size[root_y]+=self.size[root_x];
                    self.parent[root_x]=root_y;
                }
                _=> {self.size[root_x]+=self.size[root_y];
                    self.parent[root_y]=root_x;
                }
            }
        }
        fn find(&mut self,x:usize)->usize{
            if self.parent[x]==x {
                return x
            }
            self.parent[x]=self.find(self.parent[x]);
            self.parent[x]
        }

    }
    pub fn solve( edges:Vec<(usize,usize,usize)>,n:usize)->usize{
        let mut uf=UnionFind::new(n);
        let mut edges = edges.clone();
        edges.sort_unstable_by_key(|v| v.2);
        let last=n-2;
        let mut sum=0;
        let mut cnt=0;
        for  (x,y,value) in edges{
            if cnt==last{
                break;
            }
            if uf.find(x)==uf.find(y){
                continue;
            }
            uf.union(x,y);
            sum+=value;
            cnt+=1;
        }

        sum
    }
}



pub mod bishop{
    pub struct Chess{
        board:[[bool;10];10],
        left:[bool;20],
        right:[bool;20],
        size:usize,
        answer:[usize;2],
    }
    impl Chess{
        fn new(b:Vec<Vec<usize>>,n:usize)->Chess{
            let mut board=[[false;10];10];
            for i in 0..n {
                for j in 0..n {
                    if b[i][j]==1{
                        board[i][j]=true;
                    }
                }
            }
            Chess{
                board,
                left:[false;20],
                right:[false;20],
                size:n,
                answer:[0;2],
            }
        }
        pub fn solve(board:Vec<Vec<usize>>)->usize{
            let n=board.len();
            let mut chess=Chess::new(board,n);
            chess.back_tracking(0,0,0,0);
            chess.back_tracking(0,1,1,0);
            chess.answer[0]+chess.answer[1]
        }

        fn back_tracking(&mut self,row:usize, colum:usize, color:usize, value:usize){
            if row>=self.size{
                self.answer[color]=self.answer[color].max(value);
                return;
            }
            let left_group=(self.size+colum)-row-1;
            let right_group=colum+row;
            let next_row=row+1;
            let next_colum=if colum+2>=self.size {colum+2}else if colum%2==0{ 1 }else{0};
            if self.board[row][colum]
                &&!self.left[left_group]
                &&!self.right[right_group]{
                self.left[left_group]=true;
                self.right[right_group]=true;
                self.back_tracking(next_row,next_colum,color,value+1);
                self.left[left_group]=false;
                self.right[right_group]=false;
            }
            self.back_tracking(next_row,next_colum,color,value);

        }
    }

}