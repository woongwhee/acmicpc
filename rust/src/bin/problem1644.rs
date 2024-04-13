use std::io::{BufRead, BufReader, stdin};
fn get_prime_nums(end:usize)->Vec<usize>{
    let mut primes=Vec::new();
    let mut is_primes=vec![true;end+1];
    is_primes[0]=false;
    is_primes[1]=false;

    for i in 2..=end {
        if is_primes[i] {
            primes.push(i);
            (i*i..=end).step_by(i).for_each(|j| is_primes[j]=false);
        }
    }
    primes

}
fn two_pointer(target:usize)->usize{
    let prime_nums = get_prime_nums(target);
    let mut sum=0;
    let mut start=0;
    let mut end=0;
    let mut result=0;
    while  end<prime_nums.len() {
        if sum<target {
                sum += prime_nums[end];
                end += 1;
        }else if sum==target {
            result+=1;
            sum-=prime_nums[start];
            start+=1;
        }else{
            sum-=prime_nums[start];
            start+=1;
        }
    }
    while sum >= target && start < prime_nums.len() {
        if sum == target {
            result += 1;
        }
        sum -= prime_nums[start];
        start += 1;
    }
    result
}
fn main() {
    let stdin=stdin();
    let mut stdin =BufReader::new(stdin.lock());
    let mut buffer=String::new();
    stdin.read_line(&mut buffer).expect("");
    let n=buffer.trim().parse::<usize>().expect("");
    let result = two_pointer(n);
    println!("{}",result);


}