package com.github.jetqin.domain.mongodb;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Document(collection = "todayStock")
public class Stocks
{
    @Id
    private String id;

    private String code;

    private String name;

    private BigDecimal changepercent;

    private BigDecimal trade;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal settlement;

    private BigDecimal volume;

    private BigDecimal turnoverratio;

    private BigDecimal amount;

    private BigDecimal per;

    private BigDecimal pb;

    private BigDecimal mktcap;

    private BigDecimal nmc;

    public Stocks() {}

    public Stocks(String code, String name, BigDecimal trade)
    {
        this.code = code;
        this.name = name;
        this.trade = trade;
    }

    public Stocks(String id, String code, String name, BigDecimal changepercent, BigDecimal trade, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal settlement, BigDecimal volume, BigDecimal turnoverratio, BigDecimal amount, BigDecimal per, BigDecimal pb, BigDecimal mktcap, BigDecimal nmc)
    {
        this.id = id;
        this.code = code;
        this.name = name;
        this.changepercent = changepercent;
        this.trade = trade;
        this.open = open;
        this.high = high;
        this.low = low;
        this.settlement = settlement;
        this.volume = volume;
        this.turnoverratio = turnoverratio;
        this.amount = amount;
        this.per = per;
        this.pb = pb;
        this.mktcap = mktcap;
        this.nmc = nmc;
    }

    @Override
    public String toString()
    {
        return "Stocks{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", changepercent=" + changepercent +
                ", trade=" + trade +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", settlement=" + settlement +
                ", volume=" + volume +
                ", turnoverratio=" + turnoverratio +
                ", amount=" + amount +
                ", per=" + per +
                ", pb=" + pb +
                ", mktcap=" + mktcap +
                ", nmc=" + nmc +
                '}';
    }
}
