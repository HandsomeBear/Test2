import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**文件注释：Demo1.java<br>
 *作者：孙浩<br>
 *时间：2016年11月17日-上午11:43:57<br>
 *类型：文件<br>
 *用途：该文件用于<br>
 *备注：***<br> 
 */

/**<div class="HCLASS"><p>类说明</p>
 *<p>作者：孙浩</p>
 *<p>时间:2016年11月17日 上午11:43:57</p>
 *<p>主要用途:</p>
 *<p>设计原理:</p>
 *<p></p>
 *<p>预期目标:无</p>
 *</div>
 */

public class Demo1 {

	public static void main(String[] args) throws Exception{
		BufferedWriter writer = null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			File alarmlog=new File("F:/infoExAlarm.log");
			if(!alarmlog.exists()){
				alarmlog.createNewFile();//告警文件不存在创建新文件
			}
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(alarmlog,true), "UTF-8"));
			String alarminfo="[";//告警内容
			alarminfo+=sd.format(new Date())+"] ";//拼接时间格式[2016-10-20] 
			alarminfo+="该线程执行同步超时，疑似数据库表被锁\r\n";
			writer.write(alarminfo);
		}catch(Exception e){
			
		}finally{
			if(writer!=null){
				writer.close();
			}
		}
	}
	
	public void test() throws Exception{
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.YEAR, 2016);
//		calendar.set(Calendar.MONTH,10);//从0开始，0表是1月，1表示2月依次类推
//		calendar.set(Calendar.DAY_OF_MONTH, 31);
//		calendar.set(Calendar.HOUR_OF_DAY, 01);
//		calendar.set(Calendar.MINUTE, 59);
//		calendar.set(Calendar.SECOND, 0);
//		calendar.add(Calendar.DAY_OF_MONTH, -1);
//		Date date = calendar.getTime();
//		System.out.println(sd.format(date).split(" ")[0]);
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date date = calendar.getTime();
		
		String starttime = "";
		
		String dtlastsyntime = "2016-11-28 15:55:57.000";
		
		if(dtlastsyntime.toString().split(" ")[0].compareTo(sd.format(date).split(" ")[0])>=0){
			//上次同步时间大于等于当前时间，用上次同步时间当做start
			starttime = dtlastsyntime.toString().split(" ")[0];
		}else{
			//上次同步时间小于当前时间，用上次同步时间+一天作为start
			Date d=sd.parse(dtlastsyntime.toString());
			d.setTime(d.getTime()+24*60*60*1000);
			starttime=sd.format(d).split(" ")[0];
		}
		System.out.println(starttime);
	}
}
