package com.avantari.dhyanagraphsdemo

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_blutooth.*

class BlutoothActivity : AppCompatActivity() {

    private val perms = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.CAMERA
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blutooth)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(perms, 11)
        } else {
            setStatus(isBluetoothOn())
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun setStatus(isOn: Boolean) {
        status_text.text = "Bluetooth Status : ${if (isOn) "ON" else "OFF"}"
    }

    fun isBluetoothOn(): Boolean {
        val manager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager?
        val adapter = manager?.adapter
        if (adapter?.isEnabled == false) {
            val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableIntent, 111)
        }
        return adapter?.isEnabled ?: false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.filter { it != PackageManager.PERMISSION_GRANTED }.isNotEmpty()) {
            Toast.makeText(this, "Need permissions", Toast.LENGTH_SHORT).show()
        } else {
            setStatus(isBluetoothOn())
        }

    }
}