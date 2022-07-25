package my.edu.tarc.insurance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.edu.tarc.insurance.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    //TODO:2 Create an instance of View Binding
    //lateint = late initialize
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO 3: Initialize binding object
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.calculate.setOnClickListener {
            val age = binding.spinnerAge.selectedItemPosition
            val gender = binding.radioGroupGender.checkedRadioButtonId
            val smoker = binding.checkBoxSmoker.isChecked

            var premium = when(age){
                0 -> 60
                1 -> 70
                2 -> 90
                3 -> 120
                4,5 -> 150
                else -> 0
            }

            if(gender == binding.radioButtonMale.id){
                when(age){
                    1 -> {premium  += 50}
                    2 -> {premium  += 100}
                    3 -> {premium  += 150}
                    4,5 -> {premium  += 200}
                    else -> 0
                }
            }

            if(smoker){
                when(age){
                    1 -> {premium  += 100}
                    2 -> {premium  += 150}
                    3 -> {premium  += 200}
                    4 -> {premium  += 250}
                    5 -> {premium  += 300}
                    else -> 0
                }
            }

            val premium_output = NumberFormat.getCurrencyInstance().format(premium)
            binding.textViewPremium.text = premium_output
        }

        binding.reset.setOnClickListener {
            binding.textViewPremium.setText(null)
            binding.checkBoxSmoker.setChecked(false)
            binding.radioGroupGender.clearCheck()
            binding.spinnerAge.setSelection(0)
        }
    }
}