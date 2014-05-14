package de.ewackernagel.volley.joomla.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class JCategory implements Parcelable {
	public static final Parcelable.Creator<JCategory> CREATOR = new Parcelable.Creator<JCategory>() {
		@Override
		public JCategory createFromParcel(Parcel source) {
			return new JCategory( source );
		}

		@Override
		public JCategory[] newArray(int size) {
			return new JCategory[ size ];
		}
	};
	
	private JCategory( Parcel source ) {
		readFromParcel( source );
	}

	private long id;
	private long parent_id;
	private String title;
	
	public long getId() {
		return id;
	}
	
	public long getParentId() {
		return parent_id;
	}
	
	public String getTitle() {
		return title;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append( "JCategory [ " )
			.append( "id=" ).append( id ).append(", ")
			.append( "parentId=" ).append( parent_id ).append(", ")
			.append( "title=" ).append( title ).append(", ")
			.append( " ]" )
			.toString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeLong(parent_id);
		dest.writeString(title);
	}
	
	private void readFromParcel(Parcel source) {
		id = source.readLong();
		parent_id = source.readLong();
		title = source.readString();
	}
}
