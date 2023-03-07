package domain

import blackjack.domain.card.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class CardsTest {

    @Test
    fun `게임을 시작할때 카드가 두장이 아니면 예외를 발생시킨다`() {
        assertThrows<IllegalArgumentException> {
            Cards(listOf(Card(CardNumber.A, Shape.SPADE)))
        }
    }

    @Test
    fun `게임을 시작할때 카드가 두장이어야한다`() {
        assertDoesNotThrow {
            Cards(listOf(Card(CardNumber.A, Shape.SPADE), Card(CardNumber.A, Shape.HEART)))
        }
    }

    @Test
    fun `Ace를 1로 처리했을 떄의 합을 구한다`() {
        assertThat(
            Cards(
                listOf(
                    Card(CardNumber.K, Shape.HEART),
                    Card(CardNumber.A, Shape.SPADE)
                )
            ).getMinimumCardsScore()
        ).isEqualTo(11)
    }

    @Test
    fun `가지고 있는 카드 중 Ace 카드가 한장인 경우 무조건 11로 계산`() {
        assertThat(
            Cards(
                listOf(
                    Card(CardNumber.A, Shape.HEART),
                    Card(CardNumber.SEVEN, Shape.SPADE)
                )
            ).getTotalCardsScore()
        ).isEqualTo(18)
    }

    @Test
    fun `가지고 있는 Ace 카드가 2장 이상이면 한장만 11, 나머지는 1로 점수 계산`() {
        assertThat(
            Cards(
                listOf(
                    Card(CardNumber.A, Shape.HEART),
                    Card(CardNumber.A, Shape.SPADE)
                )
            ).getTotalCardsScore()
        ).isEqualTo(12)
    }

    @Test
    fun `처음 받은 두장의 카드 합이 21인 경우 BLACKJACK 상태가 된다`() {
        val cards = Cards(
            listOf(
                Card(CardNumber.K, Shape.SPADE),
                Card(CardNumber.A, Shape.CLOVER)
            )
        )

        assertThat(cards.state).isEqualTo(CardsState.BlackJack)
    }

    @Test
    fun `가지고 있는 카드의 최소 합이 21이하인 경우 RUNNING 상태이다`() {
        val cards = Cards(
            listOf(
                Card(CardNumber.K, Shape.SPADE),
                Card(CardNumber.FOUR, Shape.CLOVER)
            )
        )

        cards.draw(Card(CardNumber.FOUR, Shape.SPADE))

        assertThat(cards.state).isEqualTo(CardsState.Running)
    }

    @Test
    fun `카드를 뽑았을때 가지고 있는 카드의 최소 합이 21보다 커지면 BURST 상태가 된다`() {
        val cards = Cards(
            listOf(
                Card(CardNumber.K, Shape.SPADE),
                Card(CardNumber.K, Shape.CLOVER)
            )
        )
        cards.draw(Card(CardNumber.FOUR, Shape.HEART))

        assertThat(cards.state).isEqualTo(CardsState.Burst)
    }
}
