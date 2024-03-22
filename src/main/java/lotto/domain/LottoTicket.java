package lotto.domain;

import java.util.*;

public class LottoTicket {

  private static final int LOTTO_MIN_NUMBER = 1;
  private static final int LOTTO_MAX_NUMBER = 45;
  private static final List<Integer> POSSIBLE_LOTTO_NUMBER_CANDIDATES = makeCandidateNumbers();
  public static final String INVALID_LOTTO_NUMBER_INPUT = "해당 숫자는 로또 번호 범위가 아닙니다. 번호를 다시 확인해주세요. input: %s";
  public static final String INVALID_LOTTO_NUMBER_SIZE = "입력 개수를 다시 확인해주세요. input: %s";

  private final Set<Integer> lottoNumbers;

  private LottoTicket(Set<Integer> lottoNumbers) {

    if (Boolean.FALSE.equals(checkNumbersRange(lottoNumbers))) {
      throw new IllegalArgumentException(String.format(INVALID_LOTTO_NUMBER_INPUT, lottoNumbers));
    }

    if (lottoNumbers.size() != 6) {
      throw new IllegalArgumentException(String.format(INVALID_LOTTO_NUMBER_SIZE, lottoNumbers));
    }

    this.lottoNumbers = lottoNumbers;
  }

  public static Set<LottoTicket> generate(int ticketCount) {
    Set<LottoTicket> lottoTickets = new HashSet<>();
    for (int i = 0; i < ticketCount; i++) {
      Collections.shuffle(POSSIBLE_LOTTO_NUMBER_CANDIDATES);
      lottoTickets.add(new LottoTicket(new HashSet<>(POSSIBLE_LOTTO_NUMBER_CANDIDATES.subList(0, 6))));
    }
    return lottoTickets;
  }

  public static LottoTicket generate(Set<Integer> winningNumbers) {
    return new LottoTicket(winningNumbers);
  }

  public int size() {
    return lottoNumbers.size();
  }

  public boolean haveCorrectNumbers() {
    return checkNumbersRange(this.lottoNumbers);
  }

  public boolean isSame(Set<Integer> numbers) {
    return this.lottoNumbers.equals(numbers);
  }

  public int getMatchCount(LottoTicket myLottoTicket) {
    return (int) this.lottoNumbers.stream()
        .filter(myLottoTicket.lottoNumbers::contains)
        .count();
  }

  public boolean contain(int value) {
    return this.lottoNumbers.contains(value);
  }

  private static List<Integer> makeCandidateNumbers() {
    final List<Integer> candidates = new ArrayList<>();
    for (int lottoNumber = LOTTO_MIN_NUMBER; lottoNumber <= LOTTO_MAX_NUMBER; lottoNumber++) {
      candidates.add(lottoNumber);
    }
    return candidates;
  }

  private boolean checkNumbersRange(Set<Integer> numbers) {
    return numbers.stream()
        .allMatch(number -> LOTTO_MIN_NUMBER <= number && number <= LOTTO_MAX_NUMBER);
  }

  @Override
  public String toString() {
    return lottoNumbers.toString();
  }
}
