package com.example.gogo.habit2.detail

import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gogo.R


data class RectangleState(var isDone: Boolean, var handler: Handler? = null)

class RectangleAdapter(private val progressBarUtil: ProgressBarUtil,private val habitProgressManager: HabitProgressManager) : RecyclerView.Adapter<RectangleAdapter.RectangleViewHolder>() {
    private val rectangleStates = ArrayList<RectangleState>()
    private var lastClickedPosition: Int = -1


    init {
        for (i in 1..66) {
            rectangleStates.add(RectangleState(isDone = false))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RectangleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rectangle, parent, false)
        return RectangleViewHolder(view)
    }

    override fun onBindViewHolder(holder: RectangleViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return rectangleStates.size
    }

    inner class RectangleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewNone: ImageView = itemView.findViewById(R.id.noneImageView)
        private val imageViewDone: ImageView = itemView.findViewById(R.id.doneImageView)


        fun bind(position: Int) {
            val isDoneState = rectangleStates[position].isDone

            if (isDoneState) {
                imageViewNone.visibility = View.GONE
                imageViewDone.visibility = View.VISIBLE
                startResetHandler(position)
            } else {
                imageViewNone.visibility = View.VISIBLE
                imageViewDone.visibility = View.GONE
                stopResetHandler(position)
            }

            itemView.setOnClickListener {
                if (lastClickedPosition == position) {
                    // 클릭한 항목이 이미 최근에 클릭한 항목인 경우, 상태를 반전시킴
                    val currentState = rectangleStates[position]
                    val newState = RectangleState(isDone = !currentState.isDone, handler = null)
                    rectangleStates[position] = newState
                    notifyDataSetChanged()

                    if (newState.isDone) {
                        startResetHandler(position)
                        progressBarUtil.incrementProgress()
                        habitProgressManager.updateStatusNum()
                    } else {
                        stopResetHandler(position)
                        progressBarUtil.resetProgress()
                        habitProgressManager.resetStatusNum()
                    }
                    lastClickedPosition -=1
                } else if (lastClickedPosition == -1 || lastClickedPosition + 1 == position) {
                    // 새로운 항목을 클릭한 경우
                    val currentState = rectangleStates[position]
                    val newState = RectangleState(isDone = !currentState.isDone, handler = null)
                    rectangleStates[position] = newState
                    notifyDataSetChanged()

                    if (newState.isDone) {
                        startResetHandler(position)
                        progressBarUtil.incrementProgress()
                        habitProgressManager.updateStatusNum()
                    } else {
                        stopResetHandler(position)
                        progressBarUtil.resetProgress()
                        habitProgressManager.resetStatusNum()
                    }

                    lastClickedPosition = position

                } else {
                    // 순서가 잘못된 경우에 대한 처리
                    val toast = Toast.makeText(itemView.context, "바로 옆의 BOX만 선택할 수 있습니다", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                }
                updateCheckboxState()
            }
        }

        private fun startResetHandler(position: Int) {
            stopAllResetHandlers()
            val handler = Handler()
            val resetDelay = 60 * 1000L // 1분(밀리초)

            val resetTask = Runnable {
                for (i in 0 until rectangleStates.size) {
                    val currentState = rectangleStates[i]
                    if (currentState.isDone) {
                        val newState = RectangleState(isDone = false, handler = null)
                        rectangleStates[i] = newState
                    }
                }
                notifyDataSetChanged()
                lastClickedPosition = -1 //  초기화
                progressBarUtil.allresetProgress()
                habitProgressManager.allreset()
                updateCheckboxState()
            }

            rectangleStates[position].handler = handler
            handler.postDelayed(resetTask, resetDelay)
        }

        private fun stopResetHandler(position: Int) {
            val currentState = rectangleStates[position]
            currentState.handler?.removeCallbacksAndMessages(null)
            currentState.handler = null
        }

        private fun stopAllResetHandlers() {
            for (i in 0 until rectangleStates.size) {
                val currentState = rectangleStates[i]
                currentState.handler?.removeCallbacksAndMessages(null)
                currentState.handler = null
            }
        }



        // Check if all rectangles are done
        fun areAllRectanglesDone(): Boolean {
            for (state in rectangleStates) {
                if (!state.isDone) {
                    return false // If any rectangle is not done, return false
                }
            }
            return true // All rectangles are done
        }

        // Update the checkbox state
        fun updateCheckboxState() {
            val view2 = LayoutInflater.from(itemView.context).inflate(R.layout.list_item, null, false)
            val checkBox = view2.findViewById<CheckBox>(R.id.checkBox)
            checkBox.isChecked = areAllRectanglesDone()
        }
    }


}