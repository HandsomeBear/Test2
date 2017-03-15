package sunhao;

import java.math.BigDecimal;

public class IntAndFloatTest {

	public static void main(String[] args) {

//		isOdd();
		
//		findZero();
		
//		longInteger();
		
		hexCal();
	}
	
	/**
	 * @author 孙浩
	 * @date 2017年1月12日 下午2:44:03
	 * @Description:寻找奇数--注意负奇数
	 * @return
	 */
	public static boolean isOdd(){
		int i = -3;
		
//		return i % 2 == 1;
//		return i % 2 != 0;
		return (i & 1) != 0;
	}
	
	/**
	 * @author 孙浩
	 * @date 2017年1月12日 下午2:44:38
	 * @Description:
	 * 		找0--并不是所有的小数都可以用二进制浮点数来精确表示的
	 * 		二进制浮点数的 double 运算,不可能将 0.1——或者 10 的其它任何次负幂——精确表示为一个长度有限的二进制小数
	 * 		使用执行精确小数运算的 BigDecimal;一定要用BigDecimal(String)构造器，而千万不要用 BigDecimal(double)。
	 */
	public static void findZero(){
		System.out.println(2.00-1.10);  //0.8999999999999999
		System.out.printf("%.2f%n",2.00 - 1.10);  //0.90
		System.out.println((200 - 110) + "cents");  //90
		System.out.println(new BigDecimal("2.00").subtract(new BigDecimal("1.10")));  //0.90
	}
	
	/**
	 * @author 孙浩
	 * @date 2017年1月12日 下午2:45:33
	 * @Description:长整型计算
	 */
	public static void longInteger(){
		final long MICROS_PER_DAY = 24*60*60*1000*1000; //这里的数据已经不对了，int数值相乘本身还是int，已经超出了int最大值，然后再提升到long
		final long MILLIS_PER_DAY = 24*60*60*1000;
		System.out.println(MICROS_PER_DAY); //500654080;正常计算应该是：86400000000
		System.out.println(MILLIS_PER_DAY); //86400000
		System.out.println(MICROS_PER_DAY/MILLIS_PER_DAY); //5
		
		System.out.println("=====以long来计算====");
		
		final long MICROS_PER_DAY_ = 24L*60*60*1000*1000;
		final long MILLIS_PER_DAY_ = 24L*60*60*1000;
		System.out.println(MICROS_PER_DAY_);
		System.out.println(MILLIS_PER_DAY_);
		System.out.println(MICROS_PER_DAY_/MILLIS_PER_DAY_); //1000
	}
	
	/**
	 * @author 孙浩
	 * @date 2017年1月12日 下午2:47:59
	 * @Description:十六进制的计算
	 */
	public static void hexCal(){
		//这个计算实际上：0x0000000100000000L+0xffffffffcafebabe;高位的1丢掉
		System.out.println(Long.toHexString(0x100000000L+0xcafebabe)); //cafebabe
		System.out.println(Long.toHexString(0x100000000L+0xcafebabeL)); //1cafebabe
	}
}
