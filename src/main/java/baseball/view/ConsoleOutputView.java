package baseball.view;

import baseball.domain.scores.Scores;

public class ConsoleOutputView {

    public void print(final Scores scores) {
        int strike = scores.strike();
        int ball = scores.ball();

        if (strike == 0 && ball == 0) {
            System.out.println("낫싱");
            return;
        }
        System.out.println(scoresText(strike, ball));
    }

    private String scoresText(final int strike, final int ball) {
        StringBuilder sb = new StringBuilder();
        if (strike != 0) {
            sb.append(strike).append("스트라이크 ");
        }
        if (ball != 0) {
            sb.append(ball).append("볼");
        }
        return sb.toString();
    }

    public void printFinish() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
    }
}
