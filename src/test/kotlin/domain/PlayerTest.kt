package domain

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Cards
import blackjack.domain.DrawState
import blackjack.domain.Player
import blackjack.domain.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `21점을 넘으면 추가적인 카드를 뽑지 못한다`() {
        val cards = Cards(
            listOf(
                Card(CardNumber.K, Shape.HEART),
                Card(CardNumber.K, Shape.SPADE)
            )
        )

        val player = Player(
            "우기",
            cards
        )

        assertThat(
            player.drawCard()
        ).isEqualTo(DrawState.IMPOSSIBLE)
    }
}