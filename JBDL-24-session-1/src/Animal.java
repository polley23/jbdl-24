public abstract class Animal implements Movable{
    protected Integer weight;

    public Animal(final Integer weight) {
        this.weight = weight;
    }

    protected void breathe(){
        System.out.println("Breathing");
    }
    abstract void eat();
}
