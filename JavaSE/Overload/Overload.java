public class Overload
{
	Overload()
	{
		System.out.println("no args");
	}
	Overload(int i)
	{
		this();
		System.out.println("have args");
	}
	public static void main(String[] args)
	{
		Overload tt = new Overload(1);
	}

}
