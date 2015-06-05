package UI;
import java.util.Calendar;
import java.util.GregorianCalendar;


class myCalendar
{
	static public String getDate()
	{
		int day;
		 int month;
		 int year;
		 Calendar  cal = new GregorianCalendar();
		 day = cal.get(Calendar.DAY_OF_MONTH);
			month = cal.get(Calendar.MONTH);
			year = cal.get(Calendar.YEAR);
		String temp = Integer.toString(day) + " . " + Integer.toString(month+1) + " . " + Integer.toString(year);
		return temp;
	}
}