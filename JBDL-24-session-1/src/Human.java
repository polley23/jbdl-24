public abstract class Human extends Animal implements INoise{
    protected Integer salary;
    public Human(final Integer salary, final Integer weight) {
        super(weight);
        this.salary = salary;
    }
}
