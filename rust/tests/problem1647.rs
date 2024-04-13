#[cfg(test)]
mod test{
    use rust::{kruskal, prim};
    use super::*;
    #[test]
    fn problem1647_prim(){
        let case="7 12 1 2 3 1 3 2 3 2 1 2 5 2 3 4 4 7 3 6 5 1 5 1 6 2 6 4 1 6 5 3 4 5 3 6 7 4";
        let mut next={
            let mut input=case.split_ascii_whitespace().flat_map(str::parse::<usize>);
            move || input.next().unwrap()
        };
        let (n,m)=(next(),next());
        let edges=(0..m).map(|_|(next(),next(),next())).collect();
        let result = prim::solve(n,edges);
        assert_eq!(result,8)
    }
    #[test]
    fn problem1647_kruskal(){
        let case="7 12 1 2 3 1 3 2 3 2 1 2 5 2 3 4 4 7 3 6 5 1 5 1 6 2 6 4 1 6 5 3 4 5 3 6 7 4";
        let mut next={
            let mut input=case.split_ascii_whitespace().flat_map(str::parse::<usize>);
            move || input.next().unwrap()
        };
        let (n,m)=(next(),next());
        let edges=(0..m).map(|_|(next(),next(),next())).collect();
        let result = kruskal::solve(edges,n);
        assert_eq!(result,8)
    }
}
