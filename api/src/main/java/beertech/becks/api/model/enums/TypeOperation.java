package beertech.becks.api.model.enums;

public enum TypeOperation {

    DEPOSIT("D"),
    WITHDRAW("S"),
    TRANSFER("T");

    private String description;

    TypeOperation(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static boolean isWITHDRAW(String description) {
        return WITHDRAW.getDescription().equals(description);
    }

    public static boolean isDEPOSIT(String description) {
        return DEPOSIT.getDescription().equals(description);
    }

    public static boolean isTransfer(String description) {
        return TRANSFER.getDescription().equals(description);
    }

    public static TypeOperation getTypeOperationByDescription(String description) {
        return isWITHDRAW(description) ? WITHDRAW : isDEPOSIT(description) ? DEPOSIT : TRANSFER;
    }

}

