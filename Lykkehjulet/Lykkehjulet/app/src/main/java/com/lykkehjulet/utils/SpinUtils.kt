package com.lykkehjulet.utils



class Score {

    fun generateScore(): List<SpinScore> {

        val score = mutableListOf<SpinScore>()

        // Money
        score.add(SpinScore.Score(1000))
        score.add(SpinScore.Score(800))
        score.add(SpinScore.Score(600))
        score.add(SpinScore.Score(400))
        score.add(SpinScore.Score(200))

        // Bankrupt
        score.add(SpinScore.Bankrupt)
        score.add(SpinScore.Bankrupt)
        score.add(SpinScore.Bankrupt)


        return score.toList()
    }

}