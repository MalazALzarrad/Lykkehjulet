package com.lykkehjulet.utils

import com.lykkehjulet.screen.play.CharBoard
import com.lykkehjulet.screen.play.data.SelectedChar

class CategoryUtils  {

    fun createCategoryBoard(category : String): List<CharBoard> {

        val limit = 10

        val words = category.uppercase().split(" ")

        var tempindex = 0

        val totalCharBoard = mutableListOf<CharBoard>()



        words.forEach { word ->

            val rowCharBoard = mutableListOf<CharBoard>()


            val remaingvalue =    limit - word.length

            // pre Added Value
            val startingValue =  remaingvalue/2

            for (n in 1..startingValue) {
                tempindex += 1
                rowCharBoard.add(CharBoard(index= tempindex , char = '_', isVisible = false , isChar = false))

            }

            // Add the word

            word.forEach {
                tempindex += 1

                rowCharBoard.add(CharBoard(index= tempindex , char = it, isVisible = false , isChar = true))

            }


            // Add suffix word
            if (rowCharBoard.size < limit.minus(1)) {

                for (n in rowCharBoard.size..limit.minus(1)) {
                    tempindex += 1
                    rowCharBoard.add(CharBoard(index= tempindex , char = '_', isVisible = false , isChar = false))

                }

            }

            // Add to final

            totalCharBoard.addAll(rowCharBoard)

        }


        return totalCharBoard.toList()
    }


    fun checkIsSelected(existingList : List<CharBoard> , selected : Char)  : SelectedChar {

        val found = mutableListOf<Int>()


        existingList.forEachIndexed { index, charBoard ->

            if (charBoard.isChar) {
                if (charBoard.char == selected) {
                    found.add(index)
                }

            }

        }

       return if (found.size > 0) {

            val newList = mutableListOf<CharBoard>()
            newList.addAll(existingList)
            found.forEach {
              val oldValue: CharBoard = existingList[it]
                newList.removeAt(it)
                newList.add(it,oldValue.copy(isVisible = true))
            }

           newList.sortBy { it.index }
            SelectedChar.NewUpdate(found,newList)
        } else {
            SelectedChar.NoChanges
        }

    }





}