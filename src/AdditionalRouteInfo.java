public class AdditionalRouteInfo {
    private String weather;
    private String destinationInfo;

    public AdditionalRouteInfo(String weather, String destinationInfo) {
        this.weather = weather;
        this.destinationInfo = destinationInfo;
    }

    @Override
    public String toString() {
        return "Погода: " + weather + "\nИнформация о пункте назначения: "
                + destinationInfo + "\n";
    }
}
