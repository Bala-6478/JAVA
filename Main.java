public class Main{
    public static boolean istwodigit(int number){
        if(number<0){
            number =-number;
        }if(number >=10 && number <=99){
            return true;
        }else{
            return false;
        }
    }
    public static void main(String[] args) {
        System.out.println(istwodigit(25));
        System.out.println(istwodigit(5));
    }
}