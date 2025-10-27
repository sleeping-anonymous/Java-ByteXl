import java.util.*;
public class PartA {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0;i < 3;i++){
            System.out.println("Please enter the value of index:"+i);
            int num = sc.nextInt();
            list.add(num);  // int => Integer autoboxing
        }
        int sum =0;
        for(Integer ele : list){
            sum += ele;    //Unboxing
        }

        System.out.println(sum);
    }
}
