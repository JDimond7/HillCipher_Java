

package hillcipher;

import java.util.HashMap;
import java.util.Arrays;
import org.apache.commons.math3.linear.*; //third party library for efficient matrix computations. 

public class HillCipher {
	//if I add ' to alphastring, encrypt and decrypt are no longer inverses...!?!?!?
    //static String alphastring = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,;:!?()\"\n";
	static String alphastring = "abcdefghijklmnopqrstuvwxyz "; 
	public static enum Mode {ENCRYPT, DECRYPT};
	static HashMap<Character, Integer> AlphaNum = makeAlphaNum();
	
	public static HashMap<Character, Integer> makeAlphaNum(){
		HashMap<Character, Integer> AlphaToNumbers = new HashMap<>();
		for (int i = 0; i < alphastring.length(); i++) {
			AlphaToNumbers.put(alphastring.charAt(i), i);
		}
		return AlphaToNumbers;
	}
	
	public static String cipher (String msg, double[][] exampleKey, Mode mode) { 
		
		RealMatrix m = MatrixUtils.createRealMatrix(exampleKey);
		Matrix key = new Matrix(3);
		//System.out.println(alphastring.length());//check # of symbols. 
		
		if (mode == Mode.ENCRYPT){ //usual
			key.element = m.getData();
		} 
		else if (mode == Mode.DECRYPT){ //turn procedure into decryption. 
			RealMatrix mInverse = new LUDecomposition(m).getSolver().getInverse();
		
			double mDet = new LUDecomposition(m).getDeterminant();
			System.out.println("determinant as calculated is " + mDet);
			//int modScale = modularInverse((int)mDet);
			int modScale = modularInverse((int)Math.round(mDet), alphastring.length());
		
			mInverse = mInverse.scalarMultiply(mDet*modScale); //apply appropreiate scaling. 
			key.element = mInverse.getData(); //try out the stuff from the library to invert. 
		
			//still required to make entries positive, then mod by # of symbols. 
			System.out.println("Length of alphastring " + alphastring.length());
			for (int i = 0; i < key.size(); i++) {
				for (int j = 0; j < key.size(); j++) {
					while (key.element[i][j] < 0.0){
						key.element[i][j] += alphastring.length();
					}
					key.element[i][j] = Math.round(key.element[i][j])%alphastring.length();
					System.out.println(key.element[i][j]);
				}
			}
		} 

		if (msg.length() % 3 != 0){//make message fit into blocks.
			int spacesNeeded = 3-(msg.length()%3); //need to replace 3 later with size of key. 
			for (int i=spacesNeeded; i>0; --i){
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
		for (int i = 0; i < messageAsInts.length/3 ; i++) {//i counts up in blocks. replace 3 with key size later. 
			int[] block = Arrays.copyOfRange(messageAsInts, 3*i, 3*i+3); //from is included, to is excluded. 
			block = matrixVectorMult(key.element, block);
			for (int j = 0; j < 3; j++) {
				encryptedInts[3*i+j]=block[j]%alphastring.length();
			}
		}
		
		//assemble encrypted string from encryptedInts. 
		char[] encryptedMsg = new char[messageAsInts.length];
		for (int i = 0; i < encryptedMsg.length; i++) {
			encryptedMsg[i] = alphastring.charAt(encryptedInts[i]);
		}
		
		String encryptedMessage = new String(encryptedMsg);
		return encryptedMessage;
		//System.out.println(encryptedMsg);
		
	}
	
	public static double[][] parseKey(String keyInput){
		String[] numbers = keyInput.trim().split(" "); //array of "numbers"
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
		
		//print key for debug. This new one seems to work correctly. 
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println(key[i][j]);
			}
		}
		return key;
	}
	
	public static int[] matrixVectorMult(double[][] m, int[] x){
		int[] result = new int[x.length];
		for (int i = 0; i < m[0].length; i++) {//m[0].length is simply the right number. 
			int temp = 0;
			for (int j = 0; j < m[0].length; j++) {
				temp += m[i][j]*x[j];
			}
			result[i]=temp;
		}
		return result;
	}
	
	public static int modularInverse(int a, int b){ //b will be #symbols.
		//calculates the inverse of a modulo b via extended euclidean algorithm.
		//System.out.println("a = " + a + ", b = " + b);
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
		System.out.println("modular inverse = " + s[0]);
		return s[0];
	}
}

class Matrix{
	public double[][] element; //Access to elements. 
	
	public Matrix(int n){ //only care about square matrices. 
		this.element = new double[n][n];
	}
	
	public void setMatrix(double[][] newMatrix){
		this.element = newMatrix;
	}
	
	public int size(){//simple convenience. 
		return this.element[0].length;
	}
	
	/*
	public Matrix matrixInverse(){ //this also destroys original matrix...!
		Matrix inv = new Matrix(this.size());
		for (int i = 0; i < this.size(); i++) {
			for (int j = 0; j < this.size(); j++) {
				if (i == j) {
					inv.element[i][j] = 1.0;
				} else {
					inv.element[i][j] = 0.0;
				}
			}
		}
		 
		//compute inverse via gaussian elimination. 
		for (int index = 0; index < this.size(); index++) {
			rowNormalise(index, inv);
			rowReductions(index, inv);
		}
		
		//rescale so that it's the *modular* inverse. 
		double det = inv.determinant();
		det = (int)(1/det);
		double scaleFactor = det*modularInverse((int)det);
		
		for (int i = 0; i < inv.size(); i++) {
			for (int j = 0; j < inv.size(); j++) {
				inv.element[i][j] *= scaleFactor;
				inv.element[i][j] = inv.element[i][j]%27;
				while (inv.element[i][j]<0){
					inv.element[i][j]+=27;
				}
				inv.element[i][j] = Math.round(inv.element[i][j]);
			}
		}
		return inv;
	}
	
	private void rowNormalise(int index, Matrix inv){
		
		//First, what if leading coeff is zero?
		if (this.element[index][index] == 0.0) {
			int k = -1; //initialise a test value;
			
			for (int i = ++index; i < this.size(); i++) {
				if (this.element[index][i] != 0) {
					k = i; //found a row to swap with. 
					break;
				}
			}
			
			if (k == -1) { //failure to find a suitable row. 
				System.out.println("Unable to find suitable row for row swap - is matrix invertible?");
				System.exit(0);
			}	
			
			//swap rows
			double[] rowSwapBank = new double[this.size()];
			for (int i = 0; i < this.size(); i++) {
				rowSwapBank[i] = this.element[index][i];
				this.element[index][i] = this.element[k][i];
				this.element[k][i] = rowSwapBank[i];
				rowSwapBank[i] = inv.element[index][i];
				inv.element[index][i] = inv.element[k][i];
				inv.element[k][i] = rowSwapBank[i];
			}
		}
		
		//Now proceed as usual. 
		double factor = this.element[index][index];
		for (int i = 0; i < this.size(); i++) {
			this.element[index][i] /= factor;
			inv.element[index][i] /= factor;
		}
	}
	
	private void rowReductions(int index, Matrix inv){
		for (int i = 0; i < this.size(); i++) {
			if (i == index) {
				continue;
			}
			
			double factor = this.element[i][index];
			for (int j = 0; j < this.size(); j++) {
				this.element[i][j] -= (this.element[index][j])*factor;
				inv.element[i][j] -= (inv.element[index][j])*factor;
			}
		}
	}

	private double determinant(){//horrible/lazy way to get det of a 3x3
		double det;
		det = this.element[0][0]*this.element[1][1]*this.element[2][2]
			 +this.element[0][1]*this.element[1][2]*this.element[2][0]
			 +this.element[0][2]*this.element[1][0]*this.element[2][1]
			 -this.element[0][2]*this.element[1][1]*this.element[2][0]
			 -this.element[0][1]*this.element[1][0]*this.element[2][2]
			 -this.element[0][0]*this.element[1][2]*this.element[2][1];
		return det;
	}

	public static int modularInverse(int a){ //horrible brute force for now. 
		a = a%27;
		for (int i = 2; i < 26; i++) {
			if (a*i%27 == 1){
				return i;
			}
		}
		return -1; //err
	}
}*/
	
}