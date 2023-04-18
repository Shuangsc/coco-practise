package com.coco.common;

public final class ConsumptionTypeHolder {


    private String consumptionType;


    private final static ConsumptionTypeHolder INSTANCE = new ConsumptionTypeHolder();

    private ConsumptionTypeHolder() {}

    public static ConsumptionTypeHolder getInstance() {
        return INSTANCE;
    }

    public void setConsumptionType(String consumptionType) {
        this.consumptionType = consumptionType;
    }

    public String getConsumptionType() {
        return this.consumptionType;
    }

}