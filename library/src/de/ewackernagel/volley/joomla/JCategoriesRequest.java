package de.ewackernagel.volley.joomla;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import de.ewackernagel.volley.joomla.pojos.JCategory;
import de.ewackernagel.volley.joomla.pojos.JConfiguration;

public class JCategoriesRequest extends GsonJRequest<JCategory[]> {

	public JCategoriesRequest(JConfiguration configuration, Listener<JCategory[]> successListener, ErrorListener errorListener) {
		super(configuration, "api/content.php?task=getcats", JCategory[].class, null, null, successListener,	errorListener);
	}

}
