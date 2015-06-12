public class SingletonTest
{
	public static void main(String[] args)
	{
		Singleton sin = Singleton.getInstance();
	}
	

}

class Singleton
{
	private static Singleton singleton;
	
	public static Singleton getInstance()
	{
		if(singleton == null)
		{
			singleton = new Singleton();
		}
		return singleton;
	}
}