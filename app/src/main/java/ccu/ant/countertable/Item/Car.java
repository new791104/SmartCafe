package ccu.ant.countertable.Item;

/**
 * Created by Charlie on 2016/7/16.
 */
public class Car
{
    private String predictionTime;

    private String carNo;

    private String lon;

    private String lastInfo;

    private String isFull;

    private String stopId;

    private String name;

    private String seq;

    private String carLow;

    private String lat;

    public String getPredictionTime ()
    {
        return predictionTime;
    }

    public void setPredictionTime (String predictionTime)
    {
        this.predictionTime = predictionTime;
    }

    public String getCarNo ()
    {
        return carNo;
    }

    public void setCarNo (String carNo)
    {
        this.carNo = carNo;
    }

    public String getLon ()
    {
        return lon;
    }

    public void setLon (String lon)
    {
        this.lon = lon;
    }

    public String getLastInfo ()
    {
        return lastInfo;
    }

    public void setLastInfo (String lastInfo)
    {
        this.lastInfo = lastInfo;
    }

    public String getIsFull ()
    {
        return isFull;
    }

    public void setIsFull (String isFull)
    {
        this.isFull = isFull;
    }

    public String getStopId ()
    {
        return stopId;
    }

    public void setStopId (String stopId)
    {
        this.stopId = stopId;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getSeq ()
    {
        return seq;
    }

    public void setSeq (String seq)
    {
        this.seq = seq;
    }

    public String getCarLow ()
    {
        return carLow;
    }

    public void setCarLow (String carLow)
    {
        this.carLow = carLow;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [predictionTime = "+predictionTime+", carNo = "+carNo+", lon = "+lon+", lastInfo = "+lastInfo+", isFull = "+isFull+", stopId = "+stopId+", name = "+name+", seq = "+seq+", carLow = "+carLow+", lat = "+lat+"]";
    }
}
