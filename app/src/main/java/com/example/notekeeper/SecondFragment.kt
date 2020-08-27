package com.example.notekeeper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

//        val onClickListener = View.OnClickListener {
//            val diceRoll = rollDice()
//            Toast.makeText(context, "Dice rolled to $diceRoll!", Toast.LENGTH_SHORT).show()
//        }

        val rollButton: Button = view.findViewById(R.id.rollDiceButton)
        rollButton.setOnClickListener {
            val diceRoll = rollDice()
            Toast.makeText(context, "Dice rolled to $diceRoll!", Toast.LENGTH_SHORT).show()
        }

        val imageViewDice: ImageView = view.findViewById(R.id.imageViewDice)
        imageViewDice.setOnClickListener(View.OnClickListener {
            val diceRoll = rollDice()
            Toast.makeText(context, "Dice rolled to $diceRoll!", Toast.LENGTH_SHORT).show()
        })

        // Do a dice roll when the app starts
        rollDice()
    }

    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice(): Int {
        // Create new Dice object with 6 sides and roll the dice
        val dice = Dice(6)
        val diceRoll = dice.roll()

        // Find the ImageView in the layout
        val diceImage: ImageView = view?.findViewById(R.id.imageViewDice)!!

        // Determine which drawable resource ID to use based on the dice roll
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // Update the ImageView with the correct drawable resource ID
        diceImage.setImageResource(drawableResource)

        // Update the content description
        diceImage.contentDescription = diceRoll.toString()

        return diceRoll
    }
}