package nl.penguins.learnditwin.plaats.domain;

import java.util.Objects;

public class RegioCode {
    private String regioCode;

    public RegioCode(String regioCode) {
        this.regioCode = regioCode;

        if (!this.isValideRegioCode()) throw new RuntimeException("regio code is niet valide");
    }

    public RegioCode() {
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

    public String getRegioCode() {
        return regioCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegioCode regioCode1 = (RegioCode) o;

        return Objects.equals(regioCode, regioCode1.regioCode);
    }

    @Override
    public int hashCode() {
        return regioCode != null ? regioCode.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RegioCode{" +
                "regioCode='" + regioCode + '\'' +
                '}';
    }
}
