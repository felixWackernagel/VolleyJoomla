package de.ewackernagel.volley.joomla.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class JArticle implements Parcelable {
	public static final Parcelable.Creator<JArticle> CREATOR = new Parcelable.Creator<JArticle>() {
		@Override
		public JArticle createFromParcel(Parcel source) {
			return new JArticle( source );
		}

		@Override
		public JArticle[] newArray(int size) {
			return new JArticle[ size ];
		}
	};
	
	private JArticle( Parcel source ) {
		readFromParcel( source );
	}
	
	private long id;
	private String title;
	private long cat_id;
	private String category;
	private int state;
	private String date_published;
	private String introtext;
	private String fulltext;
	
	public long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public long getCategoryId() {
		return cat_id;
	}
	
	public String getCategoryName() {
		return category;
	}
	
	public int getState() {
		return state;
	}
	
	public String getPublishingDate() {
		return date_published;
	}
	
	public String getIntroText() {
		return introtext;
	}
	
	public String getFullText() {
		return fulltext;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append( "JArticle [ " )
			.append( "id=" ).append( id ).append(", ")
			.append( "title=" ).append( title ).append(", ")
			.append( "categoryId=" ).append( cat_id ).append(", ")
			.append( "category=" ).append( category ).append(", ")
			.append( "state=" ).append( state ).append(", ")
			.append( "publishingDate=" ).append( date_published ).append(", ")
			.append( "introText=" ).append( introtext ).append(", ")
			.append( "fullText=" ).append( fulltext ).append(", ")
			.append( " ]" )
			.toString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong( id );
		dest.writeString(title);
		dest.writeLong(cat_id);
		dest.writeString(category);
		dest.writeInt(state);
		dest.writeString(date_published);
		dest.writeString(introtext);
		dest.writeString(fulltext);
	}
	
	private void readFromParcel( Parcel source ) {
		id = source.readLong();
		title = source.readString();
		cat_id = source.readLong();
		category = source.readString();
		state = source.readInt();
		date_published = source.readString();
		introtext = source.readString();
		fulltext = source.readString();
	}
}
