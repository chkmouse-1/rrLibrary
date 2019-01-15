package libraries.renrui.com.model;

public class Testa {

    private String AAA;

    public String getAAA() {
        return AAA;
    }

    public void setAAA(String value) {
        this.aaaIsSetValue = true;
        this.AAA = value;
    }

    private boolean aaaIsSetValue = false;

    public boolean getAAAIsSetValue() {
        return aaaIsSetValue;
    }

    private BBB bbb;

    public BBB getBbb() {
        return bbb == null ? new BBB() : bbb;
    }
}
