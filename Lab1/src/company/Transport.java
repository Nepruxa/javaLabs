package company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Transport {
    private int type;
    private int number;
    private int kilometers;
    private int addInfo;
    public double costs;
    private final Pattern typePattern = Pattern.compile("(?<=C)\\d{3}");
    private final Pattern numberPattern = Pattern.compile("(?<=_)\\d*");
    private final Pattern kilometersPattern = Pattern.compile("(?<=C\\d{3}_\\d-)\\d*");
    private final Pattern addInfoPattern = Pattern.compile("(?<=-)\\d*$");
    String carData;

    public Transport(String data) {
        carData = data;
    }

    private String parseType(String data) {
        String parsedType = null;
        try {
            Matcher matcher = typePattern.matcher(data);
            if (matcher.find()) {
                parsedType = matcher.group();
            }
        } catch (IllegalStateException exception) {
            exception.printStackTrace();
        }
        return parsedType;
    }

    private String parseNumber(String data) {
        String parsedNumber = null;
        try {
            Matcher matcher = numberPattern.matcher(data);
            if (matcher.find()) {
                parsedNumber = matcher.group();
            }
        } catch (IllegalStateException exception) {
            exception.printStackTrace();
        }
        return parsedNumber;
    }

    private String parseKilometers(String data) {
        String parsedKilometers = null;
        try {
            Matcher matcher = kilometersPattern.matcher(data);
            if (matcher.find()) {
                parsedKilometers = matcher.group();
            }
        } catch (IllegalStateException exception) {
            exception.printStackTrace();
        }
        return parsedKilometers;
    }

    private String parseAddInfo(String data) {
        String parsedAddInfo = null;
        if (parseType(data).equals("100")) {
            parsedAddInfo = "0000";
        } else {
            try {
                Matcher matcher = addInfoPattern.matcher(data);
                if (matcher.find()) {
                    parsedAddInfo = matcher.group();
                }
            } catch (IllegalStateException exception) {
                exception.printStackTrace();
            }
        }
        return parsedAddInfo;
    }

    public void setCarData() {
        type = Integer.parseInt(parseType(carData));
        number = Integer.parseInt(parseNumber(carData));
        kilometers = Integer.parseInt(parseKilometers(carData));
        addInfo = Integer.parseInt(parseAddInfo(carData));
    }

    public int getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public int getKilometers() {
        return kilometers;
    }

    public int getAddInfo() {
        return addInfo;
    }

    public double computeCostsFor100() {
        return 46.10 * (kilometers * (12.5 / 100.));
    }

    public double computeCostsFor200() {
        return 48.90 * (kilometers * (12. / 100.));
    }

    public double computeCostsFor300() {
        return 47.50 * (kilometers * (11.5 / 100.));
    }

    public double computeCostsFor400() {
        return 48.90 * (kilometers * (20. / 100.));
    }
}
