package blackjack.view

import blackjack.domain.player.Player

object InputView {

    private const val REQUEST_PLAYERS_NAME_MSG = "게임에 참여할 사람의 이름을 입력하세요."
    private const val TOKENIZER = ","
    private const val REQUEST_ADDITIONAL_CARDS_MSG = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val YES = "y"
    private const val NO = "n"
    private const val ADDITIONAL_DRAW_ERROR_MSG = "[ERROR] y또는 n 이외의 입력은 받지 않습니다."

    fun requestPlayersName(): List<String> {
        println(REQUEST_PLAYERS_NAME_MSG)

        return readln().split(TOKENIZER).map(String::trim)
    }

    fun requestAdditionalDraw(player: Player): Boolean {
        println(REQUEST_ADDITIONAL_CARDS_MSG.format(player.name.value))

        while (true) {
            readln().toBooleanOrNull()?.let { additionalDrawFlag -> return additionalDrawFlag }
            println(ADDITIONAL_DRAW_ERROR_MSG)
        }
    }

    private fun String.toBooleanOrNull(): Boolean? = if (this == YES) {
        true
    } else if (this == NO) {
        false
    } else {
        null
    }
}
