package domain

import blackjack.domain.BlackJackReferee
import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.PlayerGameResult
import blackjack.domain.PlayerName
import blackjack.domain.Shape
import blackjack.domain.TotalGameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackRefereeTest {

    @Test
    fun `딜러를 상대로 링링은 패배하고 우기는 승리하고 써니는 무승부`() {
        val woogi = Player(
            name = PlayerName("woogi"),
            cards = Cards(
                listOf(
                    Card(CardNumber.SEVEN, Shape.SPADE),
                    Card(CardNumber.K, Shape.SPADE)
                )
            )
        )

        val ring = Player(
            name = PlayerName("ring"),
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, Shape.HEART),
                    Card(CardNumber.ONE, Shape.DIAMOND)
                )
            )
        )

        val sunny = Player(
            name = PlayerName("sunny"),
            cards = Cards(
                listOf(
                    Card(CardNumber.EIGHT, Shape.HEART),
                    Card(CardNumber.TWO, Shape.DIAMOND)
                )
            )
        )

        val dealer = Dealer(
            Cards(
                listOf(
                    Card(CardNumber.SIX, Shape.DIAMOND),
                    Card(CardNumber.FOUR, Shape.CLOVER)
                )
            )
        )

        val blackJackReferee = BlackJackReferee()

        val actual = blackJackReferee.judgeTotalGameResults(listOf(woogi, ring, sunny), dealer)

        assertThat(actual).isEqualTo(
            TotalGameResult(
                playersGameResult = listOf(
                    PlayerGameResult("woogi", GameResult.WIN),
                    PlayerGameResult("ring", GameResult.LOSE),
                    PlayerGameResult("sunny", GameResult.DRAW)
                ),
                dealerGameResults = listOf(
                    GameResult.LOSE,
                    GameResult.WIN,
                    GameResult.DRAW
                )
            )
        )
    }
}
