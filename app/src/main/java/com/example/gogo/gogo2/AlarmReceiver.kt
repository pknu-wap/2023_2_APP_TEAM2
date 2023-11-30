package com.example.gogo.gogo2


import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import com.example.gogo.R

class AlarmReceiver() : BroadcastReceiver() {

    private lateinit var manager: NotificationManager
    private lateinit var builder: NotificationCompat.Builder

    //오레오 이상은 반드시 채널을 설정해줘야 Notification 작동함
    companion object{
        const val CHANNEL_ID = "channel"
        const val CHANNEL_NAME = "channel1"
        const val ALARM_INTERVAL = 24 * 60 * 60 * 1000 //하루가 지나면 반복 알람
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onReceive(context: Context?, intent: Intent?) {
        manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //NotificationChannel 인스턴스를 createNotificationChannel()에 전달하여 앱 알림 채널을 시스템에 등록
        //createNotificationChannel()
        manager.createNotificationChannel(
            NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
        )

        builder = NotificationCompat.Builder(context, CHANNEL_ID)

        val intent2 = Intent(context, AlarmService::class.java)
        val requestCode = intent?.extras!!.getInt("alarm_rqCode")
        val title = intent.extras!!.getString("content")

        val pendingIntent = if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.S){
            PendingIntent.getActivity(context,requestCode,intent2,PendingIntent.FLAG_IMMUTABLE); //Activity를 시작하는 인텐트 생성
        }else {
            PendingIntent.getActivity(context,requestCode,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
        }

        val notification = builder.setContentTitle(title)
            .setContentTitle("오늘 습관 지키기 잊지 않으셨죠?\uD83D\uDE09\n")
            .setContentText("오늘 하루도 습관 잘 지켜서 발전하는 어른되기💪")
            .setStyle(NotificationCompat.BigTextStyle().bigText("오늘 하루도 습관 잘 지켜서 발전하는 어른되기\uD83D\uDCAA"))
            .setSmallIcon(androidx.core.R.drawable.notify_panel_notification_icon_bg)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        manager.notify(1, notification)

        // AlarmManager를 사용하여 하루 간격으로 알람 재등록
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(context, AlarmReceiver::class.java)
        val pendingIntentAlarm = PendingIntent.getBroadcast(
            context,
            requestCode,
            alarmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val firstMillis = SystemClock.elapsedRealtime()
        val intervalMillis = ALARM_INTERVAL.toLong()
        alarmManager.setRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            firstMillis + intervalMillis,
            intervalMillis,
            pendingIntentAlarm
        )
    }
}