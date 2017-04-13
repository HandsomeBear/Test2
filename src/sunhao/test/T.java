package sunhao.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.leagsoft.hjslnotifier.HJSLNotifier;
import com.leagsoft.hjslnotifier.IHJSLNotifier;

public class T{
	public static void main(String[] args) throws Exception{
		test();
	}
	
	public static void tt() throws Exception{
		int[] temp = {1,2,3,4,5,6,7,8,9};
		int[] newtemp = (int[])arrayInc(temp,15);
		print(newtemp);
		
		String[] atr = {"a","b","c"};
		String[] str1 = (String[])arrayInc(atr,8);
		print(str1);
	}
	
	public static Object arrayInc(Object obj,int length){
		Class<?> arr = obj.getClass().getComponentType();
		Object newArr = Array.newInstance(arr, length);
		int co = Array.getLength(obj);
		System.arraycopy(obj, 0, newArr, 0, co);
		return newArr;
	}
	
	public static void print(Object obj){
		Class<?> c = obj.getClass();
		if(!c.isArray()){
			return;
		}
		System.out.println("数组长度为: "+Array.getLength(obj));
		for(int i = 0;i < Array.getLength(obj); i++){
			System.out.print(Array.get(obj, i)+" ");
		}
		System.out.println();
	}
	
	public static void ts() throws Exception{
		IHJSLNotifier notifier = new HJSLNotifier();
		notifier.setURL("http://192.168.2.154:8080/manager/interfacecenter.htm?act=saveBcMessageByInvoke");
		notifier.notifyIP("192.168.1.1", "test", "This is test");
	}
	
	public static void ta(){
		
		int i = Integer.MAX_VALUE;
		if(i + 1 < i){
			System.out.println("aaaa");
		}
		return;
		
//		double a = Double.NaN;
//		double aa = 1.0;
//		
//		float b = Float.NaN;
//		float bb = 1.0f;
//		if(a > aa){
//			System.out.println("a > b");
//		}
//		if(a <= aa){
//			System.out.println("a <= b");
//		}
	}
	
	public static String test1() {
		String a= "0";
         
        try {
            a = "10";
            return a;
        }
        catch (Exception e) {
            a = "-10";
        }
        finally {
            a = "20";
        }
         
        return a;
    }
	
	public static void readOnlyBuffer() throws Exception {  
        ByteBuffer buffer = ByteBuffer.allocate( 10 );  
          
        // 缓冲区中的数据0-9  
        for (int i=0; i<buffer.capacity(); ++i) {  
            buffer.put( (byte)i );  
        }  
  
        // 创建只读缓冲区  
        ByteBuffer readonly = buffer.asReadOnlyBuffer();  
          
        // 改变原缓冲区的内容  
        for (int i=0; i<buffer.capacity(); ++i) {  
            byte b = buffer.get( i );  
            b *= 10;  
            buffer.put( i, b );  
        }  
          
        readonly.position(0);  
        readonly.limit(buffer.capacity());  
          
        // 只读缓冲区的内容也随之改变  
        while (readonly.remaining()>0) {  
            System.out.println( readonly.get());  
        }  
    }
	
	public static boolean cas(){
		AtomicBoolean locked = new AtomicBoolean(false);
		AtomicInteger i = new AtomicInteger(10);
		i.incrementAndGet();
		
		return locked.compareAndSet(false, true);
	}
	
	public static void diff(){
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		
		for(String s: list){
			System.out.print(s);
		}
		
		System.out.println();
		
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			String s = it.next();
			System.out.print(s);
		}
	}
	
	public static void regex(){
		String strdownloadurl = "https://192.168.2.201:80/sfsf";
		String prefix = strdownloadurl.contains("https")?"https://":"http://";
		Pattern pattern = Pattern.compile(":");  
	    Matcher findMatcher = pattern.matcher(strdownloadurl);
	    int number = 0;  
        while(findMatcher.find()) {  
        	number++;  
            if(number == 2){
            	break;  
            }  
        }  
        int i = findMatcher.start();
        System.out.println(prefix+"192.168.2.154"+strdownloadurl.substring(i));
	}
	
	public static void recursionZip() throws Exception{
		
		List<File> files = new ArrayList<File>();
		files.add(new File("C:\\Users\\leagsoft\\Downloads\\ChromeStandalone_55.0.2883.87_Setup.exe"));
		File dstFile = new File("G:/4ff7-bf762ec5b517143a585-.zip");
		
		ZipOutputStream zipOut = null;
		try{
			zipOut = new ZipOutputStream(new FileOutputStream(dstFile));
			byte[] buf = new byte[1024*1024*10];
			for(File file : files){
		        InputStream input = new FileInputStream(file);
		        zipOut.putNextEntry(new ZipEntry(file.getName()));
		        int len;
		        while((len = input.read(buf)) != -1){
		        	zipOut.write(buf, 0, len);
		        }
		        input.close();
			}
		}catch(Exception e){
			throw e;
		}finally{
			zipOut.close();
		}
	}
	
	public static void test(){
		String mainCondition = "";
		String uidsoftwareid = mainCondition.substring(mainCondition.indexOf("'")+1, mainCondition.lastIndexOf("'"));
		System.out.println(uidsoftwareid);
	}
}