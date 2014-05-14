package de.ewackernagel.volley.joomla;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import de.ewackernagel.volley.joomla.pojos.JArticle;
import de.ewackernagel.volley.joomla.pojos.JConfiguration;

public class JArticleDetailsRequest extends GsonJRequest<JArticle> {
	private static final String PARAMETER_ARTICLE_ID = "artid";
	
	private static final String URL_TASK_SEGMENT = "api/content.php?task=getarticle";

	public JArticleDetailsRequest(JConfiguration configuration, JArticle jArticle, Listener<JArticle> successListener, ErrorListener errorListener) {
		this(configuration, jArticle.getId(), successListener, errorListener);
	}
	
	public JArticleDetailsRequest(JConfiguration configuration, long jArticleId, Listener<JArticle> successListener, ErrorListener errorListener) {
		super(configuration, URL_TASK_SEGMENT, JArticle.class, buildParams( jArticleId ), null, successListener,	errorListener);
	}

	private static Map<String, String> buildParams( long jArticleId) {
		final Map<String, String> params = new HashMap<String, String>( 1 );
		params.put( PARAMETER_ARTICLE_ID, String.valueOf( jArticleId ) );
		return params;
	}

}
