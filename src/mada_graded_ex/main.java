package mada_graded_ex;

import java.math.*;
import java.util.*;
public class main {

	public static void main(String[] args) {
	 
	    int bitLength = 100;
		BigInteger p = BigInteger.probablePrime(bitLength, new Random());
		BigInteger q = BigInteger.probablePrime(bitLength, new Random());
		BigInteger n;
		
		BigInteger phi = (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
		
		if (!p.equals(q) && p.isProbablePrime(bitLength) && q.isProbablePrime(bitLength)) {
			n = p.multiply(q);
			System.out.println("p: " + p + "; q: " + q + "; n: " +n + "; phi: " + phi);
			
			
			// TODO Create Eulers totient function
			// TODO Create e from phi(n)
			// TODO Create d
			// TODO Implement extended eclidian algorythm
			
		}
		else {
			
			System.out.println("Fail, same primes or p / q not a prime");
			System.out.println("p: " + p + "; q: " + q);
		}
	
	}
	
	/**
	 * phi(n)
	 * 
	 * @param n, prime
	 * @return n-1
	 */
	public BigInteger phi(BigInteger n) {
			return n.subtract(BigInteger.ONE);  
	}

}
