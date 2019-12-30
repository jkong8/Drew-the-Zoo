
public class Testing {

	public static void main(String[] args){
		int number = 12345;
		int number2 = getDigit(number,6);
		System.out.println(number2);
	}
	
	public static int getDigit(int a, int digit){
		for(int i = 1; i < digit;i++){
			a/=10;
		}
		a%=10;
		return a;
	}
}
