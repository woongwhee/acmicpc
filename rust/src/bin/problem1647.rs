
use std::{
    io::stdin,
    io::BufReader,
    io::Read
};
fn main() {
    let stdin=stdin();
    let mut stdin=BufReader::new(stdin.lock());
    let mut buffer=String::new();
    stdin.read_to_string(&mut buffer).unwrap();
    let mut next={
        let mut input=buffer.split_ascii_whitespace().flat_map(str::parse::<usize>);
        move || input.next().unwrap()
    };
    let (n,m)=(next(),next());
    let edges=(0..m).map(|_|(next(),next(),next())).collect();
    let result=kruskal::solve(edges,n);
    println!("{}",result);
}


mod kruskal{
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
            if(self.parent[x]==x){
                return x
            }
            self.parent[x]=self.find(self.parent[x]);
            self.parent[x]
        }

    }
    pub(crate) fn solve(mut edges:Vec<(usize, usize, usize)>, n:usize) ->usize{
        let mut uf=UnionFind::new(n);
        let mut edges = edges.clone();
            edges.sort_unstable_by_key(|v| v.2);
        let last=n-2;
        let mut sum=0;
        let mut cnt=0;
        for  (x,y,value) in edges{
            if(cnt==last){
                break;
            }
            if(uf.find(x)==uf.find(y)){
                continue;
            }
            uf.union(x,y);
            sum+=value;
            cnt+=1;
        }

        sum
    }
}


mod prim{
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