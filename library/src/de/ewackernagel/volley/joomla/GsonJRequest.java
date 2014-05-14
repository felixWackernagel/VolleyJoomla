package de.ewackernagel.volley.joomla;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import de.ewackernagel.volley.joomla.pojos.JConfiguration;

public class GsonJRequest<T> extends JRequest<T> {
	private final Gson gson;
	private final Class<T> clazz;
	
	public GsonJRequest( JConfiguration configuration, String task, Class<T> clazz, Map<String, String> params, Map<String, String> headers, Listener<T> successListener, ErrorListener errorListener) {
		super(configuration, task, params, headers, successListener, errorListener);
		this.clazz = clazz;
		
		final GsonBuilder gsonBuilder = new GsonBuilder();
		onConfigureTypeAdapters( gsonBuilder );
		gson = gsonBuilder.create();
	}
	
	public void onConfigureTypeAdapters( GsonBuilder gsonBuilder ) {
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String json = new String( response.data, HttpHeaderParser.parseCharset( response.headers ) );
			return Response.success( gson.fromJson( json, clazz ), HttpHeaderParser.parseCacheHeaders(response) );
		} catch( UnsupportedEncodingException e ) {
			return Response.error( new ParseError( e ) );
		} catch( JsonSyntaxException e ) {
			return Response.error( new ParseError( e ) );
		}
	}

}
