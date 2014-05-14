VolleyJoomla
============

Based on android volley library contains this library several requests to interact with a joomla api to retrieve categories and articles. The goal is to simple extend existing requests to create faster new ones.

## Login Example

```java
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
```

## Category Example

```java
private void showJoomlaCategories( JConfiguration configuration ) {
	// PREPARE YOUR CALLBACK LISTENER
  	final Listener<JCategory[]> onCategoriesLoadedListener = new Listener<JCategory[]>(){
		@Override
		public void onResponse( JCategory[] categories ) {
			// DO SOMETHING WITH THE CATEGORIES
		}
	};
	
	final ErrorListener onErrorListener = new ErrorListener(){
  		@Override
  		public void onErrorResponse(VolleyError error) {
	  
  		}
	};
	
	// CREATE THE REQUEST
	final Request<?> request = new JCategoriesRequest( configuration, onCategoriesLoadedListener, onErrorListener )
	final RequestQueue queue = Volley.newRequestQueue( this );
  	queue.add( request );
}
```
## Pojos and Requests

Following request are available:
* JLoginRequest
* JCategoriesRequest
* JArticlesRequest
* JArticleDetailsRequest
* JRequest
* GsonJRequest

Following POJOS are available:
* JWebsite
* JConfiguration
* JCategory
* JArticle

All POJOS except the JWebsite class implement the Parcelable interface and be simple transfered between
Activities or Fragments. 
```java
private void showDetails( JConfiguration configuration, JArticle article ) {
	Intent details = new Intent( context, ArticleDetailsActivity.class );
	details.putExtra( "my_config", configuration );
	details.putExtra( "my_article", article );
	startActivity( details );
}
```
The JWebsite object is only needed for the login process and stored no password.
The JConfiguration object is bound to one website and can be reused for all requests to this bound website.

## License
Apache License v2

See LICENSE for full license text.
