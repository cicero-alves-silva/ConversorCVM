package com.example.conversorcvm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.conversorcvm.databinding.ActivityCalculoResultadoBinding

class CalculoResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCalculoResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCalculo.setOnClickListener { view ->
            binding.resultado.text = ""

            val valor = binding.tValor.text.toString().replace(",", ".").trim().toDoubleOrNull()

            var erro = false
            if (valor == null || valor <= 0) {
                binding.tValor.error = "Digite um valor válido."
                erro = true
            } else binding.tValor.error = null

            if (!erro) {
                val bundle = intent.extras

                if (bundle != null && valor != null) {
                    val categoria = bundle.getString("CATEGORIA")

                    binding.resultado.text = when (categoria) {
                        "Quilômetro" -> {
                            val resultadoFinal = valor * 1.60934
                            "%.2f".format(resultadoFinal)
                        }

                        "Milha" -> {
                            val resultadoFinal = valor / 1.60934
                            "%.2f".format(resultadoFinal)
                        }

                        else -> "Categoria inválida!"
                    }
                }
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