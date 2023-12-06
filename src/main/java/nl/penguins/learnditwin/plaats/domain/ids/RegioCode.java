package nl.penguins.learnditwin.plaats.domain.ids;

import lombok.Getter;
import nl.penguins.learnditwin.plaats.exception.OngeldigeRegioCodeException;

import java.util.Objects;

@Getter
public class RegioCode {
    private String regioCode;

    public RegioCode(String regioCode) {
        this.regioCode = regioCode;

        if (!this.isValideRegioCode()) {
            throw new OngeldigeRegioCodeException("Ongeldige regiocode. Een regiocode moet beginnen met 'GM' voor een gemeente, 'WK' voor een wijk, of 'BU' voor een buurt.");
        }
    }

    public RegioCode() {
    }

    // TODO : getGemeente variant, getWijk variant
    // TODO : test
    public RegioCode getGemeenteCode(){
        return  new RegioCode("GM" + regioCode.substring(2, 6));
    }

    public RegioCode getWijkCode(){
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

    @Override
    public int hashCode() {
        return regioCode != null ? regioCode.hashCode() : 0;
    }
}
