package com.eco_trak.balance.data.usb

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbManager
import android.util.Log
import com.eco_trak.balance.data.usb.utils.ReceiveCommand
import com.eco_trak.balance.repo.USBRepository
import com.hoho.android.usbserial.driver.UsbSerialPort
import com.hoho.android.usbserial.driver.UsbSerialProber
import com.hoho.android.usbserial.util.SerialInputOutputManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.io.IOException
import java.lang.Exception


/**
 * Data source for a [USBRepository][com.eco_trak.balance.data.repo.USBRepository].
 *
 * **You should ***NEVER*** use this directly.**
 *
 * Please use a [USBRepository][com.eco_trak.balance.data.repositories.USBRepository] instead.
 */
class USBSource /*: SerialInputOutputManager.Listener*/ {
    private lateinit var port: UsbSerialPort
    private val tag = "USBSource"
    private lateinit var usbIoManager : SerialInputOutputManager

    fun connectUSB(context: Context, baudRate: Int) {
        val manager: UsbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
        val availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager)
        if (availableDrivers.isEmpty()) {
            throw IOException("No drivers found")
        }

        // Open a connection to the first available driver.
        val driver = availableDrivers[0]
        while (!manager.hasPermission(driver.device)) {
            manager.requestPermission(
                driver.device,
                PendingIntent.getBroadcast(context, 0, Intent("com.android.example.USB_PERMISSION"), PendingIntent.FLAG_IMMUTABLE))
            Log.d(tag, "Waiting for permission")
            Thread.sleep(1000)
        }
        val connection = manager.openDevice(driver.device)

        port = driver.ports[0] // Most devices have just one port (port 0)
        port.open(connection)
        port.setParameters(baudRate, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE)
    }

//    fun startRead() {
//        usbIoManager = SerialInputOutputManager(port,this)
//        usbIoManager.start()
//    }

    fun startRead(listener: SerialInputOutputManager.Listener) {
        usbIoManager = SerialInputOutputManager(port,listener)
        usbIoManager.start()
    }
    fun write(message:ByteArray,timeout:Int) {
        port.write(message,timeout)
    }

//    override fun onNewData(data: ByteArray?) {
//        if (data == null) return
//        val dataNotNull : ByteArray = data
//        try {
//            USBRepository.processData(dataNotNull)
//        } catch (e:IOException) {
//            Log.e(tag, e.message ?: "Unknown Error")
//        }
//    }
//
//    override fun onRunError(e: java.lang.Exception?) {
//        if (e != null) {
//            Log.e(tag,"An error occurred which caused the thread to stop !")
//            e.printStackTrace()
//        }
//        else {
//            Log.e(tag,"An error occurred which caused the thread to stop : Unknown Error")
//        }
//        USBRepository.isConnected = false
//        //EventBus.getDefault().post(OnUSBConnectionFail(e))
//    }
}