public class Main {
	public static int myFunction(int x) {
		int x = x+ 5 * ; //syntax error #1
		int x = x+ 5 ++ 5; //syntax error #1

		return int ; //syntax error #2
	}

	public static float myFunctionFloat(float x) {
		float fTest = (x * 50) + (x * 15)); //syntax error #3
		
		return fTest * 50 (); //syntax error #4
	}

	public static void main(String[] args) {
		myFunction(50);
		myFunctionFloat(50.0f);

	}
	/*
    public static void main(String[] args) {
		int x = 0;
		float _veryWeirdIdentifier567 = 999.9f;
		bool y = (5 > 4 && T) || (F && !(x==0));
		int[] myArray = new int[10];
		
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		System.out.println("What is the input: ");

		x = myObj.nextLine();
		// scan("What is the input: " , x); //ask for user input
		System.out.println("Input: " + x); //print the input
		
		for (int i = 1; i < 5; i++){
			System.out.println("Hello world");
		}
	}*/
}


