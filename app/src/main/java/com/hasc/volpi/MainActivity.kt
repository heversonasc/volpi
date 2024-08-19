package com.hasc.volpi

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hasc.volpi.adapter.CompanyAdapter
import com.hasc.volpi.auth.loginFragment
import com.hasc.volpi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var dataList: ArrayList<Items>
    private lateinit var recyclerView: RecyclerView
    private lateinit var companyAdapter: CompanyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding?.root)


        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.parseColor("#FFE500")

        binding?.imgLogout?.setOnClickListener {

            val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()

            // Redirecionar para a tela de login e limpar a pilha de atividades
            val intent = Intent(this, loginFragment::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        dataList = ArrayList()

        dataList.add(Items(R.drawable.petzlogo, "Petz" , "A maior rede de pet shops do Brasil.", "https://petz.custhelp.com/app/chat/chat_landing"))
        dataList.add(Items(R.drawable.olxlogo, "Olx" , "Líder no mercado de compra e venda online no país.", "https://ajuda.olx.com.br/"))
        dataList.add(Items(R.drawable.corsairlogo, "Corsair" , "Desenvolvedora e fabricante líder de equipamentos para gamers.", "https://help.corsair.com/hc/pt-br/p/Chat-With-Us"))
        dataList.add(Items(R.drawable.centaurologo, "Centauro" , "Uma das maiores redes de produtos esportivos da América Latina.", "https://www.mercadolivre.com.br/ajuda/chat/v2?hasCreditRestriction=false"))
        dataList.add(Items(R.drawable.aliexpresslogo, "AliExpress" , "Conectando compradores de todo o mundo aos fabricantes da China.","https://helpcenter.aliexpress.com/s/BuyerHelp?language=pt_BR&tsuCode=ByrCmn&region=BR&suid=1030142408130337062151E9761687"))
        dataList.add(Items(R.drawable.zattini, "Zattini" , "Uma das principais marcas de moda no e-commerce brasileiro.", "https://atendimento.zattini.com.br/hc/pt-br"))
        dataList.add(Items(R.drawable.netshoeslogo, "Netshoes" , "Uma das maiores redes de varejo online ligado á artigos esportivos.", "https://atendimento.netshoes.com.br/hc/pt-br"))
        dataList.add(Items(R.drawable.nikelogo, "Nike" , "inspiração e inovação para todos os atletas do mundo.", "https://www.nike.com.br/"))
        dataList.add(Items(R.drawable.corsairlogo, "Corsair" , "Desenvolvedora e fabricante líder de equipamentos para gamers.", "https://help.corsair.com/hc/pt-br/p/Chat-With-Us"))
        dataList.add(Items(R.drawable.centaurologo, "Centauro" , "Uma das maiores redes de produtos esportivos da América Latina.", "https://www.mercadolivre.com.br/ajuda/chat/v2?hasCreditRestriction=false"))



        companyAdapter = CompanyAdapter(dataList)
        recyclerView.adapter = companyAdapter

        val searchView = findViewById<SearchView>(R.id.search)
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                companyAdapter.filter.filter(
                    newText
                )
                return false
            }
        })

    }




}