package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.codegenerator;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
public class CodeGenerator {
    private static final Random RND = new Random(System.currentTimeMillis());

    public String generate(CodeConfig config) {
        StringBuilder sb = new StringBuilder();
        char[] chars = config.getCharset().toCharArray();
        char[] pattern = config.getPattern().toCharArray();

        if (config.getPrefix() != null) {
            sb.append(config.getPrefix());
        }

        for (char c : pattern) {
            if (c == CodeConfig.PATTERN_PLACEHOLDER) {
                sb.append(chars[RND.nextInt(chars.length)]);
            } else {
                sb.append(c);
            }
        }

        if (config.getPostfix() != null) {
            sb.append(config.getPostfix());
        }

        return sb.toString();
    }

    public List<String> generateCode(int codeLength,Long codeCount){
        var codes=new HashSet<String>();
        CodeConfig codeConfig = new CodeConfig(codeLength, null, null, null, null);
        while (codes.size()<codeCount){
            var code=generate(codeConfig);
            codes.add(code);
        }
        return codes.stream().toList();
    }
}
