package study.linear;

/** 
* @ClassName: Test 
* @Description: TODO
* @author chen.gao@baidao.com   
* @date 2017年6月22日 上午10:37:22 
*  
*/

public class Test {

	int a = 0;
	volatile boolean flag = false;

	public void writer() {
		a = 1;
		flag = true;
	}

	public void reader() {
		if (flag) {
			int i = a;
			System.out.println(i);
		}
	}

	public static void main(String[] args) {
		/*final Test tt = new Test();
		Thread t1 = new Thread() {
			@Override
			public void run() {
				tt.writer();
			}
		};
		Thread t2 = new Thread() {
			@Override
			public void run() {
				tt.reader();
			}
		};
		t1.start();
		t2.start();*/
		final String b = "123";
		String c = b;
		c = "";
	}

}
