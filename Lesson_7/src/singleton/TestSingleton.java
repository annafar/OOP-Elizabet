package singleton;

public class TestSingleton {

	public static void main(String[] args) {
		//Singleton0 sing = new Singleton0();/// the constructor is not visible
		Singleton0 s0 = Singleton0.getInstance();
		Singleton0 s1 = Singleton0.getInstance();
		System.out.println("s0 == s1? "+(s0 == s1));

		SingletonB sb0 = SingletonB.getInstance();
		SingletonB sb1 = SingletonB.getInstance();
		System.out.println("sb0 == sb1? "+(sb0 == sb1));
		
		int x = 0;
	}
}
