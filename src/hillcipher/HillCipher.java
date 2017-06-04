
package hillcipher;

import java.util.HashMap;
import java.util.Arrays;
import org.apache.commons.math3.linear.*; //third party library for efficient matrix computations. 

public class HillCipher {
    static String symbolSet = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,;:!?()\"\n";
	//static String symbolSet = "abcdefghijklmnopqrstuvwxyz "; 
	static HashMap<Character, Integer> AlphaNum = makeAlphaNum();
	
	public static HashMap<Character, Integer> makeAlphaNum(){
		HashMap<Character, Integer> AlphaToNumbers = new HashMap<>();
		for (int i = 0; i < symbolSet.length(); i++) {
			AlphaToNumbers.put(symbolSet.charAt(i), i);
		}
		return AlphaToNumbers;
	}
	
	public static int GCD(int a, int b) { return b == 0 ? a : GCD(b, a%b); }
	
	public static double[][] makeInverseKey(double[][] key){
		RealMatrix m = MatrixUtils.createRealMatrix(key);
		RealMatrix mInverse = new LUDecomposition(m).getSolver().getInverse();
		double mDet = new LUDecomposition(m).getDeterminant();
		
		if (GCD((int)Math.round(mDet), symbolSet.length()) != 1){
			return null;
		}
		
		int modScale = modularInverse((int)Math.round(mDet), symbolSet.length());
		mInverse = mInverse.scalarMultiply(mDet*modScale); //apply appropriate scaling.
		
		double[][] mInverseArray =  mInverse.getData();
		for (int i = 0; i < mInverseArray[0].length; i++) {
				for (int j = 0; j < mInverseArray[0].length; j++) {
					while (mInverseArray[i][j] < 0.0){
						mInverseArray[i][j] += symbolSet.length();
					}
					mInverseArray[i][j] = Math.round(mInverseArray[i][j])%symbolSet.length();
					System.out.println(mInverseArray[i][j]);
				}
			}
		return mInverseArray;
	}
	
	public static String cipher (String msg, double[][] key) { 
		int keysize = key[0].length;

		//make message fit into blocks by appending spaces.
		if (msg.length() % keysize != 0){
			int spacesNeeded = keysize -(msg.length()%keysize); 
			for (int i = spacesNeeded; i > 0; --i){
				msg += " ";
			}
		}
		
		//convert message to array of integers. 
		int[] messageAsInts = new int[msg.length()];
		char[] msgAsArray = msg.toCharArray();
		for (int i = 0; i < msgAsArray.length; i++) {
			messageAsInts[i] = AlphaNum.get(msgAsArray[i]);
		}
		
		//encrypt blocks into new array. 
		int[] encryptedInts = new int[messageAsInts.length];
		for (int i = 0; i < messageAsInts.length/keysize ; i++) { 
			int[] block = Arrays.copyOfRange(messageAsInts, keysize*i, keysize*(i+1));
			block = matrixVectorMult(key, block);
			for (int j = 0; j < keysize; j++) {
				encryptedInts[keysize*i+j]=block[j]%symbolSet.length();
			}
		}
		
		//assemble encrypted string from encryptedInts. 
		char[] encryptedMsg = new char[encryptedInts.length];
		for (int i = 0; i < encryptedMsg.length; i++) {
			encryptedMsg[i] = symbolSet.charAt(encryptedInts[i]);
		}
		
		return new String(encryptedMsg);
	}
	
	public static double[][] parseKey(String keyInput){
		String[] numbers = keyInput.trim().split(" ");
		
		if (Math.sqrt(numbers.length) > (int)Math.sqrt(numbers.length)){ //true if matrix nonsquare. 
			return null;
		}
		
		int[] values = new int[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			values[i] = Integer.parseInt(numbers[i]);
		}
		
		//put into key array
		int n = (int)Math.sqrt(values.length);
		double[][] key = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				key[i][j] = Math.round(values[n*i+j]);
			}
		}
		
		return key;
	}
	
	public static int[] matrixVectorMult(double[][] m, int[] x){ //computes the matrix*vector mult m*x.
		int[] result = new int[x.length];
		int temp;
		for (int i = 0; i < m[0].length; i++) {//m[0].length is the right number becasue m is square.  
			temp = 0;
			for (int j = 0; j < m[0].length; j++) {
				temp += m[i][j]*x[j];
			}
			result[i]=temp;
		}
		return result;
	}
	
	public static int modularInverse(int a, int b){ //calculates the inverse of a modulo b via extended euclidean algorithm.
		int[] r = {Math.max(a,b), Math.min(a,b)}; 
		int[] s;
		if (a <= b){
			s = new int[]{0,1};
		} else {
			s = new int[]{1,0};
		}
		
		int q, temp;
		while(r[1] != 0){
			q = (int)Math.floor(r[0]/r[1]);
			
			temp = r[1];
			r[1] = r[0] - q*r[1];
			r[0] = temp;
			
			temp = s[1];
			s[1] = s[0] - q*s[1];
			s[0] = temp;
		}
		
		while (s[0] < 0){
			s[0] += b;
		}
		
		return s[0];
	}
}
