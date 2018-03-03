package truckstationsa.truckstation;

/**
 * Created by manal on 2/8/2018.
 */

public class Rate {
    private String FID, CID;
    private double ratingValue;

    public Rate(String CID, String FID, double ratingValue) {
        this.setFID(FID);
        this.setCID(CID);
        this.setRatingValue(ratingValue);

    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public void setRatingValue(double ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getCID() {
        return CID;
    }

    public String getFID() {
        return FID;
    }

    public double getRatingValue() {
        return ratingValue;
    }
}
    /* <Button
    android:id="@+id/CustomerBtnLogin"
    style="@style/TextAppearance.AppCompat.Body1"
    android:layout_width="22dp"
    android:layout_height="22dp"
    android:layout_marginTop="33dp"
    android:layout_marginLeft="2dp"
    android:background="@drawable/background_login"
    android:clickable="true"
    android:focusable="true"
    android:layout_marginStart="2dp"
    android:padding="16dp"
    android:text="@string/log_in"
    android:textAllCaps="false"
    android:textColor="#242423"

            />/*
}
<LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="126dp">

            <ImageView
                android:id="@+id/dogimage"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentLeft="true"
                android:layout_alignParentStart="true"
                android:layout_alignParentTop="true"
                card_view:srcCompat="@mipmap/ic_launcher_round" />
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal"
                android:paddingLeft="2dp">


                <TextView
                    android:id="@+id/nameTxt"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_alignParentTop="true"
                    android:layout_marginStart="133dp"
                    android:text="TextView"
                    android:textSize="18sp"
                    android:layout_marginLeft="122dp" />


            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal"
                android:paddingLeft="2dp">


                <RatingBar
                    android:id="@+id/ratingBar"
                    style="?android:attr/ratingBarStyleIndicator"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_alignParentBottom="true"
                    android:layout_centerHorizontal="true"
                    android:layout_gravity="center"
                    android:layout_marginBottom="12dp"
                    android:numStars="5"
                    android:progressTint="#ffdb4d"
                    android:rating="4"
                    android:stepSize="0"
                    android:layout_marginStart="126dp"
                    android:layout_marginLeft="126dp"
                    android:layout_marginTop="49dp"
                    />

                <TextView
                    android:id="@+id/numrating"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"

                    android:layout_marginBottom="8dp"
                    android:layout_marginLeft="18dp"
                    android:layout_marginStart="16dp"
                    android:layout_marginTop="33pt"
                    android:layout_toEndOf="@+id/ratingBar"
                    android:layout_toRightOf="@+id/ratingBar"
                    android:text="Text"

                    />

            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="9dp"
                android:layout_marginLeft="100dp"
                android:layout_marginTop="100px"
                android:orientation="horizontal"
                android:paddingLeft="25dp">

                <TextView
                    android:id="@+id/cusin"
                    android:layout_width="63dp"
                    android:layout_height="match_parent"

                    android:layout_alignBottom="@+id/dogimage"
                    android:layout_alignLeft="@+id/ratingBar"
                    android:layout_alignStart="@+id/ratingBar"
                    android:layout_marginLeft="12dp"
                    android:layout_marginStart="12dp"
                    android:textAlignment="center"
                    android:text="cusin" />

                <ImageView
                    android:id="@+id/imageView2"
                    android:layout_width="10pt"
                    android:layout_height="10pt"

                    android:layout_gravity="center_horizontal"

                    android:layout_marginLeft="-10dp"

                    android:layout_marginStart="0dp"
                    android:src="@drawable/food" />

                <Button
                    android:id="@+id/button4"
                    android:layout_width="104dp"
                    android:layout_height="33dp"
                    android:layout_weight="1"
                    android:text="Button" />
            </LinearLayout>


        </RelativeLayout>
    </LinearLayout>*/
