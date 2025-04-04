package com.example.ms4

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    class MainActivity : AppCompatActivity() {

        // Declare variables for meal planner
        private lateinit var timeInput: EditText
        private lateinit var submitButton: Button
        private lateinit var resetButton: Button
        private lateinit var suggestionOutput: TextView
        private lateinit var suggestionImage: ImageView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            // Initialize views
            timeInput = findViewById(R.id.timeInput)
            submitButton = findViewById(R.id.submitButton)
            resetButton = findViewById(R.id.resetButton)
            suggestionOutput = findViewById(R.id.suggestionOutput)
            suggestionImage = findViewById(R.id.suggestionImage)

            // Set OnClickListener for the Submit Button
            submitButton.setOnClickListener {
                generateRandomMeals()
            }

            // Set OnClickListener for the Reset Button
            resetButton.setOnClickListener {
                resetFields()
            }
        }

        // Method to generate random meals based on the time of day
        private fun generateRandomMeals() {
            // Get the time input from the EditText
            val timeOfDay = timeInput.text.toString().trim().toLowerCase()

            // Check if the input is valid
            if (timeOfDay.isEmpty()) {
                Toast.makeText(this, "Please select a time of day.", Toast.LENGTH_SHORT).show()
                return
            }

            // Meal options for each time of day
            val morningMeals =
                listOf("Pancakes with Syrup", "Oatmeal with Fruits", "Scrambled Eggs with Toast")
            val midMorningMeals = listOf("Yogurt with Granola", "Smoothie", "Fruit Salad")
            val afternoonMeals =
                listOf("Chicken Salad", "Grilled Cheese Sandwich", "Wrap with Veggies")
            val afternoonSnacks = listOf("Trail Mix", "Granola Bar", "Apple with Peanut Butter")
            val dinnerMeals =
                listOf("Spaghetti Bolognese", "Grilled Salmon with Veggies", "Chicken Stir Fry")

            // Image resources for each meal
            val mealImages = mapOf(
                "Pancakes with Syrup" to R.drawable.pancakes,
                "Oatmeal with Fruits" to R.drawable.oatmeal,
                "Scrambled Eggs with Toast" to R.drawable.scrambled_eggs,
                "Yogurt with Granola" to R.drawable.yogurt,
                "Smoothie" to R.drawable.smoothie,
                "Fruit Salad" to R.drawable.fruit_salad,
                "Chicken Salad" to R.drawable.chicken_salad,
                "Grilled Cheese Sandwich" to R.drawable.grilled_cheese,
                "Wrap with Veggies" to R.drawable.wrap,
                "Trail Mix" to R.drawable.trail_mix,
                "Granola Bar" to R.drawable.granola_bar,
                "Apple with Peanut Butter" to R.drawable.apple,
                "Spaghetti Bolognese" to R.drawable.spaghetti,
                "Grilled Salmon with Veggies" to R.drawable.grilled_salmon,
                "Chicken Stir Fry" to R.drawable.chicken_stir_fry
            )

            // Randomly select meals based on the time of day
            val random = Random
            var suggestedMeals = ""

            when {
                timeOfDay.contains("morning") -> {
                    val meal = morningMeals[random.nextInt(morningMeals.size)]
                    suggestedMeals += "Morning Meal: $meal\n"
                    suggestionImage.setImageResource(mealImages[meal] ?: R.drawable.default_image)
                }

                timeOfDay.contains("mid") || timeOfDay.contains("snack") -> {
                    val meal = midMorningMeals[random.nextInt(midMorningMeals.size)]
                    suggestedMeals += "Mid-Morning Snack: $meal\n"
                    suggestionImage.setImageResource(mealImages[meal] ?: R.drawable.default_image)
                }

                timeOfDay.contains("afternoon") -> {
                    val meal = afternoonMeals[random.nextInt(afternoonMeals.size)]
                    suggestedMeals += "Afternoon Meal: $meal\n"
                    suggestionImage.setImageResource(mealImages[meal] ?: R.drawable.default_image)

                    val snack = afternoonSnacks[random.nextInt(afternoonSnacks.size)]
                    suggestedMeals += "Afternoon Snack: $snack\n"
                    suggestionImage.setImageResource(mealImages[snack] ?: R.drawable.default_image)
                }

                timeOfDay.contains("dinner") -> {
                    val meal = dinnerMeals[random.nextInt(dinnerMeals.size)]
                    suggestedMeals += "Dinner Meal: $meal\n"
                    suggestionImage.setImageResource(mealImages[meal] ?: R.drawable.default_image)
                }

                else -> {
                    Toast.makeText(
                        this,
                        "Invalid time. Please enter a valid time (morning, mid morning, afternoon, or dinner).",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
            }

            // Display the selected meals
            suggestionOutput.text = suggestedMeals
        }

        // Method to reset fields
        private fun resetFields() {
            timeInput.text.clear()
            suggestionOutput.text = ""
            suggestionImage.setImageResource(R.drawable.default_image)  // Reset image to default
        }
    }
}