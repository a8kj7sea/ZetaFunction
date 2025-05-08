package me.a8kj.zeta.function;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ZetaFunction {
    String name;
    List<ZetaParameter> params = new ArrayList<>();
    List<String> body = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Function name: ").append(name).append("\n");
        for (ZetaParameter p : params)
            sb.append(p).append("\n");
        sb.append("Body:\n");
        for (String line : body)
            sb.append("  ").append(line).append("\n");
        return sb.toString();
    }

    public void addParam(ZetaParameter param) {
        this.params.add(param);
    }

    public void addBodyLine(String line) {
        this.body.add(line);
    }

}
