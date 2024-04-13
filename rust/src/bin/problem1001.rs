
fn main(){
    let mut buf=String::new();
    std::io::stdin().read_line(&mut buf).unwrap();
    let num : Vec::<i32>=buf.split_whitespace().map(|s| s.parse::<i32>().unwrap()).collect();
    let num=num.get(0).unwrap()-num.get(1).unwrap();
    println!("{}",num);
}