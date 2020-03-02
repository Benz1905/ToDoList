package th.ac.su.ict.chutikarn.todolist

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var itemList:ArrayList<String> = ArrayList<String>() // <...>กำหนดชนิดตัวแปร
     //ตัวเชื่อม ArrayAdapter<....>ขั้นอยู่ว่าของในอาร์เรย์เป็นชนัิดใด
    lateinit var arrayAdapter:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemList.add("Watch TV")
        itemList.add("Play ROV")
        itemList.add("Netflix")

        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,itemList)

        //var listview1 = findViewById<ListView>(R.id.listView)

        listView.adapter = arrayAdapter

        registerForContextMenu(listView)

        btnAdd.setOnClickListener {



            itemList.add(edtInput.text.toString())
            arrayAdapter.notifyDataSetChanged() //สั่งให้ adapter มีการเปลี่ยนแปลงเพื่อแสดงผล เปลี่ยนแปลงฝจากการรับค่าของ edt

            hideKeyboard()
        }
        listView.setOnItemClickListener { parent, view, position, id ->
          Toast.makeText(this,itemList[position],Toast.LENGTH_SHORT).show()
        }






    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main_context,menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position



        when(item.itemId){
            R.id.action_remove ->{
                itemList.removeAt(position)
             arrayAdapter.notifyDataSetChanged()

            }
            R.id.action_edit ->{
            Toast.makeText(this,"Select",Toast.LENGTH_SHORT).show()
            //todo

        }
        }

        return super.onContextItemSelected(item)
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
