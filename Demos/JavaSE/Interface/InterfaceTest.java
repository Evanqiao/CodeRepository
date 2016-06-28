public class InterfaceTest
{
	public static void main(String[] args)
	{
		ParentOutput pout = new Child();
		Child c = (Child)pout;
		c.output();
		c.output1();
		c.output2();
	}

}

interface Output1
{
	public void output1();
}

interface Output2
{
	public void output2();
}

class ParentOutput
{
	public void output()
	{
		System.out.println("output");
	}
}

class Child extends ParentOutput implements Output1, Output2
{
	public void output1()
	{
		System.out.println("output1");
	}
	public void output2()
	{
		System.out.println("output2");
	}

}
