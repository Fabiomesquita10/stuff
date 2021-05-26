package src.edu.ufp.inf.lp2.ola;

import java.util.ArrayList;

class AA{
    protected int[] ints = {1,2};
    public AA(int s){
        ints = new int[s];
    }
}

class SubAA extends AA{
    protected String str;
    public SubAA(int s) { super(s); }//corrigido
    public SubAA(int x, String s) {
        super(x);
        str = s;
    }
}

public class BB{

    public static String str = "cona";
    public static void main(String[] args) {
        AA aa1 = new AA (2), aa2 = new AA(2);//corrigido
        ArrayList<AA> alist = new ArrayList();
        int a[] = new AA(2).ints;
        int i = 8;
        for (i = 0; i < a.length; i++) {
            a[i] = 2*i;
        }
        int n = ++i % 2;
        System.out.println("n=" + n);
        System.out.println("i=" + ++i);
        for (int j = 0; j < a.length; j++) { // corrigido
            if(a[0]>a[1])
                System.out.println("GREATER");
        }
        AA aa3 = new AA(2);
        alist.add(aa3);
        alist.add(new SubAA(2,""));
        AA aa4; //erro
        System.out.println("bool= " + (aa1 == aa3));
        //SubAA saa1 = new AA(2);//contrario
        SubAA saa2 = new SubAA( 2,"hello");
        aa4 = new SubAA(2, "heelo");
        System.out.println(((SubAA) aa4).str);
        BB bb1 = new BB();
        System.out.println(str);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

