package blackjack.domain.card

class Cards(
    cards: List<Card> = listOf(Card.draw(), Card.draw()),
) {

    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    var state: CardsState = CardsState.Running(getTotalCardsScore())
        private set

    init {
        require(cards.size == INITIAL_CARDS_SIZE)

        if (getTotalCardsScore() == BLACKJACK_SCORE) {
            state = CardsState.BlackJack
        }
    }

    fun draw(card: Card = Card.draw()) {
        _cards.add(card)

        updateCardsState()
    }

    private fun updateCardsState() {
        if (getMinimumCardsScore() > BLACKJACK_SCORE) {
            state = CardsState.Bust
            return
        }
        state = CardsState.Running(getTotalCardsScore())
    }

    fun getMinimumCardsScore(): Int = cards.sumOf { card -> card.number.value }

    fun getTotalCardsScore(): Int {
        val minimumScore = cards
            .sumOf { card -> card.number.value }

        if (cards.any { card -> card.number == CardNumber.A }) {
            return CardNumber.decideAceValue(minimumScore)
        }

        return minimumScore
    }

    fun checkCardsState(cardsState: CardsState): Boolean = state == cardsState

    fun isDrawnNothing() = cards.size == 2

    companion object {

        const val INITIAL_CARDS_SIZE = 2
        private const val BLACKJACK_SCORE = 21
    }
}
