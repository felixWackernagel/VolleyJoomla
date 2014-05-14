package de.ewackernagel.volley.joomla;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import de.ewackernagel.volley.joomla.pojos.JArticle;
import de.ewackernagel.volley.joomla.pojos.JCategory;
import de.ewackernagel.volley.joomla.pojos.JConfiguration;

public class JArticlesRequest extends GsonJRequest<JArticle[]> {
	private static final String PARAMETER_CATEGORY_ID = "catid";
	
	private static final String URL_TASK_SEGMENT = "api/content.php?task=getarticlelist";

	public JArticlesRequest(JConfiguration configuration, JCategory jCategory, Listener<JArticle[]> successListener, ErrorListener errorListener) {
		this(configuration, jCategory.getId(), successListener, errorListener);
	}
	
	public JArticlesRequest(JConfiguration configuration, long jCategoryId, Listener<JArticle[]> successListener, ErrorListener errorListener) {
		super(configuration, URL_TASK_SEGMENT, JArticle[].class, buildParams( jCategoryId ), null, successListener,	errorListener);
	}

	private static Map<String, String> buildParams( long jCategoryId) {
		final Map<String, String> params = new HashMap<String, String>( 1 );
		params.put( PARAMETER_CATEGORY_ID, String.valueOf( jCategoryId ) );
		return params;
	}

}
