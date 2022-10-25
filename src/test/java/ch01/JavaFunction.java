package ch01;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class JavaFunction {
    @Test
    void oldPrintHiddenFiles() {
        File[] hiddenFiles = new File(System.getProperty("user.home")).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });
        for(File hidden : hiddenFiles) {
            System.out.println(hidden);
        }
    }

    @Test
    void newPrintHiddenFiles() {
        // 메서드 참조 사용 (이 메서드를 값으로 사용하라) 를 사용
        // java8에서는 메서드를 일급값으로 취급할 수 있기 때문에 이와 같이 사용이 가능
        Arrays.stream(new File(System.getProperty("user.home")).listFiles(File::isHidden)).forEach(System.out::println);
    }

    @Test
    void encoding() {
        String a = "가";
        System.out.println(a.getBytes(StandardCharsets.UTF_8).length);
        String b = "\uFEFF\uD83D\uDE01";
        // java의 String encoding UTF16-BE (Big Endian)
        System.out.println(b);
        System.out.println(b.getBytes(StandardCharsets.UTF_8).length);
        System.out.println(b.getBytes(StandardCharsets.UTF_16BE));
        System.out.println(b.codePointAt(0));
    }
}
