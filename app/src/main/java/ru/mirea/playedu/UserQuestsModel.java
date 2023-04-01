package ru.mirea.playedu;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class UserQuestsModel implements Parcelable {
    private String mtitle_txt;
    private String mdeadline_txt;
    private String mprice_txt;
    private Drawable mcolor;

    public UserQuestsModel(String title_txt, String deadline_txt, String price_txt, Drawable color) {
        mtitle_txt = title_txt;
        mdeadline_txt = deadline_txt;
        mprice_txt = price_txt;
        mcolor = color;
    }

    public UserQuestsModel(Parcel in) {
        mtitle_txt = in.readString();
        mdeadline_txt = in.readString();
        mprice_txt = in.readString();
        Bitmap bitmap = (Bitmap)in.readParcelable(getClass().getClassLoader());
        mcolor = new BitmapDrawable(bitmap);
    }

    public Drawable getColor() {
        return mcolor;
    }

    public String getDeadline_txt() {
        return mdeadline_txt;
    }

    public String getPrice_txt() {
        return mprice_txt;
    }

    public String getTitle_txt() {
        return mtitle_txt;
    }

    public void setCcolor_id(Drawable mcolor) {
        this.mcolor = mcolor;
    }

    public void setDeadline_txt(String mdeadline_txt) {
        this.mdeadline_txt = mdeadline_txt;
    }

    public void setPrice_txt(String mprice_txt) {
        this.mprice_txt = mprice_txt;
    }

    public void setTitle_txt(String mtitle_txt) {
        this.mtitle_txt = mtitle_txt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mtitle_txt);
        dest.writeString(mdeadline_txt);
        dest.writeString(mprice_txt);
        Bitmap bitmap = (Bitmap)((BitmapDrawable) mcolor).getBitmap();
        dest.writeParcelable(bitmap, flags);
    }

    public static final Parcelable.Creator<UserQuestsModel> CREATOR = new Parcelable.Creator<UserQuestsModel>() {

        @Override
        public UserQuestsModel createFromParcel(Parcel source) {
            return new UserQuestsModel(source);
        }

        @Override
        public UserQuestsModel[] newArray(int size) {
            return new UserQuestsModel[size];
        }
    };
}
