public class Bird extends Animal {
    public Bird(final Integer weight) {
        super(weight);
    }

    @Override
    void eat() {
        System.out.println("Bird is eating");
    }

    @Override
    public void start() {
        System.out.println("Bird is moving");
    }

    @Override
    public void stop() {
        System.out.println("Bird has stopped moving");
    }
}
