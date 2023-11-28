package com.example.gogo.gogo2


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.example.gogo.databinding.FragmentAlarmBinding
import java.util.Calendar

class AlarmFragment : Fragment(){

    private lateinit var binding : FragmentAlarmBinding
    private lateinit var alarmFunctions: AlarmFunctions

//    , DatePicker.OnDateChangedListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAlarmBinding.inflate(inflater, container, false)
        alarmFunctions = AlarmFunctions(requireContext())

        return binding.root

        submitAlarm()
    }


    private fun setAlarm(alarmCode : Int, content : String, time : String){
        alarmFunctions.callAlarm(time, alarmCode, content)
    }

//    private fun initViews(position: Int){
//        val time = timeStringFormatter.parse(medicineViewModel.medicineItemList.value!!.get(position).time)
//        val calendar = Calendar.getInstance()
//        calendar.time = time
//        Log.d("datepicker : ", calendar.get(Calendar.YEAR).toString())
//
//        binding.setText(medicineViewModel.medicineItemList.value!!.get(position).name)
//        binding.addMedicineItemDescData.setText(medicineViewModel.medicineItemList.value!!.get(position).desc)
//        binding.addMedicineTimePicker.hour = calendar.get(Calendar.HOUR_OF_DAY)
//        binding.addMedicineTimePicker.minute = calendar.get(Calendar.MINUTE)
//        binding.addMedicineDatePicker.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH), null)
//
//    }

    private fun submitAlarm(){
        val hour = binding.addTimePicker.hour
        val min = binding.addTimePicker.minute

        val year = binding.addDatePicker.year
        val month = binding.addDatePicker.month +1
        val day = binding.addDatePicker.dayOfMonth

        val time = "$year-$month-$day $hour:$min:00" // 알람이 울리는 시간
        Log.d("set time : ", time)

//        val medicineName = binding.addMedicineItemTitleData.text.toString()
//        val medicineDesc = binding.addMedicineItemDescData.text.toString()
//
//        val newMedicineItem = MedicineListItem(time,medicineName,medicineDesc)

//        medicineViewModel.setMedicineItemList(newMedicineItem,medicineViewModel.clickedMedicineItem.value!!)

        val random = (1..100000) // 1~100000 범위에서 알람코드 랜덤으로 생성
        val alarmCode = random.random()
        val content = "얘는 AlarmFragment"


        setAlarm(alarmCode, content, time)



    }


}




