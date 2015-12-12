package eu.hcomb.authn;

import eu.hcomb.common.crypt.PasswordHash;

public class TestHash {

	public static void main(String[] args) throws Exception {
		
		String password = "pippo";
		String hash = PasswordHash.createHash(password);
		boolean valid = PasswordHash.validatePassword(password, hash);
		boolean invalid = PasswordHash.validatePassword(password+"aa", hash);
		System.out.println("password:"+password);
		System.out.println("hash    :"+hash);
		System.out.println("valid   : " + valid);
		System.out.println("invalid : " + invalid);
		
	}
}
