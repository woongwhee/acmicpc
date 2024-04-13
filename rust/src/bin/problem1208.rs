
use std::io;
use std::io::Read;
use std::error::Error;
use std::collections::HashMap;

struct Scanner<'a>{
    input : std::str::SplitAsciiWhitespace<'a>
}
impl<'a> Scanner <'a>{
    fn new(s:&'a str)->Scanner{
        Scanner{
            input: s.split_ascii_whitespace(),
        }
    }
    fn read<T: std::str::FromStr>(&mut self) -> T {
        self.input.next().unwrap().parse::<T>().ok().unwrap()
    }

}
fn calculate_sums(map:&mut HashMap<i32,usize>,vec:&[i32],cur:usize,value:i32){
    if cur==vec.len(){
        return;
    }
    calculate_sums(map,vec,cur+1,value);
    let value= value+vec[cur];
    if let Some(count)=map.get(&value){
        map.insert(value,count+1);
    }else{
        map.insert(value,1);
    }
    calculate_sums(map,vec,cur+1,value);
}
fn main()->Result<(),Box<dyn Error>>{
    let mut input=String::new();
    io::stdin().read_to_string(&mut input)?;
    let mut scanner=Scanner::new(&input);
    let n=scanner.read::<usize>();
    let s=scanner.read::<i32>();
    let mut sequence:Vec<i32>=Vec::new();
    for _ in 0..n{
        sequence.push(scanner.read::<i32>());
    }
    let left_sequence=&sequence[0..n/2usize];
    let right_sequence=&sequence[n/2usize..n];
    let mut left_map:HashMap<i32,usize>=HashMap::new();
    let mut right_map:HashMap<i32,usize>=HashMap::new();
    calculate_sums(&mut left_map,left_sequence,0,0);
    calculate_sums(&mut right_map,right_sequence,0,0);

    let mut result=0;
    for (value,l_count) in &left_map{
        if let Some(r_count) = right_map.get(&(s-value)){
            result+=l_count*r_count;
        }
    }
    if let Some(count)=right_map.get(&s){
        result+=count;
    };
    if let Some(count)=left_map.get(&s){
        result+=count;
    };
    println!("{result}");
    Ok(())
}



