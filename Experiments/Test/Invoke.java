package Test;

public class Invoke {
	int dd,mm,yyyy;
	boolean flag=false;		
	String s="";
	String result="";

	public boolean checkLeapYear(){
		if(yyyy%4==0 && yyyy%100!=0 || yyyy%400==0){
			flag=true;
			System.out.println("Leap Year");
		}
		return flag;
	}	
	public String printDate(String date) { //DD-MM--YYYY
		String[] dateArr=date.split("-");
		dd=Integer.parseInt(dateArr[0]);
		mm=Integer.parseInt(dateArr[1]);
		yyyy=Integer.parseInt(dateArr[2]);
		System.out.println(dd+" "+mm+" "+yyyy);
		//return date;

		if((dd<=31 && ( mm==1 || mm==3 || mm==5 || mm==7 || mm==8 || mm==10 || mm==12))) {

			if(dd<31 && mm!=12) {
				dd=dd+1;
			} else if (dd==31 && mm==12){
				dd=1;
				mm=1;
				yyyy=yyyy+1;
			}else if(dd==31){
				mm=mm+1;
				dd=1;
			}else {
				dd=dd+1;
			}
			result= s+dd+"-"+mm+"-"+yyyy;
            return result;
		}else if(dd<=30 && (mm==4 || mm==6 || mm==9 || mm==11)) {
			if(dd<30) {
				dd=dd+1;	
			}else {
				dd=1;
				mm=mm+1;
			}
			result= s+dd+"-"+mm+"-"+yyyy;
			 return result;
		}else if(dd<=28 && mm==2 && checkLeapYear()==false ) {
			if(dd<27) {
				dd=dd+1;
			}else {
				dd=1;
				mm=3;
			}

			result= s+dd+"-"+mm+"-"+yyyy;
			 return result;
		}else if(dd<=29 && mm==2 && checkLeapYear()==true ) {
			if(dd<=28) {
				dd=dd+1;
				
			}else {
				dd=1;
				mm=3;
			}

			result= s+dd+"-"+mm+"-"+yyyy;
			 return result;
		}else {
           return "Invalid date";
		}

}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Invoke I =new Invoke();
		String s=I.printDate("30-02-2020");
		System.out.println(s);
	
		
	}

}
/* Test Case
1. Test calender program for a month having 31 days such as Input is "31/01/2018"
2. Test calender program for a month having 31 days such as Input is "30/01/2018"
3. Test calender program for a month having 30 days such as Input is "30/04/2018"
4. Test calender program for a month having 30 days such as Input is "10/04/2018"
5. Test calender program for a leap year such that input is "29/02/2020"
6. Test calender program for a leap year such that input is "28/02/2020"
7. Test calender program for a non-leap year such that input is "28/02/2019"
8. Test calender program for a non-leap year such that input is "28/02/2019"
9. Test calender program for last day of a year such that input is "31/12/2019"
10. Test calender program for a incorrect date of a month and the selected month having 30 days in a month.("31/04/2018")
11. Test calender program for a incorrect date of a month and the selected month having 31 days in a month.("32/05/2018")
12. Test calender program with an input data having incorrect month. (05/13/2018)
13. Test calender program for a non-leap year such that input is "29/02/2019"
*/