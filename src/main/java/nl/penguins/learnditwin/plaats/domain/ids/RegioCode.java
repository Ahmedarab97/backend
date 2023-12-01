package nl.penguins.learnditwin.plaats.domain.ids;

import nl.penguins.learnditwin.plaats.exception.OngeldigeRegioCodeException;

import java.util.Objects;

public record RegioCode(String regioCode) {
    public RegioCode(String regioCode) {
        this.regioCode = regioCode;

        if (!this.isValideRegioCode()) {
            throw new OngeldigeRegioCodeException("Ongeldige regiocode. Een regiocode moet beginnen met 'GM' voor een gemeente, 'WK' voor een wijk, of 'BU' voor een buurt.");
        }
    }

    public RegioCode getGemeenteCode() {
        return new RegioCode("GM" + regioCode.substring(2, 6));
    }

    public RegioCode getWijkCode() {
        if(this.isGemeente()) throw new OngeldigeRegioCodeException
                ("Van een gemeente is geen wijk niveau te vinden");
        return new RegioCode("WK" + regioCode.substring(2, 8));
    }

    public boolean isValideRegioCode() {
        return (this.isGemeente() || this.isWijk() || this.isBuurt());
    }

    public boolean isGemeente() {
        return getRegioKarakters().equals("GM");
    }

    public boolean isBuurt() {
        return getRegioKarakters().equals("BU");
    }

    public boolean isWijk() {
        return getRegioKarakters().equals("WK");
    }

    public String getRegioKarakters() {
        return this.regioCode.substring(0, 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegioCode regioCode1 = (RegioCode) o;

        return Objects.equals(regioCode, regioCode1.regioCode);
    }
}
