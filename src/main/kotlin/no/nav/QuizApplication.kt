package no.nav

import no.nav.db.Database
import no.nav.quizrapid.*
import no.nav.rapid.Assessment
import no.nav.rapid.Question


/**
 * QuizApplication
 *
 * Her skal teamet bygge ut funksjonalitet for å løse oppgavene i leesah-game.
 */
class QuizApplication(private val teamName: String, database: Database? = null): QuizParticipant(teamName) {

    override fun handle(question: Question) {
        logger.log(question)
        if (question.category == "team-registration") handleRegisterTeam(question)
        if (question.category == "arithmetic") solveArithmetic(question)
        if (question.category == "make-ingress") solveIngress(question)
    }


    override fun handle(assessment: Assessment) {
        logger.log(assessment)
    }

    /**
     * Spørsmål handlers
     */

    private fun handleRegisterTeam(question: Question) {
        answer(question.category, question.messageId, "fraatilaa")
    }

    private fun solveArithmetic(question: Question) {
        val questionString = question.question
        val list = questionString.split("\\s".toRegex()).toTypedArray()
        val firstNumber = list[0].toInt()
        val lastNumber = list[2].toInt()
        val operator = list[1]

        var answer = 0

        if(operator == "+") {
            answer = firstNumber + lastNumber
        } else if (operator == "-"){
            answer = firstNumber - lastNumber
        } else if (operator == "/"){
            answer = (firstNumber / lastNumber).toInt()
        } else if (operator == "*"){
            answer = firstNumber * lastNumber
        }

        answer(question.category, question.messageId, answer.toString())
    }

    private fun solveIngress(question: Question){
        answer(question.category, question.messageId, "https://fraatilaa.dev.intern.nav.no")
    }

}