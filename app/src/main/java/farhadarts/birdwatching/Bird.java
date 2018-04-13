package farhadarts.birdwatching;

import java.io.Serializable;

/**
 * Created by art_i on 4/12/2018.
 */

public class Bird implements Serializable {

   // @SerializedName("BirdId") // Name of JSON attribute. Used for GSON de-serialization
    private int birdId;

//    @SerializedName("Id")
//    private int id;
//
//    @SerializedName("Latitude")
    private double latitiude;
//
//    @SerializedName("Longitude")
   private double longitudue;
//
//    @SerializedName("Placename")
//    private String placeName;
//
//    @SerializedName("UserId")
//    private int userId;
//
//    @SerializedName("NameDanish")
   private String nameDanish;
//
//    @SerializedName("NameEnglish")
   private String nameEnglish;



    public Bird() {
    }

    public Bird(int birdId, double latitiude, double longitudue, String nameDanish, String nameEnglish){

        this.birdId = birdId ;
        this.latitiude = latitiude;
        this.longitudue = longitudue;
        this.nameDanish = nameDanish;
        this.nameEnglish = nameEnglish;
    }

    public int getBirdId() {
        return birdId ;
    }
    public double getBirdLatitude() {
        return latitiude  ;
    }public double getBirdLongitude() {
        return longitudue ;
    }public String getBirdNameDanish() {
        return nameDanish ;
    }public String getBirdNameEnglish() {
        return nameEnglish ;
    }

    @Override
    public String toString() {return birdId + nameDanish;}


//    public Bird(int birdId, int id, double latitiude, double longitudue, String placeName, int userId, String nameDanish, String nameEnglish) {
//        this.birdId = birdId ;
//        this.id = id;
//        this.latitiude = latitiude;
//        this.longitudue = longitudue;
//        this.placeName = placeName;
//        this.userId = userId;
//        this.nameDanish = nameDanish;
//        this.nameEnglish = nameEnglish;
//    }

}
