package christmas.domain.menu;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MenuCategoryTest {

    @ParameterizedTest
    @MethodSource("provideTestData")
    @DisplayName("정상적인 메뉴이름을 넘길 시 메뉴enum을 반환한다")
    void getDishDataByName(String name, Optional<MenuCategory> expected) {
        Optional<MenuCategory> dishDataByName = MenuCategory.getDishDataByName(name);
        Assertions.assertThat(dishDataByName).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of("아이스크림", Optional.of(MenuCategory.ICE_CREAM)),
                Arguments.of("타파스", Optional.of(MenuCategory.TAPAS)),
                Arguments.of("제로콜라", Optional.of(MenuCategory.ZERO_COLA))
        );
    }

    @Test
    @DisplayName("메뉴에 없는 음식을 넘길시 null을 반환해야한다")
    void getDishDataByWrongName() {
        // given
        String name = "뿌슝뿌슝";
        // when
        Optional<MenuCategory> dishDataByName = MenuCategory.getDishDataByName(name);
        // then
        Assertions.assertThat(dishDataByName).isEqualTo(Optional.empty());
    }

}