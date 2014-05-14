package de.ewackernagel.volley.joomla;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import de.ewackernagel.volley.joomla.pojos.JConfiguration;

public abstract class JRequest<T> extends Request<T> {
	public static final String PARAMETER_UUID = "uuid";
	public static final String PARAMETER_SID = "sid";
	
	private final JConfiguration configuration;
	private final Listener<T> successListener;
	
	private Map<String, String> params;
	private Map<String, String> headers;
	
	public JRequest( JConfiguration configuration, String urlPath, ErrorListener errorListener) {
		this(configuration, urlPath, null, null, null, errorListener);
	}
	
	public JRequest( JConfiguration configuration, String urlPath, Listener<T> successListener, ErrorListener errorListener) {
		this( configuration, urlPath, null, null, successListener, errorListener );
	}

	public JRequest( JConfiguration configuration, String urlPath, Map<String, String> params, Map<String, String> headers, Listener<T> successListener, ErrorListener errorListener) {
		super( Method.POST, buildUrl( configuration, urlPath ), errorListener );
		
		this.configuration = configuration;
		this.headers = headers;
		this.successListener = successListener;
		
		initParams( params, configuration );
	}
	
	private static String buildUrl( JConfiguration configuration, String urlPath ) {
		String host = configuration.getHost();
		if ( host != null && !host.endsWith( "/" ) ) {
			host += "/";
		}
		return host + urlPath;
	}
	
	private void initParams( Map<String, String> params, JConfiguration configuration ) {
		final boolean hasExtraParams = (params != null);
		
		this.params = new HashMap<String, String>( hasExtraParams ? params.size() + 2 : 2 );
		this.params.put( PARAMETER_UUID, configuration.getUuId() );
		this.params.put( PARAMETER_SID, configuration.getSessionId() );
		
		if( hasExtraParams ) {
			this.params.putAll( params );
		}
	}
	
	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return headers != null ? headers : super.getHeaders();
	}
	
	public void setHeaders( Map<String, String> headers ) {
		if( headers != null && this.headers == null ) {
			this.headers = headers;
		}
	}
	
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return params != null ? params : super.getParams();
	}
	
	public void setParams( Map<String, String> params ) {
		if( params != null && this.params == null ) {
			initParams( params, configuration );
		}
	}
	
	public JConfiguration getJConfiguration() {
		return configuration;
	}
	
	public Listener<T> getSuccessListener() {
		return successListener;
	}

	@Override
	protected void deliverResponse(T response) {
		if( successListener != null ) {
			successListener.onResponse( response );
		}
	}
}
