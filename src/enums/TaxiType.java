package enums;

import java.math.BigDecimal;

public enum TaxiType {
    STANDART(BigDecimal.valueOf(10),BigDecimal.valueOf(100)),
    COMFORT(BigDecimal.valueOf(20),BigDecimal.valueOf(200)),
    BUSINESS(BigDecimal.valueOf(30),BigDecimal.valueOf(300));
    BigDecimal pricePerKm;
    BigDecimal priceForLanding;

    TaxiType(BigDecimal pricePerKm, BigDecimal priceForLanding) {
        this.pricePerKm = pricePerKm;
        this.priceForLanding = priceForLanding;
    }

    public BigDecimal getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(BigDecimal pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public BigDecimal getPriceForLanding() {
        return priceForLanding;
    }

    public void setPriceForLanding(BigDecimal priceForLanding) {
        this.priceForLanding = priceForLanding;
    }
}
