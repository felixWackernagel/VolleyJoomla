package de.ewackernagel.volley.joomla;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import android.text.TextUtils;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import de.ewackernagel.volley.joomla.pojos.JConfiguration;
import de.ewackernagel.volley.joomla.pojos.JWebsite;

public class JLoginRequest extends JRequest<JConfiguration> {
	private static final String PARAMETER_USERNAME = "username";
	private static final String PARAMETER_PASSWORD = "password";
	private static final String PARAMETER_REMEMBER = "remember";
	private static final String PARAMETER_RETURN = "return";
	private static final String PARAMETER_TASK = "task";

	public JLoginRequest( JWebsite website, Listener<JConfiguration> successListener, ErrorListener errorListener) {
		super( website, "api/api.php", buildParams( website ), null, successListener, errorListener);
	}
	
	private static Map<String, String> buildParams( JWebsite website ) {
		final Map<String, String> params = new HashMap<String, String>( 5 );
		params.put( PARAMETER_USERNAME , website.getUsername() );
		params.put( PARAMETER_PASSWORD, website.getPassword() );
		params.put( PARAMETER_REMEMBER, String.valueOf( false ));
		params.put( PARAMETER_RETURN, "L0REU1JlbC9pbmRleC5waHA");
		params.put( PARAMETER_TASK, "user.login");
		return params;
	}

	@Override
	protected Response<JConfiguration> parseNetworkResponse(NetworkResponse response) {
		try {
			String parsed = new String( response.data, HttpHeaderParser.parseCharset( response.headers ) );
			if( !TextUtils.isEmpty( parsed ) ) {
				if( parsed.startsWith( "logged in as" ) ) {
					final String[] responseParts = parsed.split( Pattern.quote( "|" ) );
					if( responseParts.length > 1 && responseParts[1].startsWith( "sid=" ) ) {
						String sessionId = responseParts[1].substring(4).trim();
						return Response.success( new JConfiguration( getJConfiguration().getHost(), sessionId, getJConfiguration().getUuId() ), HttpHeaderParser.parseCacheHeaders(response));
					} else {
						return Response.error( new VolleyError( "Response containe not enough information to be logged in!" ) );
					}
				} else {
					return Response.error( new VolleyError( parsed ) );
				}				
			}
			return Response.error( new VolleyError( "Response is empty!" ) );
		} catch( NullPointerException ne ) {
			return Response.error( new VolleyError( ne.getMessage() ) );
		} catch( UnsupportedEncodingException ue ) {
			return Response.error( new VolleyError( ue.getMessage() ) );
		}		
	}

}
