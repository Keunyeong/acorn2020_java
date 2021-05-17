package test.dto;

public class BirthDto {
	private int num;
	private String name;
	private String birthday;
	
	public BirthDto() {}

	public BirthDto(int num, String name, String birthday) {
		super();
		this.num = num;
		this.name = name;
		this.birthday = birthday;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	};
	
}
