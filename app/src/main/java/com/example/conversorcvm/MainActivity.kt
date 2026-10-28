package com.example.conversorcvm

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.conversorcvm.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btClicar.setOnClickListener { view ->
            var categorias = ""

            when (binding.categorias.checkedRadioButtonId) {
                binding.mpk.id -> categorias = "Quilômetro"
                binding.kpm.id -> categorias = "Milha"

                else ->
                    Snackbar.make(
                        view,
                        "Selecione uma das opções antes de clicar no botão.",
                        Snackbar.LENGTH_LONG
                    )
                        .setBackgroundTint(Color.RED)
                        .setTextColor(Color.WHITE)
                        .show()
            }

            if (categorias.isNotEmpty()) {
                val intent = Intent(
                    this,
                    CalculoResultadoActivity::class.java
                )

                intent.putExtra("CATEGORIA", categorias)
                startActivity(intent)
            }
        }

        binding.includeBarraApp.materialToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.direitosAutorais -> {
                    val intent = Intent(this, DireitosAutoraisActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }
    }
}