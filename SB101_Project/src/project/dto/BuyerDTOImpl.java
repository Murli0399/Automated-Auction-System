package project.dto;

public class BuyerDTOImpl implements BuyerDTO {
	private int id;
	private String name;
	private String address;
	private String mobile;
	private String username;
	private String password;

	public BuyerDTOImpl(String name, String address, String mobile, String username, String password) {
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Id = " + id + ", Name = " + name + ", Address = " + address + ", Mobile = " + mobile + ", username = "
				+ username + ", password = " + password;
	}
}
