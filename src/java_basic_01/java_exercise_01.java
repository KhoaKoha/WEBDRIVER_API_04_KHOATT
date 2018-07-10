package java_basic_01;

public class java_exercise_01 {

	public static void main(String[] args) {
// Viet Ctrinh thuc hien thao tac voi cac chuoi sau: "Automation Testing Tutorials Online 123456"
		// Kiem tra so luong ki tu la "a" có trong chuoi tren.
		// Kiem tra chuoi co chua tu "Testing" hay khong
		// Kiem tra chuoi có bat dau bang tu "Automation" hay khong
		// Kiem tra chuoi co ket thuc bang tu "Online" hay khong
		// Lay vị tri cua tu "Tutorials" co trong chuoi
		// Thay the tu " online" bang "offline"
		// Dem so luong ky tu la so co trong chuoi

		String automation = "Automation Testing Tutorials Online 123456";
		String lowerCase = automation.toLowerCase();
		char checkA = 'a';
		int count = 0;
//		int countNumber = 0;
		
		for(int i = 0; i < lowerCase.length(); i++) {
			if (lowerCase.charAt(i) == checkA) {
				count++;
			}
		}		
		System.out.println("So lan xuat hien cua ky tu " + checkA + " la: " + count);
		
		boolean testing = automation.contains("Testing");
		System.out.println("Kiem tra tu 'Testing' có trong chuoi: " +testing);
		
		boolean startword = automation.startsWith("Automation");
		System.out.println("Kiem tra chuoi bat dau bang tu 'Automation': " +startword);
		
		boolean endword = automation.endsWith("Online");
		System.out.println("Kiem tra chuoi ket thuc bang tu 'Online': " +endword);
		
		int index = automation.indexOf("Tutorials");
		System.out.println("Vi tri cua tu 'Tutorials': " +index);
		
		automation = automation.replace("Online", "Offline");
		System.out.println("Chuoi sau khi thay the: " +automation);
		

		
		
		
	
	}

}
