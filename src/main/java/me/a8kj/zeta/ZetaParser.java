package me.a8kj.zeta;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.*;

import me.a8kj.zeta.function.ZetaFunction;
import me.a8kj.zeta.function.ZetaParameter;

public class ZetaParser {

    public static List<ZetaFunction> parseFunctions(String code) {
        List<ZetaFunction> functions = new ArrayList<>();
        Pattern pattern = Pattern.compile("fn\\s+(\\w+):(?:\\s*\\[(.*?)\\])?\\s*\\{(.*?)\\}", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(code);

        while (matcher.find()) {
            ZetaFunction function = new ZetaFunction();
            function.setName(matcher.group(1).trim());

            String paramBlock = matcher.group(2);
            if (paramBlock != null) {
                String[] paramLines = paramBlock.split(";\\s*");
                for (String paramLine : paramLines) {
                    paramLine = paramLine.strip();
                    if (!paramLine.isEmpty()) {
                        function.addParam(parseParam(paramLine + ";"));
                    }
                }
            }

            String bodyBlock = matcher.group(3);
            String[] bodyLines = bodyBlock.strip().split("\\n");
            for (String bodyLine : bodyLines) {
                function.addBodyLine(bodyLine.strip());
            }

            functions.add(function);
        }

        return functions;
    }

    private static ZetaParameter parseParam(String line) {
        ZetaParameter param = new ZetaParameter();

        Pattern pattern = Pattern
                .compile("~param\\(([^)]+)\\)\\s+as\\s+(\\w+)(?:\\s*=\\s*([^\\s;]+))?(?:\\s+if\\s+(.+))?;");
        Matcher matcher = pattern.matcher(line);

        if (!matcher.find())
            throw new RuntimeException("Invalid param syntax: " + line);

        param.setType(matcher.group(1).trim());
        param.setName(matcher.group(2).trim());
        param.setDefaultValue(matcher.group(3) != null ? matcher.group(3).trim() : null);
        param.setCondition(matcher.group(4) != null ? matcher.group(4).trim() : null);

        return param;
    }

    public static void main(String[] args) {
        try {
            InputStream inputStream = ZetaParser.class.getClassLoader().getResourceAsStream("script.zeta");
            if (inputStream == null) {
                throw new FileNotFoundException("script.zeta not found in resources folder");
            }
            String input = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            List<ZetaFunction> functions = parseFunctions(input);
            if (functions.isEmpty())
                throw new IllegalStateException("functions list is empty!");
            System.out.println("-----------------------------------");
            for (ZetaFunction fn : functions) {
                System.out.println(fn);
                System.out.println("-----------------------------------");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

}
