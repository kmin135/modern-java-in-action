package chap05;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MakeStreamTest {
    @Test
    void fromValue() {
        Stream<String> stream = Stream.of("Modern ", "Java ", "In ", "Action");
        String join = stream.map(String::toUpperCase).collect(Collectors.joining());
        Assertions.assertThat(join).isEqualTo("MODERN JAVA IN ACTION");
    }

    @Test
    @DisplayName("java9부터 null일 수 잇는 개체를 스트림으로 만들 수 있음")
    void nullable() {
        String isnull = System.getProperty("i am null");
        Stream<String> nullStream = Stream.ofNullable(isnull);
        long count = nullStream.count();
        Assertions.assertThat(count).isEqualTo(0);

        Stream<String> mixStream = Stream.of("java.home", "i am null", "os.version");
        Stream<String> validStream = mixStream.flatMap(k -> Stream.ofNullable(System.getProperty(k)));
        Assertions.assertThat(validStream.count()).isEqualTo(2);
    }

    @Test
    @DisplayName("파일에서 단어 수 세기")
    void countWordFromFile() throws IOException {
        long wordCount = Files.lines(Paths.get("README.md"))
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .count();

        System.out.println(wordCount);
    }

    @Test
    void infiniteStream() {
        // 무한 스트림. 언바운드 스트림이라고도 부름
        // 보통 limit나 takeWhile 등으로 원하는 만큼 끊어서 사용한다.
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    void infiniteStream2() {
        /*
        generate는 iterate와 같이 무한스트림을 만든다.
        다만 iterate와 달리 generate 는 생상된 각 값을 연속적으로 계산하지 않는다.
        대신 Supplier<T> 를 통해 새로운 값을 생산한다.
         */
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);
    }
}
