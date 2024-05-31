
public class Main {

	public static void main(String[] args) {
		Supervizor s=new Supervizor();
		Controller c = new Controller();
		Robot r = new Robot ();
		c.r = r;
		c.s=s;
		r.c = c;
		s.c=c;
		c.start();
		r.start();
		s.start();
	}
}
