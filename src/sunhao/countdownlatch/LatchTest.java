package sunhao.countdownlatch;

public class LatchTest {

	public static void main(String[] args) {
		boolean result = false;
		try{
			result = ApplicationStartUpUtil.getInstance().checkExternalServices();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("External services validation completed!!result was::"+result);
	}
}
