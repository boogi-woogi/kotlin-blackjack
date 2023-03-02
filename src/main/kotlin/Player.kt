class Player(
    val name: String,
    val cards: Cards = Cards()
) {
    // TODO: 로직 개선

    fun isPossibleToDraw(): Boolean {
        if (cards.cards.sumOf { card -> card.number.value } > 21)
            return false

        return true
    }

    fun drawCard() {
        cards.draw()
    }
}
