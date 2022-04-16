package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MyParametrizeTest {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
    }

    @ValueSource(strings = {
            "Парки Москвы",
            "Аренда"
    })
    @ParameterizedTest(name = "Проверка поиска Мос.ру {0}")
    void mosSearchTest(String dataTest) {
        Selenide.open("https://www.mos.ru/search?category=common&page=1");
        $(".mos-search__input").setValue(dataTest);
        $(".mos-search__button").click();
        $$(".search-result-item").find(text(dataTest)).shouldBe(visible);
    }

    @CsvSource(value = {
            "Aleksey | azaza@aol.ru | Moscow, Orehovo station | Kursk, Kursk",
            "Vadim | vaddddd33@mail.com | Bryansk, priborov | Voroneg, Revolutsii"
    },
            delimiter = '|')
    @ParameterizedTest(name = "Проверка формы регистрации {0}")
    void RegistrationFormTest(String nameInfo, String emailInfo, String currentAddress, String permanentAddress) {
        Selenide.open("https://demoqa.com/text-box");
        $("#userName").setValue(nameInfo);
        $("#userEmail").setValue(emailInfo);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();

        $$("#name.mb-1").find(text(nameInfo)).shouldBe(visible);
        $$("#email.mb-1").find(text(emailInfo)).shouldBe(visible);
        $$("#currentAddress.mb-1").find(text(currentAddress)).shouldBe(visible);
        $$("#permanentAddress.mb-1").find(text(permanentAddress)).shouldBe(visible);
    }

    static Stream<Arguments> methodSourceExampleTest() {
        return Stream.of(
                Arguments.of("first str", List.of(112, 64)),
                Arguments.of("second str", List.of(89, 66))
        );
    }

    @MethodSource("methodSourceExampleTest")
    @ParameterizedTest
    void methodSourceExampleTest(String s, List<Integer> list) {
        System.out.println(s + "and list" + list);
    }
}
