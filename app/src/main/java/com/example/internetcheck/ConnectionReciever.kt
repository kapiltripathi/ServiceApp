package com.example.internetcheck
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

private const val TAG = "MyBroadcastReceiver"

class ConnectionReciever : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(intent.getAction())) {
            if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)
                == BluetoothAdapter.STATE_OFF
            )
                Toast.makeText(context, "Bluetooth is off", Toast.LENGTH_LONG).show()
            else if(intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)
                == BluetoothAdapter.STATE_ON
            )
                Toast.makeText(context, "Bluetooth is ON", Toast.LENGTH_LONG).show()

        }
    }
}