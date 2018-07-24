package Test;

public class Prog1 {
	int j=0;
	
	public boolean findSubString(String str, String str2) {
		char []arr1=str.toCharArray();
		char []arr2=str2.toCharArray();
		int len1=arr1.length;
		int len2=arr2.length;
		
		for(int i=0; i<len2;i++) {
			//System.out.println(arr1[i]+":"+arr2[j]);
			for(;j<len1;) {
				if(arr1[j]==arr2[i]) {
					System.out.println(arr1[j]+":"+j+"--"+arr2[i]+":"+i);
					j++;
				}
				break;
			}
		}
		
		System.out.println(j+"   "+ len2+" "+arr2[1]);
		if (len2==j)
		return true;
		else 
			return false;
		
	}
    
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Prog1 p=new Prog1();
	System.out.println(p.findSubString("prabhat", "rab"));	
	}

}
