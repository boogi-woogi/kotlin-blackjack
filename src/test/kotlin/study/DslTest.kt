package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {

    @Test
    fun company() {
        val person = introduce {
            name("링링")
            company("우아한테크코스")
            skills {
                soft("인사")
                hard("코틀린")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.name).isEqualTo("링링")
        assertThat(person.company).isEqualTo("우아한테크코스")
        assertThat(person.skill).isEqualTo(
            Skill().apply {
                soft("인사")
                hard("코틀린")
            }
        )
        assertThat(person.language).isEqualTo(
            Language().apply {
                "Korean" level 5
                "English" level 3
            }
        )
    }

    data class Language(
        var languages: MutableList<Pair<String, Int>> = mutableListOf()
    ) {
        infix fun String.level(that: Int) {
            languages.add(Pair(this, that))
        }
    }

    data class Skill(
        var hardSkills: MutableList<String> = mutableListOf<String>(),
        var softSkills: MutableList<String> = mutableListOf<String>()
    ) {

        fun soft(name: String) {
            softSkills.add(name)
        }

        fun hard(name: String) {
            hardSkills.add(name)
        }
    }

    fun introduce(block: PersonBuilder.() -> Unit): Person {
        return PersonBuilder().apply(block).build()
    }

    class PersonBuilder {
        private lateinit var name: String
        private var company: String? = null
        private lateinit var skill: Skill
        private lateinit var language: Language

        fun name(value: String) {
            name = value
        }

        fun company(value: String) {
            company = value
        }

        fun skills(block: Skill.() -> Unit) {
            skill = Skill().apply(block)
        }

        fun languages(block: Language.() -> Unit) {
            language = Language().apply(block)
        }

        fun build(): Person {
            return Person(
                name,
                company,
                skill,
                language
            )
        }
    }

    data class Person(
        val name: String,
        val company: String?,
        val skill: Skill,
        val language: Language
    )
}