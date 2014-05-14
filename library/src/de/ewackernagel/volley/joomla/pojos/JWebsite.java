package de.ewackernagel.volley.joomla.pojos;


public class JWebsite extends JConfiguration {
	private final String username;
	private final String password;
	
	public JWebsite( String host, String username, String password, String uuId ) {
		super(host, "", uuId);
		
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
}
