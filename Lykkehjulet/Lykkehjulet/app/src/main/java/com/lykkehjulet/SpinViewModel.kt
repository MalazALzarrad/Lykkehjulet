package com.lykkehjulet

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lykkehjulet.data.Puzzle
import com.lykkehjulet.data.PuzzleRepository
import com.lykkehjulet.screen.alert.score.ActionState
import com.lykkehjulet.screen.play.CharBoard
import com.lykkehjulet.screen.play.GameState
import com.lykkehjulet.screen.play.data.SelectedChar
import com.lykkehjulet.utils.CategoryUtils
import com.lykkehjulet.utils.GeneratePuzzle
import com.lykkehjulet.utils.SpinScore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SpinViewModel @Inject constructor(
    val puzzleRepository: PuzzleRepository
) : ViewModel() {

    companion object {
        private const val TAG = "SpinViewModel"
    }



    // Game State
    private  var   _GameState = MutableStateFlow<GameState?>(null)
    val gameState = _GameState.asStateFlow()

    // temp Sore State
     var tempScore : SpinScore? = null

    var CharBoardNewChangesIndex : List<Int>? = null


    private val _CharBoardState = MutableStateFlow<List<CharBoard>>(emptyList())
    val charBoardState = _CharBoardState.asStateFlow()

    private val _UserSelectionState = MutableStateFlow<Boolean>(false)
    val userSelectionState = _UserSelectionState.asStateFlow()


    private val _SelecedtedChar = MutableStateFlow<MutableList<Pair<Char,Int>>>(mutableListOf())
    val selecedtedChar = _SelecedtedChar.asStateFlow()

    init {
        // generate

        startGame()
    }


    fun startGame() {
        viewModelScope.launch(Dispatchers.IO) {

         val new = withContext(Dispatchers.Default) { GeneratePuzzle().create(puzzleRepository) }
            _GameState.value = new
            // step 2
            val list = CategoryUtils().createCategoryBoard( _GameState.value!!.category)
            _CharBoardState.value = list

            _SelecedtedChar.value = mutableListOf()

        }

    }


    fun insert(puzzle: Puzzle) {
        viewModelScope.launch(Dispatchers.IO) {
            puzzleRepository.insertPuzzle(puzzle)
        }

    }

    fun delete(puzzle: Puzzle) {
        viewModelScope.launch(Dispatchers.IO) {
            puzzleRepository.deletePuzzle(puzzle)
        }

    }
    fun onCharClick(char: Char) {

        // finding match

       if (char in _SelecedtedChar.value.map {
            it.first
        })
       {
           Log.i(TAG, "onCharClick: Already Char  $char ")
           return
       }


        Log.i(TAG, "onCharClick: totalSize ${_CharBoardState.value.size}")

      val result: SelectedChar =  CategoryUtils().checkIsSelected(_CharBoardState.value,char)

        when (result) {
            is SelectedChar.NewUpdate -> {
                _CharBoardState.value = result.updatedList
                CharBoardNewChangesIndex = result.indexPosition

                Log.i(TAG, "onCharClick: char $char  ${result.indexPosition}")

                _SelecedtedChar.value.add( char to result.indexPosition.size)

            }
            is SelectedChar.NoChanges -> {
                CharBoardNewChangesIndex = null
                Log.i(TAG, "onCharClick: char $char No Changes")
            }
             else -> {

             }
        }

        _UserSelectionState.value = true

    }


    fun isWon(): Boolean {

      var total: Int =   _CharBoardState.value.count {
            it.isChar
        }

      var found=   _SelecedtedChar.value.sumOf {
            it.second
        }


       return total == found

    }


    fun doAction(): ActionState {

        _UserSelectionState.value = false

        if (_GameState.value == null) {
         //   throw Exception ("Set Game State")
            return ActionState.OnLoading
        }

       return if (tempScore != null) {

            val currentAmount = _GameState.value!!.userScore
            if (tempScore is SpinScore.Score) {

                if (CharBoardNewChangesIndex == null) {
                    ActionState.OnLooseLife
                } else {
                    val count = CharBoardNewChangesIndex!!.size
                    val amount = (tempScore as SpinScore.Score).value

                    val newChanges = count*amount
                    val newScore = currentAmount.plus(newChanges)

                    ActionState.ScoreChanges(from = currentAmount , to = newScore , count,amount )
                }


            } else {

                ActionState.OnBankrupt(from = currentAmount,to = 0)
            }


        } else {
           return ActionState.OnLoading
       }

    }

    fun resetGame() {
        CharBoardNewChangesIndex = null
        tempScore = null
        startGame()
    }

    fun applyChanges(actionState: ActionState) : Int {

        if (_GameState.value == null) {
             throw Exception ("Set Game State")
        }


        when (actionState) {
            is ActionState.OnBankrupt -> {
                newScore(0)
                tempScore = null
            }
            ActionState.OnLoading -> {

            }
            ActionState.OnLooseLife -> {
                loseLife()
            }
            is ActionState.ScoreChanges -> {
                newScore(actionState.to)
                CharBoardNewChangesIndex = null
                tempScore = null
            }
        }

         return  _GameState.value!!.userLife

    }







    fun loseLife() {

        if (_GameState.value == null) {
            throw Exception ("Set Game State")
        }

        val life = _GameState.value!!.userLife

        if (life > 0) {
            _GameState.value = _GameState.value!!.copy(userLife = life.minus(1))
        }

    }

    fun newScore(newScore: Int) {

        if (_GameState.value == null) {
            throw Exception ("Set Game State")
        }

        _GameState.value = _GameState.value!!.copy(userScore = newScore)


    }







}