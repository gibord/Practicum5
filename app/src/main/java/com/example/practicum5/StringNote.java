package com.example.practicum5;

import android.os.Parcel;
import android.os.Parcelable;

public class StringNote implements Parcelable {
    private String NoteIndex;

    protected StringNote(Parcel in) {
        NoteIndex = in.readString();
    }

    public static final Creator<StringNote> CREATOR = new Creator<StringNote>() {
        @Override
        public StringNote createFromParcel(Parcel in) {
            return new StringNote(in);
        }

        @Override
        public StringNote[] newArray(int size) {
            return new StringNote[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(NoteIndex);
    }
    public String getNoteIndex() {
        return NoteIndex;
    }

    public void setNoteIndex(String index) {
        this.NoteIndex = NoteIndex;
    }
}
