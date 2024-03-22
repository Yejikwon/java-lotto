package lotto;

import lotto.domain.LottoTicket;
import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static lotto.domain.LottoTicket.INVALID_LOTTO_NUMBER_INPUT;
import static lotto.domain.LottoTicket.INVALID_LOTTO_NUMBER_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumbersTest {

  @Test
  @DisplayName("당첨 번호 관리 기능 테스트")
  void winningNumbersTest() {
    Set<Integer> given = Set.of(1, 2, 3, 4, 5, 6);
    Integer given2 = 7;

    assertThat(WinningNumbers.of(given, given2).isSame(given)).isTrue();
  }

  @Test
  @DisplayName("1 ~ 45 내의 숫자가 들어왔는지 벨리데이션")
  void winningNumbersTest2() {
    Set<Integer> given = Set.of(100, 2, 3, 4, 5, 6);
    assertThatThrownBy(() -> LottoTicket.generate(given))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(INVALID_LOTTO_NUMBER_INPUT, given));
  }

  @Test
  @DisplayName("6개 숫자가 들어왔는지 벨리데이션")
  void winningNumbersTest3() {
    Set<Integer> given = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    assertThatThrownBy(() -> LottoTicket.generate(given))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(INVALID_LOTTO_NUMBER_SIZE, given));
  }
}
