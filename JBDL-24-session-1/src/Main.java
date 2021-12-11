import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Man man = new Man(1000,67);
//        man.eat();
//        man.breathe();
//        man.start();
        Bird bird = new Bird(100);
//        bird.eat();
//        bird.start();
//        move(man);
//        move(bird);
        Aeroplane aeroplane =new Aeroplane();
//        try {
//            divide(0,2);
//        } catch (DivideByZeroException e) {
//            e.printStackTrace();
//        }
//        move(aeroplane);
//        noise(man);
//        noise(aeroplane);
        //Wrapper class
//        Integer i = new Integer(1);
//        System.out.println("hashcode is : "+i.hashCode());
//        Integer j = new Integer(1);
//        System.out.println("hashcode is : "+j.hashCode());
//

        String str1 = "string";
        String str2 = "string";

        System.out.println("hashcode is : "+str1.hashCode());
        System.out.println("hashcode is : "+str2.hashCode());

        if(str1 == str2){
            System.out.println("Strings are of same object");
        }else {
            System.out.println("Strings are not of same object");
        }

        System.out.println(LocalDateTime.now().getDayOfMonth());


    }
    //abstraction
    private static void move(Movable movable){
        movable.start();
        movable.stop();
    }

    private static void noise(INoise iNoise) {
        iNoise.makeNoise();
    }

    public static Integer divide  (Integer a , Integer b)
            throws DivideByZeroException {
        if(b==0){
            throw new DivideByZeroException("denominator is 0");
        }
        return a/b;
    }
}
