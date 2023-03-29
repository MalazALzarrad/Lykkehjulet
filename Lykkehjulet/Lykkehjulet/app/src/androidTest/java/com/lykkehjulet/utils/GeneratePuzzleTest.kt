package com.lykkehjulet.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lykkehjulet.screen.play.CharBoard
import com.lykkehjulet.screen.play.data.SelectedChar
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GeneratePuzzleTest {


    @Test
    fun testCondition() {

        var category = "Super Mario"

        val list: List<CharBoard> = CategoryUtils().createCategoryBoard(category)

        printList(list , "Before")


        val char: Char = 'R'

        val result: SelectedChar =  CategoryUtils().checkIsSelected(list,char)

        when (result) {
            is SelectedChar.NewUpdate -> {
                printList(result.updatedList , "After")
            }
            SelectedChar.NoChanges -> {
                System.out.println(" Converted NoChanges ")
            }
        }



    }

    fun printList(list: List<CharBoard> , label : String) {
        var map = list.map {

            var visible = if (it.isVisible) "1" else "0"

            it.char to visible }
        System.out.println(" Converted $label - $map ")
    }




}