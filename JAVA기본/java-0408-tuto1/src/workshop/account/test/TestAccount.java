package workshop.account.test;

import workshop.account.entity.Account;
public class TestAccount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		

		Account account = new Account("A1100","221-22-3477",100000);
		System.out.println(account.toString());
		
		System.out.println("°í°´ ¹øÈ£ : "+account.getCustId() + ",  °èÁÂ ¹øÈ£ : "+account.getAccId()); 
		System.out.println("ÀÜ¾× : "+ account.getBalance());
		account.deposit(10000);
		System.out.println("ÀÜ¾× : "+ account.getBalance());
		account.withdraw(20000);
		System.out.println("ÀÜ¾× : "+ account.getBalance());
	}

}
