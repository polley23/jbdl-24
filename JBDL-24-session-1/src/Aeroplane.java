public class Aeroplane  extends Vehicle{
    @Override
    public void start() {
        System.out.println("aeroplane is moving");

    }

    @Override
    public void stop() {
        System.out.println("aeroplane is stopping");

    }

    @Override
    public void makeNoise() {
        System.out.println("aeroplane is making noise");
    }
}
