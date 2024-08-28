import java.io.Serializable;

/**
 * Classe Coordenadas
 */
public class Coordenadas implements Serializable {
    private double latitude;
    private double longitude;

    /**
     * Construtor da classe, recebe dados para a inicialização
     * @param latitude Latitude
     * @param longitude Longetitude
     */
    public Coordenadas(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "latitude=" + latitude + "º, longitude=" + longitude+"º";
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
