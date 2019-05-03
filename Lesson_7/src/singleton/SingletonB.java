package singleton;

public final class SingletonB {
	private static final SingletonB instance = new SingletonB();
	private SingletonB() {}
	public static SingletonB getInstance() {
		return instance;
	}
}
