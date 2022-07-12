package com.eco_trak.balance

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * NOTE: You must tell Android to use this class as an application
 *       by adding an android:name attribute to the application element
 *       within the AndroidManifest.xml file
 *
 *       <application
 *           android:name=".BalanceApp"
 *           ...
 *        </application>
 */
@HiltAndroidApp
class BalanceApp: Application()