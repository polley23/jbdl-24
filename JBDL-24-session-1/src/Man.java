public class Man extends Human {
    public Man(final Integer salary, final Integer weight) {
        super(salary,weight);
    }
    @Override
    void eat() {
        System.out.println("Man is eating");
    }

    @Override
    public void start() {
        System.out.println("Man is moving");
    }

    @Override
    public void stop() {
        System.out.println("Man is stopping");

    }

    @Override
    public void makeNoise() {
        System.out.println("Man is speaking");
    }
}
