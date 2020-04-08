package workshop.person.entity;

public class PersonEntity {
	private String name;
	private char gender;
	
	private String ssn;
	private String address;
	private String phone;
	
	public PersonEntity() {

	}

	public PersonEntity(String name, String ssn, String address, String phone) {
		this.name = name;
		this.ssn = ssn;
		this.address = address;
		this.phone = phone;
		if(this.ssn.charAt(6)=='1' ||this.ssn.charAt(6)=='3') {
			gender='남';
		}
		if(this.ssn.charAt(6)=='2' ||this.ssn.charAt(6)=='4') {
			gender='여';
		}

	}
	public char getGender() {
		return gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
		if(this.ssn.charAt(6)=='1' ||this.ssn.charAt(6)=='3') {
			gender='남';
		}
		if(this.ssn.charAt(6)=='2' ||this.ssn.charAt(6)=='4') {
			gender='여';
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	@Override
	public String toString() {
		return ("[이름] "+name+"    [성별] "+gender+"    [전화번호] "+phone);
		
	}
	
	
	
	
}
