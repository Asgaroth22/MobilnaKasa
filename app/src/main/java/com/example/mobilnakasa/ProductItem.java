package com.example.mobilnakasa;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductItem implements Parcelable {
    String id;
    String name;
    Double price;
    Double vat;
    Double amount;
    String unit;

    public ProductItem(String id, String name, Double price, Double vat, Double amount, String unit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.vat = vat;
        this.amount = amount;
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(id);
        out.writeString(name);
        out.writeDouble(price);
        out.writeDouble(vat);
        out.writeDouble(amount);
        out.writeString(unit);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<ProductItem> CREATOR = new Parcelable.Creator<ProductItem>() {
        public ProductItem createFromParcel(Parcel in) {
            return new ProductItem(in);
        }

        public ProductItem[] newArray(int size) {
            return new ProductItem[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private ProductItem(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readDouble();
        vat = in.readDouble();
        amount=in.readDouble();
        unit=in.readString();
    }

}
