public class PolyTest
{
	public static void main(String[] args)
	{
		Fruit f = new Apple();
		f.run();
	}

}

class Fruit
{
	public void run1()
	{
		System.out.println("Fruit");
	}
}

class Apple extends Fruit
{
	public void run()
	{
		System.out.println("Apple");
	}

}
