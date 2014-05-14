VolleyJoomla
============

Based on android volley library contains this library several requests to interact with a joomla api to retrieve categories and articles. The goal is to simple extend existing requests to create faster new ones.

Example
=======

	private void loginToJoomla() {
  		// SETUP YOU WEBSITE DATA WHICH ARE PREVIOUSLY SET ON YOUR JOOMLA API
  		final String host = "http://www.MY-JOOMLA-PAGE.com";
  		final String username = "api_user";
		final String password = "api_password";
  		final String uuid = "api_user_id";
  
  		// PREPARE YOUR CALLBACK LISTENER
  		final Listener<JConfiguration> onLoginSuccessListener = new Listener<JConfiguration>(){
			@Override
			public void onResponse( JConfiguration configuration ) {
				// THE CONFIGURATION OBJECT IS YOUR AUTHORISATION FOR MORE REQUESTS
			}
		};
		
		final ErrorListener onErrorListener = new ErrorListener(){
	  		@Override
	  		public void onErrorResponse(VolleyError error) {
		  
	  		}
		};

  		// CREATE YOUR WEBSITE AND LOGIN REQUEST
		final JWebsite website = new JWebsite(host, username, password, uuid);
		final Request<?> request = new JLoginRequest( website, onLoginSuccessListener, onErrorListener );
  		final RequestQueue queue = Volley.newRequestQueue( this );
  		queue.add( request );
	}
