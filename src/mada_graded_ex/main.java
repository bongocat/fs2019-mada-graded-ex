package mada_graded_ex;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.math.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class main {

	public static void main(String[] args) {

		System.out.println("Type 'random' for random rsa key generation");
		System.out.println("Type 'encrypt' to encrypt a message");
		System.out.println("Type 'decrypt' to decrypt a message");
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		in.close();

		switch (s) {

		case "encrypt":
			System.out.println("Not supportet at the time");
			break;

		case "decrypt":
			System.out.println("Not supportet at the time");
			break;

		case "random":
			// choose bitlength for rsa
			int bitLength = 1024; 

			// Initialize BigIntegers
			BigInteger p = BigInteger.probablePrime(bitLength, new Random());
			BigInteger q = BigInteger.probablePrime(bitLength, new Random());
			BigInteger n;
			BigInteger d;
			BigInteger phi;
			BigInteger e = BigInteger.ZERO;
			BigInteger gdc = BigInteger.probablePrime(bitLength, new Random());

			// check p =! q
			if (!p.equals(q) && p.isProbablePrime(bitLength) && q.isProbablePrime(bitLength)) {

				// create n & phi
				n = p.multiply(q);
				phi = (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
				System.out.println("p: " + p + "; q: " + q + "; n: " + n + "; phi: " + phi);
				System.out.println();

				// choose random e with gdc(n,e) = 1
				System.out.println("create e");
				while (!e.gcd(phi.subtract(BigInteger.ONE)).equals(BigInteger.ONE) && !e.equals(p) && !e.equals(q)) {
					e = BigInteger.probablePrime(bitLength, new Random());
					System.out.println("> try e: " + e);
					System.out.println("> gdc(" + e + "," + phi.subtract(BigInteger.ONE) + ") = " + e.gcd(p));
				}
				System.out.println("chosen e: " + e);
				System.out.println();

				// create d via extended euclidian algorythm
				d = eea(n, e);
				
				if (d.signum() == -1) {
					throw new ArithmeticException("Only positive d supported atm");
		        }
					
				System.out.println("chosen d: " + d);
				System.out.println();

				System.out.println("Public  Key (n,e) = (" + n + "," + e + ")");
				System.out.println("Private Key (n,d) = (" + n + "," + d + ")");
				System.out.println();

				// create storage file for the public key
				System.out.println("create public key storage file");
				try (BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream("pk.txt"), StandardCharsets.UTF_8))) {
					writer.write("(" + n + "," + e + ")");
				} catch (java.io.IOException exception) {
					exception.printStackTrace();
					return;
				}

				// create storage file for the private key
				System.out.println("create private/secret key storage file");
				try (BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream("sk.txt"), StandardCharsets.UTF_8))) {
					writer.write("(" + n + "," + d + ")");
				} catch (java.io.IOException exception) {
					exception.printStackTrace();
					return;
				}

			} else {
				System.out.println("Fail, same primes or p / q not a prime");
				System.out.println("p: " + p + "; q: " + q);
				System.out.println("Re-run the program to try again");
			}
			break;
		}
	}

	public static BigInteger eea(BigInteger a, BigInteger b) {
		BigInteger x0, x1, y0, y1, q, r, tmpx, tmpy;
		x0 = BigInteger.ONE;
		y0 = BigInteger.ZERO;
		x1 = BigInteger.ZERO;
		y1 = BigInteger.ONE;

		System.out.println("extended euclidian algorythm for n & e");

		while (!b.equals(BigInteger.ZERO)) {
			q = a.divide(b);
			r = a.remainder(b);
			a = b;
			b = r;

			tmpx = x0;
			tmpy = y0;
			x0 = x1;
			y0 = y1;
			x1 = tmpx.subtract(q.multiply(x1));
			y1 = tmpy.subtract(q.multiply(y1));
			System.out.println("a: " + a + " b: " + b + " x0: " + x0 + " y0: " + y0 + " x1: " + x1 + " y1: " + y1
					+ " q: " + q + " r: " + r);

			
			
		}
		return y0;
	}
}
