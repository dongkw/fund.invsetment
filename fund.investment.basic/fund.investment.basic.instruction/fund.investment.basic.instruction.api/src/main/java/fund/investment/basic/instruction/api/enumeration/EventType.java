package fund.investment.basic.instruction.api.enumeration;

public enum EventType {

    APPROVAL_REJECT("APPROVAL_REJECT", "审批拒绝"),
    DISTRIBUTE_REJECT("DISTRIBUTE_REJECT","分发拒绝");

    EventType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
