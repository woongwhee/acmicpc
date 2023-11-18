package net.acmicpc.bfs;

public class Tire {
    String whireType;
    String primeryKey;
    int num;
    public Tire() {
    }

    public Tire(String whireType, String primeryKey) {
        this.whireType = whireType;
        this.primeryKey = primeryKey;
    }

    public boolean equelsType(Tire o){
        if(o.whireType==this.whireType&&o.primeryKey==this.primeryKey){return true;}
        return false;
    }
    public boolean equelsType(Tire o,int a){
        if(o.whireType==this.whireType&&o.primeryKey==this.primeryKey){return true;}
        return false;
    }

    public static void main(String[] args) {
        Tire tire1=new Tire("2","4");
        Tire tire3=new Tire();
        int trie4=0;
        long tire5=3;
        //함수 오버라이딩
        //함수 오버로딩
        Tire tire2=new Tire("2","5");
        System.out.println(tire2.whireType);
        System.out.println(tire2.num);

        tire3.whireType="3";
        tire3.primeryKey="5";
        String a=tire1.whireType;
        String b=tire2.whireType;




    }


}
