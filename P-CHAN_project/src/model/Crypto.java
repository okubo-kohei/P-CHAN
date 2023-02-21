package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Crypto {
	public String generateString(int length)
	{
	    final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random rdm = new Random();
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = CHARACTERS.charAt(rdm.nextInt(CHARACTERS.length()));
	    }
	    return new String(text);
	}
	
	public String sha256(String cryptWord) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		byte[] sha256_result = md.digest(cryptWord.getBytes());
		String sha256 = String.format("%040x", new BigInteger(1, sha256_result));
		
		return sha256;
	}
}
