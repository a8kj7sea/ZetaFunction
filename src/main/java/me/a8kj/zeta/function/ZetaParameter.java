package me.a8kj.zeta.function;

import lombok.Data;

@Data
public class ZetaParameter {
    String name;
    String type;
    String condition;
    String defaultValue;

    public String toString() {
        return "Param(name=" + name + ", type=" + type +
                ", default=" + defaultValue + ", condition=" + condition + ")";
    }
}
