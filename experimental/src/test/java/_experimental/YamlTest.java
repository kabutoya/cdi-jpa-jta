package _experimental;

import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class YamlTest {

    private static final String FORMAT_KEY = "key=%s";
    private static final String FORMAT_VAL = "val=%s";

    //@Test
    public void experiment_1() {
        Yaml yaml = new Yaml();
//        String document = "  a: 1\n  b:\n    c: 3\n    d: 4\n";
        StringBuilder builder = new StringBuilder();
//        builder.append("  a: 1").append("\n");
//        builder.append("  b: ").append("\n");
//        builder.append("    c: 3").append("\n");
//        builder.append("    d: 4").append("\n");
        builder.append("a: 1").append("\n");
        builder.append("b: ").append("\n");
        builder.append(" c: 3").append("\n");
        builder.append(" d: 4").append("\n");
        String document = builder.toString();
        System.out.println(document);
        System.out.println(yaml.dump(yaml.load(document)));
        Map<String, Object> yamlMap = yaml.loadAs(document, Map.class);
        yamlMap.entrySet().stream().forEach(e1 -> {
            System.out.println(String.format(FORMAT_KEY, e1.getKey()));
            if (e1.getValue() instanceof Map) {
                ((Map<String, Object>) e1.getValue()).entrySet().stream().forEach(e2 -> {
                    System.out.println(String.format("    " + FORMAT_KEY, e2.getKey()));
                    System.out.println(String.format("    " + FORMAT_VAL, e2.getValue()));
                });
            } else {
                System.out.println(String.format(FORMAT_VAL, e1.getValue()));
            }
        });
    }
}
