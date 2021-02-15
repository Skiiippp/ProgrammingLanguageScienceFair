package originalcode;

public class TestSciFair {

    public static void main(String args[]){

        int i = 2;

        if(i < 3){
            System.out.println("i is less than three");
        }

        System.out.println("");
        i = i * 2;

        if(i < 3){
            System.out.println("i is still less than three");
        }

        if(i > 3){
            System.out.println("i is now greater than three");
        }

        System.out.println("");
        System.out.println("done");
    }

}
