package de.ewackernagel.volley.joomla.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class JConfiguration implements Parcelable {
	public static final Parcelable.Creator<JConfiguration> CREATOR = new Parcelable.Creator<JConfiguration>() {
		@Override
		public JConfiguration createFromParcel(Parcel source) {
			return new JConfiguration( source );
		}

		@Override
		public JConfiguration[] newArray(int size) {
			return new JConfiguration[ size ];
		}
	};

	private final String host;
	private final String sessionId;
	private final String uuId;
	
	public JConfiguration( String host, String sessionId, String uuId ) {
		this.host = host;
		this.sessionId = sessionId;
		this.uuId = uuId;
	}
	
	private JConfiguration( Parcel source ) {
		host = source.readString();
		sessionId = source.readString();
		uuId = source.readString();
	}
	
	public String getHost() {
		return host;
	}
	
	public boolean hasHost() {
		return host != null;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public boolean hasSessionId() {
		return sessionId != null;
	}
	
	public String getUuId() {
		return uuId;
	}
	
	public boolean hasUuId() {
		return uuId != null;
	}

	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append( "JConfiguration [ " )
			.append( "host=" ).append( host ).append(", ")
			.append( "sessionId=" ).append( sessionId ).append(", ")
			.append( "uuId=" ).append( uuId ).append(", ")
			.append( " ]" )
			.toString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(host);
		dest.writeString(sessionId);
		dest.writeString(uuId);
	}
}
